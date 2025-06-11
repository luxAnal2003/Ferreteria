package controlador;

import Services.EmpleadoService;
import modelo.Empleado;
import vista.FrmEmpleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {

    private FrmEmpleado vista;
    private EmpleadoService servicio;

    public EmpleadoController(FrmEmpleado vista) {
        this.vista = vista;
        this.servicio = new EmpleadoService();

        cargarEmpleados();

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnNuevo.addActionListener(e -> limpiarCampos());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tablaEmpleados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarSeleccionado();
            }
        });
    }

    private void cargarEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) vista.modeloTabla;
        modelo.setRowCount(0); // limpiar
        for (Empleado e : servicio.obtenerTodos()) {
            modelo.addRow(new Object[]{e.getId(), e.getNombre(), e.getCargo()});
        }
    }

    private void guardar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText().trim());
            String nombre = vista.txtNombre.getText().trim();
            String cargo = vista.txtCargo.getText().trim();

            Empleado existente = servicio.buscarPorId(id);
            if (existente != null) {
                existente.setNombre(nombre);
                existente.setCargo(cargo);
                servicio.actualizar(existente);
                JOptionPane.showMessageDialog(vista, "Empleado actualizado");
            } else {
                servicio.agregar(new Empleado(id, nombre, cargo));
                JOptionPane.showMessageDialog(vista, "Empleado agregado");
            }

            cargarEmpleados();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID inválido");
        }
    }

    private void eliminar() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
            servicio.eliminar(id);
            cargarEmpleados();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Empleado eliminado");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un empleado");
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtCargo.setText("");
        vista.tablaEmpleados.clearSelection();
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila >= 0) {
            vista.txtId.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
            vista.txtNombre.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
            vista.txtCargo.setText(vista.modeloTabla.getValueAt(fila, 2).toString());
        }
    }
}
