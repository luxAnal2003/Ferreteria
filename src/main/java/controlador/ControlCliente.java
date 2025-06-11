/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jotar
 */
public class ControlCliente {
    // Crea un nuevo cliente en la base de datos
    public boolean crear(Cliente cliente) {
        String sql = "INSERT INTO clientes (cedula, nombre, direccion, correo, rol, estado) VALUES (?, ?, ?, ?, ?, ?)";
        // Asigna rol por defecto
        cliente.setRol("administrador");
        cliente.setEstado(1); // activo

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getRol());
            ps.setInt(6, cliente.getEstado());

            int affected = ps.executeUpdate();
            if (affected == 0) return false;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    // Obtiene todos los clientes activos (estado = 1)
    public List<Cliente> listarActivos() {
        String sql = "SELECT id, cedula, nombre, direccion, correo, rol, estado FROM clientes WHERE estado = 1";
        List<Cliente> lista = new ArrayList<>();

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getString("rol"),
                    rs.getInt("estado"),
                    rs.getInt("id"),
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("correo")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // Actualiza datos de un cliente existente
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET cedula = ?, nombre = ?, direccion = ?, correo = ?, rol = ? WHERE id = ? AND estado = 1";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getRol());
            ps.setInt(6, cliente.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Eliminación lógica: actualiza el estado a 0
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE clientes SET estado = 0 WHERE id = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente (lógico): " + e.getMessage());
            return false;
        }
    }

    // Obtiene un cliente por ID
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT id, cedula, nombre, direccion, correo, rol, estado FROM clientes WHERE id = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("rol"),
                        rs.getInt("estado"),
                        rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }
        return null;
    }
}
