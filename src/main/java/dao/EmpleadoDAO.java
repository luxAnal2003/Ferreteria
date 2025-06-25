package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAO {

    public boolean guardar(Empleado empleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "INSERT INTO empleado (idRol, cedula, direccion,estado,idUsuario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, empleado.getIdRol());
            consulta.setString(2, empleado.getCedula());
            consulta.setString(3, empleado.getDireccion());
            consulta.setInt(4, empleado.getEstado());
            consulta.setInt(5, empleado.getIdUsuario());

            respuesta = consulta.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar empleado: " + e);
        }
        return respuesta;
    }

    public boolean actualizar(Empleado empleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE empleado SET idRol = ?, cedula = ?, direccion = ?, estado = ? WHERE idEmpleado = ?";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, empleado.getIdRol());
            consulta.setString(2, empleado.getCedula());
            consulta.setString(3, empleado.getDireccion());
            consulta.setInt(4, empleado.getEstado());
            consulta.setInt(5, empleado.getIdEmpleado());

            respuesta = consulta.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e);
        }

        return respuesta;
    }

    public boolean desactivar(int idEmpleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "UPDATE empleado SET estado = 0 WHERE idEmpleado = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idEmpleado);
            respuesta = pst.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al desactivar empleado: " + e);
        }
        return respuesta;
    }

    public boolean activar(int idEmpleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE empleado SET estado = 1 WHERE idEmpleado = ?");
            consulta.setInt(1, idEmpleado);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al activar empleado: " + e);
        }

        return respuesta;
    }

    public boolean existeEmpleado(String empleado) {
        boolean respuesta = false;
        String sql = "select cedula from empleado where cedula = '" + empleado + "'";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar empleado: " + e);
        }
        return respuesta;
    }

    public List<Object[]> obtenerEmpleadosConUsuario() {
        List<Object[]> empleados = new ArrayList<>();
        String sql = "SELECT e.idEmpleado, e.cedula, e.direccion, e.idRol AS empleado_idRol, e.estado AS empleado_estado, e.idUsuario, "
                + "u.nombre, u.apellido, u.telefono, u.correo, u.usuario, u.contrasenia, r.tipo AS rol, u.estado AS usuario_estado "
                + "FROM empleado e "
                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
                + "INNER JOIN rol r ON u.idRol = r.idRol "
                + "WHERE r.idRol = 2";

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = new Object[12];
                fila[0] = rs.getInt("idEmpleado");
                fila[1] = rs.getString("cedula");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                fila[4] = rs.getString("telefono");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("correo");
                fila[7] = rs.getString("usuario");
                fila[8] = rs.getString("contrasenia");
                fila[9] = rs.getString("rol");
                fila[10] = rs.getInt("usuario_estado") == 1 ? "Activo" : "Inactivo";
                fila[11] = rs.getInt("idUsuario");

                empleados.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener empleados con usuario en EmpleadoDAO: " + e.getMessage());
        }
        return empleados;
    }

    public int obtenerIdUsuarioPorIdEmpleado(int idEmpleado) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idUsuario = -1;
        String sql = "SELECT idUsuario FROM Empleado WHERE idEmpleado = ?";
        try {
            con = Conexion.conectar();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idEmpleado);
            rs = pst.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener idUsuario por idEmpleado en EmpleadoDAO: " + e.getMessage());
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
                System.err.println("Error al cerrar conexión en obtenerIdUsuarioPorIdEmpleado de EmpleadoDAO: " + e.getMessage());
            }
        }
        return idUsuario;
    }

    public Empleado getEmpleadoById(int idEmpleado) {
        Empleado empleado = null;

        String sql = "SELECT * FROM Empleado WHERE idEmpleado = ?";

        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setIdRol(rs.getInt("idRol"));
                empleado.setCedula(rs.getString("cedula"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setEstado(rs.getInt("estado"));
                empleado.setIdUsuario(rs.getInt("idUsuario"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener empleado por ID: " + e.getMessage());
        }

        return empleado;
    }

    public List<Object[]> obtenerEmpleadosActivos() {
        List<Object[]> empleados = new ArrayList<>();
        String sql = "SELECT e.idEmpleado, u.nombre, u.apellido, r.tipo AS rol, e.cedula, e.direccion, u.telefono, u.estado "
                + "FROM Empleado e "
                + "INNER JOIN Usuario u ON e.idUsuario = u.idUsuario "
                + "INNER JOIN Rol r ON e.idRol = r.idRol "
                + "WHERE u.estado = 1";

        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idEmpleado");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("rol");
                fila[4] = rs.getString("cedula");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getInt("estado") == 1 ? "Activo" : "Inactivo";

                empleados.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public List<Object[]> buscarEmpleados(String criterio) {
        List<Object[]> empleados = new ArrayList<>();
        String sql = "SELECT e.idEmpleado, u.nombre, u.apellido, r.tipo AS rol, e.cedula, e.direccion, u.telefono, u.estado "
                + "FROM Empleado e "
                + "INNER JOIN Usuario u ON e.idUsuario = u.idUsuario "
                + "INNER JOIN Rol r ON e.idRol = r.idRol "
                + "WHERE u.estado = 1 AND (u.nombre LIKE ? OR u.apellido LIKE ? OR e.cedula LIKE ?)";

        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            String valor = "%" + criterio + "%";
            pst.setString(1, valor);
            pst.setString(2, valor);
            pst.setString(3, valor);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idEmpleado");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("rol");
                fila[4] = rs.getString("cedula");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getInt("estado") == 1 ? "Activo" : "Inactivo";

                empleados.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public int contarEmpleadosActivos() {
        String sql = "SELECT COUNT(*) FROM Empleado e INNER JOIN Usuario u ON e.idUsuario = u.idUsuario WHERE u.estado = 1";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int obtenerIdEmpleadoPorUsuario(int idUsuario) {
        int idEmpleado = -1;
        String sql = "SELECT idEmpleado FROM empleado WHERE idUsuario = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idEmpleado = rs.getInt("idEmpleado");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener idEmpleado por idUsuario: " + e.getMessage());
        }
        return idEmpleado;
    }

}
