/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class Usuario implements Serializable {
    protected int id;
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String direccion;
    protected String correo;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime eliminado;
    private int creadoPor;
    private int modificadoPor;

    public Usuario(){
        this.id = 0;
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.direccion = "";
        this.correo = "";
        this.creado = LocalDateTime.now();
        this.creadoPor = 0;
        this.modificado = LocalDateTime.now();
        this.modificadoPor = 0;
    }

    public Usuario(int id, String cedula, String nombre, String apellido, String telefono, String direccion, String correo, LocalDateTime creado, LocalDateTime modificado, LocalDateTime eliminado, int creadoPor, int modificadoPor) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.creado = creado;
        this.modificado = modificado;
        this.eliminado = eliminado;
        this.creadoPor = creadoPor;
        this.modificadoPor = modificadoPor;
    }

    public Usuario(String cedula, String nombre, String apellido, String telefono, String direccion, String correo, LocalDateTime creado, LocalDateTime modificado, LocalDateTime eliminado, int creadoPor, int modificadoPor) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.creado = creado;
        this.modificado = modificado;
        this.eliminado = eliminado;
        this.creadoPor = creadoPor;
        this.modificadoPor = modificadoPor;
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

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public void setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getEliminado() {
        return eliminado;
    }

    public void setEliminado(LocalDateTime eliminado) {
        this.eliminado = eliminado;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    public int getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(int modificadoPor) {
        this.modificadoPor = modificadoPor;
    }
    
    
    
    void registrar(){}
    void editar(){}
    void eliminar(){}
    void consultar(){}

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", direccion=" + direccion + ", correo=" + correo + ", creado=" + creado + ", modificado=" + modificado + ", eliminado=" + eliminado + ", creadoPor=" + creadoPor + ", modificadoPor=" + modificadoPor + '}';
    }
    
}
