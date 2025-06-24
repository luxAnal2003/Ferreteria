/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import modelo.Cliente;

/**
 *
 * @author admin
 */
public class ClienteController {
    
    private ClienteDAO clienteDAO = new ClienteDAO(); 

    public Cliente obtenerClientePorCedula(String cedula) {
        Cliente cliente = null;
        try {
            cliente = clienteDAO.buscarPorCedula(cedula);
        } catch (Exception e) {
            System.out.println("Error al obtener el cliente por cédula: " + e);
        }
        return cliente;
    }
}
