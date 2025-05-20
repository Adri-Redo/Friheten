/**
 * 
 */
package Negocio.Marca_Cafeteria;

import java.util.List;
import java.util.Set;
import Negocio.Nave.TNave;


public interface SAMarca {
	
	public Integer create(TMarca tmarca);

	
	public TMarca read(Integer id);

	
	public Integer update(TMarca tmarca);

	
	public Integer delete(Integer id);

	
	public List<TMarca> readAll();
}