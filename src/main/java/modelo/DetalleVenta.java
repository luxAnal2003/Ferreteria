/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author admin
 */
public class DetalleVenta {

    private int idDetalleVenta;
    private int idCabeceraVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    private double iva;
    private double descuento;
    private double totalPagar;
    private int estado;

    public DetalleVenta() {
        this.idDetalleVenta = 0;
        this.idDetalleVenta = 0;
        this.idProducto = 0;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subTotal = 0.0;
        this.iva = 0.0;
        this.descuento = 0.0;
        this.totalPagar = 0.0;
        this.estado = 0;
    }

    public DetalleVenta(int idDetalleVenta, int idCabeceraVenta, int idProducto, int cantidad, double precioUnitario, double subTotal, double iva, double descuento, double totalPagar, int estado) {
        this.idDetalleVenta = idDetalleVenta;
        this.idCabeceraVenta = idCabeceraVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.iva = iva;
        this.descuento = descuento;
        this.totalPagar = totalPagar;
        this.estado = estado;
    }

    public DetalleVenta(int idProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.estado = 1;
//        calcularTotales();
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

//    private void calcularTotales() {
//        this.subTotal = cantidad * precioUnitario;
//        this.iva = subTotal * 0.12;
//        this.descuento = subTotal * 0.005;
//        this.totalPagar = subTotal + iva - descuento;
//    }
    
    public static DetalleVenta crearDetalleDesdeProducto(Producto producto, int cantidad) {
        double precio = producto.getPrecio();
        double subtotal = precio * cantidad;
        double iva = subtotal * producto.getPorcentajeIva() / 100;
        double descuento = subtotal * 0.005;
        double total = subtotal + iva - descuento;

        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdProducto(producto.getIdProducto());
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubTotal(subtotal);
        detalle.setIva(iva);
        detalle.setDescuento(descuento);
        detalle.setTotalPagar(total);
        detalle.setEstado(1);
        return detalle;
    }

}
