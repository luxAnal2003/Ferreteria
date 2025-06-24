/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.VentaDAO;
import java.util.List;
import modelo.DetalleVenta;
import modelo.Venta;

/**
 *
 * @author admin
 */
public class VentaController {

    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public boolean guardarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        VentaDAO dao = new VentaDAO();
        return dao.guardarVentaCompleta(venta, detalles);
    }

}
