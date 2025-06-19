package modelo;

public class Cliente{
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private int estado;
    private int idCliente;
    private String cedula;
    private String direccion;

    public Cliente(){
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.correo = "";
        this.estado = 0;
        this.cedula = "";
        this.idCliente = 0;
        this.cedula = "";
        this.direccion  ="";
    }

    public Cliente(String nombre, String apellido, String telefono, String correo, int estado, int idCliente, String cedula, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
    
}