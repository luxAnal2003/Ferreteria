///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package vista;
//
///**
// *
// * @author admin
// */
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class FrmProducto extends JFrame {
//    public JTextField txtId, txtNombre, txtDescripcion, txtPrecio, txtStock;
//    public JComboBox<String> comboProveedor;
//    public Map<String, Integer> proveedorMap = new LinkedHashMap<>();
//    public JButton btnGuardar, btnNuevo, btnEliminar;
//    public JTable tablaProductos;
//    public DefaultTableModel modeloTabla;
//
//    public FrmProducto() {
//        setTitle("Gestión de Productos");
//        setSize(700, 400);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout(10, 10));
//
//        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
//        panelCampos.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//        panelCampos.add(new JLabel("ID:"));
//        txtId = new JTextField();
//        txtId.setEditable(false);
//        txtId.setEnabled(false);
//        panelCampos.add(txtId);
//
//        panelCampos.add(new JLabel("Nombre:"));
//        txtNombre = new JTextField();
//        panelCampos.add(txtNombre);
//
//        panelCampos.add(new JLabel("Descripción:"));
//        txtDescripcion = new JTextField();
//        panelCampos.add(txtDescripcion);
//
//        panelCampos.add(new JLabel("Precio Unitario:"));
//        txtPrecio = new JTextField();
//        panelCampos.add(txtPrecio);
//
//        panelCampos.add(new JLabel("Stock:"));
//        txtStock = new JTextField();
//        panelCampos.add(txtStock);
//
//        panelCampos.add(new JLabel("Proveedor:"));
//        comboProveedor = new JComboBox<>();
//        proveedorMap.put("Maria Garcia Rodolfa", 1);
//        proveedorMap.put("William Cusme Alvarado", 2);
//        proveedorMap.put("Salvador Salvatierra Santos", 3);
//        for (String nombre : proveedorMap.keySet()) {
//            comboProveedor.addItem(nombre);
//        }
//        panelCampos.add(comboProveedor);
//
//        add(panelCampos, BorderLayout.NORTH);
//
//        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Precio", "Stock", "Proveedor"}, 0);
//        tablaProductos = new JTable(modeloTabla);
//        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
//
//        JPanel panelBotones = new JPanel();
//        btnNuevo = new JButton("Nuevo");
//        btnGuardar = new JButton("Guardar");
//        btnEliminar = new JButton("Eliminar");
//        panelBotones.add(btnNuevo);
//        panelBotones.add(btnGuardar);
//        panelBotones.add(btnEliminar);
//        add(panelBotones, BorderLayout.SOUTH);
//    }
//    public static void main(String[] args) {
//        new FrmProducto();
//    }
//}
