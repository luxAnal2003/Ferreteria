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

    public Cliente(String rol, int estado) {
        this.rol = rol;
        this.estado = estado;
    }

    public Cliente(String rol, int estado, int id, String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
        super(id, cedula, nombre, apellido, telefono, direccion, correo);
        this.rol = rol;
        this.estado = estado;
    }

    public Cliente(String rol, int estado, String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
        super(cedula, nombre, apellido, telefono, direccion, correo);
        this.rol = rol;
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

}
