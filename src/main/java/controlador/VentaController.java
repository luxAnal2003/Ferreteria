/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import config.Sesion;
import dao.ProductoDAO;
import dao.VentaDAO;
import java.util.List;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.Venta;
import vista.JPanelVentaNuevo;

/**
 *
 * @author admin
 */
public class VentaController {

    private VentaDAO ventaDAO;
    private ProductoDAO productoDAO;
    private EmpleadoController empleadoController;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
        this.productoDAO = new ProductoDAO();
        this.empleadoController = new EmpleadoController();
    }

    public boolean guardarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        VentaDAO dao = new VentaDAO();
        return dao.guardarVentaCompleta(venta, detalles);
    }

    public String desactivarVenta(int idVenta) {
        int estadoActual = ventaDAO.obtenerEstadoVenta(idVenta);
        if (estadoActual == 0) {
            return "Esta venta ya fue anulada previamente";
        }

        boolean cabecera = ventaDAO.cambiarEstado(idVenta, 0);
        boolean detalles = ventaDAO.cambiarEstadoDetallesVenta(idVenta, 0);

        if (cabecera && detalles) {
            return "Venta anulada correctamente. El inventario ha sido actualizado";
        } else {
            return "Ocurrió un error al anular la venta. Intente nuevamente o contacte al administrador";
        }
    }

    //PARA VERIFICAR SI HAY VENTS EN EL SISTEMA
    public boolean existenVentas() {
        return ventaDAO.verificarExistenciaVentas();
    }

    //OBTIENE TODAS LAS VENTAS PARA LAS TABLAS
    public List<Object[]> obtenerVentas() {
        return ventaDAO.llenarTablaVentas();
    }

    //HACE LA CONSULTA DE UNA VENTA EN CRITERIO
    public List<Object[]> buscarVentas(String criterio) {
        return ventaDAO.buscarVentas(criterio);
    }

    //SE OBTIENEN LOS TOTALES AL MOMENTO DE CONSULTAR
    public Object[] obtenerTotalesVenta(int idCabeceraVenta) {
        return ventaDAO.obtenerTotalesVenta(idCabeceraVenta);
    }

    //MUESTRA EL DETALLE DE VENTA AL MOMENTO DE CONSULTAR
    public List<Object[]> obtenerDetalleVenta(int idCabeceraVenta) {
        return ventaDAO.obtenerDetalleVenta(idCabeceraVenta);
    }

    //AGREGA UN PRODUCTO A LA LISTA DE DETALLES
    public void agregarProductoALista(String nombreProducto, JPanelVentaNuevo vista) {
        Producto producto = productoDAO.buscarProductoPorNombre(nombreProducto);

        if (producto == null) {
            vista.mostrarMensaje("Producto no encontrado o inactivo. Por favor consultar en sus productos");
            return;
        }
       
        List<DetalleVenta> listaDetalle = vista.getListaDetalle();

        if (detalleYaExiste(producto, listaDetalle)) {
            vista.mostrarMensaje("El producto ya ha sido agregado");
            return;
        }

        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdProducto(producto.getIdProducto());
        detalle.setCantidad(1);
        detalle.setPrecioUnitario(producto.getPrecio());
        detalle.setSubTotal(producto.getPrecio() * 1);
        detalle.setDescuento(0.05);
        detalle.setIva(12);
        detalle.setTotalPagar(detalle.getSubTotal());

        listaDetalle.add(detalle);

        vista.agregarFilaProductoTabla(
                producto.getIdProducto(),
                producto.getNombreProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubTotal()
        );

        vista.actualizarTotales(listaDetalle);
    }

    //PARA VERIDFICAR SI UN PRODUCTO YA HA SIDO AGREGADO A LA LISTA
    private boolean detalleYaExiste(Producto producto, List<DetalleVenta> listaDetalle) {
        for (DetalleVenta detalle : listaDetalle) {
            if (detalle.getIdProducto() == producto.getIdProducto()) {
                return true;
            }
        }
        return false;
    }

    //PARA ELIMINAR UN PRODUCTO DE LA LISTA
    public void eliminarProductoDeLista(int idProducto, JPanelVentaNuevo vista) {
        List<DetalleVenta> listaDetalle = vista.getListaDetalle();

        DetalleVenta detalleAEliminar = null;
        for (DetalleVenta d : listaDetalle) {
            if (d.getIdProducto() == idProducto) {
                detalleAEliminar = d;
                break;
            }
        }

        if (detalleAEliminar != null) {
            listaDetalle.remove(detalleAEliminar);
            vista.actualizarTabla(listaDetalle);
            vista.actualizarTotales(listaDetalle);
        } else {
            vista.mostrarMensaje("Producto no encontrado en la lista");
        }
    }

    //PARA AUMENTAR LA CANTIDAD DE UN PRODUCTO
    public void aumentarCantidadProducto(int idProducto, JPanelVentaNuevo vista) {
        List<DetalleVenta> listaDetalle = vista.getListaDetalle();

        for (DetalleVenta detalle : listaDetalle) {
            if (detalle.getIdProducto() == idProducto) {
                Producto producto = productoDAO.obtenerProductoPorId(idProducto);
                if (producto == null) {
                    vista.mostrarMensaje("Producto no encontrado");
                    return;
                }

                if (detalle.getCantidad() >= producto.getCantidad()) {
                    vista.mostrarMensaje("No hay suficiente stock");
                    return;
                }

                detalle.setCantidad(detalle.getCantidad() + 1);
                double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
                detalle.setSubTotal(subtotal);
                detalle.setIva(subtotal * producto.getPorcentajeIva() / 100);
                detalle.setDescuento(subtotal * 0.05);
                detalle.setTotalPagar(subtotal + detalle.getIva() - detalle.getDescuento());

                vista.aumentarProducto(detalle);
                vista.actualizarTotales(listaDetalle);
                break;
            }
        }
    }

    //PARA DISMINUIR LA CANTIDAD
    public void disminuirCantidad(int fila, JPanelVentaNuevo vista) {
        List<DetalleVenta> listaDetalle = vista.getListaDetalle();

        int cantidadActual = vista.getCantidadProductoEnFila(fila);
        if (cantidadActual <= 1) {
            vista.mostrarMensaje("Límite alcanzado para disminuir.");
            return;
        }

        cantidadActual--;
        int idProducto = vista.getIdProductoEnFila(fila);
        double precioUnitario = vista.getPrecioProductoEnFila(fila);
        double nuevoSubtotal = cantidadActual * precioUnitario;

        Producto producto = productoDAO.obtenerProductoPorId(idProducto);
        double iva = nuevoSubtotal * producto.getPorcentajeIva() / 100;
        double descuento = nuevoSubtotal * 0.05;
        double total = nuevoSubtotal + iva - descuento;

        for (DetalleVenta detalle : listaDetalle) {
            if (detalle.getIdProducto() == idProducto) {
                detalle.setCantidad(cantidadActual);
                detalle.setSubTotal(nuevoSubtotal);
                detalle.setIva(iva);
                detalle.setDescuento(descuento);
                detalle.setTotalPagar(total);
                break;
            }
        }

        vista.disminuirProducto(fila, cantidadActual, nuevoSubtotal);
        vista.actualizarTotales(listaDetalle);
    }

    //PARA CONFIRMAR UNA VENTA
    public boolean procesarConfirmarVenta(
            int idClienteSeleccionado, boolean radioButtonSiIsSelected, boolean radioButtonNoIsSelected,
            String cedula, String nombres, String apellidos, String telefono,
            String correo, String direccion, String totalStr,
            List<DetalleVenta> detallesTabla, JPanelVentaNuevo vista) {

        int idClienteParaVenta;

        if (!radioButtonSiIsSelected && !radioButtonNoIsSelected) {
            vista.mostrarMensaje("Debe seleccionar una opción de facturación (Sí o No)");
            return false;
        }

        if (radioButtonSiIsSelected) {
            if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty()
                    || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
                vista.mostrarMensaje("Debe completar todos los datos del cliente para facturar");
                return false;
            }

            if (idClienteSeleccionado == -1) {
                vista.mostrarMensaje("Debe buscar o registrar un cliente antes de confirmar la venta");
                return false;
            }
            idClienteParaVenta = idClienteSeleccionado;
        } else {
            idClienteParaVenta = 1;
        }

        int idUsuarioLogueado = Sesion.getUsuarioActual().getIdUsuario();
        int idEmpleadoLogueado = -1;
        try {
            idEmpleadoLogueado = empleadoController.obtenerIdEmp(idUsuarioLogueado);
        } catch (Exception e) {
            System.err.println("Error al obtener idEmpleado para el usuario " + idUsuarioLogueado + ": " + e.getMessage());
            vista.mostrarMensaje("Error interno al obtener datos del empleado. No se puede registrar la venta");
            return false;
        }

        if (idEmpleadoLogueado == -1) {
            vista.mostrarMensaje("El usuario logueado no está registrado como empleado o el ID de empleado es inválido. No se puede registrar la venta");
            return false;
        }

        double totalVenta;
        try {
            totalVenta = Double.parseDouble(totalStr);
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("El campo Total no es un número válido");
            return false;
        }

        Venta venta = new Venta();
        venta.setIdCliente(idClienteParaVenta);
        venta.setIdEmpleado(idEmpleadoLogueado);
        venta.setTotal(totalVenta);
        venta.setEstado(1);

        boolean exito = ventaDAO.guardarVentaCompleta(venta, detallesTabla);

        if (!exito) {
            vista.mostrarMensaje("Error al registrar la venta. Por favor, intente nuevamente");
        }
        return exito;
    }
}
