package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrmVenta extends JFrame {
    public JTextField txtIdVenta;
    public JTextField txtSubtotal;
    public JButton btnGuardar;
    public JButton btnNueva;
    public JButton btnEliminar;
    public JTable tablaVentas;
    public DefaultTableModel modeloTabla;

    public JComboBox<String> comboCliente;
    public Map<String, Integer> clienteMap = new LinkedHashMap<>();

    public JComboBox<String> comboProducto;
    public Map<String, Integer> productoMap = new LinkedHashMap<>();

    public JComboBox<String> comboEmpleado;
    public Map<String, Integer> empleadoMap = new LinkedHashMap<>();

    public FrmVenta() {
        setTitle("Gestión de Ventas");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtIdVenta = new JTextField();
        txtIdVenta.setEditable(false);
        txtIdVenta.setEnabled(false);

        txtSubtotal = new JTextField();

        // Combos
        comboCliente = new JComboBox<>();
        comboProducto = new JComboBox<>();
        comboEmpleado = new JComboBox<>();

        // Datos quemados
        clienteMap.put("Susy Bareiro Montalvan", 1);
        clienteMap.put("Eduardo Castillo Lomas", 2);
        clienteMap.put("Cesar Pauta Quila", 3);
        for (String nombre : clienteMap.keySet()) {
            comboCliente.addItem(nombre);
        }

        productoMap.put("Taladro Bosch", 101);
        productoMap.put("Martillo Stanley", 102);
        productoMap.put("Sierra Makita", 103);
        for (String nombre : productoMap.keySet()) {
            comboProducto.addItem(nombre);
        }

        empleadoMap.put("Carlos Mendoza", 201);
        empleadoMap.put("Lucía Andrade", 202);
        empleadoMap.put("Jorge Rivera", 203);
        for (String nombre : empleadoMap.keySet()) {
            comboEmpleado.addItem(nombre);
        }

        // Agregar componentes
        panelFormulario.add(new JLabel("ID Venta:"));
        panelFormulario.add(txtIdVenta);

        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(comboCliente);

        panelFormulario.add(new JLabel("Producto:"));
        panelFormulario.add(comboProducto);

        panelFormulario.add(new JLabel("Empleado:"));
        panelFormulario.add(comboEmpleado);

        panelFormulario.add(new JLabel("Subtotal:"));
        panelFormulario.add(txtSubtotal);

        btnGuardar = new JButton("Guardar");
        btnNueva = new JButton("Nuevo");
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnNueva);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Cliente", "Producto", "Empleado", "Fecha", "Subtotal"}, 0);
        tablaVentas = new JTable(modeloTabla);
        add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        // Botón eliminar
        btnEliminar = new JButton("Eliminar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmVenta frame = new FrmVenta();
            frame.setVisible(true);
        });
    }
}