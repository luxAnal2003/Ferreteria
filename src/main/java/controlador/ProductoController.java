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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        try {
            String sql = "INSERT INTO producto (nombre, idProveedor, cantidad, descripcion, precio, iva, idCategoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, producto.getNombreProducto());
            consulta.setInt(2, producto.getIdProveedor().getIdProveedor());
            consulta.setInt(3, producto.getCantidad());
            consulta.setString(4, producto.getDescripcion());
            consulta.setDouble(5, producto.getPrecio());
            consulta.setDouble(6, producto.getIva());
            consulta.setInt(7, producto.getIdCategoria().getIdCategoria());
            consulta.setInt(8, producto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }

        return respuesta;
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();

       String sql = "SELECT * FROM producto p " +
             "INNER JOIN categoria c ON p.idCategoria = c.idCategoria " +
             "INNER JOIN proveedor pr ON p.idProveedor = pr.idProveedor";

        try (PreparedStatement stmt = cn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setNombreProducto(rs.getString("nombre"));

                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombreProveedor"));
                p.setIdProveedor(proveedor);

                p.setCantidad(rs.getInt("cantidad"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIva(rs.getDouble("iva"));

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("descripcionCategoria"));
                p.setIdCategoria(categoria);

                p.setEstado(rs.getInt("estado"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        }

        return lista;
    }

    public boolean existeProducto(String nombreProducto) {
        boolean respuesta = false;
        String sql = "SELECT nombreProducto FROM producto WHERE nombre = ?";
        try (Connection cn = Conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de producto: " + e);
        }
        return respuesta;
    }
}
