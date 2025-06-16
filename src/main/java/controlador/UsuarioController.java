/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.Conexion;
import java.sql.Connection;
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
public class UsuarioController {

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

}
