package Services;

import modelo.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoService {

    private List<Empleado> empleados = new ArrayList<>();

    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    public void agregar(Empleado e) {
        empleados.add(e);
    }

    public Empleado buscarPorId(int id) {
        return empleados.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Empleado empActualizado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empActualizado.getId()) {
                empleados.set(i, empActualizado);
                return;
            }
        }
    }

    public void eliminar(int id) {
        empleados.removeIf(emp -> emp.getId() == id);
    }
}
