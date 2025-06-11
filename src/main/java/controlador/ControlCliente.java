package controlador;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ControlCliente {
    private final List<Cliente> lista = new ArrayList<>();
    private int nextId = 1;

    public boolean crear(Cliente cliente) {
        cliente.setId(nextId++);
        cliente.setRol(cliente.getRol() != null ? cliente.getRol() : "administrador");
        cliente.setEstado(1);
        lista.add(cliente);
        return true;
    }

    public List<Cliente> listarActivos() {
        List<Cliente> activos = new ArrayList<>();
        for (Cliente c : lista) {
            if (c.getEstado() == 1) activos.add(c);
        }
        return activos;
    }

    public boolean actualizar(Cliente cliente) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            if (c.getId() == cliente.getId() && c.getEstado() == 1) {
                lista.set(i, cliente);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarLogico(int id) {
        for (Cliente c : lista) {
            if (c.getId() == id) {
                c.setEstado(0);
                return true;
            }
        }
        return false;
    }

    public Cliente obtenerPorId(int id) {
        for (Cliente c : lista) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}