/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.ProductoDAO;
import java.util.List;

/**
 *
 * @author admin
 */
public class VentaCalculadora {

    public static Totales calcularTotales(List<DetalleVenta> detalles, ProductoDAO productoDAO) {
        double subtotal = 0;
        double iva = 0;
        double descuento = 0;
        double total = 0;

        for (DetalleVenta detalle : detalles) {
            Producto producto = productoDAO.obtenerProductoPorId(detalle.getIdProducto());
            double sub = detalle.getCantidad() * detalle.getPrecioUnitario();
            double ivaProducto = sub * producto.getPorcentajeIva() / 100.0;
            double desc = sub * 0.005;
            double tot = sub + ivaProducto - desc;

            subtotal += sub;
            iva += ivaProducto;
            descuento += desc;
            total += tot;
        }

        return new Totales(subtotal, iva, descuento, total);
    }
}
