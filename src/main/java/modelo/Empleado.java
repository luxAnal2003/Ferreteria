/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author admin
 */
public class Empleado extends Usuario{
    private int idEmpleado;
    private String cedula;
    private String direccion;

    public Empleado(){
        this.idEmpleado = 0;
        this.cedula = "";
        this.direccion = "";
    }
    
    public Empleado(int idEmpleado, String cedula, String direccion) {
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public Empleado(int idEmpleado, String cedula, String direccion, Rol idRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        super(idRol, usuario, contrasenia, nombre, apellido, telefono, correo, estado);
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public Empleado(int idEmpleado, String cedula, String direccion, int idUsuario, Rol idRol, String usuario, String contrasenia, String nombre, String apellido, String telefono, String correo, int estado) {
        super(idUsuario, idRol, usuario, contrasenia, nombre, apellido, telefono, correo, estado);
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public Rol getIdRol() {
        return idRol;
    }

    @Override
    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public String getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int getEstado() {
        return estado;
    }

    @Override
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
