package Negocio.Cliente;

public class TEmpresa extends TCliente {

	private static final long serialVersionUID = 1L;
	private String nomEmpresa;
	
	
	public TEmpresa(Integer id, String usuario, String contrasena, String correo, Boolean activo, String nom){
		super(id, usuario, contrasena, correo, true, activo);
		this.nomEmpresa = nom;
	}
	
	public TEmpresa() {
		super();
	}
	
	public String getNombre(){
		return this.nomEmpresa;
	}

	public void setNombre(String nom) {
		this.nomEmpresa = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		TEmpresa empresa = (TEmpresa) obj;
		
		return super.equals(obj) && this.nomEmpresa.equals(empresa.getNombre());
	}

	@Override
	public String[] getAtributos() {
		 String[] atributos = {"id", "usuario", "contrasena", "correo", "nombre empresa", "activo" };
	        return atributos;
	}

}
