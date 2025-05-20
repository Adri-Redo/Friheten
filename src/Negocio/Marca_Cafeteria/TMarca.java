/**
 * 
 */
package Negocio.Marca_Cafeteria;

import java.io.Serializable;


public class TMarca implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nombre;
	
	private String categoria;
	
	private Boolean activo;
	
	public TMarca() { }

	public TMarca(Integer id, String nombre, String categoria, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.activo = activo;
	}
	
	public Integer getId() {return this.id;	}

	
	public void setId(Integer id) {this.id = id; }

	
	public String getNombre() {return this.nombre;	}

	
	public void setNombre(String nombre) {this.nombre = nombre;	}

	
	public String getCategoria() {return this.categoria;	}

	
	public void setCategoria(String categoria) {this.categoria = categoria;}


	public Boolean getActivo() {return this.activo;	}

	
	public void setActivo(Boolean activo) {this.activo = activo;	}
	

}