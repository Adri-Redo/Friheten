/**
 * 
 */
package Negocio.Alergeno_Cafeteria;

import java.io.Serializable;
import java.util.Set;

import Negocio.Producto_Cafeteria.Producto;
import jakarta.persistence.*;



@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Alergeno.findByid", query = "select obj from Alergeno obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Alergeno.findByproductos", query = "select obj from Alergeno obj where :productos MEMBER OF obj.producto "),
		@NamedQuery(name = "Negocio.Alergeno.findByfuente", query = "select obj from Alergeno obj where :fuente = obj.fuente "),
		@NamedQuery(name = "Negocio.Alergeno.findByriesgo", query = "select obj from Alergeno obj where :riesgo = obj.riesgo "),
		@NamedQuery(name = "Negocio.Alergeno.findBynombre", query = "select obj from Alergeno obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Alergeno.findByactivo", query = "select obj from Alergeno obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.Alergeno.findAll", query = "select obj from Alergeno obj")})
public class Alergeno implements Serializable {

	private static final long serialVersionUID = 0;


	public Alergeno() {
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToMany
	private Set<Producto> producto;
	@Version
	private int version;
	private String fuente;
	private Integer riesgo;
	private String nombre;
	private Boolean activo;

	
	public Integer getId() {
		return this.id;
	}

	
	public String getFuente() {
		return this.fuente;
	}

	
	public Integer getRiesgo() {
		return riesgo;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	
	public void setRiesgo(Integer riesgo) {
		this.riesgo = riesgo;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Producto> getProductos(){
		return this.producto;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}
}