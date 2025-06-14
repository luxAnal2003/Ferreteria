/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import modelo.Usuario;

/**
 *
 * @author admin
 */
public class Sesion {
    private static Usuario usuarioActual;

    public static void setUsuarioActual(Usuario usuario) {
        Sesion.usuarioActual = usuario;
    }
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public static void cerrarSesion() {
        Sesion.usuarioActual = null;
    }
}
