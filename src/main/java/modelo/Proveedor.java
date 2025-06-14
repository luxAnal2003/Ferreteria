///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package modelo;
//
//import java.time.LocalDateTime;
//
///**
// *
// * @author admin
// */
//public class Proveedor extends Usuario{
//    private String rol;
//    private int estado;
//    private String razonSocial;
//    private String tipoProducto;
//
//    public Proveedor(int id, String cedula, String razonSocial, String nombre, String telefono, String direccion, String correo, String tipoProducto) {
//        this.idUsuario=id;
//        this.cedula=cedula;
//        this.razonSocial =razonSocial;
//        this.nombre=nombre;
//        this.telefono=telefono;
//        this.direccion=direccion;
//        this.correo=correo;
//        this.tipoProducto=tipoProducto;
//    }
//
//    public Proveedor(String rol, int estado, String razonSocial, String tipoProducto,int id, String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
//        super(id, cedula, nombre, apellido, telefono, direccion, correo);
//        this.rol = rol;
//        this.estado = estado;
//        this.razonSocial=razonSocial;
//        this.tipoProducto=tipoProducto;
//    }
//
//    public Proveedor(String rol, int estado,String razonSocial, String tipoProducto, String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
//        super(cedula, nombre, apellido, telefono, direccion, correo);
//        this.rol = rol;
//        this.estado = estado;
//        this.razonSocial=razonSocial;
//        this.tipoProducto=tipoProducto;
//    }
//
//   
//
//    public String getRol() {
//        return rol;
//    }
//
//    public void setRol(String rol) {
//        this.rol = rol;
//    }
//
//    public int getEstado() {
//        return estado;
//    }
//
//    public void setEstado(int estado) {
//        this.estado = estado;
//    }
//    
//    public String getRazonSocial(){
//        return razonSocial;
//    }
//    
//    public void setRazonSocial(String razonSocial){
//        this.razonSocial=razonSocial;
//    }
//    
//    public String getTipoProducto(){
//        return tipoProducto;
//    }
//    
//    public void setTipoProducto(String tipoProducto){
//        this.tipoProducto = tipoProducto;
//    }
//    
//    public int getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(int id) {
//        this.idUsuario = id;
//    }
//
//    public String getCedula() {
//        return cedula;
//    }
//
//    public void setCedula(String cedula) {
//        this.cedula = cedula;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getApellido() {
//        return apellido;
//    }
//
//    public void setApellido(String apellido) {
//        this.apellido = apellido;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public String getCorreo() {
//        return correo;
//    }
//
//    public void setCorreo(String correo) {
//        this.correo = correo;
//    }
//
//   
//    
//
//    @Override
//    public String toString() {
//        return "Proveedor{" +
//               super.toString() +
//               ", rol='" + rol + '\'' +
//               ", estado='" + estado + '\'' +
//               ", Razón social='" + razonSocial + '\'' +
//               ", tipoProducto='" + tipoProducto + '\'' +
//               '}';
//    }
//    
//}
