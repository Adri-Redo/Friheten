package Negocio.Personal;

import java.io.Serializable;

public abstract class TPersonal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nif;
	private String nombre;
	private String apellido;
	private int id;
	private int idNave;
	private boolean activo;
	private String cargo;
	private int nomina;
	private int responsabilidades;
	private int bonificaciones;

	public TPersonal() {}

	public TPersonal(String nif, String nombre, String apellido, int id, int idNave, boolean activo, String cargo,int nomina,int bonificaciones
			,int responsabilidades) {

		this.nif = nif;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.idNave = idNave;
		this.activo = activo;
		this.cargo=cargo;
		this.nomina = nomina;
		this.bonificaciones = bonificaciones;
		this.responsabilidades = responsabilidades;
	}

	public abstract String[] getAtributos();

	public static String[] getTipos() {
		String[] t = { "Empleado", "Jefe" };
		return t;
	}

	public String getNif() {

		return this.nif;
	}

	public void setNif(String nif) {

		this.nif = nif;
	}

	public String getNombre() {

		return this.nombre;

	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public String getApellido() {

		return this.apellido;

	}

	public void setApellido(String apellido) {

		this.apellido = apellido;
	}

	public int getId() {

		return this.id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getIdNave() {

		return this.idNave;
	}

	public void setIdNave(int id) {

		this.idNave = id;
	}

	public boolean getActivo() {

		return this.activo;
	}

	public void setActivo(boolean activo) {

		this.activo = activo;
	}
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || this.getClass() != obj.getClass()) {

			return false;
		}
		TPersonal personal = (TPersonal) obj;

		return this.nif.equals(personal.getNif()) && this.id == personal.getId() && this.nombre.equals(personal.getNombre())
				&& this.apellido.equals(personal.getApellido()) && this.idNave == personal.getIdNave();

	}

	public int getNomina() {
		return nomina;
	}

	public void setNomina(int nomina) {
		this.nomina = nomina;
	}

	public int getResponsabilidades() {
		return responsabilidades;
	}

	public void setResponsabilidades(int responsabilidades) {
		this.responsabilidades = responsabilidades;
	}

	public int getBonificaciones() {
		return bonificaciones;
	}

	public void setBonificaciones(int bonificaciones) {
		this.bonificaciones = bonificaciones;
	}

}
