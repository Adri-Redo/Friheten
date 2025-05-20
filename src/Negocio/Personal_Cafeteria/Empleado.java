package Negocio.Personal_Cafeteria;

import jakarta.persistence.Entity;

@Entity
public class Empleado extends PersonalCafeteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String puesto;
	private Integer bonificaciones;
	
	public Empleado() {
	}
	
	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto=puesto;
	}

	public Integer getBonificaciones() {
		return this.bonificaciones;
	}

	public void setBonificaciones(Integer bonificaciones) {
		this.bonificaciones=bonificaciones;
	}

	@Override
	public double calcularNomina() {
		return salarioBase + bonificaciones;
	}

	@Override
	public TPersonalCafeteria toTransfer() {
		return new TPersonalCafeteria(this.getId(), this.nif, this.nombre, this.apellidos, null, puesto, bonificaciones, false, null, salarioBase, turno.getIdTurno(), getActivo());
	}
}
