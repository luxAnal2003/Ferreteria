/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author admin
 */
import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class ProductoController {

    public boolean guardar(Producto producto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "INSERT INTO producto (idProducto, nombre, cantidad, precio, descripcion, iva, idCategoria, idProveedor, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, 0);
            consulta.setString(2, producto.getNombreProducto());
            consulta.setInt(3, producto.getCantidad());
            consulta.setDouble(4, producto.getPrecio());
            consulta.setString(5, producto.getDescripcion());
            consulta.setInt(6, producto.getPorcentajeIva());
            consulta.setInt(7, producto.getIdCategoria().getIdCategoria());
            consulta.setInt(8, producto.getIdProveedor().getIdProveedor());
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

    public boolean actualizar(Producto producto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE producto SET nombre = ?, idCategoria = ?, idProveedor = ?, precio = ?, cantidad = ?, descripcion = ?, iva = ?, estado = ? WHERE idProducto = ?"
            );
            consulta.setString(1, producto.getNombreProducto());
            consulta.setInt(2, producto.getIdCategoria().getIdCategoria());
            consulta.setInt(3, producto.getIdProveedor().getIdProveedor());
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

    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("DELETE FROM producto WHERE idProducto = ?");
            consulta.setInt(1, idProducto);
            consulta.executeUpdate();
            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }

    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from producto where nombre = '" + producto + "'";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    public boolean desactivar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE producto SET estado = 0 WHERE idProducto = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idProducto);
            respuesta = pst.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al desactivar producto: " + e);
        }
        return respuesta;
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();

        String sql = "SELECT * FROM producto WHERE estado = 1";

        try (PreparedStatement stmt = cn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombre"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setPorcentajeIva(rs.getInt("iva"));
                p.setEstado(rs.getInt("estado"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        }

        return lista;
    }

    public boolean activar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE producto SET estado = 1 WHERE idProducto = ?");
            consulta.setInt(1, idProducto);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al activar producto: " + e);
        }

        return respuesta;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, "%" + nombre + "%");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPorcentajeIva(rs.getInt("iva"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    public Producto buscarProductoPorId(int idProducto) {
        Producto producto = null;
        Connection cn = Conexion.conectar();

        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        try (PreparedStatement stmt = cn.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                producto.setIdCategoria(categoria);

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                producto.setIdProveedor(proveedor);

                producto.setEstado(rs.getInt("estado"));
                producto.setPorcentajeIva(rs.getInt("iva"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar producto por ID: " + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return producto;
    }
}
