package vista;

import controlador.ControlCliente;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ClienteWiew extends JPanel {
    private final ControlCliente controller = new ControlCliente();
    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField txtCedula, txtNombre, txtDireccion, txtCorreo;
    private JButton btnGuardar, btnNuevo, btnEliminar, btnActualizar;
    private int editingId = -1;

    public ClienteWiew() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID","Cédula","Nombre","Dirección","Correo","Rol"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de botones acciones
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnNuevo = new JButton("Nuevo");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        actionPanel.add(btnNuevo);
        actionPanel.add(btnEliminar);
        actionPanel.add(btnActualizar);
        add(actionPanel, BorderLayout.NORTH);

        // Formulario
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        form.setBorder(new EmptyBorder(10,0,0,0));
        form.add(new JLabel("Cédula:"));    txtCedula    = new JTextField(); form.add(txtCedula);
        form.add(new JLabel("Nombre:"));    txtNombre    = new JTextField(); form.add(txtNombre);
        form.add(new JLabel("Dirección:")); txtDireccion = new JTextField(); form.add(txtDireccion);
        form.add(new JLabel("Correo:"));    txtCorreo    = new JTextField(); form.add(txtCorreo);
        form.add(new JLabel());
        btnGuardar = new JButton("Guardar");
        form.add(btnGuardar);
        add(form, BorderLayout.SOUTH);

        // Eventos
        btnNuevo.addActionListener(e -> clearForm());
        btnGuardar.addActionListener(e -> onSave());
        btnActualizar.addActionListener(e -> onLoadForEdit());
        btnEliminar.addActionListener(e -> onDelete());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) onLoadForEdit();
            }
        });

        // Carga inicial de datos con manejo de errores
        SwingUtilities.invokeLater(() -> {
            try {
                loadTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "No se pudo cargar la tabla: " + ex.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<Cliente> lista = controller.listarActivos();
        if (lista != null) {
            for (Cliente c : lista) {
                tableModel.addRow(new Object[]{
                    c.getId(), c.getCedula(), c.getNombre(), c.getDireccion(), c.getCorreo(), c.getRol()
                });
            }
        }
    }

    private void clearForm() {
        editingId = -1;
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        btnGuardar.setText("Guardar");
    }

    private void onSave() {
        String ced = txtCedula.getText().trim();
        String nom = txtNombre.getText().trim();
        String dir = txtDireccion.getText().trim();
        String cor = txtCorreo.getText().trim();
        if (ced.isEmpty() || nom.isEmpty() || dir.isEmpty() || cor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }
        Cliente c = new Cliente("administrador", 1, ced, nom, dir, cor, null, null);
        boolean success;
        if (editingId < 0) {
            success = controller.crear(c);
            if (success) JOptionPane.showMessageDialog(this, "Cliente creado");
        } else {
            c.setId(editingId);
            success = controller.actualizar(c);
            if (success) JOptionPane.showMessageDialog(this, "Cliente actualizado");
        }
        clearForm();
        loadTable();
    }

    private void onLoadForEdit() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        Cliente c = controller.obtenerPorId(id);
        if (c != null) {
            editingId = c.getId();
            txtCedula.setText(c.getCedula());
            txtNombre.setText(c.getNombre());
            txtDireccion.setText(c.getDireccion());
            txtCorreo.setText(c.getCorreo());
            btnGuardar.setText("Actualizar");
        }
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        if (controller.eliminarLogico(id)) {
            JOptionPane.showMessageDialog(this, "Cliente eliminado (lógico)");
            loadTable();
        }
    }
}
