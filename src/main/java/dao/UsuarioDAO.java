/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author admin
 */
public class UsuarioDAO {

    public Usuario login(Usuario usuario) {
        Connection cn = Conexion.conectar();
        Usuario usuarioAutenticado = null;

        String sql = "SELECT u.idUsuario, u.usuario, u.contrasenia, u.nombre, u.apellido, u.telefono, "
                + "u.estado, u.idRol, r.tipo "
                + "FROM usuario u "
                + "INNER JOIN rol r ON u.idRol = r.idRol "
                + "WHERE u.usuario = ? AND u.contrasenia = ?";

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getContrasenia());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    usuarioAutenticado = new Usuario();
                    usuarioAutenticado.setIdUsuario(rs.getInt("idUsuario"));
                    usuarioAutenticado.setUsuario(rs.getString("usuario"));
                    usuarioAutenticado.setContrasenia(rs.getString("contrasenia"));
                    usuarioAutenticado.setNombre(rs.getString("nombre"));
                    usuarioAutenticado.setApellido(rs.getString("apellido"));
                    usuarioAutenticado.setTelefono(rs.getString("telefono"));
                    usuarioAutenticado.setEstado(rs.getInt("estado"));

                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setTipo(rs.getString("tipo"));
                    usuarioAutenticado.setIdRol(rol);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }

        return usuarioAutenticado;
    }

    public int registrarUsuario(Usuario usuario) {
        int idGenerado = -1;
        String sql = "INSERT INTO Usuario (idRol, nombre, apellido, usuario, contrasenia, telefono, correo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, usuario.getIdRol().getIdRol());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContrasenia());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getCorreo());
            ps.setInt(8, usuario.getEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }

        return idGenerado;
    }

    public boolean actualizarUsuario(Usuario usuario, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE usuario SET nombre = ?, apellido = ?, usuario = ?, contrasenia = ?, telefono = ?, correo = ?, estado = ?, idRol = ? WHERE idUsuario = ?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasenia());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setInt(7, usuario.getEstado());
            ps.setInt(8, usuario.getIdRol().getIdRol());
            ps.setInt(9, idUsuario);

            respuesta = ps.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }

        return respuesta;
    }

    public boolean cambiarEstado(int idUsuario, int estado) {
        String sql = "UPDATE usuario SET estado = ? WHERE idUsuario = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;

        String sql = "SELECT u.*, r.tipo FROM Usuario u "
                + "INNER JOIN Rol r ON u.idRol = r.idRol "
                + "WHERE u.idUsuario = ?";

        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));

                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setTipo(rs.getString("tipo"));
                usuario.setIdRol(rol);

                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener usuario por ID: " + e.getMessage());
        }

        return usuario;
    }
}