package controlador;

import dao.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Empleado;

public class EmpleadoController {

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

    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();

        String sql = "SELECT e.*, r.tipo FROM empleado e INNER JOIN rol r ON e.idRol = r.idRol";

        try {
            PreparedStatement consulta = cn.prepareStatement(sql);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setCedula(rs.getString("cedula"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellido(rs.getString("apellido"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setCorreo(rs.getString("correo"));
                emp.setEstado(rs.getInt("estado"));
                emp.setIdRol(rs.getInt("idRol"));

                lista.add(emp);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e);
        }

        return lista;
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

    public int obtenerIdEmpleadoPorIdUsuario(int idUsuario) {
        int idEmpleado = -1;
        Connection cn = Conexion.conectar();

        String sql = "SELECT idEmpleado FROM empleado WHERE idUsuario = ?";

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idEmpleado = rs.getInt("idEmpleado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener idEmpleado: " + e.getMessage());
        }

        return idEmpleado;
    }

}
