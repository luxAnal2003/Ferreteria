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

    public boolean guardarCategoria(Categoria categoria) {
        return categoriaDAO.guardar(categoria);
    }

    public boolean actualizarCategoria(Categoria categoria, int idCategoria) {
        return categoriaDAO.actualizar(categoria, idCategoria);
    }

    public boolean desactivarCategoria(int idCategoria) {
        return categoriaDAO.desactivar(idCategoria);
    }

    public boolean activarCategoria(int idCategoria) {
        return categoriaDAO.activar(idCategoria);
    }

    public boolean existeCategoriaPorDescripcion(String descripcionCategoria) {
        return categoriaDAO.existeCategoria(descripcionCategoria);
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        return categoriaDAO.obtenerCategoriaPorId(idCategoria);
    }
}
