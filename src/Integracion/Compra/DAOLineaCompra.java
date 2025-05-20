/**
 * 
 */
package Integracion.Compra;

import Negocio.Compra.TLineaCompra;
import java.util.Set;

public interface DAOLineaCompra {

	public Integer create(TLineaCompra lc);

	public TLineaCompra read(Integer idCompra, Integer idMueble);

	public Integer update(TLineaCompra lc);

	public Integer delete(Integer idCompra, Integer idMueble);

	public Set<TLineaCompra> readAll();

	public Set<TLineaCompra> readLineasCompraPorCompra(Integer idCompra);

	public Set<TLineaCompra> readLineasCompraPorMueble(Integer idMueble);
}