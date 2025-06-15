/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

//import Services.ProveedorService;
import dao.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
//import vista.FrmProveedor;

/**
 *
 * @author bdfz
 */
public class ProveedorController {

    public boolean guardar(Proveedor proveedor) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("""
                INSERT INTO proveedor (cedula, nombreProveedor, apellidoProveedor, direccionProveedor,
                                       numeroTelefonoProveedor, correo, estado)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """);

            consulta.setString(1, proveedor.getCedula());
            consulta.setString(2, proveedor.getNombre());
            consulta.setString(3, proveedor.getApellido());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setString(5, proveedor.getTelefono());
            consulta.setString(6, proveedor.getCorreo());
            consulta.setInt(7, proveedor.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar proveedor: " + e);
        }

        return respuesta;
    }

    public boolean existeProveedorPorCedula(String cedula) {
        boolean existe = false;
        String sql = "SELECT cedula FROM proveedor WHERE cedula = ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement stmt = cn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar proveedor: " + e);
        }

        return existe;
    }

    public List<Proveedor> listarProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";

        try (Connection cn = Conexion.conectar(); PreparedStatement stmt = cn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombreProveedor"));
                p.setApellido(rs.getString("apellidoProveedor"));
                p.setDireccion(rs.getString("direccionProveedor"));
                p.setTelefono(rs.getString("numeroTelefonoProveedor"));
                p.setCorreo(rs.getString("correo"));
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e);
        }

        return lista;
    }
}
