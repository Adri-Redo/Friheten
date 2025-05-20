/**
 * 
 */
package Negocio.Compra;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;


public class TCarrito {



	private Set<TLineaCarrito> lineasCarrito;

	private Integer idCliente;
	private Integer idPersonal;
	
	public TCarrito() {
		lineasCarrito = new HashSet<TLineaCarrito>();
	}
	
	public TCarrito(Integer idCliente, Integer idPersonal) {
		
		lineasCarrito = new HashSet<TLineaCarrito>();
		this.idCliente = idCliente;
		this.idPersonal = idPersonal;
		
	}
	
	public void addLineaCarrito(TLineaCarrito lc) {
		lineasCarrito.add(lc);
	}


	public Set<TLineaCarrito> getLineasCarrito() {
		
		return lineasCarrito;
		
	}

	public void deleteLineaCarrito(TLineaCarrito lc) {
		lineasCarrito.remove(lc);
	}
	
	


	public void setIdCliente(Integer id) {
		idCliente = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdPersonal(Integer id) {
		idPersonal = id;
	}

	public Integer getIdPersonal() {
	
		return idPersonal;
	}
	
	public TLineaCarrito get_LineaCarrito_por_nombre(String nombre)
	{
		TLineaCarrito linea = null;
		for(TLineaCarrito a: lineasCarrito)
		{
			if(a.getNombre().equals(nombre))
			{
				linea = a;
			}
		}
		return linea;
	}

	public void Operation1() {
	
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TCarrito tCarrito = (TCarrito) obj;
        return Objects.equals(lineasCarrito, tCarrito.lineasCarrito) &&
               Objects.equals(idCliente, tCarrito.idCliente) &&
               Objects.equals(idPersonal, tCarrito.idPersonal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineasCarrito, idCliente, idPersonal);
    }
}