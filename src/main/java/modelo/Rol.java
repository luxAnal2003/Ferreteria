/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author admin
 */
public class Rol {
    private int idRol;
    private String tipo;
    private int estado;

    public Rol() {
        this.idRol = 0;
        this.tipo = "";
        this.estado = 0;
    }

    public Rol(int idRol, String tipo, int estado) {
        this.idRol = idRol;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getNombre() {
        return tipo;
    }
}
