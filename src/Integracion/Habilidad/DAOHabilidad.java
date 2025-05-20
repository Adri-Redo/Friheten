package Integracion.Habilidad;

import java.util.List;

import Negocio.Habilidad.THabilidad;

public interface DAOHabilidad {

	public int create(THabilidad habilidad);

	public THabilidad read(int id);

	public List<THabilidad> readAll();

	public int update(THabilidad habilidad);

	public int delete(int id);

	public THabilidad readByName(String nombre);

}
