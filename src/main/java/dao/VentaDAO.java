/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import modelo.DetalleVenta;

/**
 *
 * @author admin
 */
public class VentaDAO {

    public boolean guardarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        Connection con = null;
        boolean exito = false;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            String sqlCabecera = "INSERT INTO cabeceraventa (idCliente, idEmpleado, total, fechaVenta, estado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstCabecera = con.prepareStatement(sqlCabecera, PreparedStatement.RETURN_GENERATED_KEYS);
            pstCabecera.setInt(1, venta.getIdCliente());
            pstCabecera.setInt(2, venta.getIdEmpleado());
            pstCabecera.setDouble(3, venta.getTotal());
            LocalDate fechaActual = LocalDate.now();
            pstCabecera.setDate(4, Date.valueOf(fechaActual));
            pstCabecera.setInt(5, venta.getEstado());
            pstCabecera.executeUpdate();

            ResultSet rs = pstCabecera.getGeneratedKeys();
            int idCabeceraVenta = -1;
            if (rs.next()) {
                idCabeceraVenta = rs.getInt(1);
            }

            String sqlDetalle = "INSERT INTO detalleventa (idCabeceraVenta, idProducto, cantidad, precioUnitario, subTotal, iva, descuento, totalPagar, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstDetalle = con.prepareStatement(sqlDetalle);

            ProductoDAO productoDAO = new ProductoDAO();

            for (DetalleVenta det : detalles) {
                pstDetalle.setInt(1, idCabeceraVenta);
                pstDetalle.setInt(2, det.getIdProducto());
                pstDetalle.setInt(3, det.getCantidad());
                pstDetalle.setDouble(4, det.getPrecioUnitario());
                pstDetalle.setDouble(5, det.getSubTotal());
                pstDetalle.setDouble(6, det.getIva());
                pstDetalle.setDouble(7, det.getDescuento());
                pstDetalle.setDouble(8, det.getTotalPagar());
                pstDetalle.setInt(9, det.getEstado());
                pstDetalle.addBatch();

                boolean stockActualizado = productoDAO.disminuirStock(det.getIdProducto(), det.getCantidad(), con);
                if (!stockActualizado) {
                    throw new SQLException("Stock insuficiente para el producto con ID: " + det.getIdProducto());
                }
            }

            pstDetalle.executeBatch();

