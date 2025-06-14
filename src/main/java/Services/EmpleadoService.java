//package Services;
//
//import modelo.Empleado;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class EmpleadoService {
//
//    private List<Empleado> empleados = new ArrayList<>();
//    private int contadorId = 1;
//    
//    public List<Empleado> obtenerTodos() {
//        return empleados;
//    }
//
//    public void agregar(Empleado e) {
//        e.setIdUsuario(contadorId++);
//        empleados.add(e);
//    }
//
//    public Empleado buscarPorId(int id) {
//        return empleados.stream()
//                .filter(emp -> emp.getIdUsuario() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void actualizar(Empleado empActualizado) {
//        for (int i = 0; i < empleados.size(); i++) {
//            if (empleados.get(i).getIdUsuario() == empActualizado.getIdUsuario()) {
//                empleados.set(i, empActualizado);
//                return;
//            }
//        }
//    }
//
//    public void eliminar(int id) {
//        empleados.removeIf(emp -> emp.getIdUsuario() == id);
//    }
//}
