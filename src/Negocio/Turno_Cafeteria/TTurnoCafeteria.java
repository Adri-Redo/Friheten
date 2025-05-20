/**
 * 
 */
package Negocio.Turno_Cafeteria;

public class TTurnoCafeteria {
	
	private int id;
	private int num_horas;
	private String nombre_Turno;
	private boolean activo;

	public TTurnoCafeteria(int id, int numHoras, String nombreTurno, boolean activo) {
		this.id = id;
		this.nombre_Turno = nombreTurno;
		this.num_horas = numHoras;
		this.activo = activo;
	}
	
	public TTurnoCafeteria() {}

	public int getIdTurno() {
		return id;
	}

	public int getNumHoras() {
		return num_horas;
	}
	
	public String getNombreTurno() {
		return nombre_Turno;
	}

	public void setNumHoras(int numHoras) {
		this.num_horas = numHoras;
	}
	
	public void setNombreTurno(String nombreTurno) {
		this.nombre_Turno = nombreTurno;
	}

	public void setIdTurno(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}