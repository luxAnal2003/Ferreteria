/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

/**
 *
 * @author bdfz
 */
public class ProveedorController {

    public boolean guardar(Proveedor proveedor) {
        boolean respuesta = false;
        Connection cn = null; 

        try {
            cn = Conexion.conectar();
           
            PreparedStatement consulta = cn.prepareStatement("""
                INSERT INTO proveedor (ruc, nombreProveedor, telefonoProveedor, direccionProveedor,
                                      correoProveedor, estado)
                VALUES (?, ?, ?, ?, ?, ?)
            """);

            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getNombre());
            consulta.setString(3, proveedor.getTelefono()); 
            consulta.setString(4, proveedor.getDireccion());
            consulta.setString(5, proveedor.getCorreo()); 
            consulta.setInt(6, proveedor.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar proveedor: " + e.getMessage()); 
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en guardar proveedor: " + e.getMessage());
            }
        }
        return respuesta;
    }

    /**
     * Verifica si un proveedor con el RUC dado ya existe en la base de datos.
     * @param ruc El RUC a verificar.
     * @return true si el proveedor existe, false en caso contrario.
     */
    public boolean existeProveedor(String ruc) {
        boolean existe = false;
        Connection cn = null;
        String sql = "SELECT ruc FROM proveedor WHERE ruc = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setString(1, ruc);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                existe = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar proveedor: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en existeProveedor: " + e.getMessage());
            }
        }
        return existe;
    }

    /**
     * Lista todos los proveedores de la base de datos.
     * @return Una lista de objetos Proveedor.
     */
    public List<Proveedor> listarProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        Connection cn = null;
        String sql = "SELECT * FROM proveedor";

        try {
            cn = Conexion.conectar();
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor")); 
                p.setRuc(rs.getString("ruc"));
                p.setNombre(rs.getString("nombreProveedor"));
                p.setDireccion(rs.getString("direccionProveedor"));
                p.setTelefono(rs.getString("telefonoProveedor"));
                p.setCorreo(rs.getString("correoProveedor"));
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en listarProveedores: " + e.getMessage());
            }
        }
        return lista;
    }

    /**
     * Actualiza la información de un proveedor existente en la base de datos.
     * @param proveedor El objeto Proveedor con la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(Proveedor proveedor) {
        boolean respuesta = false;
        Connection cn = null;
        
        String sql = "UPDATE proveedor SET ruc = ?, nombreProveedor = ?, telefonoProveedor = ?, "
                   + "direccionProveedor = ?, correoProveedor = ?, estado = ? WHERE idProveedor = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement(sql);

            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getNombre());
            consulta.setString(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setString(5, proveedor.getCorreo());
            consulta.setInt(6, proveedor.getEstado());
            consulta.setInt(7, proveedor.getIdProveedor());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en actualizar proveedor: " + e.getMessage());
            }
        }
        return respuesta;
    }

    /**
     * Cambia el estado de un proveedor a inactivo (0).
     * @param idProveedor El ID del proveedor a desactivar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean desactivar(int idProveedor) {
        boolean respuesta = false;
        Connection cn = null;
        String sql = "UPDATE proveedor SET estado = 0 WHERE idProveedor = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idProveedor);
            respuesta = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar proveedor: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en desactivar proveedor: " + e.getMessage());
            }
        }
        return respuesta;
    }

    /**
     * Cambia el estado de un proveedor a activo (1).
     * @param idProveedor El ID del proveedor a activar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean activar(int idProveedor) {
        boolean respuesta = false;
        Connection cn = null;
        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("UPDATE proveedor SET estado = 1 WHERE idProveedor = ?");
            consulta.setInt(1, idProveedor);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al activar proveedor: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en activar proveedor: " + e.getMessage());
            }
        }
        return respuesta;
    }
}
