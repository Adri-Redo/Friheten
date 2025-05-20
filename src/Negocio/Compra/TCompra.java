/**
 * 
 */
package Negocio.Compra;

import java.io.Serializable;
import java.util.Objects;


public class TCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String fecha;

	private Boolean activo;

	//private java.io.Serializable serializable;

	private Integer idCliente;
	private Integer idPersonal;
	
	private Integer precioTotal;
	
	public TCompra() {
		
	}
	public TCompra(Integer id, String fecha, Integer idCliente, Integer idPersonal, Integer precioTotal, Boolean activo) {
		
		this.id = id;
		this.fecha = fecha;
		this.activo = activo;
		this.idCliente = idCliente;
		this.idPersonal = idPersonal;
		this.setPrecioTotal(precioTotal);
		
	}
	
	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setIdCliente(Integer id) {
		this.idCliente = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Integer getIdPersonal() {
		return idPersonal;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPrecioTotal() {
		return precioTotal;
	}
	
	public void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    TCompra tCompra = (TCompra) obj;
	    return Objects.equals(id, tCompra.id) &&
	           Objects.equals(fecha, tCompra.fecha) &&
	           Objects.equals(activo, tCompra.activo) &&
	           Objects.equals(idCliente, tCompra.idCliente) &&
	           Objects.equals(idPersonal, tCompra.idPersonal);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, fecha, activo, idCliente, idPersonal);
	}
	
	
}