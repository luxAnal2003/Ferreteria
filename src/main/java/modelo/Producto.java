/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author admin
 */

public class Producto {
    private String nombreProducto;
    private Proveedor idProveedor;
    private int cantidad;
    private String descripcion;
    private double precio;
    private double iva;
    private Categoria idCategoria;
    private int estado;

    public Producto(){
        this.nombreProducto = "";
        this.idProveedor = new Proveedor();
        this.cantidad = 0;
        this.descripcion = "";
        this.precio = 0.0;
        this.iva = 0.0;
        this.idCategoria = new Categoria();
        this.estado = 0;
    }

    public Producto(String nombreProducto, Proveedor idProveedor, int cantidad, String descripcion, double precio, double iva, Categoria idCategoria, int estado) {
        this.nombreProducto = nombreProducto;
        this.idProveedor = idProveedor;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.iva = iva;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
