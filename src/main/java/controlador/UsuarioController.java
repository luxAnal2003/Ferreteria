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
        return usuarioDAO.guardar(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    public boolean desactivarUsuario(int idUsuario) {
        return usuarioDAO.desactivar(idUsuario);
    }

    public boolean activarUsuario(int idUsuario) {
        return usuarioDAO.activar(idUsuario);
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.getUsuarioById(idUsuario);
    }
}
