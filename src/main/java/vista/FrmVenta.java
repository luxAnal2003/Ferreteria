package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmVenta extends JFrame {

    public JTextField txtIdVenta, txtCliente, txtSubtotal;
    public JButton btnNueva, btnGuardar, btnEditar, btnEliminar;
    public JTable tablaVentas;
    public DefaultTableModel modeloTabla;

    public FrmVenta() {
        setTitle("Gestión de Ventas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("ID Venta:"));
        txtIdVenta = new JTextField();
        panelForm.add(txtIdVenta);

        panelForm.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        panelForm.add(txtCliente);

        panelForm.add(new JLabel("Subtotal:"));
        txtSubtotal = new JTextField();
        panelForm.add(txtSubtotal);

        add(panelForm, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID Venta", "Cliente", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // desactivar edición en tabla
            }
        };
        tablaVentas = new JTable(modeloTabla);
        tablaVentas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();

        btnNueva = new JButton("Nueva");
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnNueva);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FrmVenta();
    }
}
