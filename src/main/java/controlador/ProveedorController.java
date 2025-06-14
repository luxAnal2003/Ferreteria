///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controlador;
//
//import Services.ProveedorService;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
//import modelo.Proveedor;
//import vista.FrmProveedor;
//
//
//
///**
// *
// * @author bdfz
// */
//public class ProveedorController {
//    
//    private FrmProveedor vista;
//    private ProveedorService servicio;
//    
//    public ProveedorController(FrmProveedor vista){
//        this.vista=vista;
//        this.servicio=new ProveedorService();
//        
//        cargarProveedores();
//        
//        vista.btnGuardar.addActionListener(e -> guardar());
//        vista.btnNuevo.addActionListener(e -> limpiarCampos());
//        vista.btnEliminar.addActionListener(e -> eliminar());
//
//        vista.tablaProveedores.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                cargarSeleccionado();
//            }
//        });
//        
//        
//    }
//    
//    private void cargarProveedores() {
//        DefaultTableModel modelo = (DefaultTableModel) vista.modeloTabla;
//        modelo.setRowCount(0);
//        for (Proveedor prove : servicio.obtenerTodos()) {
//            modelo.addRow(new Object[]{prove.getIdUsuario(), prove.getCedula(), prove.getRazonSocial(),
//                prove.getNombre(),prove.getTelefono(),prove.getDireccion(),prove.getCorreo(),
//                prove.getTipoProducto()});
//        }
//    }
//    
//    private void guardar(){
//        String cedula = vista.txtCedula.getText().trim();
//        String razonSocial = vista.txtRazonSocial.getText().trim();
//        String nombre = vista.txtNombreContacto.getText().trim();
//        String telefono = vista.txtTelefono.getText().trim();
//        String direccion = vista.txtDireccion.getText().trim();
//        String correo = vista.txtEmail.getText().trim();
//        String tipoProducto = vista.txtTipoProducto.getText().trim();
//        
//        if (cedula.isEmpty() || razonSocial.isEmpty()|| nombre.isEmpty()|| telefono.isEmpty()
//                || direccion.isEmpty()|| correo.isEmpty()|| tipoProducto.isEmpty()) {
//            JOptionPane.showMessageDialog(vista, "Completa todos los campos");
//            return;
//        }
//        
//        String idTexto = vista.txtId.getText().trim();
//        if (!idTexto.isEmpty()) {
//            int id = Integer.parseInt(idTexto);
//            Proveedor existente = servicio.buscarPorId(id);
//            if (existente != null) {
//                existente.setCedula(cedula);
//                existente.setRazonSocial(razonSocial);
//                existente.setNombre(nombre);
//                existente.setTelefono(telefono);
//                existente.setDireccion(direccion);
//                existente.setCorreo(correo);
//                existente.setTipoProducto(tipoProducto);
//                servicio.actualizar(existente);
//                JOptionPane.showMessageDialog(vista, "Proveedor actualizado");
//            }
//        } else {
//            Proveedor nuevo = new Proveedor(0, cedula, razonSocial, nombre, telefono, direccion, correo, tipoProducto); 
//            servicio.agregar(nuevo);
//            JOptionPane.showMessageDialog(vista, "Proveedor agregado");
//        }
//
//        cargarProveedores();
//        limpiarCampos();
//    }
//    
//    private void eliminar() {
//        int fila = vista.tablaProveedores.getSelectedRow();
//        if (fila >= 0) {
//            int id = Integer.parseInt(vista.modeloTabla.getValueAt(fila, 0).toString());
//            servicio.eliminar(id);
//            cargarProveedores();
//            limpiarCampos();
//            JOptionPane.showMessageDialog(vista, "Proveedor eliminado");
//        } else {
//            JOptionPane.showMessageDialog(vista, "Seleccione un proveedor");
//        }
//    }
//
//    private void limpiarCampos() {
//        vista.txtId.setText("");
//        vista.txtCedula.setText("");
//        vista.txtRazonSocial.setText("");
//        vista.txtNombreContacto.setText("");
//        vista.txtTelefono.setText("");
//        vista.txtDireccion.setText("");
//        vista.txtEmail.setText("");
//        vista.txtTipoProducto.setText("");
//        vista.tablaProveedores.clearSelection();
//    }
//
//    private void cargarSeleccionado() {
//        int fila = vista.tablaProveedores.getSelectedRow();
//        if (fila >= 0) {
//            vista.txtId.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
//            vista.txtCedula.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
//            vista.txtRazonSocial.setText(vista.modeloTabla.getValueAt(fila, 2).toString());
//            vista.txtNombreContacto.setText(vista.modeloTabla.getValueAt(fila, 3).toString());
//            vista.txtTelefono.setText(vista.modeloTabla.getValueAt(fila, 4).toString());
//            vista.txtDireccion.setText(vista.modeloTabla.getValueAt(fila, 5).toString());
//            vista.txtEmail.setText(vista.modeloTabla.getValueAt(fila, 6).toString());
//            vista.txtTipoProducto.setText(vista.modeloTabla.getValueAt(fila, 7).toString());
//        }
//    }
//    
//}
