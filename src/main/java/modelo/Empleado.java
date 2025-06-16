/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author admin
 */
public class Empleado extends Usuario{
    private int idEmpleado;
    private String cedula;
    private String direccion;

    public Empleado(){
        this.idEmpleado = 0;
        this.cedula = "";
        this.direccion = "";
    }
    
    public Empleado(int idEmpleado, String cedula, String direccion) {
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public Empleado(int idEmpleado, String cedula, String direccion, int idRol, String tipoRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        super(idRol, tipoRol, usuario, contrasenia, nombre, apellido, telefono, correo, estado);
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public Empleado(int idEmpleado, String cedula, String direccion, int idUsuario, int idRol, String tipoRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        super(idUsuario, idRol, tipoRol, usuario, contrasenia, nombre, apellido, telefono, correo, estado);
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
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
}
