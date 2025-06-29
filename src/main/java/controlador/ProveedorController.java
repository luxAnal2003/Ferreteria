/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProveedorDAO;
import java.util.List;
import modelo.Proveedor;

/**
 *
 * @author admin
 */
public class ProveedorController {
    
    private ProveedorDAO proveedorDAO; 

    public ProveedorController() {
        this.proveedorDAO = new ProveedorDAO();
    }

    public boolean guardarProveedor(Proveedor proveedor) {
        return proveedorDAO.guardar(proveedor);
    }

    public boolean existeProveedorPorRuc(String ruc) {
        return proveedorDAO.existeProveedor(ruc);
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        return proveedorDAO.actualizar(proveedor);
    }

    public boolean activarProveedor(int idProveedor) {
        return proveedorDAO.activar(idProveedor);
    }

    public boolean desactivarProveedor(int idProveedor) {
        return proveedorDAO.desactivar(idProveedor);
    }

    public Proveedor obtenerProveedorPorId(int idProveedor) {
        return proveedorDAO.obtenerProveedorPorId(idProveedor);
    }
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorDAO.listarProveedores();
    }

    public List<Proveedor> buscarProveedoresPorCriterio(String criterio) {
        return proveedorDAO.buscarProveedores(criterio);
    }

    public boolean existenProveedoresEnSistema() {
        return proveedorDAO.verificarExistenciaDeProveedores();
    }
    
    public int obtenerProveedorPorNombre(String proveedor) {
        return proveedorDAO.getIdProveedorPorNombre(proveedor);
    }
    
}
