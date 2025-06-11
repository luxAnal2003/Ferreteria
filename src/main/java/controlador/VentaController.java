package controlador;

import Services.EmpleadoService;
import Services.VentaService;
import modelo.Empleado;
import modelo.Venta;
import vista.FrmEmpleado;
import vista.FrmVenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentaController {

    private FrmVenta vista;
    private VentaService servicio;

    public VentaController(FrmVenta vista) {
        this.vista = vista;
        this.servicio = new VentaService();

        cargarVentas();

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnNueva.addActionListener(e -> limpiarCampos());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tablaVentas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarSeleccionado();
            }
        });
    }

    private void cargarVentas() {
        DefaultTableModel modelo = (DefaultTableModel) vista.modeloTabla;
        modelo.setRowCount(0); // limpiar
        for (Venta v : servicio.obtenerTodos()) {
            modelo.addRow(new Object[]{v.getIdVenta(), v.getCliente(), v.getTotal()});
        }
    }

    private void guardar() {
        try {
            int id = Integer.parseInt(vista.txtIdVenta.getText().trim());
            String idCliente = vista.txtCliente.getText().trim();
            String subtotal = vista.txtSubtotal.getText().trim();

            Venta existente = servicio.buscarPorId(id);
            if (existente != null) {
                existente.setCliente(idCliente);
                existente.setTotal(Double.parseDouble(subtotal));
                servicio.actualizar(existente);
                JOptionPane.showMessageDialog(vista, "Venta actualizada");
            } else {
                servicio.agregar(new Venta(id, idCliente, subtotal));
                JOptionPane.showMessageDialog(vista, "Venta agregada");
            }

            cargarVentas();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID inválido");
        }
    }

    private void eliminar() {
        int fila = vista.tablaVentas.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
            servicio.eliminar(id);
            cargarVentas();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Venta eliminada");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una venta");
        }
    }

    private void limpiarCampos() {
        vista.txtIdVenta.setText("");
        vista.txtCliente.setText("");
        vista.txtSubtotal.setText("");
        vista.tablaVentas.clearSelection();
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaVentas.getSelectedRow();
        if (fila >= 0) {
            vista.txtIdVenta.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
            vista.txtCliente.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
            vista.txtSubtotal.setText(vista.modeloTabla.getValueAt(fila, 2).toString());
        }
    }
}
