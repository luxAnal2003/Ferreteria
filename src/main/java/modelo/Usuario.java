/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Usuario implements Serializable {
    protected int idUsuario;
    protected Rol idRol;
    protected String usuario;
    protected String contrasenia;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;
    protected int estado;
    
    public Usuario(){
        this.idUsuario = 0;
        this.idRol = new Rol();
        this.usuario = "";
        this.contrasenia = "";
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.correo = "";
        this.estado = 0;
    }

    public Usuario(Rol idRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        this.idRol = idRol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }
    
    public Usuario(int idUsuario, Rol idRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }
}
