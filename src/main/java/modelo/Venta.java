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
public class Venta {
    private int idVenta;
    private LocalDateTime fechaVenta;
    private int idCliente;
    private int idUsuario;
    private double subtotal;
    private double iva;
    private double total;
    private double descuento;
    private String estado;
    private String tipoPago;
    private String motivoAnulacion;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime eliminado;
    private int creadoPor;
    private int modificadoPor;

    public Venta() {
    }

    public Venta(int idVenta, LocalDateTime fechaVenta, int idCliente, int idUsuario, double subtotal, double iva, double total, double descuento, String estado, String tipoPago, String motivoAnulacion, LocalDateTime creado, LocalDateTime modificado, LocalDateTime eliminado, int creadoPor, int modificadoPor) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.descuento = descuento;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.motivoAnulacion = motivoAnulacion;
        this.creado = creado;
        this.modificado = modificado;
        this.eliminado = eliminado;
        this.creadoPor = creadoPor;
        this.modificadoPor = modificadoPor;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", idCliente=" + idCliente + ", idUsuario=" + idUsuario + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + ", descuento=" + descuento + ", estado=" + estado + ", tipoPago=" + tipoPago + ", motivoAnulacion=" + motivoAnulacion + ", creado=" + creado + ", modificado=" + modificado + ", eliminado=" + eliminado + ", creadoPor=" + creadoPor + ", modificadoPor=" + modificadoPor + '}';
    }
    
}
