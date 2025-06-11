/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author admin
 */
import Services.ProductoService;
import modelo.Producto;
import vista.FrmProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class ProductoController {

    private FrmProducto vista;
    private ProductoService servicio;

    public ProductoController(FrmProducto vista) {
        this.vista = vista;
        this.servicio = new ProductoService();

        cargarProductos();

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnNuevo.addActionListener(e -> limpiarCampos());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tablaProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarSeleccionado();
            }
        });
    }

    private void cargarProductos() {
        DefaultTableModel modelo = (DefaultTableModel) vista.modeloTabla;
        modelo.setRowCount(0);
        for (Producto p : servicio.obtenerTodos()) {
            String nombreProveedor = "";
            for (Map.Entry<String, Integer> entry : vista.proveedorMap.entrySet()) {
                if (entry.getValue() == p.getIdProveedor()) {
                    nombreProveedor = entry.getKey();
                    break;
                }
            }
            modelo.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecioUnitario(), p.getStock(), nombreProveedor
            });
        }
    }

    private void guardar() {
        try {
            String nombre = vista.txtNombre.getText().trim();
            String descripcion = vista.txtDescripcion.getText().trim();
            String precioTexto = vista.txtPrecio.getText().trim();
            String stockTexto = vista.txtStock.getText().trim();
            String proveedorNombre = (String) vista.comboProveedor.getSelectedItem();

            // VALIDACIÓN: Verificar campos vacíos
            if (nombre.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty() || proveedorNombre == null) {
                JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.");
                return;
            }

            double precio = Double.parseDouble(precioTexto);
            int stock = Integer.parseInt(stockTexto);
            int proveedorId = vista.proveedorMap.get(proveedorNombre);

            String idTexto = vista.txtId.getText().trim();
            if (!idTexto.isEmpty()) {
                int id = Integer.parseInt(idTexto);
                Producto existente = servicio.buscarPorId(id);
                if (existente != null) {
                    existente.setNombre(nombre);
                    existente.setDescripcion(descripcion);
                    existente.setPrecioUnitario(precio);
                    existente.setStock(stock);
                    existente.setIdProveedor(proveedorId);
                    servicio.actualizar(existente);
                    JOptionPane.showMessageDialog(vista, "Producto actualizado");
                }
            } else {
                Producto nuevo = new Producto(0, nombre, descripcion, precio, stock, proveedorId);
                servicio.agregar(nuevo);
                JOptionPane.showMessageDialog(vista, "Producto agregado");
            }

            cargarProductos();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio y stock deben ser valores numéricos válidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar producto: " + ex.getMessage());
        }
    }

    private void eliminar() {
        int fila = vista.tablaProductos.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
            servicio.eliminar(id);
            cargarProductos();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Producto eliminado");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto");
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.comboProveedor.setSelectedIndex(0);
        vista.tablaProductos.clearSelection();
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaProductos.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
            Producto producto = servicio.buscarPorId(id);
            if (producto != null) {
                vista.txtId.setText(String.valueOf(producto.getId()));
                vista.txtNombre.setText(producto.getNombre());
                vista.txtDescripcion.setText(producto.getDescripcion());
                vista.txtPrecio.setText(String.valueOf(producto.getPrecioUnitario()));
                vista.txtStock.setText(String.valueOf(producto.getStock()));

                for (int i = 0; i < vista.comboProveedor.getItemCount(); i++) {
                    String nombre = vista.comboProveedor.getItemAt(i);
                    if (vista.proveedorMap.get(nombre) == producto.getIdProveedor()) {
                        vista.comboProveedor.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }
}
