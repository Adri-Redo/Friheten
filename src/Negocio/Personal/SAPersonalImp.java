package Negocio.Personal;

import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Personal.DAOPersonal;
import Integracion.Queries.FactoryQuery;
import Integracion.Queries.Query;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Habilidad.THabilidad;
import Negocio.Habilidad.THabilidadPersonal;
import Negocio.Nave.TNave;

public class SAPersonalImp implements SAPersonal {
	
	@Override
	public int create(TPersonal tp1) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			List<TPersonal> personal = FactoryIntgr.getInstance().generateDAOPersonal().readAll();
			
			if (personal != null) {
				
				for (TPersonal p : personal) {

					if (p.getNif().equals(tp1.getNif())) {

						if (!p.getActivo()) {
							
							if(p.getCargo() != tp1.getCargo()) {
								nueva_transaccion.rollback();
								return -3;
							}
							tp1.setActivo(true);
							tp1.setId(p.getId());
							int result = FactoryIntgr.getInstance().generateDAOPersonal().update(tp1);
							nueva_transaccion.commit();
							return result;
						} else {
							nueva_transaccion.rollback();
							return -1;
						}

					}
				}
			}
			
			TNave nave = FactoryNeg.getInstance().generateSANave().read(tp1.getIdNave());
			if (nave == null) {
				nueva_transaccion.rollback();
				return -2;
			}
			int result;
			if (tp1.getCargo() == null) { //jefe
				TJefe jefe = (TJefe) tp1;
				result = FactoryIntgr.getInstance().generateDAOPersonal().create(jefe);
			}
			else {
				TEmpleado emp = (TEmpleado) tp1;
				result = FactoryIntgr.getInstance().generateDAOPersonal().create(emp);
			}
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
	public TPersonal read(int id) {
		TPersonal p;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			p = FactoryIntgr.getInstance().generateDAOPersonal().read(id);
			nueva_transaccion.commit();
			return (p != null && p.getActivo()) ? p : null;
		} catch (Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TPersonal> readAll() {
		List<TPersonal> personal = null;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			personal = FactoryIntgr.getInstance().generateDAOPersonal().readAll();
			nueva_transaccion.commit();
			return personal;
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
	public int update(TPersonal tp1) {
		boolean yaExiste = false;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			DAOPersonal daoPers = FactoryIntgr.getInstance().generateDAOPersonal();
			List<TPersonal> listaTransfers = daoPers.readAll();
			TPersonal pers = daoPers.read(tp1.getId());
			
			for(TPersonal tp : listaTransfers) {
				if(!tp.equals(tp1) && tp.getNif().equals(tp1.getNif())) {
					yaExiste = true;
					nueva_transaccion.rollback();
					return -2;
				}
			}
			
			if (!yaExiste && pers != null) {
				pers.setNif(tp1.getNif());
				pers.setNombre(tp1.getNombre());
				pers.setApellido(tp1.getApellido());
				
				TNave nave = FactoryIntgr.getInstance().generateDAONave().read(tp1.getIdNave());
				if (nave == null) {
					nueva_transaccion.rollback();
					return -3;
				}
				pers.setIdNave(tp1.getIdNave());
				
				/* COMPROBAR SI SE INTRODUCE CARGO*/
				if (tp1.getCargo() != null) {
					pers.setCargo(tp1.getCargo());
				}
				
				int result = daoPers.update(pers);
				nueva_transaccion.commit();
				return result;
			} else {
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

	/*
	 * SOLO SE PODRÁ BORRAR DE LA BASE DE DATOS UNA PERSONA EN EL CASO DE QUE NO
	 * ESTE VINCULADA A NINGUNA HABILIDAD
	 */
	@Override
	public int delete(int id) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			TPersonal p = FactoryIntgr.getInstance().generateDAOPersonal().read(id);

			if(p == null) {
				nueva_transaccion.rollback();
				return -1;
			}
			if (!p.getActivo()) {
				nueva_transaccion.rollback();
				return -2;
			}
			
			List<THabilidadPersonal> habPerList = FactoryIntgr.getInstance()
	                    .generateDAOHabilidadPersonal().readPersonalVinculadoHabilidad(id);
			if (!habPerList.isEmpty()) {
				for (THabilidadPersonal habPer : habPerList) {
                    if (habPer.getActivo()) {
                        nueva_transaccion.rollback();
                        return -2;
                    }
                }
			}
			
			int result = FactoryIntgr.getInstance().generateDAOPersonal().delete(id);
			nueva_transaccion.commit();
			return result;
		} catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public TPersonal readPersonalByNif(String nif) {
		Transaction nueva_transaccion = null;
	    try {
	        nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
	        nueva_transaccion.start();
	        TPersonal pers = FactoryIntgr.getInstance().generateDAOPersonal().readPersonalByNif(nif);
	        
	        if (pers != null && pers.getActivo()) {
	            nueva_transaccion.commit();
	            return pers;
	            
	        } else {
	            nueva_transaccion.rollback();
	            return null;
	        }
	        
	    } catch (Exception e) {
	        if (nueva_transaccion != null) {
	            nueva_transaccion.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public List<TPersonal> readPersonalByNave(int idNave) {
		Transaction nueva_transaccion = null;
	    try {
	        nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
	        nueva_transaccion.start();
	        TNave nave = FactoryIntgr.getInstance().generateDAONave().read(idNave);
	        
	        if (nave != null) {
	        	List<TPersonal> lista_personal = FactoryIntgr.getInstance().generateDAOPersonal().readPersonalByNave(idNave);
	            nueva_transaccion.commit();
	            return lista_personal;
	        } else {
	            nueva_transaccion.rollback();
	            return null;
	        }
	        
	    } catch (Exception e) {
	        if (nueva_transaccion != null) {
	            nueva_transaccion.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public List<TPersonal> readPersonalByHabilidad(int idHabilidad) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			THabilidad habilidad = FactoryIntgr.getInstance().generateDAOHabilidad().read(idHabilidad);
			
			if(habilidad != null && habilidad.getActivo()) {
				List<THabilidadPersonal> listaPersonalPorHabilidad = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadVinculadaPersonal(idHabilidad);
				/* LISTA DE PERSONAS POR HABILIDAD */
				List<TPersonal> lista_personal = new ArrayList<>();
				
				for (THabilidadPersonal thp : listaPersonalPorHabilidad) {
					TPersonal transferLeido = FactoryIntgr.getInstance().generateDAOPersonal().read(thp.getIdPersonal());
					lista_personal.add(transferLeido);
				}
				
				nueva_transaccion.commit();
				return lista_personal;
			} else {
				nueva_transaccion.rollback();
				return null;
			}
		} catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<THabilidadPersonal> readPersonaVinculadaHabilidad(int idPersonal) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			List<THabilidadPersonal> list_PerHab = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readPersonalVinculadoHabilidad(idPersonal);
			nueva_transaccion.commit();
			return list_PerHab;
			
		} catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	@Override 
	public List<TPersonal> query(Object data) {
		Object[] peticion = (Object[]) data;
		Query nueva_query = FactoryQuery.getInstance().nuevaQuery((String) peticion[0]);
		Object resultado = nueva_query.execute(peticion[1]);
		List<TPersonal> lista_result = new ArrayList<>();
		if(resultado != null) {
			for (TPersonal personal: (List<TPersonal>) resultado) {
				if(personal.getActivo()) {
					lista_result.add(personal);
				}
			}
		}
        return lista_result.isEmpty() ? null : lista_result;
	}

}
