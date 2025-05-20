/**
 * 
 */
package Negocio.Cliente;

import java.io.Serializable;

public abstract class TCliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String usuario;
	private String contrasena;
	private String correo;
	private Boolean empresa;
	private Boolean activo;
	
	public TCliente(int id, String usuario, String contrasena, String correo, Boolean empresa, Boolean activo) {
		this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.empresa = empresa;
        this.activo = activo;
        
	}
	
	public TCliente() {}

	public abstract String[] getAtributos();

	
	public static String[] getTipos() {
		String[] t = { "Empresa", "Individual" };
		return t;
	}
	
	public int getId() {
		return this.id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public String getCorreo() {
		return this.correo;
	}
	
	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Boolean getTipo() {
		return empresa;
	}

	public void setTipo(Boolean empresa) {
		this.empresa = empresa;
	}
	
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		TCliente cliente = (TCliente) obj; 
		
		return  this.id == cliente.getId() && 
				this.usuario.equals(cliente.getUsuario()) && 
				this.contrasena.equals(cliente.getContrasena()) && 
				this.correo.equals(cliente.getCorreo());
	}

	
	
}