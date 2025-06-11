/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class Cliente extends Usuario{
    private String rol;
    private int estado;

    public Cliente(String rol, int estado, String string1, String string2, String string3, String string4) {
        this.rol = rol;
        this.estado = estado;
    }

    public Cliente(String rol, int estado, int id, String cedula, String nombre, String apellido, String telefono, String direccion, String correo, LocalDateTime creado, LocalDateTime modificado, LocalDateTime eliminado, int creadoPor, int modificadoPor) {
        super(id, cedula, nombre, apellido, telefono, direccion, correo, creado, modificado, eliminado, creadoPor, modificadoPor);
        this.rol = rol;
        this.estado = estado;
    }

    public Cliente(String rol, int estado, String cedula, String nombre, String apellido, String telefono, String direccion, String correo, LocalDateTime creado, LocalDateTime modificado, LocalDateTime eliminado, int creadoPor, int modificadoPor) {
        super(cedula, nombre, apellido, telefono, direccion, correo, creado, modificado, eliminado, creadoPor, modificadoPor);
        this.rol = rol;
        this.estado = estado;
    }

    public Cliente(String administrador, int i, String ced, String nom, String dir, String cor, Object object, Object object0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Cliente(String string, int aInt, int aInt0, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Override
    public String toString() {
        return "Empleado{" +
               super.toString() +
               ", rol='" + rol + '\'' +
               ", estado='" + estado + '\'' +
               '}';
    }
    
}
