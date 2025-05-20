package Negocio.Habilidad;

import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Habilidad.DAOHabilidad;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Personal.TPersonal;

public class SAHabilidadesImp implements SAHabilidades {

	@Override
	public int create(THabilidad habilidad) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			List<THabilidad> habilidades = FactoryIntgr.getInstance().generateDAOHabilidad().readAll();
			if (habilidades != null) {// Lista existe

				for (THabilidad th : habilidades) {

					if (th.getNombre().equals(habilidad.getNombre())) {

						if (th.getActivo() == false) {
							//si estaba inactivo lo pone a true
							habilidad.setActivo(true);
							habilidad.setId(th.getId());
							int result = FactoryIntgr.getInstance().generateDAOHabilidad().update(habilidad);
							nueva_transaccion.commit();
							return result;
						}

						else {
							nueva_transaccion.rollback();
							return -1;
						}

					}
				}

			}
			int result = FactoryIntgr.getInstance().generateDAOHabilidad().create(habilidad);
			nueva_transaccion.commit();
			return result;
		} catch (Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public THabilidad read(int id) {
		
		THabilidad hab;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			hab = FactoryIntgr.getInstance().generateDAOHabilidad().read(id);
			nueva_transaccion.commit();
			if(!hab.getActivo())
				return null;
			return hab;
		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<THabilidad> readAll() {
		
		List<THabilidad> habilidades1 = null;
		List<THabilidad> habilidades2 = new ArrayList<>();
		
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			habilidades1 = FactoryIntgr.getInstance().generateDAOHabilidad().readAll();
			nueva_transaccion.commit();
			
			for(THabilidad t: habilidades1) {
				
				if(t.getActivo()) {
					habilidades2.add(t);
				}
			}
			
			if(habilidades2.isEmpty()) {
				return null;
			}
			return habilidades2;
		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public int update(THabilidad habilidad) {

		boolean yaExiste = false;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			DAOHabilidad daoHab = FactoryIntgr.getInstance().generateDAOHabilidad();
			List<THabilidad> listaTransfers = daoHab.readAll();
			THabilidad hab = daoHab.read(habilidad.getId());

			for (THabilidad th : listaTransfers) {

				if (th.equals(habilidad)) {// Que no haya 2 habilidades con// el mismo nombre.
					yaExiste = true;
																							
					nueva_transaccion.rollback();
					return -2;
				}

			}

			if (!yaExiste && hab != null && hab.getActivo()) {

				hab.setNombre(habilidad.getNombre());

				int result = daoHab.update(hab);
				nueva_transaccion.commit();
				return result;
				
			}else
			{
				nueva_transaccion.rollback();
				return -1;
			}

			
				
			
		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		

	}

	@Override
	public int delete(int id) {

		/*
		 * SOLO SE PODRÁ BORRAR DE LA BASE DE DATOS UNA HABILIDAD EN EL CASO DE QUE NO
		 * ESTE VINCULADA A NINGUNA PERSONA
		 */
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			List<THabilidadPersonal> habilidadPersonal = FactoryIntgr.getInstance().generateDAOHabilidadPersonal()
					.readHabilidadVinculadaPersonal(id);
			THabilidad tHab = FactoryIntgr.getInstance().generateDAOHabilidad().read(id);

			if (tHab != null && tHab.getActivo()) {
				
				boolean vinculado = false;
				/* COMPROBAR SI NO ESTA VINCULADA A NINGUNA PERSONA */
				for(THabilidadPersonal habPer: habilidadPersonal)
				{
					if(habPer.getActivo()) {
						vinculado = true;
					}
				}
				if (!vinculado) {

					int result = FactoryIntgr.getInstance().generateDAOHabilidad().delete(id);
					nueva_transaccion.commit();
					return result;
				} else
				{
					nueva_transaccion.rollback();
					return -2;
				}
					

			}
			else
			{
				nueva_transaccion.rollback();
			}

			return -1;
			
		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int createHabilidadPersonal(THabilidadPersonal habilidadPersonal) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			THabilidad habilidad = FactoryIntgr.getInstance().generateDAOHabilidad().read(habilidadPersonal.getIdHabilidad());
			TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(habilidadPersonal.getIdPersonal());

			if (habilidad != null && personal != null && habilidad.getActivo() && personal.getActivo()) {

				/* AMBOS EXISTEN EN LA BASE DE DATOS. VER SI YA ESTAN ASIGNADOS O NO */
					
				THabilidadPersonal habPer_read =  FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(habilidadPersonal.getIdPersonal()
						, habilidadPersonal.getIdHabilidad());

				if (habPer_read == null) {
					/* SI NO EXISTE LO CREAMOS */

					int result = FactoryIntgr.getInstance().generateDAOHabilidadPersonal()
							.createHabilidadPersonal(habilidadPersonal);
					nueva_transaccion.commit();
					return result;

				} 
				else if(!habPer_read.getActivo())
				{
					habPer_read.setActivo(true);
					int result = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().updateHabilidadPersonal(habPer_read);
					nueva_transaccion.commit();
					return result;
				}
				else
				{
					/* YA EXISTE */
					nueva_transaccion.rollback();
					return -1;
				}

			} else if (habilidad == null) {
				nueva_transaccion.rollback();
				return -2;
			}

			/* PERSONAL NO EXISTE */
			nueva_transaccion.rollback();
			return -3;

		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public THabilidadPersonal readHabilidadPersonal(int idPer, int idHab) {
		THabilidadPersonal habPer = null;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			habPer = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(idPer, idHab);
			if(!habPer.getActivo())
				habPer = null;
			
			nueva_transaccion.commit();
			
		}catch(Exception e)
		{
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			
		}
		return habPer;
	}

	@Override
	public int deleteHabilidadPersonal(THabilidadPersonal habilidadPersonal) {
		Transaction nueva_transaccion = null;
		try {
			
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			
			THabilidadPersonal habilidadpersonal = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(habilidadPersonal.getIdPersonal()
					, habilidadPersonal.getIdHabilidad());
					

			THabilidad habilidad = FactoryIntgr.getInstance().generateDAOHabilidad().read(habilidadPersonal.getIdHabilidad());
			TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(habilidadPersonal.getIdPersonal());

			if (habilidad == null || !habilidad.getActivo()) {
				nueva_transaccion.rollback();
				return -2;
			}

			if (personal == null || !personal.getActivo()) {
				nueva_transaccion.rollback();
				return -3;
			}

			if (habilidadpersonal != null && habilidadpersonal.getActivo()) {
				
				int result = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().deleteHabilidadPersonal(habilidadpersonal);
				nueva_transaccion.commit();
				return result;
			}

			return -1;
			
		}catch(Exception e)
		{
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public List<THabilidadPersonal> readHabilidadVinculadaPersonal(int idHabilidad) {
		List<THabilidadPersonal> list_habPer = null;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			list_habPer = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadVinculadaPersonal(idHabilidad);
			nueva_transaccion.commit();
			
		}catch(Exception e)
		{
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			
		}
		 
		return list_habPer;
	}

	@Override
	public List<THabilidad> readHabilidadPorPersonal(int idPersonal) {

		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			TPersonal personal = FactoryIntgr.getInstance().generateDAOPersonal().read(idPersonal);
			
			if (personal != null && personal.getActivo()) {
				List<THabilidadPersonal> listaHabilidadesConEsaPersona = FactoryIntgr.getInstance()
						.generateDAOHabilidadPersonal().readPersonalVinculadoHabilidad(idPersonal);

				/* AHI YA TENEMOS LA LISTA DE HABILIDADES QUE TIENE UNA PERSONA */

				List<THabilidad> lista_habilidades = new ArrayList<>();
				THabilidad transferLeido;

				for (THabilidadPersonal tHP : listaHabilidadesConEsaPersona) {
					if(tHP.getActivo()) {
						transferLeido =  FactoryIntgr.getInstance().generateDAOHabilidad().read(tHP.getIdHabilidad());
						if(transferLeido.getActivo())
							lista_habilidades.add(transferLeido);
					}
					
				}
				
				nueva_transaccion.commit();
				return lista_habilidades;

			} else
				nueva_transaccion.rollback();
				return null;
		}catch(Exception e)
		{
			if(nueva_transaccion != null)
			{
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
	}

}
