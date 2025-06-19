/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import modelo.Venta;
import modelo.DetalleVenta;

/**
 *
 * @author admin
 */
public class VentaController {
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
}
