/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import java.util.List;
import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author admin
 */
public class ProductoController {

    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;
    private ProveedorDAO proveedorDAO;

    public List<Categoria> obtenerCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public List<Proveedor> obtenerProveedores() {
        return proveedorDAO.listarProveedores();
    }

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.proveedorDAO = new ProveedorDAO();
    }

    public String guardarProducto(String nombreProducto, String stockTexto, String precioTexto, String descripcion,
            String nombreCategoria, String nombreProveedor, int iva, int estado) {
        int idCat = categoriaDAO.getIdCategoriaPorNombre(nombreCategoria);
        int idProv = proveedorDAO.getIdProveedorPorNombre(nombreProveedor);

        if (idCat == -1 || idProv == -1) {
            return "Error: No se pudo encontrar ID de categoría o proveedor por nombre";
        }

        if (nombreProducto.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        precioTexto = precioTexto.replace(",", ".");

        double precio;
        int stock;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            return "El precio debe ser numérico";
        }

        try {
            stock = Integer.parseInt(stockTexto);
        } catch (NumberFormatException e) {
            return "El Stock debe ser numérico y entero";
        }

        if (precio < 0) {
            return "El precio debe ser mayor o igual a cero";
        } else if (stock < 0) {
            return "El stock debe ser mayor o igual a cero";
        }

        if (productoDAO.existeProductoConNombre(nombreProducto)) {
            return "El producto ya existe con este nombre";
        }

        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(stock);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCat);
        producto.setCategoria(categoria);

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProv);
        producto.setProveedor(proveedor);

        producto.setPorcentajeIva(iva);
        producto.setEstado(estado);

        boolean registrado = productoDAO.registrarProducto(producto);
        return registrado ? "Producto registrado correctamente" : "Error al registrar el producto";
    }

    public String actualizarProducto(String nombreProducto, String stockTexto, String precioTexto, String descripcion,
            String nombreCategoria, String nombreProveedor, int iva, int estado, int idProducto) {
        int idCat = categoriaDAO.getIdCategoriaPorNombre(nombreCategoria);
        int idProv = proveedorDAO.getIdProveedorPorNombre(nombreProveedor);

        if (idCat == -1 || idProv == -1) {
            return "Error: No se pudo encontrar ID de categoría o proveedor por nombre";
        }

        if (nombreProducto.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        precioTexto = precioTexto.replace(",", ".");

        double precio;
        int stock;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            return "El precio debe ser numérico";
        }

        try {
            stock = Integer.parseInt(stockTexto);
        } catch (NumberFormatException e) {
            return "El Stock debe ser numérico y entero";
        }

        if (precio < 0) {
            return "El precio debe ser mayor o igual a cero";
        } else if (stock < 0) {
            return "El stock debe ser mayor o igual a cero";
        }

        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(stock);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCat);
        producto.setCategoria(categoria);

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProv);
        producto.setProveedor(proveedor);

        producto.setPorcentajeIva(iva);
        producto.setEstado(estado);

        boolean registrado = productoDAO.actualizarProducto(producto, idProducto);
        return registrado ? "Producto actualizado correctamente" : "Error al actualizado el producto";
    }

    public String desactivarCategoria(int idProducto) {
        Producto produc = productoDAO.obtenerProductoPorId(idProducto);
        if (produc == null) {
            return "La categoría no existe.";
        }

        if (produc.getEstado() == 0) {
            return "La categoría ya está desactivada.";
        }

        boolean resultado = productoDAO.cambiarEstado(idProducto, 0);
        return resultado ? "Categoría desactivada correctamente." : "Error al desactivar la categoría.";
    }

    public String activarCategoria(int idProducto) {
        Producto produc = productoDAO.obtenerProductoPorId(idProducto);
        if (produc == null) {
            return "La categoría no existe.";
        }

        if (produc.getEstado() == 1) {
            return "La categoría ya está activa.";
        }

        boolean resultado = productoDAO.cambiarEstado(idProducto, 1);
        return resultado ? "Categoría activada correctamente." : "Error al activar la categoría.";
    }
    
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listarProductos();
    }
    
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorDAO.listarProveedores();
    }
    
//    public boolean existeProductoPorNombre(String nombreProducto) {
//        return productoDAO.existeProductoConNombre(nombreProducto);
//    }

    public Producto obtenerProductoPorNombre(String nombreProducto) {
        return productoDAO.buscarProductoPorNombre(nombreProducto);
    }

    public Producto obtenerProductoPorId(int idProducto) {
        return productoDAO.obtenerProductoPorId(idProducto);
    }

    public boolean existenProductosEnSistema() {
        return productoDAO.existenProductos();
    }
}
