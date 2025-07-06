package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.Rol;

public class EmpleadoDAO {

    public boolean registrarEmpleado(Empleado empleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        String sql = "INSERT INTO empleado (idRol, cedula, direccion,estado,idUsuario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, empleado.getIdRol().getIdRol());
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

    public boolean actualizarEmpleado(Empleado empleado, int idEmpleado) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE empleado SET idRol = ?, cedula = ?, direccion = ?, estado = ? WHERE idEmpleado = ?"
            );
            consulta.setInt(1, empleado.getIdRol().getIdRol());
            consulta.setString(2, empleado.getCedula());
            consulta.setString(3, empleado.getDireccion());
            consulta.setInt(4, empleado.getEstado());
            consulta.setInt(5, idEmpleado);

            respuesta = consulta.executeUpdate() > 0;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e);
        }

        return respuesta;
    }

    public boolean cambiarEstado(int idEmpleado, int estado) {
        String sql = "UPDATE empleado SET estado = ? WHERE idEmpleado = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, idEmpleado);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
                + "INNER JOIN rol r ON u.idRol = r.idRol ";

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

    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        Empleado empleado = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT e.idEmpleado, e.cedula, e.direccion, e.estado AS estadoEmpleado, e.idUsuario, "
                + "u.nombre, u.apellido, u.telefono, u.correo, u.usuario, u.contrasenia, u.estado AS estadoUsuario, u.idRol, "
                + "r.tipo AS tipoRol "
                + "FROM empleado e "
                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
                + "INNER JOIN rol r ON u.idRol = r.idRol "
                + "WHERE e.idEmpleado = ?";

        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEmpleado);
            rs = stmt.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setCedula(rs.getString("cedula"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setEstado(rs.getInt("estadoEmpleado"));
                empleado.setIdUsuario(rs.getInt("idUsuario"));
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setTipo(rs.getString("tipoRol"));
                empleado.setIdRol(rol);

            }

        } catch (SQLException e) {
            System.err.println("Error al obtener empleado por ID con datos relacionados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en obtenerEmpleadoPorId: " + e.getMessage());
            }
        }

        return empleado;
    }

    public List<Object[]> obtenerEmpleadosActivos() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.idEmpleado, e.idRol, e.cedula, u.nombre, u.apellido, u.telefono, "
                + "u.correo, e.direccion, u.usuario, u.contrasenia, u.estado, e.idUsuario "
                + "FROM empleado e "
                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
                + "WHERE u.estado = 1";

        try (Connection cn = Conexion.conectar(); PreparedStatement pst = cn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[12];
                fila[0] = rs.getInt("idEmpleado");
                fila[1] = rs.getString("cedula");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                fila[4] = rs.getString("telefono");
                fila[5] = rs.getString("correo");
                fila[6] = rs.getString("direccion");
                fila[7] = rs.getString("usuario");
                fila[8] = rs.getString("contrasenia");
                fila[9] = rs.getInt("idRol");
                fila[10] = rs.getInt("estado");
                fila[11] = rs.getInt("idUsuario");
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener empleados activos: " + e.getMessage());
        }

        return lista;
    }

//    public List<Empleado> buscarEmpleados(String criterio) {
//        List<Empleado> empleados = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        String sql = "SELECT e.idEmpleado, e.idRol, e.cedula, u.nombre, u.apellido, u.telefono, "
//                + "u.correo, e.direccion, u.usuario, u.contrasenia, u.estado, e.idUsuario "
//                + "FROM empleado e "
//                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
//                + "WHERE u.nombre LIKE ? OR u.apellido LIKE ? OR e.cedula LIKE ?";
//
//        try {
//            con = Conexion.conectar();
//            pst = con.prepareStatement(sql);
//            String busquedaLike = "%" + criterio.toLowerCase() + "%";
//            pst.setString(1, busquedaLike);
//            pst.setString(2, busquedaLike);
//            pst.setString(3, busquedaLike);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                Empleado empleado = new Empleado();
//                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
//                empleado.setCedula(rs.getString("cedula"));
//                empleado.setNombre(rs.getString("nombre"));
//                empleado.setApellido(rs.getString("apellido"));
//                empleado.setTelefono(rs.getString("telefono"));
//                empleado.setDireccion(rs.getString("direccion"));
//                empleado.setCorreo(rs.getString("correo"));
//                empleado.setEstado(rs.getInt("estado"));
//                empleados.add(empleado);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error SQL al buscar empleado: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                System.err.println("Error al cerrar conexión: " + e.getMessage());
//            }
//        }
//        return empleados;
//    }
//    public List<Object[]> buscarEmpleados(String criterio) {
//        List<Object[]> lista = new ArrayList<>();
//        String sql = "SELECT e.idEmpleado, e.cedula, u.nombre, u.apellido, u.telefono, "
//                + "       u.correo, e.direccion, u.usuario, u.contrasenia, "
//                + "       r.tipo AS rol, u.estado "
//                + "FROM empleado e "
//                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
//                + "INNER JOIN rol r ON u.idRol = r.idRol "
//                + "WHERE u.nombre LIKE ? OR u.apellido LIKE ? OR e.cedula LIKE ?";
//
//        try (Connection cn = Conexion.conectar(); PreparedStatement pst = cn.prepareStatement(sql)) {
//            String valor = "%" + criterio + "%";
//            pst.setString(1, valor);
//            pst.setString(2, valor);
//            pst.setString(3, valor);
//
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                Object[] fila = new Object[12];
//                fila[0] = rs.getInt("idEmpleado");
//                fila[1] = rs.getString("cedula");
//                fila[2] = rs.getString("nombre");
//                fila[3] = rs.getString("apellido");
//                fila[4] = rs.getString("telefono");
//                fila[5] = rs.getString("correo");
//                fila[6] = rs.getString("direccion");
//                fila[7] = rs.getString("usuario");
//                fila[8] = rs.getString("contrasenia");
//                fila[9] = rs.getString("rol");
//                fila[10] = rs.getInt("estado");
//                fila[11] = rs.getInt("idUsuario");
//                lista.add(fila);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al buscar empleados: " + e.getMessage());
//        }
//
//        return lista;
//    }
    public List<Object[]> buscarEmpleados(String criterio) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.idEmpleado, e.cedula, e.direccion, e.idRol AS empleado_idRol, e.estado AS empleado_estado, e.idUsuario, "
                + "u.nombre, u.apellido, u.telefono, u.correo, u.usuario, u.contrasenia, r.tipo AS rol, u.estado AS usuario_estado "
                + "FROM empleado e "
                + "INNER JOIN usuario u ON e.idUsuario = u.idUsuario "
                + "INNER JOIN rol r ON u.idRol = r.idRol "
                + "WHERE u.nombre LIKE ? OR u.apellido LIKE ? OR e.cedula LIKE ?";

        try (Connection cn = Conexion.conectar(); PreparedStatement pst = cn.prepareStatement(sql)) {
            String valor = "%" + criterio + "%";
            pst.setString(1, valor);
            pst.setString(2, valor);
            pst.setString(3, valor);

            ResultSet rs = pst.executeQuery();

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

                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar empleados: " + e.getMessage());
        }

        return lista;
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
