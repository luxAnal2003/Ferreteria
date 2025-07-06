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
import modelo.Rol;

/**
 *
 * @author admin
 */
public class RolDAO {

    public int getIdRolPorNombre(String rol) {
        int id = -1;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT idRol FROM rol WHERE tipo = ?";

        try {
            cn = Conexion.conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, rol);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idRol");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener id de rol: " + e.getMessage());
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

    public List<Rol> listarRoles() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM rol";

        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setTipo(rs.getString("tipo"));
                rol.setEstado(rs.getInt("estado"));
                lista.add(rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
