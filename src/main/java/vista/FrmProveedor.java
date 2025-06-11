package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmProveedor extends JFrame {

    public JTextField txtId, txtCedula, txtRazonSocial,txtNombreContacto, 
            txtTelefono, txtDireccion, txtEmail, txtTipoProducto;
    public JButton btnNuevo, btnGuardar, btnEliminar;
    public JTable tablaProveedores;
    public DefaultTableModel modeloTabla;

    public FrmProveedor() {
        setTitle("Gestión de Proveedor");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setEnabled(false);
        panelForm.add(txtId);

        panelForm.add(new JLabel("Cedula:"));
        txtCedula = new JTextField();
        panelForm.add(txtCedula);

        panelForm.add(new JLabel("Razón Social:"));
        txtRazonSocial = new JTextField();
        panelForm.add(txtRazonSocial);
        
        panelForm.add(new JLabel("Nombre del Contacto:"));
        txtNombreContacto = new JTextField();
        panelForm.add(txtNombreContacto);
        
        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);
        
        panelForm.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelForm.add(txtDireccion);
        
        panelForm.add(new JLabel("Correo electrónico:"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);
        
        panelForm.add(new JLabel("Tipo de Producto:"));
        txtTipoProducto = new JTextField();
        panelForm.add(txtTipoProducto);

        add(panelForm, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Cedula", "Razón Social", "Nombre del Contacto", "Teléfono", "Dirección", "Correo electrónico", "Tipo de Producto"}, 0);
        tablaProveedores = new JTable(modeloTabla);
        add(new JScrollPane(tablaProveedores), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FrmProveedor();
    }
}
