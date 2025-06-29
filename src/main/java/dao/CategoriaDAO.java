/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

/**
 *
 * @author admin
 */
public class CategoriaDAO {

    public boolean registrarCategoria(Categoria categoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("insert into categoria values (?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, categoria.getNombre());
            consulta.setInt(3, categoria.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }
        return respuesta;
    }

    public boolean actualizarCategoria(Categoria categoria, int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE categoria SET descripcionCategoria = ? WHERE idCategoria = ?");
            consulta.setString(1, categoria.getNombre());
            consulta.setInt(2, idCategoria);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }
        return respuesta;
    }

    public boolean cambiarEstado(int idCategoria, int estado) {
        String sql = "UPDATE categoria SET estado = ? WHERE idCategoria = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idCategoria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeCategoriaConNombre(String categoria) {
        String sql = "SELECT COUNT(*) FROM categoria WHERE descripcionCategoria = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";

        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNombre(rs.getString("descripcionCategoria"));
                cat.setEstado(rs.getInt("estado"));
                lista.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        Categoria categoria = null;
        String sql = "SELECT idCategoria, descripcionCategoria, estado FROM categoria WHERE idCategoria = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCategoria);
            rs = pst.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("descripcionCategoria"));
                categoria.setEstado(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categoria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return categoria;
    }

    //OJITO. este método es para obtener los nombres de categoria en el módulo de productos.
    //NO se usa en alguna clase de categoría. Es para llenar los combobox.
    public int getIdCategoriaPorNombre(String nombreCategoria) {
        int id = -1;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT idCategoria FROM categoria WHERE descripcionCategoria = ?";

        try {
            cn = Conexion.conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, nombreCategoria);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idCategoria");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener id de Categoria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return id;
    }
    
    public boolean existenCategorias() {
        String sql = "SELECT COUNT(*) FROM categoria";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar categorias: " + e.getMessage());
        }

        return false;
    }
}
