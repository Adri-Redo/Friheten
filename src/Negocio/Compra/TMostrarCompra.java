package Negocio.Compra;

import java.util.Objects;
import java.util.Set;

import Negocio.Cliente.TCliente;
import Negocio.Personal.TPersonal;

public class TMostrarCompra {
	
	private TPersonal personal;
	private TCliente cliente;
	private TCompra compra;
	private Set<TLineaCompra> lineas;
	// no tiene una lista de muebles ya que toda la informacion que queremos mostrar de los muebles esta en las lineas de compra
	
	
	
	public TMostrarCompra(TPersonal p, TCliente c, TCompra compra, Set<TLineaCompra> lineas) {
		personal = p;
		cliente = c;
		this.compra = compra;
		this.lineas = lineas;
		
	}
	
	public TPersonal getPersonal()
	{
		return personal;
	}
	
	public TCliente getCliente()
	{
		return cliente;
	}
	
	public Set<TLineaCompra> getMuebles()
	{
		return lineas;
	}
	
	public TCompra getCompra()
	{
		return compra;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TMostrarCompra that = (TMostrarCompra) o;
		return Objects.equals(personal, that.personal) &&
			   Objects.equals(cliente, that.cliente) &&
			   Objects.equals(compra, that.compra) &&
			   Objects.equals(lineas, that.lineas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personal, cliente, compra, lineas);
	}
	
}
