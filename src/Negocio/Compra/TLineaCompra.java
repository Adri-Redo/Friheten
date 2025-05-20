/**
 * 
 */
package Negocio.Compra;

import java.util.Objects;

public class TLineaCompra {
	
	
	private Integer idMueble;
	
	private Integer idCompra;
	
	private Integer unidades;
	
	private double precio;
	
	public TLineaCompra() {
		
	}
	
	public TLineaCompra(Integer idMueble, Integer idCompra, Integer unidades, double precio) {
		
		this.idMueble = idMueble;
		this.idCompra = idCompra;
		this.unidades = unidades;
		this.precio = precio;
		
		
	}


	public void setIdMueble(Integer id) {
		this.idMueble = id;
	}


	public Integer getIdMueble() {
		
		return idMueble;
		
	}
	
	
	public void setIdCompra(Integer id) {
		this.idCompra = id;
	}

	
	public Integer getIdCompra() {
		
		return idCompra;
		
	}

	
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	
	public Integer getUnidades() {
		
		return unidades;
	
	}

	
	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public double getPrecio() {
		
		return precio;
		
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    TLineaCompra otraLineaCompra = (TLineaCompra) obj;
	    return Objects.equals(idMueble, otraLineaCompra.idMueble) &&
	           Objects.equals(idCompra, otraLineaCompra.idCompra) &&
	           Double.compare(otraLineaCompra.precio, precio) == 0 &&
	           Objects.equals(unidades, otraLineaCompra.unidades);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(idMueble, idCompra, precio, unidades);
	}

	

}