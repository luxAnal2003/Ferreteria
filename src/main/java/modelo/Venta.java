///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package modelo;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author admin
// */
//public class Venta {
//    private int idVenta;
//    private boolean clienteRegistrado;
//    private String cedula;
//    private Cliente cliente;
//    private LocalDateTime fechaVenta;
//    private int idEmpleado;
//    private double subtotal;
//    private double iva;
//    private double total;
//    private double descuento;
//    private String estado;
//    private String motivoAnulacion;
//    private Date fechaAnulacion;
//    private List<DetalleVenta> detalles;
//    
//    public Venta() {
//    }
//
//    public Venta(int idVenta, String Cliente, String subtotal){}
//
//    public Venta(int idVenta, boolean clienteRegistrado, String cedula, Cliente cliente, LocalDateTime fechaVenta, int idEmpleado, double subtotal, double iva, double total, double descuento, String estado, String motivoAnulacion, Date fechaAnulacion, List<DetalleVenta> detalles) {
//        this.idVenta = idVenta;
//        this.clienteRegistrado = clienteRegistrado;
//        this.cedula = cedula;
//        this.cliente = cliente;
//        this.fechaVenta = fechaVenta;
//        this.idEmpleado = idEmpleado;
//        this.subtotal = subtotal;
//        this.iva = iva;
//        this.total = total;
//        this.descuento = descuento;
//        this.estado = estado;
//        this.motivoAnulacion = motivoAnulacion;
//        this.fechaAnulacion = fechaAnulacion;
//        this.detalles = detalles;
//    }
//
//    public int getIdVenta() {
//        return idVenta;
//    }
//
//    public void setIdVenta(int idVenta) {
//        this.idVenta = idVenta;
//    }
//
//    public boolean isClienteRegistrado() {
//        return clienteRegistrado;
//    }
//
//    public void setClienteRegistrado(boolean clienteRegistrado) {
//        this.clienteRegistrado = clienteRegistrado;
//    }
//
//    public String getCedula() {
//        return cedula;
//    }
//
//    public void setCedula(String cedula) {
//        this.cedula = cedula;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public LocalDateTime getFechaVenta() {
//        return fechaVenta;
//    }
//
//    public void setFechaVenta(LocalDateTime fechaVenta) {
//        this.fechaVenta = fechaVenta;
//    }
//
//    public int getIdEmpleado() {
//        return idEmpleado;
//    }
//
//    public void setIdEmpleado(int idEmpleado) {
//        this.idEmpleado = idEmpleado;
//    }
//
//    public double getSubtotal() {
//        return subtotal;
//    }
//
//    public void setSubtotal(double subtotal) {
//        this.subtotal = subtotal;
//    }
//
//    public double getIva() {
//        return iva;
//    }
//
//    public void setIva(double iva) {
//        this.iva = iva;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
//
//    public double getDescuento() {
//        return descuento;
//    }
//
//    public void setDescuento(double descuento) {
//        this.descuento = descuento;
//    }
//
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
//
//    public String getMotivoAnulacion() {
//        return motivoAnulacion;
//    }
//
//    public void setMotivoAnulacion(String motivoAnulacion) {
//        this.motivoAnulacion = motivoAnulacion;
//    }
//
//    public Date getFechaAnulacion() {
//        return fechaAnulacion;
//    }
//
//    public void setFechaAnulacion(Date fechaAnulacion) {
//        this.fechaAnulacion = fechaAnulacion;
//    }
//
//    public List<DetalleVenta> getDetalles() {
//        return detalles;
//    }
//
//    public void setDetalles(List<DetalleVenta> detalles) {
//        this.detalles = detalles;
//    }
//
//}
