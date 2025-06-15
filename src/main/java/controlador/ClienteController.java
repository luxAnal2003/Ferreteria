package controlador;

import dao.Conexion;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteController {

    public boolean crear(Cliente cliente) {
        String sql = "INSERT INTO Cliente (idRol, cedula, nombre, apellido, telefono, direccion, correo, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        cliente.setEstado(1);

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cliente.getIdRol());
            ps.setString(2, cliente.getCedula());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getCorreo());
            ps.setInt(8, cliente.getEstado());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                return false;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listarActivos() {
        String sql = "SELECT * FROM Cliente WHERE estado = 1";
        List<Cliente> lista = new ArrayList<>();

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("cedula"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getInt("idCliente"), // idUsuario == idCliente
                        rs.getInt("idRol"),
                        null, // tipoRol se puede cargar con join si se necesita
                        null, null, // usuario, contrasenia (desde tabla Usuario si aplica)
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getInt("estado")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET cedula = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ?, correo = ?, idRol = ? "
                + "WHERE idCliente = ? AND estado = 1";

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getCorreo());
            ps.setInt(7, cliente.getIdRol());
            ps.setInt(8, cliente.getIdCliente());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLogico(int id) {
        String sql = "UPDATE Cliente SET estado = 0 WHERE idCliente = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente (lógico): " + e.getMessage());
            return false;
        }
    }

    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("cedula"),
                            rs.getString("direccion"),
                            rs.getString("correo"),
                            rs.getInt("idCliente"),
                            rs.getInt("idRol"),
                            null,
                            null, null,
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("telefono"),
                            rs.getInt("estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }
        return null;
    }
}
