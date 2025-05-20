package Negocio.Personal;

public class TEmpleado extends TPersonal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TEmpleado() {}
	
	public TEmpleado(String nif, String nombre, String apellido, int id, int idNave, String cargo, boolean activo, int nomina, 
			int bonificaciones) {
		super(nif, nombre, apellido, id, idNave, activo,cargo,nomina,bonificaciones,0);
	}

	@Override
	public String[] getAtributos() {
		String[] at = { "Id", "Nif", "Nombre", "Apellido", "IdNave", "Cargo", "Activo","Nomina","Bonificaciones" };
		return at;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {

			return false;
		}
		TEmpleado empleado = (TEmpleado) obj;

		return super.equals(empleado) && this.getCargo().equals(empleado.getCargo());

	}

}
