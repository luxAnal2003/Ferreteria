/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Venta {
    private int idVenta;
    private LocalDateTime fechaVenta;
    private int idCliente;
    private int idProducto;
    private int idEmpleado;
    private double subtotal;
    private double iva;
    private double total;
    private double descuento;
    private String estado;
    private String tipoPago;
    private String motivoAnulacion;
    private String Cliente;
    private Date fecha;
    
    public Venta() {
    }

    public Venta(int idVenta, String Cliente, String subtotal){}

    public Venta(int idVenta, LocalDateTime fechaVenta, int idCliente, int idProducto, int idEmpleado, double subtotal, double iva, double total, double descuento, String estado, String tipoPago, String motivoAnulacion, String Cliente, Date fecha) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.idEmpleado = idEmpleado;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.descuento = descuento;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.motivoAnulacion = motivoAnulacion;
        this.Cliente = Cliente;
        this.fecha = fecha;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
