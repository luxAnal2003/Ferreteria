///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
///**
// *
// * @author admin
// */
//
//import modelo.Cliente;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClienteDAO {
//    private Connection con;
//
//    public ClienteDAO() {
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteria", "root", "");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Cliente buscarPorCedula(String cedula) {
//        String sql = "SELECT * FROM Cliente WHERE cedula = ?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, cedula);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Cliente(
//                    rs.getString("cedula"),
//                    rs.getString("nombre"),
//                    rs.getString("direccion"),
//                    rs.getString("telefono"),
//                    rs.getString("email")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void registrar(Cliente c) {
//        String sql = "INSERT INTO Cliente (cedula, nombre, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, c.getCedula());
//            ps.setString(2, c.getNombre());
//            ps.setString(3, c.getDireccion());
//            ps.setString(4, c.getTelefono());
//            ps.setString(5, c.getCorreo());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void actualizar(Cliente c) {
//        String sql = "UPDATE Cliente SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE cedula = ?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, c.getNombre());
//            ps.setString(2, c.getDireccion());
//            ps.setString(3, c.getTelefono());
//            ps.setString(4, c.getCorreo());
//            ps.setString(5, c.getCedula());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Cliente> listar() {
//        List<Cliente> lista = new ArrayList<>();
//        String sql = "SELECT * FROM Cliente";
//        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                lista.add(new Cliente(
//                    rs.getString("cedula"),
//                    rs.getString("nombre"),
//                    rs.getString("direccion"),
//                    rs.getString("telefono"),
//                    rs.getString("email")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
//}
//
