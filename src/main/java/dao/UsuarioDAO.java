/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author admin
 */
public class UsuarioDAO {

    public Usuario login(Usuario usuario) {
        Connection cn = Conexion.conectar();
        Usuario usuarioAutenticado = null;

        String sql = "SELECT u.*, r.tipo FROM usuario u "
                + "INNER JOIN rol r ON u.idRol = r.idRol "
                + "WHERE u.usuario = '" + usuario.getUsuario() + "' "
                + "AND u.contrasenia = '" + usuario.getContrasenia() + "'";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setIdUsuario(rs.getInt("idUsuario"));
                usuarioAutenticado.setUsuario(rs.getString("usuario"));
                usuarioAutenticado.setContrasenia(rs.getString("contrasenia"));
                usuarioAutenticado.setNombre(rs.getString("nombre"));
                usuarioAutenticado.setApellido(rs.getString("apellido"));
                usuarioAutenticado.setTelefono(rs.getString("telefono"));
                usuarioAutenticado.setEstado(rs.getInt("estado"));
                usuarioAutenticado.setIdRol(rs.getInt("idRol"));

                usuarioAutenticado.setTipoRol(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
            System.out.println("Error: " + e);
        }

        return usuarioAutenticado;
    }

    public int guardar(Usuario usuario) {
        int idGenerado = -1;
        String sql = "INSERT INTO Usuario (idRol, nombre, apellido, usuario, contrasenia, telefono, correo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, usuario.getIdRol());
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

    public boolean actualizar(Usuario usuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, usuario = ?, contrasenia = ?, telefono = ?, correo = ?, estado = ?, idRol = ? WHERE idUsuario = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasenia());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setInt(7, usuario.getEstado());
            ps.setInt(8, usuario.getIdRol());
            ps.setInt(9, usuario.getIdUsuario());

            respuesta = ps.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }

        return respuesta;
    }

    public boolean desactivar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE usuario SET estado = 0 WHERE idUsuario = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            respuesta = pst.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al desactivar usuario: " + e);
        }
        return respuesta;
    }
    
    public boolean activar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE usuario SET estado = 1 WHERE idUsuario = ?");
            consulta.setInt(1, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al activar usuario: " + e);
        }

        return respuesta;
    }
}
