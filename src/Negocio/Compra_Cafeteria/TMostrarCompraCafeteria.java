package Negocio.Compra_Cafeteria;

import java.util.Objects;
import java.util.Set;

import Negocio.Personal.TPersonal;

public class TMostrarCompraCafeteria {
	
	private TPersonal personal;
	private TCompraCafeteria compra;
	private Set<TLineaCompraCafeteria> lineas;
	// no tiene una lista de muebles ya que toda la informacion que queremos mostrar de los muebles esta en las lineas de compra
	
	
	
	public TMostrarCompraCafeteria(TPersonal p, TCompraCafeteria compra, Set<TLineaCompraCafeteria> lineas) {
		personal = p;
		this.compra = compra;
		this.lineas = lineas;
		
	}
	
	public TPersonal getPersonal()
	{
		return personal;
	}
	
	
	public Set<TLineaCompraCafeteria> getProductos()
	{
		return lineas;
	}
	
	public TCompraCafeteria getCompra()
	{
		return compra;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TMostrarCompraCafeteria that = (TMostrarCompraCafeteria) o;
		return Objects.equals(personal, that.personal) &&
			   Objects.equals(compra, that.compra) &&
			   Objects.equals(lineas, that.lineas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personal, compra, lineas);
	}
	
}
