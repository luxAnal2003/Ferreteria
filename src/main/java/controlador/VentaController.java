//package controlador;
//
//import dao.VentaDao;
//import modelo.Venta;
//import vista.FrmVenta;
//
//public class VentaController {
//    private VentaDao ventaDAO;
//    private FrmVenta vista;
//
//    public VentaController(FrmVenta vista) {
//        this.vista = vista;
//    }
//    
//    public VentaController() {
//        this.ventaDAO = new VentaDao();
//    }
//
//    public int registrarVenta(Venta venta) throws Exception {
//        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
//            throw new Exception("No hay productos en la venta.");
//        }
//        return ventaDAO.registrarVenta(venta);
//    }
//
//    public boolean anularVenta(int idVenta, String motivo) {
//        return ventaDAO.anularVenta(idVenta, motivo);
//    }
//
//    public Venta consultarVenta(int idVenta) {
//        return ventaDAO.consultarVentaPorId(idVenta);
//    }
//}
