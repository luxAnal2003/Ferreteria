package modelo;

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private String direccion;
    private String correo;
    private String rol;
    private int estado; // 1=activo, 0=eliminado

    public Cliente(int id, String cedula, String nombre, String direccion, String correo, String rol, int estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}