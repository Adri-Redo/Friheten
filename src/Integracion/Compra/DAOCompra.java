/**
 * 
 */
package Integracion.Compra;

import Negocio.Compra.TCompra;
import java.util.Set;

public interface DAOCompra {

	public Integer create(TCompra compra);

	public TCompra read(Integer id);

	public Set<TCompra> readAll();

	public Integer update(Integer id, TCompra compra);

	public Integer delete(Integer id);

	public Set<TCompra> readByIDCliente(Integer id);

	
}