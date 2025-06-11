package Services;

import modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentaService {

    private List<Venta> ventas = new ArrayList<>();
    private int contadorId = 1;

    public List<Venta> obtenerTodas() {
        return ventas;
    }

    public void agregar(Venta venta) {
        venta.setIdVenta(contadorId++);
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
