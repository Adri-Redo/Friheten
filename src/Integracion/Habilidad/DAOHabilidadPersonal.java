/**
 * 
 */
package Integracion.Habilidad;

import java.util.List;

import Negocio.Habilidad.THabilidadPersonal;

public interface DAOHabilidadPersonal {

	public int createHabilidadPersonal(THabilidadPersonal THabilidadPersonal);

	public THabilidadPersonal readHabilidadPersonal(int idPer, int idHab);

	public int deleteHabilidadPersonal(THabilidadPersonal THabilidadPersonal);

	public List<THabilidadPersonal> readAll();

	public List<THabilidadPersonal> readHabilidadVinculadaPersonal(int idHabilidad);

	public List<THabilidadPersonal> readPersonalVinculadoHabilidad(int idPersonal);

	int updateHabilidadPersonal(THabilidadPersonal tHabilidadPersonal);

}