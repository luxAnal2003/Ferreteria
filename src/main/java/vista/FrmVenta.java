//package vista;
//
//import controlador.VentaController;
//import modelo.Cliente;
//import modelo.Producto;
//import modelo.DetalleVenta;
//import modelo.Venta;
//import dao.ClienteDAO;
//import dao.ProductoDAO;
//import dao.VentaDao;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.*;
//
//public class FrmVenta extends JFrame {
//
//    private JCheckBox chkFactura;
//    private JTextField txtCedula, txtNombre, txtDireccion, txtTelefono, txtEmail;
//    private JButton btnBuscar, btnAgregar, btnEliminar, btnConfirmar, btnNuevo;
//    private JComboBox<String> cmbProductos;
//    private JSpinner spnCantidad;
//    private JTable tblDetalles;
//    private DefaultTableModel modeloTabla;
//    private JLabel lblMensaje;
//
//    private Cliente clienteActual;
//    private List<DetalleVenta> detalles = new ArrayList<>();
//    private Map<String, Producto> mapaProductos;
//
//    public FrmVenta() {
//        super("Registrar Venta");
//        setSize(800, 600);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(null);
//
//        chkFactura = new JCheckBox("¿Generar factura con datos del cliente?");
//        chkFactura.setBounds(30, 20, 300, 25);
//        add(chkFactura);
//
//        txtCedula = new JTextField();
//        txtCedula.setBounds(30, 50, 150, 25);
//        txtCedula.setEnabled(false);
//        add(txtCedula);
//
//        btnBuscar = new JButton("Buscar");
//        btnBuscar.setBounds(190, 50, 90, 25);
//        btnBuscar.setEnabled(false);
//        add(btnBuscar);
//
//        txtNombre = new JTextField();
//        txtDireccion = new JTextField();
//        txtTelefono = new JTextField();
//        txtEmail = new JTextField();
//        txtNombre.setBounds(30, 90, 150, 25);
//        txtDireccion.setBounds(190, 90, 150, 25);
//        txtTelefono.setBounds(30, 120, 150, 25);
//        txtEmail.setBounds(190, 120, 150, 25);
//
//        add(txtNombre);
//        add(txtDireccion);
//        add(txtTelefono);
//        add(txtEmail);
//
//        // Deshabilitar por defecto
//        Arrays.asList(txtNombre, txtDireccion, txtTelefono, txtEmail).forEach(f -> f.setEnabled(false));
//
//        cmbProductos = new JComboBox<>();
//        cmbProductos.setBounds(30, 160, 150, 25);
//        add(cmbProductos);
//
//        spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
//        spnCantidad.setBounds(190, 160, 70, 25);
//        add(spnCantidad);
//
//        btnAgregar = new JButton("Agregar");
//        btnAgregar.setBounds(270, 160, 100, 25);
//        add(btnAgregar);
//
//        modeloTabla = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Precio", "Subtotal"}, 0);
//        tblDetalles = new JTable(modeloTabla);
//        JScrollPane scroll = new JScrollPane(tblDetalles);
//        scroll.setBounds(30, 200, 700, 150);
//        add(scroll);
//
//        btnEliminar = new JButton("Eliminar");
//        btnEliminar.setBounds(30, 360, 100, 25);
//        add(btnEliminar);
//
//        btnNuevo = new JButton("Nuevo");
//        btnNuevo.setBounds(140, 360, 100, 25);
//        add(btnNuevo);
//
//        btnConfirmar = new JButton("Confirmar Venta");
//        btnConfirmar.setBounds(250, 360, 160, 25);
//        btnConfirmar.setEnabled(false);
//        add(btnConfirmar);
//
//        lblMensaje = new JLabel();
//        lblMensaje.setForeground(Color.RED);
//        lblMensaje.setBounds(30, 400, 400, 25);
//        add(lblMensaje);
//
//        cargarProductos();
//        registrarEventos();
//    }
//
//    private void cargarProductos() {
//        mapaProductos = new HashMap<>();
//        for (Producto p : new ProductoDAO().listar()) {
//            cmbProductos.addItem(p.getNombre());
//            mapaProductos.put(p.getNombre(), p);
//        }
//    }
//
//    private void registrarEventos() {
//        chkFactura.addActionListener(e -> {
//            boolean conDatos = chkFactura.isSelected();
//            txtCedula.setEnabled(conDatos);
//            btnBuscar.setEnabled(conDatos);
//            Arrays.asList(txtNombre, txtDireccion, txtTelefono, txtEmail).forEach(f -> f.setEnabled(false));
//        });
//
//        btnBuscar.addActionListener(e -> {
//            String cedula = txtCedula.getText().trim();
//            Cliente c = new ClienteDAO().buscarPorCedula(cedula);
//            if (c != null) {
//                clienteActual = c;
//                txtNombre.setText(c.getNombre());
//                txtDireccion.setText(c.getDireccion());
//                txtTelefono.setText(c.getTelefono());
//                txtEmail.setText(c.getCorreo());
//                Arrays.asList(txtNombre, txtDireccion, txtTelefono, txtEmail).forEach(f -> f.setEnabled(false));
//            } else {
//                clienteActual = null;
//                Arrays.asList(txtNombre, txtDireccion, txtTelefono, txtEmail).forEach(f -> {
//                    f.setText("");
//                    f.setEnabled(true);
//                });
//            }
//        });
//
//        btnAgregar.addActionListener(e -> {
//            String nombre = (String) cmbProductos.getSelectedItem();
//            Producto p = mapaProductos.get(nombre);
//            int cantidad = (int) spnCantidad.getValue();
//
//            if (cantidad > p.getStock()) {
//                lblMensaje.setText("Stock insuficiente para el producto seleccionado");
//                return;
//            }
//
//            double subtotal = cantidad * p.getPrecioUnitario();
//            modeloTabla.addRow(new Object[]{p.getNombre(), cantidad, p.getPrecioUnitario(), subtotal});
//
//            DetalleVenta dv = new DetalleVenta();
//            dv.setProducto(p);
//            dv.setCantidad(cantidad);
//            dv.setPrecioUnitario(p.getPrecioUnitario());
//            dv.setSubtotal(subtotal);
//            detalles.add(dv);
//
//            lblMensaje.setText("");
//            btnConfirmar.setEnabled(true);
//        });
//
//        btnEliminar.addActionListener(e -> {
//            int row = tblDetalles.getSelectedRow();
//            if (row >= 0) {
//                detalles.remove(row);
//                modeloTabla.removeRow(row);
//                if (detalles.isEmpty()) {
//                    btnConfirmar.setEnabled(false);
//                }
//            }
//        });
//
//        btnNuevo.addActionListener(e -> limpiarFormulario());
//
//        btnConfirmar.addActionListener(e -> {
//            try {
//                Venta v = new Venta();
//                v.setClienteRegistrado(chkFactura.isSelected());
//
//                if (chkFactura.isSelected()) {
//                    if (clienteActual == null) {
//                        if (Arrays.asList(txtNombre, txtDireccion, txtTelefono, txtEmail).stream().anyMatch(f -> f.getText().trim().isEmpty())) {
//                            lblMensaje.setText("Falta ingresar datos del cliente");
//                            return;
//                        }
//                        clienteActual = new Cliente(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText());
//                        new ClienteDAO().registrar(clienteActual);
//                    }
//                    v.setCedula(clienteActual.getCedula());
//                } else {
//                    v.setClienteRegistrado(false);
//                    v.setCedula("0000000000");
//                    clienteActual = new Cliente("0000000000", "ferreteriaUser", "desconocido", "000000000", "ferreteria@local.com");
//                }
//
//                v.setCliente(clienteActual);
//                v.setFechaVenta(java.time.LocalDateTime.now());
//                v.setIdEmpleado(1);
//
//                double subtotal = detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
//                double iva = subtotal * 0.12;
//                double total = subtotal + iva;
//
//                v.setSubtotal(subtotal);
//                v.setIva(iva);
//                v.setTotal(total);
//                v.setDescuento(0.0);
//                v.setEstado("ACTIVA");
//                v.setDetalles(new ArrayList<>(detalles));
//
//                VentaController controlador = new VentaController();
//                controlador.registrarVenta(v);
//
//                lblMensaje.setForeground(Color.GREEN);
//                lblMensaje.setText("Venta agregada correctamente");
//                btnConfirmar.setEnabled(false);
//            } catch (Exception ex) {
//                lblMensaje.setForeground(Color.RED);
//                lblMensaje.setText("Error: " + ex.getMessage());
//            }
//        });
//
//    }
//
//    private void limpiarFormulario() {
//        txtCedula.setText("");
//        txtNombre.setText("");
//        txtDireccion.setText("");
//        txtTelefono.setText("");
//        txtEmail.setText("");
//        modeloTabla.setRowCount(0);
//        detalles.clear();
//        lblMensaje.setText("");
//        btnConfirmar.setEnabled(false);
//        clienteActual = null;
//    }
//
//    public static void main(String[] args) {
//        new FrmVenta().setVisible(true);
//    }
//}
