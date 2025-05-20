/**
 * 
 */
package Negocio.Nave;

import java.io.Serializable;
import java.util.Objects;

import Negocio.Habilidad.THabilidad;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author 
 * @generated "UML a Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */

public class TNave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private Integer capacidad;
	private String localizacion;
	private Boolean activo;

	public TNave() {

	}

	public TNave(Integer id, String localizacion, String nombre, Integer capacidad, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.localizacion = localizacion;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {

			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {

			return false;
		}
		TNave nave = (TNave) obj;
		return this.nombre.equals(nave.nombre) && this.activo.equals(nave.activo)
				&& this.capacidad.equals(nave.capacidad) && this.localizacion.equals(nave.localizacion);
	}
}