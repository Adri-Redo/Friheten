package Negocio.Personal_Cafeteria;

import jakarta.persistence.Entity;

@Entity
public class Jefe extends PersonalCafeteria {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer responsabilidades;
	private String cargo;
	
	public Integer getResponsabilidades() {
		return this.responsabilidades;
	}

	public void setResponsabilidades(Integer responsabilidades) {
		this.responsabilidades=responsabilidades;
	}
	
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo=cargo;
	}

	@Override
	public double calcularNomina() {
		return salarioBase * responsabilidades;
	}
	
	@Override
	public TPersonalCafeteria toTransfer() {
		return new TPersonalCafeteria(this.getId(), this.nif, this.nombre, this.apellidos, responsabilidades, null, null, true, cargo, salarioBase, turno.getIdTurno(), getActivo());
	}
}
