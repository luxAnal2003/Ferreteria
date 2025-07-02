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
import modelo.Proveedor;

/**
 *
 * @author bdfz
 */
public class ProveedorDAO {

    public boolean registrarProveedor(Proveedor proveedor) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("""
                INSERT INTO proveedor (ruc, nombreProveedor, telefonoProveedor, direccionProveedor,
                                      correoProveedor, estado)
                VALUES (?, ?, ?, ?, ?, ?)
            """);

            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getRazonSocial());
            consulta.setString(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setString(5, proveedor.getCorreo());
            consulta.setInt(6, proveedor.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al guardar proveedor: " + e.getMessage());
        } 
        return respuesta;
    }

    public boolean actualizarProveedor(Proveedor proveedor, int idProveedor) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE proveedor SET ruc = ?, nombreProveedor = ?, telefonoProveedor = ?, "
                + "direccionProveedor = ?, correoProveedor = ?, estado = ? WHERE idProveedor = ?"
            );

            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getRazonSocial());
            consulta.setString(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setString(5, proveedor.getCorreo());
            consulta.setInt(6, proveedor.getEstado());
            consulta.setInt(7, idProveedor);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
        } 
        return respuesta;
    }

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
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return existe;
    }

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
                p.setRazonSocial(rs.getString("nombreProveedor"));
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
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return lista;
    }

    public boolean cambiarEstado(int idProveedor, int estado) {
        String sql = "UPDATE proveedor SET estado = ? WHERE idProveedor = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idProveedor);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Proveedor obtenerProveedorPorId(int idProveedor) {
        Proveedor proveedor = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT idProveedor, ruc, nombreProveedor, telefonoProveedor, correoProveedor, direccionProveedor, estado "
                + "FROM proveedor WHERE idProveedor = ?";

        try {
            cn = Conexion.conectar();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, idProveedor);
            rs = pst.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setRuc(rs.getString("ruc"));
                proveedor.setRazonSocial(rs.getString("nombreProveedor"));
                proveedor.setTelefono(rs.getString("telefonoProveedor"));
                proveedor.setCorreo(rs.getString("correoProveedor"));
                proveedor.setDireccion(rs.getString("direccionProveedor"));
                proveedor.setEstado(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor por ID: " + e.getMessage());
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
        return proveedor;
    }

    public List<Proveedor> buscarProveedores(String criterio) {
        List<Proveedor> proveedores = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT idProveedor, ruc, nombreProveedor, telefonoProveedor, correoProveedor, direccionProveedor, estado "
                + "FROM proveedor "
                + "WHERE LOWER(ruc) LIKE ? OR LOWER(nombreProveedor) LIKE ?";

        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(sql);
            String busquedaLike = "%" + criterio.toLowerCase() + "%";
            pst.setString(1, busquedaLike);
            pst.setString(2, busquedaLike);
            rs = pst.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setRuc(rs.getString("ruc"));
                proveedor.setRazonSocial(rs.getString("nombreProveedor"));
                proveedor.setTelefono(rs.getString("telefonoProveedor"));
                proveedor.setCorreo(rs.getString("correoProveedor"));
                proveedor.setDireccion(rs.getString("direccionProveedor"));
                proveedor.setEstado(rs.getInt("estado"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al buscar proveedores activos en ProveedorDAO: " + e.getMessage());
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
                System.err.println("Error al cerrar conexión en ProveedorDAO.buscarProveedores: " + e.getMessage());
            }
        }
        return proveedores;
    }

    public boolean verificarExistenciaDeProveedores() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Conexion.conectar();
            String sql = "SELECT COUNT(*) FROM Proveedor";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL al verificar existencia de proveedores (count) en ProveedorDAO: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en ProveedorDAO.checkProveedorExistenceCount: " + e.getMessage());
            }
        }
    }

    public int getIdProveedorPorNombre(String nombreProveedor) {
        int id = -1;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT idProveedor FROM proveedor WHERE nombreProveedor = ?";

        try {
            cn = Conexion.conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, nombreProveedor);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idProveedor");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener idProveedor: " + e.getMessage());
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
