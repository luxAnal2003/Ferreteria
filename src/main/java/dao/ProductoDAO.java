/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class ProductoDAO {

    public boolean registrarProducto(Producto producto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO producto (idProducto, nombre, cantidad, precio, descripcion, iva, idCategoria, idProveedor, estado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            consulta.setInt(1, 0);
            consulta.setString(2, producto.getNombreProducto());
            consulta.setInt(3, producto.getCantidad());
            consulta.setDouble(4, producto.getPrecio());
            consulta.setString(5, producto.getDescripcion());
            consulta.setInt(6, producto.getPorcentajeIva());
            consulta.setInt(7, producto.getCategoria().getIdCategoria());
            consulta.setInt(8, producto.getProveedor().getIdProveedor());
            consulta.setInt(9, producto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }
        return respuesta;
    }

    public boolean actualizarProducto(Producto producto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE producto SET nombre = ?, idCategoria = ?, idProveedor = ?, precio = ?, cantidad = ?, descripcion = ?, iva = ?, estado = ? WHERE idProducto = ?"
            );
            consulta.setString(1, producto.getNombreProducto());
            consulta.setInt(2, producto.getCategoria().getIdCategoria());
            consulta.setInt(3, producto.getProveedor().getIdProveedor());
            consulta.setDouble(4, producto.getPrecio());
            consulta.setInt(5, producto.getCantidad());
            consulta.setString(6, producto.getDescripcion());
            consulta.setInt(7, producto.getPorcentajeIva());
            consulta.setInt(8, producto.getEstado());
            consulta.setInt(9, idProducto);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }

    public boolean existeProductoConNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM producto WHERE nombre = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cambiarEstado(int idProducto, int estado) {
        String sql = "UPDATE producto SET estado = ? WHERE idProducto = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.iva, p.estado, "
                + "c.idCategoria, c.descripcionCategoria, pr.idProveedor, pr.nombreProveedor "
                + "FROM producto p "
                + "INNER JOIN categoria c ON p.idCategoria = c.idCategoria "
                + "INNER JOIN proveedor pr ON p.idProveedor = pr.idProveedor";

        try {
            cn = Conexion.conectar();
            stmt = cn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombre"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setPorcentajeIva(rs.getInt("iva"));
                p.setEstado(rs.getInt("estado"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("descripcionCategoria").trim());
                p.setCategoria(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor").trim());
                p.setProveedor(proveedor);

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return lista;
    }

    public Producto buscarProductoPorNombre(String nombreProducto) {
        String sql = "SELECT * FROM producto WHERE nombre LIKE ? AND estado = 1";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombreProducto + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPorcentajeIva(rs.getInt("iva"));
                producto.setEstado(rs.getInt("estado"));

                int idCategoria = rs.getInt("idCategoria");
                int idProveedor = rs.getInt("idProveedor");

                Categoria categoria = new CategoriaDAO().obtenerCategoriaPorId(idCategoria);
                Proveedor proveedor = new ProveedorDAO().obtenerProveedorPorId(idProveedor);

                producto.setCategoria(categoria);
                producto.setProveedor(proveedor);

                return producto;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        Connection cn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.iva, p.estado, "
                + "c.idCategoria, c.descripcionCategoria, pr.idProveedor, pr.nombreProveedor "
                + "FROM producto p "
                + "INNER JOIN categoria c ON p.idCategoria = c.idCategoria "
                + "INNER JOIN proveedor pr ON p.idProveedor = pr.idProveedor "
                + "WHERE p.idProducto = ?";
        try {
            cn = Conexion.conectar();
            stmt = cn.prepareStatement(sql);
            stmt.setInt(1, idProducto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPorcentajeIva(rs.getInt("iva"));
                producto.setEstado(rs.getInt("estado"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("descripcionCategoria").trim());
                producto.setCategoria(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor").trim());
                producto.setProveedor(proveedor);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar producto por ID en ProductoDAO: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión en buscarProductoPorId de ProductoDAO: " + e.getMessage());
            }
        }
        return producto;
    }

    public boolean existenProductos() {
        String sql = "SELECT COUNT(*) FROM producto";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar productos: " + e.getMessage());
        }

        return false;
    }

    public boolean disminuirStock(int idProducto, int cantidad, Connection con) throws SQLException {
        String sql = "UPDATE producto SET cantidad = cantidad - ? WHERE idProducto = ? AND cantidad >= ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, cantidad);
        pst.setInt(2, idProducto);
        pst.setInt(3, cantidad);
        int filasAfectadas = pst.executeUpdate();
        return filasAfectadas > 0;
    }
}
