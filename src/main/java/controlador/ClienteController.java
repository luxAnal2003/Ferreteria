/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import java.util.List;
import modelo.Cliente;
import vista.JPanelVentaNuevo;

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
        return clienteDAO.listarClientes();
    }

    public String guardarCliente(String cedula, String nombres, String apellidos, String telefono, String email, String direccion, int estado) {
        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        cliente.setNombre(nombres);
        cliente.setApellido(apellidos);
        cliente.setTelefono(telefono);
        cliente.setCorreo(email);
        cliente.setDireccion(direccion);
        cliente.setEstado(estado);

        if (!cedula.matches("\\d{10}")) {
            return "La cédula debe ser numérica y de 10 caracteres";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe ser numérica y de 10 caracteres";
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido";
        }

        if (clienteDAO.existeClientePorCedula(cedula)) {
            return "El cliente ya existe con esa cédula";
        }

        boolean registrado = clienteDAO.registrarCliente(cliente);
        return registrado ? "Cliente registrado correctamente" : "Error al registrar el Cliente";
    }

    public String actualizarCliente(String cedula, String nombres, String apellidos, String telefono, String email, String direccion, int estado, int idCliente) {
        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        cliente.setNombre(nombres);
        cliente.setApellido(apellidos);
        cliente.setTelefono(telefono);
        cliente.setCorreo(email);
        cliente.setDireccion(direccion);
        cliente.setEstado(estado);

        if (!cedula.matches("\\d{10}")) {
            return "La cédula debe ser numérica y de 10 caracteres";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe ser numérica y de 10 caracteres";
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido";
        }

        boolean actualizado = clienteDAO.actualizarCliente(cliente, idCliente);
        return actualizado ? "Cliente actualizado correctamente" : "Error al actualizar el Cliente";
    }

    public String desactivarCliente(int idCliente) {
        Cliente cli = clienteDAO.obtenerClientePorId(idCliente);
        if (cli == null) {
            return "El cliente no existe";
        }

        if (cli.getEstado() == 0) {
            return "El cliente ya está desactivado";
        }

        boolean resultado = clienteDAO.cambiarEstado(idCliente, 0);
        return resultado ? "Cliente desactivado correctamente" : "Error al desactivar el cliente";
    }

    public String activarCliente(int idCliente) {
        Cliente cli = clienteDAO.obtenerClientePorId(idCliente);
        if (cli == null) {
            return "El cliente no existe";
        }

        if (cli.getEstado() == 1) {
            return "El cliente ya está activo";
        }

        boolean resultado = clienteDAO.cambiarEstado(idCliente, 1);
        return resultado ? "Cliente activado correctamente" : "Error al activar el cliente";
    }

    public boolean existenClientesEnSistema() {
        return clienteDAO.verificarExistenciaClientes();
    }

    public boolean existeClientePorCedula(String cedula) {
        return clienteDAO.existeClientePorCedula(cedula);
    }

    //Este metodo se usa en el módulo de ventas, no eliminar
    public void obtenerClientePorCedula(String cedula, JPanelVentaNuevo vista) {
        if (cedula == null || cedula.isEmpty()) {
            vista.mostrarMensaje("Ingrese una cédula o RUC para buscar");
            return;
        }

        if (!cedula.matches("\\d{10}")) {
            vista.mostrarMensaje("La cédula debe tener exactamente 10 caracteres numéricos");
            return;
        }

        Cliente cliente = clienteDAO.buscarPorCedula(cedula);

        if (cliente != null) {
            vista.mostrarCliente(cliente);
            vista.mostrarMensaje("Cliente encontrado. Datos autocompletados");
        } else {
            vista.clienteNoEncontrado();
        }
    }

    public Cliente obtenerClientePorId(int idCliente) {
        return clienteDAO.obtenerClientePorId(idCliente);
    }

    public List<Cliente> buscarClientes(String criterio) {
        return clienteDAO.buscarClientesPorCriterio(criterio);
    }

    public int obtenerUltimoIdInsertado() {
        return clienteDAO.obtenerUltimoIdInsertado();
    }

}
