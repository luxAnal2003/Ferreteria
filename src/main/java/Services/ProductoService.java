/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author admin
 */

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();
    private int contadorId = 1;

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public void agregar(Producto p) {
        p.setId(contadorId++);
        productos.add(p);
    }

    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                return;
            }
        }
    }

    public void eliminar(int id) {
        productos.removeIf(p -> p.getId() == id);
    }
}

