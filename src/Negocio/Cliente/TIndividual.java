package Negocio.Cliente;

public class TIndividual extends TCliente {

	private static final long serialVersionUID = 1L;
	private int cod_postal;
	
	public TIndividual(Integer id, String usuario, String contrasena, String correo, Boolean activo,  int cod_postal) {
		super(id, usuario, contrasena, correo, false, activo);
		this.cod_postal = cod_postal;
	}

	public TIndividual() {
		super();
	}
	
	public int getCodigoPostal() {
		return this.cod_postal;
	}
	
	public String getCodigoPostalStr() {
		String cp = String.valueOf(cod_postal);
		while(cp.length() < 5) {
			cp = "0" + cp;
		}
		return cp;
	}
	
	public void setCodigoPostal(int codPostal) {
		this.cod_postal = codPostal;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		return super.equals(obj);
	}

	@Override
	public String[] getAtributos() {
		 String[] atributos = {"id", "usuario", "contrasena", "correo", "codigo postal", "activo"};
	        return atributos;
	}

	
}
