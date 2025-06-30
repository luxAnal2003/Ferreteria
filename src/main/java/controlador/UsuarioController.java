/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

/**
 *
 * @author admin
 */
public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario login(String usuario, String contrasenia) {
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContrasenia(contrasenia);
        return usuarioDAO.login(u);
    }

    public int guardarUsuario(Usuario usuario) {
        return usuarioDAO.registrarUsuario(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario, int idUsuario) {
        return usuarioDAO.actualizarUsuario(usuario, idUsuario);
    }

    public String desactivarUsuario(int idUsuario) {
        Usuario usu = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        if (usu == null) {
            return "El usuario no existe";
        }

        if (usu.getEstado() == 0) {
            return "El usuario ya está desactivado";
        }

        boolean resultado = usuarioDAO.cambiarEstado(idUsuario, 0);
        return resultado ? "Usuario desactivado correctamente" : "Error al desactivar el usuario";
    }

    public String activarUsuario (int idUsuario) {
        Usuario usu = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        if (usu == null) {
            return "El usuario no existe";
        }

        if (usu.getEstado() == 1) {
            return "El usuario ya está activo";
        }

        boolean resultado = usuarioDAO.cambiarEstado(idUsuario, 1);
        return resultado ? "Usuario activado correctamente" : "Error al activar el usuario";
    }
    
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.obtenerUsuarioPorId(idUsuario);
    }
}
