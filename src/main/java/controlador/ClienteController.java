package controlador;

import dao.Conexion;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteController {

    public boolean guardar(Cliente cliente) {
        boolean respuesta = false;
        Connection cn = null;

        String sql = "INSERT INTO cliente (nombre, apellido, telefono, correo, cedula, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getTelefono());
            consulta.setString(4, cliente.getCorreo());
            consulta.setString(5, cliente.getCedula());
            consulta.setString(6, cliente.getDireccion());
            consulta.setInt(7, cliente.getEstado());

            respuesta = consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
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

    public boolean actualizar(Cliente cliente) {
        boolean respuesta = false;
        Connection cn = null;

        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, correo = ?, "
                + "cedula = ?, direccion = ?, estado = ? WHERE idCliente = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getTelefono());
            consulta.setString(4, cliente.getCorreo());
            consulta.setString(5, cliente.getCedula());
            consulta.setString(6, cliente.getDireccion());
            consulta.setInt(7, cliente.getEstado());
            consulta.setInt(8, cliente.getIdCliente());

            respuesta = consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
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

    public boolean desactivar(int idCliente) {
        boolean respuesta = false;
        Connection cn = null;

        String sql = "UPDATE cliente SET estado = 0 WHERE idCliente = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idCliente);
            respuesta = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar cliente: " + e.getMessage());
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

    public boolean activar(int idCliente) {
        boolean respuesta = false;
        Connection cn = null;

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("UPDATE cliente SET estado = 1 WHERE idCliente = ?");
            consulta.setInt(1, idCliente);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al activar cliente: " + e.getMessage());
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

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        Connection cn = null;

        String sql = "SELECT idCliente, nombre, apellido, telefono, correo, cedula, direccion, estado FROM cliente";

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement(sql);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("idCliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCorreo(rs.getString("correo"));
                cli.setCedula(rs.getString("cedula")); 
                cli.setDireccion(rs.getString("direccion"));
                cli.setEstado(rs.getInt("estado"));
                lista.add(cli);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
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

    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        Connection cn = null;

        String sql = "SELECT cedula FROM cliente WHERE cedula = ?";

        try {
            cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, cedula);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar cliente: " + e.getMessage());
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

    public int obtenerUltimoIdInsertado() {
        int id = -1;
        Connection con = null;
        String sql = "SELECT MAX(idCliente) FROM Cliente";
        try {
            con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener último idCliente: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return id;
    }
}
