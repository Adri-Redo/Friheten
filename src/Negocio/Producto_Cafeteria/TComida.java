package Negocio.Producto_Cafeteria;

public class TComida extends TProductoCafeteria {

    public TComida() {
        super();
    }
    
    public TComida(Integer idProducto, String nombre, double precio, Integer stock, 
                  char nivelNutricion, Boolean activo, String tipoComida, Integer idMarca) {
        super(idProducto, nombre, precio, stock, nivelNutricion, activo, tipoComida, null, idMarca);
    }
    
    public TComida(TProductoCafeteria tProducto) {
        super(tProducto.getId(), tProducto.getNombre(), tProducto.getPrecio(), 
              tProducto.getStock(), tProducto.getNivelNutricion(), tProducto.getActivo(), 
              tProducto.getTipoComida(), null, tProducto.getIdMarca());
    }
    
    public void setTipoComida(String tipoComida) {
        super.setTipoComida(tipoComida);
    }
    
    public String getTipoComida() {
        return super.getTipoComida();
    }
}
