package Negocio.Habilidad;

import java.io.Serializable;

public class THabilidadPersonal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPersonal;
	private int idHabilidad;
	private boolean activo;

	public THabilidadPersonal(int idPersonal, int idHabilidad, boolean activo) {

		this.idPersonal = idPersonal;
		this.idHabilidad = idHabilidad;
		this.setActivo(activo);

	}

	public int getIdHabilidad() {

		return this.idHabilidad;
	}

	public void setIdHabilidad(int id) {
		this.idHabilidad = id;
	}

	public int getIdPersonal() {

		return this.idPersonal;
	}

	public void setIdPersonal(int id) {
		this.idPersonal = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
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
		THabilidadPersonal habilidadPersonal = (THabilidadPersonal) obj;
		return this.idPersonal == habilidadPersonal.idPersonal && this.idHabilidad == habilidadPersonal.idHabilidad;

	}

}
