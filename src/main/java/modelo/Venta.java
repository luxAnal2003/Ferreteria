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
    private int idCabecera;
    private int idCliente;
    private int idEmpleado;
    private double total;
    private int estado;
    private String fechaVenta;

    public Venta() {
        this.idCabecera = 0;
        this.idCliente = 0;
        this.idEmpleado = 0;
        this.total = 0.0;
        this.estado = 0;
        this.fechaVenta = "";
    }

    public Venta(int idCabecera, int idCliente, int idEmpleado, int total, int estado, String fechaVenta) {
        this.idCabecera = idCabecera;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.total = total;
        this.estado = estado;
        this.fechaVenta = fechaVenta;
    }

    public int getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(int idCabecera) {
        this.idCabecera = idCabecera;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
