package Negocio.Producto_Cafeteria;

public class TBebida extends TProductoCafeteria {

    public TBebida() {
        super();
    }
    
    public TBebida(Integer idProducto, String nombre, double precio, Integer stock, 
                  char nivelNutricion, Boolean activo, Integer nivelAzucar, Integer idMarca) {
        super(idProducto, nombre, precio, stock, nivelNutricion, activo, null, nivelAzucar, idMarca);
    }

    public TBebida(TProductoCafeteria tProducto) {
        super(tProducto.getId(), tProducto.getNombre(), tProducto.getPrecio(), 
              tProducto.getStock(), tProducto.getNivelNutricion(), tProducto.getActivo(), 
              null, tProducto.getNivelAzucar(), tProducto.getIdMarca());
    }
    
    public void setNivelAzucar(Integer nivelAzucar) {
        super.setNivelAzucar(nivelAzucar);
    }
    
    public Integer getNivelAzucar() {
        return super.getNivelAzucar();
    }
}
