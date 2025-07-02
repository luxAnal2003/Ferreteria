/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author admin
 */
public class Proveedor{

    private int idProveedor;
    private String ruc;
    private String razonSocial;
    private String telefono;
    private String correo;
    private String direccion;
    private int estado;

    public Proveedor() {
        super();
        this.idProveedor = 0;
        this.ruc = "";
        this.correo = "";
        this.direccion = "";
    }

    public Proveedor(int idProveedor, String ruc, String nombre, String telefono, String correo, String direccion, int estado) {
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        this.razonSocial = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return this.razonSocial;
    }

}
