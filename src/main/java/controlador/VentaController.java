package controlador;

import Services.VentaService;
import modelo.Venta;
import vista.FrmVenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Map;

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
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Venta v : servicio.obtenerTodas()) {
            String nombreCliente = "";
            String nombreProducto = "";
            String nombreEmpleado = "";

            for (Map.Entry<String, Integer> entry : vista.clienteMap.entrySet()) {
                if (entry.getValue() == v.getIdCliente()) {
                    nombreCliente = entry.getKey();
                    break;
                }
            }
            for (Map.Entry<String, Integer> entry : vista.productoMap.entrySet()) {
                if (entry.getValue() == v.getIdProducto()) {
                    nombreProducto = entry.getKey();
                    break;
                }
            }
            for (Map.Entry<String, Integer> entry : vista.empleadoMap.entrySet()) {
                if (entry.getValue() == v.getIdEmpleado()) {
                    nombreEmpleado = entry.getKey();
                    break;
                }
            }

            modelo.addRow(new Object[]{
                v.getIdVenta(),
                nombreCliente,
                nombreProducto,
                nombreEmpleado,
                sdf.format(v.getFecha()),
                v.getSubtotal()
            });
        }
    }

    private void guardar() {
        try {
            String nombreCliente = (String) vista.comboCliente.getSelectedItem();
            String nombreProducto = (String) vista.comboProducto.getSelectedItem();
            String nombreEmpleado = (String) vista.comboEmpleado.getSelectedItem();

            int idCliente = vista.clienteMap.get(nombreCliente);
            int idProducto = vista.productoMap.get(nombreProducto);
            int idEmpleado = vista.empleadoMap.get(nombreEmpleado);

            double subtotal = Double.parseDouble(vista.txtSubtotal.getText().trim());

            String idTexto = vista.txtIdVenta.getText().trim();
            if (!idTexto.isEmpty()) {
                int id = Integer.parseInt(idTexto);
                Venta existente = servicio.buscarPorId(id);
                if (existente != null) {
                    existente.setIdCliente(idCliente);
                    existente.setIdProducto(idProducto);
                    existente.setIdEmpleado(idEmpleado);
                    existente.setSubtotal(subtotal);
                    servicio.actualizar(existente);
                    JOptionPane.showMessageDialog(vista, "Venta actualizada");
                }
            } else {
                Venta nueva = new Venta();
                nueva.setIdCliente(idCliente);
                nueva.setIdProducto(idProducto);
                nueva.setIdEmpleado(idEmpleado);
                nueva.setSubtotal(subtotal);
                nueva.setFecha(new java.util.Date());
                servicio.agregar(nueva);
                JOptionPane.showMessageDialog(vista, "Venta agregada");
            }

            cargarVentas();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Datos inválidos");
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
        // vista.txtCliente.setText(""); 
        vista.comboCliente.setSelectedIndex(0);
        vista.txtSubtotal.setText("");
        vista.tablaVentas.clearSelection();
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaVentas.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
            Venta venta = servicio.buscarPorId(id);
            if (venta != null) {
                vista.txtIdVenta.setText(String.valueOf(venta.getIdVenta()));

                for (int i = 0; i < vista.comboCliente.getItemCount(); i++) {
                    String nombre = vista.comboCliente.getItemAt(i);
                    if (vista.clienteMap.get(nombre) == venta.getIdCliente()) {
                        vista.comboCliente.setSelectedIndex(i);
                        break;
                    }
                }

                vista.txtSubtotal.setText(String.valueOf(venta.getSubtotal()));
            }
        }
    }
}
