package Services;

import modelo.Empleado;
import modelo.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentaService {
    private List<Venta> ventas = new ArrayList<>();

    public List<Venta> obtenerTodos() {
        System.out.println(ventas);
        return ventas;
    }

    public void agregar(Venta venta) {
        ventas.add(venta);
    }

    public Venta buscarPorId(int id) {
        return ventas.stream()
                .filter(v -> v.getIdVenta() == id)
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Venta ventaActualizada) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getIdVenta() == ventaActualizada.getIdVenta()) {
                ventas.set(i, ventaActualizada);
                return;
            }
        }
    }

    public void eliminar(int id) {
        ventas.removeIf(v -> v.getIdVenta() == id);
    }
}
