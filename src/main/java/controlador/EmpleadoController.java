/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EmpleadoDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import java.util.List;
import modelo.Empleado;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author admin
 */
public class EmpleadoController {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private RolDAO rolDAO = new RolDAO();

    public EmpleadoController() {
        this.empleadoDAO = new EmpleadoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.rolDAO = new RolDAO();
    }

    public List<Rol> obtenerRoles() {
        return rolDAO.listarRoles();
    }

    public String guardarEmpleadoConUsuario(String cedula, String nombres, String apellidos, String telefono,
            String email, String direccion, String nombreUsuario, String contrasenia, String rol, int estado) {

        int idRol = rolDAO.getIdRolPorNombre(rol);

        if (idRol == -1) {
            return "No se pudo encontrar ID de rol";
        }

        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty()
                || direccion.isEmpty() || nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        if (!cedula.matches("\\d{10}")) {
            return "La cédula debe tener exactamente 10 caracteres numéricos";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe tener exactamente 10 caracteres numéricos";
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido";
        }

        if (empleadoDAO.existeEmpleado(cedula)) {
            return "La cédula ingresada ya pertenece a otro empleado";
        }

        if (contrasenia.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }

        Rol rolSeleccionado = new Rol();
        rolSeleccionado.setIdRol(idRol);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombres);
        usuario.setApellido(apellidos);
        usuario.setTelefono(telefono);
        usuario.setCorreo(email);
        usuario.setUsuario(nombreUsuario);
        usuario.setContrasenia(contrasenia);
        usuario.setIdRol(rolSeleccionado);
        usuario.setEstado(estado);

        Empleado empleado = new Empleado();
        empleado.setCedula(cedula);
        empleado.setDireccion(direccion);
        empleado.setIdRol(rolSeleccionado);
        empleado.setEstado(estado);

        int idUsuarioGenerado = usuarioDAO.registrarUsuario(usuario);
        if (idUsuarioGenerado != -1) {
            empleado.setIdUsuario(idUsuarioGenerado);
            boolean registrado = empleadoDAO.registrarEmpleado(empleado);
            return registrado ? "Empleado registrado correctamente" : "Error al registrar el empleado";
        }
        return "Error al registrar el usuario";
    }

    public String actualizarEmpleadoConUsuario(String cedula, String nombres, String apellidos, String telefono,
            String email, String direccion, String nombreUsuario, String contrasenia, String rol, int estado, int idEmpleado, int idUsuario) {

        int idRol = rolDAO.getIdRolPorNombre(rol);

        if (idRol == -1) {
            return "No se pudo encontrar ID de rol";
        }

        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty()
                || direccion.isEmpty() || nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            return "Todos los campos son obligatorios";
        }

        if (contrasenia.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }

        if (!cedula.matches("\\d{10}")) {
            return "La cédula debe tener exactamente 10 caracteres numéricos";
        }

        if (!telefono.matches("\\d{10}")) {
            return "El teléfono debe tener exactamente 10 caracteres numéricos";
        }

        if (!email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Formato de Email inválido.";
        }

        Rol rolSeleccionado = new Rol();
        rolSeleccionado.setIdRol(idRol);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombres);
        usuario.setApellido(apellidos);
        usuario.setTelefono(telefono);
        usuario.setCorreo(email);
        usuario.setUsuario(nombreUsuario);
        usuario.setContrasenia(contrasenia);
        usuario.setIdRol(rolSeleccionado);
        usuario.setEstado(estado);

        Empleado empleado = new Empleado();
        empleado.setCedula(cedula);
        empleado.setDireccion(direccion);
        empleado.setIdRol(rolSeleccionado);
        empleado.setEstado(estado);

        boolean usuarioActualizado = usuarioDAO.actualizarUsuario(usuario, idUsuario);
        boolean empleadoActualizado = empleadoDAO.actualizarEmpleado(empleado, idEmpleado);

        boolean actualizado = usuarioActualizado && empleadoActualizado;
        return actualizado ? "Empleado actualizado correctamente" : "Error al actualizado el empleado";
    }

    //Para enviar datos de empleado y editar
    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        return empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
    }
    
    //Para enviar datos de empleado y editar
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.obtenerUsuarioPorId(idUsuario);
    }
    
    //Para enviar datos de empleado y editar
    public int obtenerIdUsuarioAsociadoAEmpleado(int idEmpleado) {
        return empleadoDAO.obtenerIdUsuarioPorIdEmpleado(idEmpleado);
    }

    //Para cargar los datos en las tablas
    public List<Object[]> obtenerEmpleados() {
        return empleadoDAO.obtenerEmpleadosConUsuario();
    }

    //Para verificar que no existan mas empleados con la misma cédula
    public boolean existeCedulaEmpleado(String cedula) {
        return empleadoDAO.existeEmpleado(cedula);
    }

    //Desactivar el empleado
    public String desactivarEmpleado(int idEmpleado) {
        Empleado emp = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
        if (emp == null) {
            return "El empleado no existe";
        }

        if (emp.getEstado() == 0) {
            return "El empleado ya está desactivado";
        }

        boolean resultado = empleadoDAO.cambiarEstado(idEmpleado, 0);
        return resultado ? "Empleado desactivado correctamente" : "Error al desactivar el empleado";
    }

    //Activar el empleado
    public String activarEmpleado(int idEmpleado) {
        Empleado emp = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
        if (emp == null) {
            return "El empleado no existe";
        }

        if (emp.getEstado() == 1) {
            return "El empleado ya está activa";
        }

        boolean resultado = empleadoDAO.cambiarEstado(idEmpleado, 1);
        return resultado ? "Empleado activado correctamente" : "Error al activar el empleado";
    }
    //Para buscar empleados
    public List<Object[]> buscarEmpleados(String criterio) {
        return empleadoDAO.buscarEmpleados(criterio);
    }

    //Para verificar si hay empleados registrados en el sistema
    public boolean existenEmpleadosActivos() {
        return empleadoDAO.contarEmpleadosActivos() > 0;
    }

    //Para obtener el id del empleado que realizó una venta 
    public int obtenerIdEmp(int idEmpleado) {
        return empleadoDAO.obtenerIdEmpleadoPorUsuario(idEmpleado);
    }
}
