/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author admin
 */
public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.getAllClientes();
    }

    public boolean activarCliente(int idCliente) {
        boolean exito = clienteDAO.activar(idCliente);
        if (!exito) {
            System.err.println("Error al activar cliente ID: " + idCliente);
        }
        return exito;
    }

    public boolean desactivarCliente(int idCliente) {
        boolean exito = clienteDAO.desactivar(idCliente);
        if (!exito) {
            System.err.println("Error al desactivar cliente ID: " + idCliente);
        }
        return exito;
    }

    public boolean existenClientes() {
        return clienteDAO.verificarExistenciaClientes();
    }

    public boolean guardarCliente(Cliente cliente) {
        return clienteDAO.guardar(cliente);
    }

    
    public boolean existeClientePorCedula(String cedula) {
        return clienteDAO.existeClientePorCedula(cedula);
    }

    
    public Cliente obtenerClientePorCedula(String cedula) {
        return clienteDAO.buscarPorCedula(cedula);
    }
    
    public boolean actualizarCliente(Cliente cliente) {
        boolean exito = clienteDAO.actualizar(cliente);
        if (!exito) {
            System.err.println("Error en ClienteController al actualizar cliente ID: " + cliente.getIdCliente());
        }
        return exito;
    }
    
    public Cliente obtenerClientePorId(int idCliente) {
        return clienteDAO.obtenerClientePorId(idCliente);
    }
    
    public List<Cliente> buscarClientesActivosPorCriterio(String criterio) {
        return clienteDAO.buscarClientesActivos(criterio);
    }
}
