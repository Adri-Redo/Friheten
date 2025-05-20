/**
 * 
 */
package Negocio.Nave;

import java.util.List;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Nave.DAONave;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Personal.TPersonal;

public class SANaveImp implements SANave {

	@Override
	public Integer create(TNave tnave) {
		Transaction  new_transaction = null;
		try {
			new_transaction = TransactionManager.getInstance().nuevaTransaccion();
			new_transaction.start();
			List<TNave> lista_naves = FactoryIntgr.getInstance().generateDAONave().readAll();;
			if(lista_naves != null) {
				
				for(TNave ship: lista_naves) {
					if(ship.getNombre().equals(tnave.getNombre()) 
							&& ship.getLocalizacion().equals(tnave.getLocalizacion())) {
						if(ship.getActivo() == false) {
							tnave.setActivo(true);
							tnave.setId(ship.getId());
							int result = FactoryIntgr.getInstance().generateDAONave().update(tnave);
							new_transaction.commit();
							return result;
							
						}else {
							new_transaction.rollback();
							return -1;
						}
					}
				}
			}
			
			int resultado = FactoryIntgr.getInstance().generateDAONave().create(tnave);
			new_transaction.commit();
			return resultado;
		}catch(Exception e){
			if(new_transaction != null)new_transaction.rollback();
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	

	@Override
	public TNave read(Integer id) {
		TNave nave;
		Transaction new_transaction = null;
		try {
			new_transaction = TransactionManager.getInstance().nuevaTransaccion();
			new_transaction.start();
			nave = FactoryIntgr.getInstance().generateDAONave().read(id);
			new_transaction.commit();
			return nave;
		}catch(Exception e) {
			if(new_transaction != null) new_transaction.rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public List<TNave> readAll() {
		List<TNave> lista_naves = null;
		Transaction new_transaction = null;
		try {
			new_transaction = TransactionManager.getInstance().nuevaTransaccion();
			new_transaction.start();
			lista_naves = FactoryIntgr.getInstance().generateDAONave().readAll();
			new_transaction.commit();
		}catch(Exception e) {
			if(new_transaction != null)new_transaction.rollback();
			e.printStackTrace();
		}
		return lista_naves;
	}
	

	
	@Override
	public Integer update(TNave nave) {

	    boolean yaExiste = false;
	    Transaction new_transaction = null;
	    try {
	        new_transaction = TransactionManager.getInstance().nuevaTransaccion();
	        new_transaction.start();
	        DAONave daoN = FactoryIntgr.getInstance().generateDAONave();
	        List<TNave> listaTransfers = daoN.readAll();
	        TNave n = daoN.read(nave.getId());

	        for (TNave ship : listaTransfers) {

	            if (ship.equals(nave)) {
	                yaExiste = true;
	                new_transaction.rollback();
	                return -2;
	            }if (nave.getCapacidad() < 0) {
	            	new_transaction.rollback();
					return -3;
				}

	        }

	        if (!yaExiste && n != null) {

	            //n.setNombre(nave.getNombre());

	            int result = daoN.update(nave);
	            new_transaction.commit();
	            
	            return result;

	        }

	        else
	            new_transaction.rollback();
	            return -1;

	    }catch(Exception e)
	    {
	        if(new_transaction != null)
	        {
	            new_transaction.rollback();
	        }
	        e.printStackTrace();
	        return -1;
	    }


	}
	
	


	@Override
	public Integer delete(Integer id) {
        Transaction new_transaction = null;
        try {
            new_transaction = TransactionManager.getInstance().nuevaTransaccion();
            new_transaction.start();
            TNave nave = FactoryIntgr.getInstance().generateDAONave().read(id);

            if(nave != null && nave.getId().equals(id) && nave.getActivo() == true) {
                int result = FactoryIntgr.getInstance().generateDAONave().delete(id);
                List<TPersonal> personal_nave = FactoryIntgr.getInstance().generateDAOPersonal().readPersonalByNave(result);
                for(TPersonal p : personal_nave) {
                	if(p.getActivo())
                		FactoryIntgr.getInstance().generateDAOPersonal().delete(p.getId());
                }
                new_transaction.commit();
                return result;
            }else return -1;
        }catch(Exception e) {
            if(new_transaction != null) new_transaction.rollback();
            e.printStackTrace();
            return -2;
        }
    }
	 
	
}
