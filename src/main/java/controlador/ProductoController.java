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
    
    public ProductoController() {
        this.productoDAO = new ProductoDAO();
        this.categoriaDAO = new CategoriaDAO(); 
        this.proveedorDAO = new ProveedorDAO(); 
    }
    
    public boolean guardarProducto(Producto producto, String nombreCategoria, String nombreProveedor) {
        int idCat = categoriaDAO.getIdCategoriaPorNombre(nombreCategoria);
        int idProv = proveedorDAO.getIdProveedorPorNombre(nombreProveedor);

        if (idCat == -1 || idProv == -1) {
            System.err.println("Error: No se pudo encontrar ID de categoría o proveedor por nombre.");
            return false;
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCat);
        producto.setIdCategoria(categoria);

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProv);
        producto.setIdProveedor(proveedor);

        return productoDAO.guardar(producto);
    }
    public boolean actualizarProducto(Producto producto, String nombreCategoria, String nombreProveedor, int idProducto) {
        int idCat = categoriaDAO.getIdCategoriaPorNombre(nombreCategoria);
        int idProv = proveedorDAO.getIdProveedorPorNombre(nombreProveedor);

        if (idCat == -1 || idProv == -1) {
            System.err.println("Error: No se pudo encontrar ID de categoría o proveedor por nombre para actualizar.");
            return false;
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCat);
        producto.setIdCategoria(categoria);

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProv);
        producto.setIdProveedor(proveedor);

        return productoDAO.actualizar(producto, idProducto);
    }
    
    public boolean desactivarProducto(int idProducto) {
        return productoDAO.desactivar(idProducto);
    }

    public boolean activarProducto(int idProducto) {
        return productoDAO.activar(idProducto);
    }

    public boolean existeProductoPorNombre(String nombreProducto) {
        return productoDAO.existeProducto(nombreProducto);
    }

    public Producto obtenerProductoPorNombre(String nombreProducto) {
        return productoDAO.buscarProductoPorNombre(nombreProducto);
    }
    
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listarProductos();
    }

    public Producto obtenerProductoPorId(int idProducto) {
        return productoDAO.buscarProductoPorId(idProducto);
    }
    
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorDAO.listarProveedores();
    }

    public boolean existenProductosActivos() {
        return productoDAO.existenProductos();
    }
}
