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
    protected int id;
    protected String cedula;
    protected String nombre;
    protected String direccion;
    protected String correo;

    public Usuario(){
        this.id = 0;
        this.cedula = "";
        this.nombre = "";
        this.direccion = "";
        this.correo = "";
    }
    
    public Usuario(int id, String cedula, String nombre, String direccion, String correo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }
    
    public Usuario(String cedula, String nombre, String direccion, String correo, String usuario, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
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

  
    
    
    void registrar(){}
    void editar(){}
    void eliminar(){}
    void consultar(){}

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion + ", correo=" + correo + '}';
    }
    
}
