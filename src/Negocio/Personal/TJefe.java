package Negocio.Personal;

public class TJefe extends TPersonal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TJefe() {}

	public TJefe(String nif, String nombre, String apellido, int id, int idNave, boolean activo,int nomina,int responsabilidades,
			int bonificaciones) {

		super(nif, nombre, apellido, id, idNave, activo,null,nomina,bonificaciones,responsabilidades);
	}

	@Override
	public String[] getAtributos() {
		String[] at = { "Id", "Nif", "Nombre", "Apellido", "IdNave", "Activo","Nomina","Responsabilidades","Bonificaciones"};
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

		return super.equals(obj);

	}

}
