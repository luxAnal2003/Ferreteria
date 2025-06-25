/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.VentaDAO;
import java.sql.SQLException;
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

    public boolean desactivarVenta(int idUsuario) {
        return ventaDAO.desactivar(idUsuario);
    }

    public boolean activarVenta(int idUsuario) {
        return ventaDAO.activar(idUsuario);
    }

    public boolean existenVentas() {
        return ventaDAO.verificarExistenciaVentas();
    }

    public List<Venta> listarVentasActivas() throws SQLException {
        return ventaDAO.obtenerTodasLasVentasActivas();
    }

    public List<Object[]> obtenerVentas() {
        return ventaDAO.obtenerVentasConDetalle();
    }
    
     public List<Object[]> buscarVentas(String criterio) {
        return ventaDAO.buscarVentas(criterio);
    }

    public Object[] obtenerTotalesVenta(int idCabeceraVenta) {
        return ventaDAO.obtenerTotalesVenta(idCabeceraVenta);
    }

    public List<Object[]> obtenerDetalleVenta(int idCabeceraVenta) {
        return ventaDAO.obtenerDetalleVenta(idCabeceraVenta);
    }

}
