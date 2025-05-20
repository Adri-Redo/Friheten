package Negocio.Personal;

import java.util.List;

import Negocio.Habilidad.THabilidadPersonal;

public interface SAPersonal {

	public int create(TPersonal personal);

	public TPersonal read(int id);

	public List<TPersonal> readAll();

	public int update(TPersonal personal);

	public int delete(int id);

	public TPersonal readPersonalByNif(String nif);

	public List<TPersonal> readPersonalByNave(int idNave);

	public List<TPersonal> readPersonalByHabilidad(int idHabilidad);

	public List<THabilidadPersonal> readPersonaVinculadaHabilidad(int idPersonal);

	List<TPersonal> query(Object data);

}
