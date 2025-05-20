
/**
 * 
 */
package Negocio.Mueble;

import java.util.List;
import java.util.stream.Collectors;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class SAMuebleImp implements SAMueble {

	@Override
	public Integer create(TMueble tmueble) 
	{
		Transaction nueva_transaccion = null;
		
		int idNewMueble = -1;
		int idMuebleExiste = -2;
		TMueble mueblesCoincidence = null; 
		
		try 
		{
			if (TransactionManager.getInstance().getTransaccion() == null)
				nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			else
				nueva_transaccion = TransactionManager.getInstance().getTransaccion();
			
			nueva_transaccion.start();

			mueblesCoincidence = FactoryIntgr.getInstance().generateDAOMueble().readByName(tmueble.getNombre());
			
			if (mueblesCoincidence == null)
				idNewMueble = FactoryIntgr.getInstance().generateDAOMueble().create(tmueble);
			else
				idNewMueble = idMuebleExiste; 
			
			nueva_transaccion.commit();
			return idNewMueble;
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return idNewMueble; 
	}

	@Override
	public TMueble read(Integer id)
	{
		Transaction nueva_transaccion = null; 
		
		TMueble mueble = null;
		
		try 
		{
			if (TransactionManager.getInstance().getTransaccion() == null)
				nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			
			nueva_transaccion.start();
			
			mueble = FactoryIntgr.getInstance().generateDAOMueble().read(id);
			
			nueva_transaccion.commit();
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return mueble;
	}

	@Override
	public List<TMueble> readAll() 
	{
		Transaction nueva_transaccion = null; 
		
		List<TMueble> lstMuebles = null;
		
		try 
		{	
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			
			lstMuebles = FactoryIntgr.getInstance().generateDAOMueble().readAll();
			
			nueva_transaccion.commit();
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return lstMuebles;
	}

	@Override
	public Integer update(TMueble tMueble) 
	{
		Transaction nueva_transaccion = null; 
		
		int updateResult = -1;
		
		try 
		{
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			
			updateResult = FactoryIntgr.getInstance().generateDAOMueble().update(tMueble);
			
			nueva_transaccion.commit();
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return updateResult;
	}

	@Override
	public Integer delete(Integer id) 
	{		
		Transaction nueva_transaccion = null; 
		
		int iDelete = -1;
		
		try 
		{
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			
			iDelete = FactoryIntgr.getInstance().generateDAOMueble().delete(id);	
			
			nueva_transaccion.commit();
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return iDelete;
	}

	@Override
	public List<TMueble> readMueblePorNave(int idNave) 
	{
		Transaction nueva_transaccion = null; 
		
		List<TMueble> muebles = null;

		try 
		{
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			
			muebles = FactoryIntgr.getInstance().generateDAOMueble().readMueblePorNave(idNave);
			
			nueva_transaccion.commit();
		} 
		catch (Exception e) 
		{
			if(nueva_transaccion != null) nueva_transaccion.rollback();
			e.printStackTrace();
		}
		return muebles;
	}
}