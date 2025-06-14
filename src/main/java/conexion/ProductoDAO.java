///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import modelo.Producto;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author admin
// */
//public class ProductoDAO {
//
//    private Connection con;
//
//    public ProductoDAO() {
//        try {
//            con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/ferreteria",
//                    "root", 
//                    "1234" 
//            );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Producto> listar() {
//        List<Producto> lista = new ArrayList<>();
//        String sql = "SELECT * FROM Producto";
//        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                lista.add(new Producto(
//                        rs.getInt("idProducto"),
//                        rs.getString("nombre"),
//                        rs.getString("descripcion"),
//                        rs.getDouble("precioUnitario"),
//                        rs.getInt("stock"),
//                        rs.getInt("idProveedor")
//                ));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
//
//    public Producto buscarPorNombre(String nombre) {
//        String sql = "SELECT * FROM Producto WHERE nombre = ?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, nombre);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Producto(
//                        rs.getInt("idProducto"),
//                        rs.getString("nombre"),
//                        rs.getString("descripcion"),
//                        rs.getDouble("precioUnitario"),
//                        rs.getInt("stock"),
//                        rs.getInt("idProveedor")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void registrar(Producto producto) {
//        String sql = "INSERT INTO Producto (nombre, precioUnitario, stock) VALUES (?, ?, ?)";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, producto.getNombre());
//            ps.setDouble(2, producto.getPrecioUnitario());
//            ps.setInt(3, producto.getStock());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
