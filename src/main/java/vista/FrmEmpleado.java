//package vista;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class FrmEmpleado extends JFrame {
//
//    public JTextField txtId, txtNombre, txtCargo;
//    public JButton btnNuevo, btnGuardar, btnEditar, btnEliminar;
//    public JTable tablaEmpleados;
//    public DefaultTableModel modeloTabla;
//
//    public FrmEmpleado() {
//        setTitle("Gestión de Empleados");
//        setSize(600, 400);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
//        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        panelForm.add(new JLabel("ID:"));
//        txtId = new JTextField();
//        txtId.setEditable(false);
//        txtId.setEnabled(false);
//        panelForm.add(txtId);
//
//        panelForm.add(new JLabel("Nombre:"));
//        txtNombre = new JTextField();
//        panelForm.add(txtNombre);
//
//        panelForm.add(new JLabel("Cargo:"));
//        txtCargo = new JTextField();
//        panelForm.add(txtCargo);
//
//        add(panelForm, BorderLayout.NORTH);
//
//        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Cargo"}, 0);
//        tablaEmpleados = new JTable(modeloTabla);
//        add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);
//
//        JPanel panelBotones = new JPanel();
//
//        btnNuevo = new JButton("Nuevo");
//        btnGuardar = new JButton("Guardar");
//        btnEditar = new JButton("Editar");
//        btnEliminar = new JButton("Eliminar");
//
//        panelBotones.add(btnNuevo);
//        panelBotones.add(btnGuardar);
//        panelBotones.add(btnEditar);
//        panelBotones.add(btnEliminar);
//
//        add(panelBotones, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new FrmEmpleado();
//    }
//}
