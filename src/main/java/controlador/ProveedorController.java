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

    public String guardarProveedor(String ruc, String nombreComercial, String telefono, String email,String direccion, int estado) {
        if (ruc.isEmpty() || nombreComercial.isEmpty() || telefono.isEmpty() || email.isEmpty()|| direccion.isEmpty()) {
            return "Todos los campos son obligatorios";
        }
        if (!ruc.matches("\\d{13}")) {
            return "El RUC debe tener exactamente 13 caracteres numéricos";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe tener exactamente 10 caracteres numéricos" ;
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido.";
        }
        
        if (proveedorDAO.existeProveedor(ruc)) {
            return "El proveedor ya existe con ese ruc";
        }
        
        Proveedor proveedor = new Proveedor();
        proveedor.setRuc(ruc);
        proveedor.setRazonSocial(nombreComercial);
        proveedor.setTelefono(telefono);
        proveedor.setCorreo(email);
        proveedor.setDireccion(direccion);
        proveedor.setEstado(estado);
        
        boolean registrado = proveedorDAO.registrarProveedor(proveedor);
        return registrado ? "Proveedor registrado correctamente" : "Error al registrar el proveedor";
    }

    public String actualizarProveedor(String ruc, String nombreComercial, String telefono, String email,String direccion, int estado, int idProveedor) {
        if (ruc.isEmpty() || nombreComercial.isEmpty() || telefono.isEmpty() || email.isEmpty()|| direccion.isEmpty()) {
            return "Todos los campos son obligatorios";
        }
        if (!ruc.matches("\\d{13}")) {
            return "El RUC debe tener exactamente 13 caracteres numéricos";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe tener exactamente 10 caracteres numéricos" ;
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido.";
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setRuc(ruc);
        proveedor.setRazonSocial(nombreComercial);
        proveedor.setTelefono(telefono);
        proveedor.setCorreo(email);
        proveedor.setDireccion(direccion);
        proveedor.setEstado(estado);
        
        boolean actualizado = proveedorDAO.actualizarProveedor(proveedor, idProveedor);
        return actualizado ? "Proveedor actualizado correctamente" : "Error al actualizar el proveedor";
    }

    public String desactivarProveedor(int idProveedor) {
        Proveedor prov = proveedorDAO.obtenerProveedorPorId(idProveedor);
        if (prov == null) {
            return "El proveedor no existe";
        }

        if (prov.getEstado() == 0) {
            return "El proveedor ya está desactivada";
        }

        boolean resultado = proveedorDAO.cambiarEstado(idProveedor, 0);
        return resultado ? "Proveedor desactivado correctamente" : "Error al desactivar el proveedor";
    }

    public String activarProveedor(int idProveedor) {
        Proveedor prov = proveedorDAO.obtenerProveedorPorId(idProveedor);
        if (prov == null) {
            return "El proveedor no existe";
        }

        if (prov.getEstado() == 1) {
            return "El proveedor ya está activa";
        }

        boolean resultado = proveedorDAO.cambiarEstado(idProveedor, 1);
        return resultado ? "Proveedor activado correctamente" : "Error al activar el proveedor";
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
