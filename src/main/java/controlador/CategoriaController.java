/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.CategoriaDAO;
import java.util.List;
import modelo.Categoria;

/**
 *
 * @author admin
 */
public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public String guardarCategoria(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre de la categoría no puede estar vacío";
        }

        if (categoriaDAO.existeCategoriaConNombre(nombre)) {
            return "La categoría ya existe";
        }

        Categoria cat = new Categoria();
        cat.setNombre(nombre.trim());
        cat.setEstado(1);

        boolean registrado = categoriaDAO.registrarCategoria(cat);
        return registrado ? "Categoría registrada correctamente" : "Error al registrar la categoría";
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public String actualizarCategoria(String nombre, int idCategoria) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre de la categoría no puede estar vacío";
        }

        Categoria cat = new Categoria();
        cat.setNombre(nombre.trim());
        cat.setEstado(1);

        boolean actualizado = categoriaDAO.actualizarCategoria(cat, idCategoria);
        return actualizado ? "Categoría actualizada correctamente" : "Error al actualizar la categoría";
    }

    public String desactivarCategoria(int idCategoria) {
        Categoria cat = categoriaDAO.obtenerCategoriaPorId(idCategoria);
        if (cat == null) {
            return "La categoría no existe.";
        }

        if (cat.getEstado() == 0) {
            return "La categoría ya está desactivada.";
        }

        boolean resultado = categoriaDAO.cambiarEstado(idCategoria, 0);
        return resultado ? "Categoría desactivada correctamente." : "Error al desactivar la categoría.";
    }

    public String activarCategoria(int idCategoria) {
        Categoria cat = categoriaDAO.obtenerCategoriaPorId(idCategoria);
        if (cat == null) {
            return "La categoría no existe.";
        }

        if (cat.getEstado() == 1) {
            return "La categoría ya está activa.";
        }

        boolean resultado = categoriaDAO.cambiarEstado(idCategoria, 1);
        return resultado ? "Categoría activada correctamente." : "Error al activar la categoría.";
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        return categoriaDAO.obtenerCategoriaPorId(idCategoria);
    }
    
    public boolean existenCategoriasEnSistema() {
        return categoriaDAO.existenCategorias();
    }
}
