package Negocio.Habilidad;

import java.io.Serializable;
import java.util.Objects;

public class THabilidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int id;
	private Boolean activo;

	public THabilidad(int id, String nombre, boolean activo) {

		this.nombre = nombre;
		this.id = id;
		this.activo = activo;

	}

	public String getNombre() {
		return this.nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(int id) {

		this.id = id;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return this.activo;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {

			return false;
		}
		THabilidad habilidad = (THabilidad) obj;
		return this.id == habilidad.id && this.nombre.equalsIgnoreCase(habilidad.nombre)
				&& Objects.equals(this.activo, habilidad.activo);

	}

}
