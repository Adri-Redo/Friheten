/**
 * 
 */
package Integracion.Nave;

import Negocio.Nave.TNave;

import java.util.List;


public interface DAONave {

	public Integer create(TNave nave);

	public TNave read(Integer id);

	public List<TNave> readAll();

	public Integer update(TNave nave);

	public Integer delete(Integer id);

	public TNave readByName(String name);
}