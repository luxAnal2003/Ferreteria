///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
//import modelo.*;
//import java.sql.*;
//import java.util.*;
//
///**
// *
// * @author admin
// */
//public class VentaDao {
//
//    private Connection con;
//
//    public int registrarVenta(Venta venta) throws SQLException {
//        int idVentaGenerado = -1;
//
//        try (Connection conn = Conexion.conectar()) {
//            conn.setAutoCommit(false);
//
//            String sqlVenta = """
//                INSERT INTO Venta (clienteRegistrado, cedulaCliente, fechaVenta, idEmpleado,
//                                   subtotal, iva, descuento, total, estado)
//                VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVA')
//            """;
//
//            PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
//            psVenta.setBoolean(1, venta.isClienteRegistrado());
//            psVenta.setString(2, venta.getCedula());
//            psVenta.setTimestamp(3, Timestamp.valueOf(venta.getFechaVenta()));
//            psVenta.setInt(4, venta.getIdEmpleado());
//            psVenta.setDouble(5, venta.getSubtotal());
//            psVenta.setDouble(6, venta.getIva());
//            psVenta.setDouble(7, venta.getDescuento());
//            psVenta.setDouble(8, venta.getTotal());
//
//            psVenta.executeUpdate();
//
//            ResultSet rs = psVenta.getGeneratedKeys();
//            if (rs.next()) {
//                idVentaGenerado = rs.getInt(1);
//            }
//
//            for (DetalleVenta det : venta.getDetalles()) {
//                String sqlDetalle = """
//                    INSERT INTO DetalleVenta (idVenta, idProducto, cantidad, precioUnitario, subtotal)
//                    VALUES (?, ?, ?, ?, ?)
//                """;
//                PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
//                psDetalle.setInt(1, idVentaGenerado);
//                psDetalle.setInt(2, det.getProducto().getIdProducto());
//                psDetalle.setInt(3, det.getCantidad());
//                psDetalle.setDouble(4, det.getPrecioUnitario());
//                psDetalle.setDouble(5, det.getSubtotal());
//                psDetalle.executeUpdate();
//
//                // Actualizar stock
//                String sqlStock = "UPDATE Producto SET stock = stock - ? WHERE idProducto = ? AND stock >= ?";
//                PreparedStatement psStock = conn.prepareStatement(sqlStock);
//                psStock.setInt(1, det.getCantidad());
//                psStock.setInt(2, det.getProducto().getIdProducto());
//                psStock.setInt(3, det.getCantidad());
//                int updated = psStock.executeUpdate();
//
//                if (updated == 0) {
//                    conn.rollback();
//                    throw new SQLException("ERROR: STOCK INSUFICIENTE PARA EL PRODUCTO ID " + det.getProducto().getIdProducto());
//                }
//            }
//
//            conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        return idVentaGenerado;
//    }
//
//    public int registrarVentaConDetalles(Venta v, List<DetalleVenta> detalles) throws SQLException {
//        int idVentaGenerado = -1;
//
//        try (Connection conn = Conexion.conectar()) {
//            conn.setAutoCommit(false);
//
//            String sqlVenta = "INSERT INTO Venta (clienteRegistrado, cedulaCliente, fechaVenta, idEmpleado, subtotal, iva, descuento, total, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
//            psVenta.setBoolean(1, v.isClienteRegistrado());
//            psVenta.setString(2, v.getCedula());
//            psVenta.setTimestamp(3, Timestamp.valueOf(v.getFechaVenta()));
//            psVenta.setInt(4, v.getIdEmpleado());
//            psVenta.setDouble(5, v.getSubtotal());
//            psVenta.setDouble(6, v.getIva());
//            psVenta.setDouble(7, v.getDescuento());
//            psVenta.setDouble(8, v.getTotal());
//            psVenta.setString(9, v.getEstado());
//            psVenta.executeUpdate();
//
//            ResultSet rs = psVenta.getGeneratedKeys();
//            if (rs.next()) {
//                idVentaGenerado = rs.getInt(1);
//            }
//
//            for (DetalleVenta d : detalles) {
//                // Validar stock
//                String checkStock = "SELECT stock FROM Producto WHERE idProducto = ?";
//                PreparedStatement psCheckStock = conn.prepareStatement(checkStock);
//                psCheckStock.setInt(1, d.getProducto().getIdProducto());
//                ResultSet rsStock = psCheckStock.executeQuery();
//                if (rsStock.next()) {
//                    int stockActual = rsStock.getInt("stock");
//                    if (stockActual < d.getCantidad()) {
//                        conn.rollback();
//                        throw new SQLException("Stock insuficiente para el producto ID " + d.getProducto().getIdProducto());
//                    }
//                } else {
//                    conn.rollback();
//                    throw new SQLException("Producto no encontrado con ID " + d.getProducto().getIdProducto());
//                }
//
//                // Insertar detalle
//                String sqlDetalle = "INSERT INTO DetalleVenta (idVenta, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
//                PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
//                psDetalle.setInt(1, idVentaGenerado);
//                psDetalle.setInt(2, d.getProducto().getIdProducto());
//                psDetalle.setInt(3, d.getCantidad());
//                psDetalle.setDouble(4, d.getPrecioUnitario());
//                psDetalle.setDouble(5, d.getSubtotal());
//                psDetalle.executeUpdate();
//
//                // Actualizar stock
//                String sqlActualizarStock = "UPDATE Producto SET stock = stock - ? WHERE idProducto = ?";
//                PreparedStatement psStock = conn.prepareStatement(sqlActualizarStock);
//                psStock.setInt(1, d.getCantidad());
//                psStock.setInt(2, d.getProducto().getIdProducto());
//                psStock.executeUpdate();
//            }
//
//            conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return idVentaGenerado;
//    }
//
//    public boolean anularVenta(int idVenta, String motivo) {
//        try (Connection conn = Conexion.conectar()) {
//            String checkEstado = "SELECT estado, fechaVenta FROM Venta WHERE idVenta = ?";
//            PreparedStatement psCheck = conn.prepareStatement(checkEstado);
//            psCheck.setInt(1, idVenta);
//            ResultSet rs = psCheck.executeQuery();
//
//            if (!rs.next()) {
//                return false;
//            }
//
//            String estado = rs.getString("estado");
//            Timestamp fecha = rs.getTimestamp("fechaVenta");
//            long dias = (System.currentTimeMillis() - fecha.getTime()) / (1000 * 60 * 60 * 24);
//
//            if ("ANULADA".equals(estado)) {
//                throw new IllegalStateException("LA VENTA YA SE ENCUENTRA ANULADA");
//            }
//            if (dias > 7) {
//                throw new IllegalStateException("ERROR: ESTA VENTA NO PUEDE SER ANULADA PORQUE SUPERA EL TIEMPO LÍMITE");
//            }
//
//            // Marcar como anulada
//            String sql = "UPDATE Venta SET estado='ANULADA', motivoAnulacion=?, fechaAnulacion=NOW() WHERE idVenta=?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, motivo);
//            ps.setInt(2, idVenta);
//            ps.executeUpdate();
//
//            // Devolver stock
//            String sqlDetalle = "SELECT idProducto, cantidad FROM DetalleVenta WHERE idVenta=?";
//            PreparedStatement psDet = conn.prepareStatement(sqlDetalle);
//            psDet.setInt(1, idVenta);
//            ResultSet rsDet = psDet.executeQuery();
//
//            while (rsDet.next()) {
//                int idProd = rsDet.getInt("idProducto");
//                int cant = rsDet.getInt("cantidad");
//                String updStock = "UPDATE Producto SET stock = stock + ? WHERE idProducto = ?";
//                PreparedStatement psUpd = conn.prepareStatement(updStock);
//                psUpd.setInt(1, cant);
//                psUpd.setInt(2, idProd);
//                psUpd.executeUpdate();
//            }
//
//            return true;
//
//        } catch (SQLException | IllegalStateException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Venta consultarVentaPorId(int idVenta) {
//        Venta venta = null;
//        try (Connection conn = Conexion.conectar()) {
//            String sql = "SELECT * FROM Venta WHERE idVenta=?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, idVenta);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                venta = new Venta();
//                venta.setIdVenta(idVenta);
//                venta.setClienteRegistrado(rs.getBoolean("clienteRegistrado"));
//                venta.setCedula(rs.getString("cedulaCliente"));
//                venta.setFechaVenta(rs.getTimestamp("fechaVenta").toLocalDateTime());
//                venta.setIdEmpleado(rs.getInt("idEmpleado"));
//                venta.setSubtotal(rs.getDouble("subtotal"));
//                venta.setIva(rs.getDouble("iva"));
//                venta.setDescuento(rs.getDouble("descuento"));
//                venta.setTotal(rs.getDouble("total"));
//                venta.setEstado(rs.getString("estado"));
//                venta.setMotivoAnulacion(rs.getString("motivoAnulacion"));
//                venta.setFechaAnulacion(rs.getTimestamp("fechaAnulacion"));
//
//                // Detalles
//                List<DetalleVenta> detalles = new ArrayList<>();
//                String sqlDet = "SELECT * FROM DetalleVenta dv JOIN Producto p ON dv.idProducto = p.idProducto WHERE idVenta=?";
//                PreparedStatement psDet = conn.prepareStatement(sqlDet);
//                psDet.setInt(1, idVenta);
//                ResultSet rsDet = psDet.executeQuery();
//
//                while (rsDet.next()) {
//                    DetalleVenta d = new DetalleVenta();
//                    Producto p = new Producto();
//                    p.setIdProducto(rsDet.getInt("idProducto"));
//                    p.setNombre(rsDet.getString("nombre"));
//                    p.setPrecioUnitario(rsDet.getDouble("precioUnitario"));
//                    d.setProducto(p);
//                    d.setCantidad(rsDet.getInt("cantidad"));
//                    d.setPrecioUnitario(rsDet.getDouble("precioUnitario"));
//                    d.setSubtotal(rsDet.getDouble("subtotal"));
//                    detalles.add(d);
//                }
//
//                venta.setDetalles(detalles);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return venta;
//    }
//}
