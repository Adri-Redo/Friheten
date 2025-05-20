/**
 * 
 */
package Negocio.Compra;


public class TLineaCarrito {
	
	private String nombreProducto;
	
	private Integer idProducto;

	private Integer unidades;
	
	public TLineaCarrito() {
		
	}
	public TLineaCarrito(String nombreProducto, Integer idProducto, Integer unidades) {
		
		this.nombreProducto = nombreProducto;
		this.idProducto = idProducto;
		this.unidades = unidades;
		
		
	}


	public Integer getIdProducto() {
		return idProducto;
	}


	public Integer getUnidades() {
	
		return unidades;
		
	}
	
	public String getNombre()
	{
		return nombreProducto;
	}


	public void setIdProducto(Integer id) {
		idProducto = id;
	}
	
	public void setNombreProducto(String nombre) {
		nombreProducto = nombre;
	}

	
	public void setUnidades(Integer num) {
		this.unidades = num;
		
	}
}