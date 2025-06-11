/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;



/**
 *
 * @author bdfz
 */
public class ProveedorService {
    private List<Proveedor> proveedores=new ArrayList<>();
    private int contadorId=1;
    
    public List<Proveedor> obtenerTodos(){
        return proveedores;
    }
    
    public void agregar(Proveedor p){
        p.setId(contadorId++);
        proveedores.add(p);
    }
    
    public Proveedor buscarPorId(int id){
        return proveedores.stream()
                .filter(prove->prove.getId()==id)
                .findFirst()
                .orElse(null);
    }
    
    public void actualizar(Proveedor proveActualizado){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getId() == proveActualizado.getId()) {
                proveedores.set(i, proveActualizado);
                return;
            }
        }
    }
    
    public void eliminar(int id) {
        proveedores.removeIf(prove -> prove.getId() == id);
    }
}
