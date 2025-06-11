package controlador;

import modelo.Cliente;
import vista.FrmCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlCliente {
//
//    private FrmCliente vista;
//    private ClienteService servicio;
//
//    public ClienteController(FrmCliente vista) {
//        this.vista = vista;
//        this.servicio = new ClienteService();
//
//        cargarClientes();
//
//        vista.btnGuardar.addActionListener(e -> guardar());
//        vista.btnNuevo.addActionListener(e -> limpiarCampos());
//        vista.btnEliminar.addActionListener(e -> eliminar());
//
//        vista.tablaClientes.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                cargarSeleccionado();
//            }
//        });
//    }
//
//    private void cargarClientes() {
//        DefaultTableModel modelo = (DefaultTableModel) vista.modeloTabla;
//        modelo.setRowCount(0);
//        for (Cliente c : servicio.obtenerTodos()) {
//            if (c.getEstado() == 1) {
//                modelo.addRow(new Object[]{
//                    c.getId(),
//                    c.getCedula(),
//                    c.getNombre(),
//                    c.getDireccion(),
//                    c.getCorreo(),
//                    c.getRol()
//                });
//            }
//        }
//    }
//
//    private void guardar() {
//        String cedula = vista.txtCedula.getText().trim();
//        String nombre = vista.txtNombre.getText().trim();
//        String direccion = vista.txtDireccion.getText().trim();
//        String correo = vista.txtCorreo.getText().trim();
//
//        if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
//            JOptionPane.showMessageDialog(vista, "Completa todos los campos");
//            return;
//        }
//
//        try {
//            String idTexto = vista.txtId.getText().trim();
//
//            if (!idTexto.isEmpty()) {
//                int id = Integer.parseInt(idTexto);
//                Cliente existente = servicio.buscarPorId(id);
//                if (existente != null) {
//                    existente.setCedula(cedula);
//                    existente.setNombre(nombre);
//                    existente.setDireccion(direccion);
//                    existente.setCorreo(correo);
//                    servicio.actualizar(existente);
//                    JOptionPane.showMessageDialog(vista, "Cliente actualizado");
//                }
//            } else {
//                Cliente nuevo = new Cliente(0, cedula, nombre, direccion, correo, "administrador", 1);
//                servicio.agregar(nuevo);
//                JOptionPane.showMessageDialog(vista, "Cliente agregado");
//            }
//
//            cargarClientes();
//            limpiarCampos();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage());
//        }
//    }
//
//    private void eliminar() {
//        int fila = vista.tablaClientes.getSelectedRow();
//        if (fila >= 0) {
//            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
//            Cliente cliente = servicio.buscarPorId(id);
//            if (cliente != null) {
//                cliente.setEstado(0); // Eliminación lógica
//                servicio.actualizar(cliente);
//                cargarClientes();
//                limpiarCampos();
//                JOptionPane.showMessageDialog(vista, "Cliente eliminado (lógico)");
//            }
//        } else {
//            JOptionPane.showMessageDialog(vista, "Seleccione un cliente");
//        }
//    }
//
//    private void limpiarCampos() {
//        vista.txtId.setText("");
//        vista.txtCedula.setText("");
//        vista.txtNombre.setText("");
//        vista.txtDireccion.setText("");
//        vista.txtCorreo.setText("");
//        vista.tablaClientes.clearSelection();
//    }
//
//    private void cargarSeleccionado() {
//        int fila = vista.tablaClientes.getSelectedRow();
//        if (fila >= 0) {
//            vista.txtId.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
//            vista.txtCedula.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
//            vista.txtNombre.setText(vista.modeloTabla.getValueAt(fila, 2).toString());
//            vista.txtDireccion.setText(vista.modeloTabla.getValueAt(fila, 3).toString());
//            vista.txtCorreo.setText(vista.modeloTabla.getValueAt(fila, 4).toString());
//        }
//    }
}
