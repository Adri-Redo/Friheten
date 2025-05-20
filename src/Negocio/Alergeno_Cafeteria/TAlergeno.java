/**
 * 
 */
package Negocio.Alergeno_Cafeteria;

import java.io.Serializable;


public class TAlergeno
		implements Serializable {
	
	private Integer IdAlergeno;
	
	private String fuente;
	
	private Integer riesgo;
	
	private String nombre;

	private Integer IdProducto;
	private boolean activo;


	public Integer getIdAlergeno() {
		return IdAlergeno;
		
	}

	
	public String getFuente() {
		return fuente;
		
	}

	
	public String getNombre() {
		return nombre;
		
	}

	
	public Integer getRiesgo() {
		return riesgo;
		
	}

	
	public Integer getIdProducto() {
		return IdProducto;
		
	}

	
	public void setIdAlergeno(Integer idAlergeno) {
		this.IdAlergeno = idAlergeno;
	}

	
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setRiesgo(Integer riesgo) {
		this.riesgo = riesgo;
	}

	
	public void setIdProducto(Integer idProducto) {
		this.IdProducto = idProducto;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}