            con.commit();
            exito = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exito;
    }

    public boolean cambiarEstado(int idVenta, int estado) {
        String sql = "UPDATE cabeceraventa SET estado = ? WHERE idCabeceraVenta = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idVenta);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean cambiarEstadoDetallesVenta(int idVenta, int nuevoEstado) {
        String sqlActualizarEstado = "UPDATE detalleventa SET estado = ? WHERE idDetalleVenta = ?";
        String sqlObtenerDetalles = "SELECT idProducto, cantidad FROM detalleventa WHERE idCabeceraVenta = ?";

        try (Connection conn = Conexion.conectar()) {
            conn.setAutoCommit(false);
            try (
                    PreparedStatement psDetalles = conn.prepareStatement(sqlObtenerDetalles); PreparedStatement psActualizarEstado = conn.prepareStatement(sqlActualizarEstado)) {
                psDetalles.setInt(1, idVenta);
                ResultSet rs = psDetalles.executeQuery();

                ProductoDAO productoDAO = new ProductoDAO();
                boolean errorStock = false;

                while (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    int cantidad = rs.getInt("cantidad");

                    boolean ok;
                    if (nuevoEstado == 0) {
                        ok = productoDAO.aumentarStock(idProducto, cantidad, conn);
                    } else {
                        ok = true; 
                    }

                    if (!ok) {
                        errorStock = true;
                        break;
                    }
                }

                if (errorStock) {
                    conn.rollback();
                    return false;
                }

                psActualizarEstado.setInt(1, nuevoEstado);
                psActualizarEstado.setInt(2, idVenta);
                boolean actualizado = psActualizarEstado.executeUpdate() > 0;

                if (actualizado) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerEstadoVenta(int idVenta) {
        String sql = "SELECT estado FROM cabeceraventa WHERE idCabeceraVenta = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("estado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean verificarExistenciaVentas() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Conexion.conectar();
            String sql = "SELECT COUNT(*) FROM cabeceraventa";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de ventas: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    public List<Object[]> llenarTablaVentas() {
        List<Object[]> ventas = new ArrayList<>();
        String sql = "SELECT cv.idCabeceraVenta, cv.fechaVenta, cv.total, "
                + "CONCAT(cl.nombre, ' ', cl.apellido) AS nombre_cliente, "
                + "CONCAT(u.nombre, ' ', u.apellido) AS nombre_empleado, cv.estado "
                + "FROM cabeceraventa cv "
                + "INNER JOIN cliente cl ON cv.idCliente = cl.idCliente "
                + "INNER JOIN empleado em ON cv.idEmpleado = em.idEmpleado "
                + "INNER JOIN usuario u ON em.idUsuario = u.idUsuario";

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idCabeceraVenta");
                fila[1] = rs.getDate("fechaVenta");
                fila[2] = rs.getDouble("total");
                fila[3] = rs.getString("nombre_cliente");
                fila[4] = rs.getString("nombre_empleado");
                fila[5] = rs.getInt("estado") == 1 ? "Activa" : "Anulada";
                ventas.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas resumidas: " + e.getMessage());
        }
        return ventas;
    }

    public List<Object[]> buscarVentas(String criterio) {
        List<Object[]> ventas = new ArrayList<>();
        String sql = "SELECT cv.idCabeceraVenta, cv.fechaVenta, cv.total, "
                + "CONCAT(cl.nombre, ' ', cl.apellido) AS nombre_cliente, "
                + "CONCAT(ue.nombre, ' ', ue.apellido) AS nombre_empleado, cv.estado "
                + "FROM CabeceraVenta cv "
                + "INNER JOIN Cliente cl ON cv.idCliente = cl.idCliente "
                + "INNER JOIN Empleado em ON cv.idEmpleado = em.idEmpleado "
                + "INNER JOIN Usuario ue ON em.idUsuario = ue.idUsuario "
                + "WHERE (CONCAT(cl.nombre, ' ', cl.apellido) LIKE ? OR cl.cedula LIKE ?)";

        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            String like = "%" + criterio + "%";
            pst.setString(1, like);
            pst.setString(2, like);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idCabeceraVenta");
                fila[1] = rs.getDate("fechaVenta");
                fila[2] = rs.getDouble("total");
                fila[3] = rs.getString("nombre_cliente");
                fila[4] = rs.getString("nombre_empleado");
                fila[5] = (rs.getInt("estado") == 1) ? "Activa" : "Anulada";
                ventas.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ventas: " + e.getMessage());
        }
        return ventas;
    }

    public Object[] obtenerTotalesVenta(int idCabeceraVenta) {
        String sql = "SELECT total FROM CabeceraVenta WHERE idCabeceraVenta = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCabeceraVenta);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Object[]{rs.getDouble("total")};
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener total de la venta: " + e.getMessage());
        }
        return null;
    }

    //para consultar
    public List<Object[]> obtenerDetalleVenta(int idCabeceraVenta) {
        List<Object[]> detalles = new ArrayList<>();
        String sql = "SELECT dv.cantidad, dv.precioUnitario, dv.subTotal, dv.descuento, dv.iva, p.nombre "
                + "FROM DetalleVenta dv "
                + "INNER JOIN Producto p ON dv.idProducto = p.idProducto "
                + "WHERE dv.idCabeceraVenta = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCabeceraVenta);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                detalles.add(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precioUnitario"),
                    rs.getDouble("subTotal"),
                    rs.getDouble("descuento"),
                    rs.getDouble("iva")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener detalle de la venta: " + e.getMessage());
        }
        return detalles;
    }
}
