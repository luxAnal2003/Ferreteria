/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EmpleadoDAO;
import dao.UsuarioDAO;
import java.util.List;
import modelo.Empleado;
import modelo.Usuario;

/**
 *
 * @author admin
 */
public class EmpleadoController {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public EmpleadoController() {
        this.empleadoDAO = new EmpleadoDAO();
        this.usuarioDAO = new UsuarioDAO(); 
    }

    public boolean guardarEmpleadoYUsuario(Empleado empleado, Usuario usuario) {
        if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
            usuario.setNombre(usuario.getNombre().substring(0, 1).toUpperCase() + usuario.getNombre().substring(1).toLowerCase());
        }
        if (usuario.getApellido() != null && !usuario.getApellido().isEmpty()) {
            usuario.setApellido(usuario.getApellido().substring(0, 1).toUpperCase() + usuario.getApellido().substring(1).toLowerCase());
        }
        
        int idUsuarioGenerado = usuarioDAO.guardar(usuario); 
        
        if (idUsuarioGenerado != -1) {
            empleado.setIdUsuario(idUsuarioGenerado);
            return empleadoDAO.guardar(empleado); 
        }
        return false;
    }

    public boolean actualizarEmpleadoYUsuario(Empleado empleado, Usuario usuario) {
        if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
            usuario.setNombre(usuario.getNombre().substring(0, 1).toUpperCase() + usuario.getNombre().substring(1).toLowerCase());
        }
        if (usuario.getApellido() != null && !usuario.getApellido().isEmpty()) {
            usuario.setApellido(usuario.getApellido().substring(0, 1).toUpperCase() + usuario.getApellido().substring(1).toLowerCase());
        }

        
        boolean usuarioActualizado = usuarioDAO.actualizar(usuario); 
        boolean empleadoActualizado = empleadoDAO.actualizar(empleado); 

        return usuarioActualizado && empleadoActualizado;
    }

    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        return empleadoDAO.getEmpleadoById(idEmpleado);
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.getUsuarioById(idUsuario); 
    }
    
    public int obtenerIdUsuarioAsociadoAEmpleado(int idEmpleado) {
        return empleadoDAO.obtenerIdUsuarioPorIdEmpleado(idEmpleado);
    }
    
    
    public List<Object[]> obtenerEmpleados() {
        return empleadoDAO.obtenerEmpleadosConUsuario(); 
    }

    
    public boolean existeCedulaEmpleado(String cedula) {
        return empleadoDAO.existeEmpleado(cedula);
    }

    public boolean desactivarEmpleado(int idEmpleado) {
        return empleadoDAO.desactivar(idEmpleado);
    }

    public boolean activarEmpleado(int idEmpleado) {
        return empleadoDAO.activar(idEmpleado);
    }
    
    public List<Object[]> obtenerEmpleadosActivos() {
        return empleadoDAO.obtenerEmpleadosActivos();
    }

    public List<Object[]> buscarEmpleados(String criterio) {
        return empleadoDAO.buscarEmpleados(criterio);
    }

    public boolean existenEmpleadosActivos() {
        return empleadoDAO.contarEmpleadosActivos() > 0;
    }
   
    public int obtenerIdEmp(int idEmpleado) {
        return empleadoDAO.obtenerIdEmpleadoPorUsuario(idEmpleado);
    }
}
