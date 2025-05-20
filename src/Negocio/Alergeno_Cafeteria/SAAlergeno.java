/**
 * 
 */
package Negocio.Alergeno_Cafeteria;

import java.util.Set;


public interface SAAlergeno {
	
	public Integer create(TAlergeno alergeno);

	
	public TAlergeno read(Integer id);

	
	public Integer update(TAlergeno alergeno);

	
	public Integer delete(Integer id);

	
	public Integer link(Integer idProducto, Integer idAlergeno);
	
	public Integer unLink(Integer idProducto,Integer idAlergeno);

	
	public Set<TAlergeno> readAll();

	
	public Set<TAlergeno> readbyProduct(Integer idProducto);
}