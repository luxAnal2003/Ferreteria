package controlador;

import dao.Conexion;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteController {

    public boolean guardar(Cliente cliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "INSERT INTO cliente (idRol, cedula, direccion, estado, idUsuario) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, cliente.getIdRol());
            consulta.setString(2, cliente.getCedula());
            consulta.setString(3, cliente.getDireccion());
            consulta.setInt(4, cliente.getEstado());
            consulta.setInt(5, cliente.getIdUsuario());

            respuesta = consulta.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }

        return respuesta;
    }

    public boolean actualizar(Cliente cliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE cliente SET idRol = ?, cedula = ?, direccion = ?, estado = ? WHERE idCliente = ?";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, cliente.getIdRol());
            consulta.setString(2, cliente.getCedula());
            consulta.setString(3, cliente.getDireccion());
            consulta.setInt(4, cliente.getEstado());
            consulta.setInt(5, cliente.getIdCliente());

            respuesta = consulta.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }

        return respuesta;
    }

    public boolean desactivar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE cliente SET estado = 0 WHERE idCliente = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idCliente);
            respuesta = pst.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al desactivar cliente: " + e);
        }
        return respuesta;
    }

    public boolean activar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE cliente SET estado = 1 WHERE idCliente = ?");
            consulta.setInt(1, idCliente);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al activar cliente : " + e);
        }

        return respuesta;
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();

        String sql = "SELECT c.*, r.tipo FROM cliente c INNER JOIN rol r ON c.idRol = r.idRol";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("idCliente"));
                cli.setIdRol(rs.getInt("idRol"));
                cli.setCedula(rs.getString("cedula"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setEstado(rs.getInt("estado"));
                cli.setIdRol(rs.getInt("idRol"));

                lista.add(cli);
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e);
        }

        return lista;
    }

    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "SELECT cedula FROM cliente WHERE cedula = ?";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, cedula);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar cliente: " + e);
        }

        return respuesta;
    }

    public int obtenerUltimoIdInsertado() {
        int id = -1;
        String sql = "SELECT MAX(idCliente) FROM Cliente";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener último idCliente: " + e.getMessage());
        }
        return id;
    }

}
