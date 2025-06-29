package dao;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAO {

    public boolean registrarCliente(Cliente cliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "INSERT INTO cliente (nombre, apellido, telefono, correo, cedula, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getTelefono());
            consulta.setString(4, cliente.getCorreo());
            consulta.setString(5, cliente.getCedula());
            consulta.setString(6, cliente.getDireccion());
            consulta.setInt(7, cliente.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
        } 
        return respuesta;
    }

    public boolean actualizarCliente(Cliente cliente, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, correo = ?, "
                    + "cedula = ?, direccion = ?, estado = ? WHERE idCliente = ?"
            );
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getTelefono());
            consulta.setString(4, cliente.getCorreo());
            consulta.setString(5, cliente.getCedula());
            consulta.setString(6, cliente.getDireccion());
            consulta.setInt(7, cliente.getEstado());
            consulta.setInt(8, idCliente);
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        } 
        return respuesta;
    }

    public boolean cambiarEstado(int idCliente, int estado) {
        String sql = "UPDATE cliente SET estado = ? WHERE idCliente = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeClientePorCedula(String cedula) {
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

    public Cliente buscarPorCedula(String cedula) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE cedula=?";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por cédula: " + e);
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT idCliente, nombre, apellido, telefono, correo, cedula, direccion, estado FROM Cliente";

        try {
            con = Conexion.conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setEstado(rs.getInt("estado"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener todos los clientes en ClienteDAO: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en ClienteDAO.getAllClientes: " + e.getMessage());
            }
        }
        return clientes;
    }

    public boolean verificarExistenciaClientes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Conexion.conectar();
            String sql = "SELECT COUNT(*) FROM Cliente";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de clientes: " + e.getMessage());
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
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    public Cliente obtenerClientePorId(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT idCliente, nombre, apellido, telefono, correo, cedula, direccion, estado FROM Cliente WHERE idCliente = ?"; // Usar 'cedula'
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener cliente por ID en ClienteDAO: " + e.getMessage());
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
                System.err.println("Error al cerrar conexión en ClienteDAO.getClienteById: " + e.getMessage());
            }
        }
        return cliente;
    }

    public List<Cliente> buscarClientesPorCriterio(String criterio) {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT idCliente, nombre, apellido, telefono, correo, cedula, direccion, estado "
                + "FROM Cliente "
                + "WHERE LOWER(nombre) LIKE ? OR LOWER(apellido) LIKE ? OR LOWER(cedula) LIKE ?";

        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(sql);
            String busquedaLike = "%" + criterio.toLowerCase() + "%";
            pst.setString(1, busquedaLike);
            pst.setString(2, busquedaLike);
            pst.setString(3, busquedaLike);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setEstado(rs.getInt("estado"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al buscar clientes en ClienteDAO: " + e.getMessage());
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
                System.err.println("Error al cerrar conexión en ClienteDAO.buscarClientesPorCriterio: " + e.getMessage());
            }
        }
        return clientes;
    }

}
