package modelo;

public class Cliente extends Usuario{
    private int idCliente;
    private String cedula;
    private String direccion;

    public Cliente(){
        this.idCliente = 0;
        this.cedula = "";
        this.direccion  ="";
    }
    public Cliente(int idCliente, String cedula, String direccion) {
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
    public int getIdRol() {
        return idRol;
    }

    @Override
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String getTipoRol() {
        return tipoRol;
    }

    @Override
    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
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