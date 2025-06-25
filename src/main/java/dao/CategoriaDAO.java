/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

/**
 *
 * @author admin
 */
public class CategoriaDAO {

    public boolean guardar(Categoria categoria) {
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

    public boolean actualizar(Categoria categoria, int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("update categoria set descripcionCategoria = ? where idCategoria = '" + idCategoria + "'");
            consulta.setString(1, categoria.getNombre());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }
        return respuesta;
    }

    public boolean desactivar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = null;

        String sql = "UPDATE categoria SET estado = 0 WHERE idCategoria = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idCategoria);
            respuesta = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar categoria: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return respuesta;
    }

    public boolean activar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = null;

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("UPDATE categoria SET estado = 1 WHERE idCategoria = ?");
            consulta.setInt(1, idCategoria);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al activar categoria: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return respuesta;
    }

    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcionCategoria from categoria where descripcionCategoria = '" + categoria + "'";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
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
            System.err.println("Error al obtener idCategoria: " + e.getMessage());
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
}
