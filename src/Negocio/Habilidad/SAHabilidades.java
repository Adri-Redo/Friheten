package Negocio.Habilidad;

import java.util.List;

public interface SAHabilidades {

	public int create(THabilidad habilidad);

	public THabilidad read(int id);

	public List<THabilidad> readAll();

	public int update(THabilidad habilidad);

	public int delete(int id);

	public int createHabilidadPersonal(THabilidadPersonal habilidadPersonal);

	public int deleteHabilidadPersonal(THabilidadPersonal habilidadPersonal);

	public THabilidadPersonal readHabilidadPersonal(int idPer, int idHab);

	public List<THabilidadPersonal> readHabilidadVinculadaPersonal(int idHabilidad);

	public List<THabilidad> readHabilidadPorPersonal(int idPersonal);

}
