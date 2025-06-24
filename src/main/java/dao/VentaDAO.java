/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.Conexion;
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

    public boolean activar(int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = null;
        String sql = "UPDATE cabeceraventa SET estado = 1 WHERE idCabeceraVenta = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idCabeceraVenta);
            respuesta = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al activar venta: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en activar venta: " + e.getMessage());
            }
        }
        return respuesta;
    }

    public boolean desactivar(int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = null;
        String sql = "UPDATE cabeceraventa SET estado = 0 WHERE idCabeceraVenta = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idCabeceraVenta);
            respuesta = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar venta: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en desactivar venta: " + e.getMessage());
            }
        }
        return respuesta;
    }
    
    public List<Venta> obtenerTodasLasVentasActivas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        Connection con = null;
        String sql = "SELECT cv.idCabeceraVenta, cv.idCliente, cv.idEmpleado, cv.fechaVenta, cv.total, cv.estado, "
                + "cl.nombre AS nombre_cliente, cl.apellido AS apellido_cliente, "
                + "u.nombre AS nombre_empleado, u.apellido AS apellido_empleado " 
                + "FROM CabeceraVenta cv "
                + "INNER JOIN Cliente cl ON cv.idCliente = cl.idCliente "
                + "INNER JOIN Empleado e ON cv.idEmpleado = e.idEmpleado "
                + "INNER JOIN Usuario u ON e.idUsuario = u.idUsuario " 
                + "WHERE cv.estado = 1";

        try {
            con = Conexion.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdCabecera(rs.getInt("idCabeceraVenta"));
                venta.setIdCliente(rs.getInt("idCliente")); 
                venta.setIdEmpleado(rs.getInt("idEmpleado"));
                venta.setFechaVenta(rs.getString("fechaVenta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getInt("estado"));
                
                ventas.add(venta);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ventas;
    }

    public List<Venta> buscarVentas(String criterio) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        Connection con = null;
        String sql = "SELECT cv.idCabeceraVenta, cv.idCliente, cv.idEmpleado, cv.fechaVenta, cv.total, cv.estado, "
                + "cl.nombre AS nombre_cliente, cl.apellido AS apellido_cliente, "
                + "u.nombre AS nombre_empleado, u.apellido AS apellido_empleado "
                + "FROM CabeceraVenta cv "
                + "INNER JOIN Cliente cl ON cv.idCliente = cl.idCliente "
                + "INNER JOIN Empleado e ON cv.idEmpleado = e.idEmpleado "
                + "INNER JOIN Usuario u ON e.idUsuario = u.idUsuario "
                + "WHERE cv.estado = 1 AND ("
                + "CONCAT(cl.nombre, ' ', cl.apellido) LIKE ? OR cl.cedula LIKE ? OR CONCAT(u.nombre, ' ', u.apellido) LIKE ? OR cv.idCabeceraVenta LIKE ?" // Buscar por ID de venta también
                + ")";

        try {
            con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            String busquedaLike = "%" + criterio + "%";
            pst.setString(1, busquedaLike);
            pst.setString(2, busquedaLike);
            pst.setString(3, busquedaLike);
            pst.setString(4, busquedaLike); 

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdCabecera(rs.getInt("idCabeceraVenta"));
                venta.setIdCliente(rs.getInt("idCliente"));
                venta.setIdEmpleado(rs.getInt("idEmpleado"));
                venta.setFechaVenta(rs.getString("fechaVenta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getInt("estado"));
                ventas.add(venta);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ventas;
    }
}
