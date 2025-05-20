package Negocio.Cliente;

import java.util.List;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Integracion.Cliente.DAOCliente;

public class SAClienteImp implements SACliente {

	@Override
	public Integer create(TCliente tCliente) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			DAOCliente daoCliente =  FactoryIntgr.getInstance().generateDAOCliente();
			List<TCliente> clientes = daoCliente.readAll();
			if (clientes != null) {
				for (TCliente tc : clientes) {
	
					if (tc.getUsuario().equals(tCliente.getUsuario()) || 
							tc.getCorreo().equals(tCliente.getCorreo())) { 
	
						if (!tc.getActivo()) {
							if(tc.getTipo() == tCliente.getTipo()) {
								tCliente.setActivo(true);
								tCliente.setId(tc.getId());
								int result = daoCliente.update(tCliente);
								nueva_transaccion.commit();
								return result;
							}
							else {
								nueva_transaccion.rollback();
								return -3;
							}
						}
	
						else if(tc.getUsuario().equals(tCliente.getUsuario())){
							nueva_transaccion.rollback();
							return -1;
						}
						else {
							nueva_transaccion.rollback();
							return -2;
						}
					}
				}
			}
			int result = daoCliente.create(tCliente);
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
	public TCliente read(Integer id) {
		TCliente cli;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			cli = FactoryIntgr.getInstance().generateDAOCliente().read(id);
			nueva_transaccion.commit();
			if(cli == null || !cli.getActivo())
				return null;		
			return cli;
		}
		catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TCliente> readAll() {
		List<TCliente> clientes = null;
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			clientes = FactoryIntgr.getInstance().generateDAOCliente().readAll();
			nueva_transaccion.commit();
			return clientes;
		}catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer update(TCliente tCliente) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			DAOCliente daoCli = FactoryIntgr.getInstance().generateDAOCliente();
			
			TCliente cli = daoCli.read(tCliente.getId());
			if(cli != null) {
				List<TCliente> clientes = daoCli.readAll();
				for (TCliente tc : clientes) {
					if(tc.getId() != tCliente.getId()) {
						if (tc.getUsuario().equals(tCliente.getUsuario())) { // Que no haya 2 clientes con el mismo usuario.
							nueva_transaccion.rollback();
							return -2;
						}
						
						if (tc.getCorreo().equals(tCliente.getCorreo())) {   // Que no haya 2 clientes con el mismo correo.
							nueva_transaccion.rollback();
							return -3;
						}
					}
				}
				
				if(tCliente.getTipo() == (cli.getTipo())){  
					if(tCliente.getTipo()){
						TEmpresa empre = (TEmpresa) tCliente;
						if(!empre.getNombre().equals(""))
							((TEmpresa)cli).setNombre(empre.getNombre());
					} else {
						TIndividual indi = (TIndividual) tCliente;
						if(indi.getCodigoPostal() != 0)
							((TIndividual)cli).setCodigoPostal(indi.getCodigoPostal());
					}
					if(!tCliente.getUsuario().equals(""))
						cli.setUsuario(tCliente.getUsuario());
					if(!tCliente.getCorreo().equals(""))
						cli.setCorreo(tCliente.getCorreo());
					if(!tCliente.getContrasena().equals(""))
						cli.setContrasena(tCliente.getContrasena());
					int result = daoCli.update(cli);
					nueva_transaccion.commit();
					return result;
				}
				else {
					nueva_transaccion.rollback();
					return -1;
				}
				
			}
			else {
				nueva_transaccion.rollback();
				return -4;
			}
		} catch(Exception e)
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
	public Integer delete(Integer id) {
		Transaction nueva_transaccion = null;
		try {
			nueva_transaccion = TransactionManager.getInstance().nuevaTransaccion();
			nueva_transaccion.start();
			TCliente c = FactoryIntgr.getInstance().generateDAOCliente().read(id);
			
			if(c != null) {
				int result = FactoryIntgr.getInstance().generateDAOCliente().delete(id);
				nueva_transaccion.commit();
				return result;
			}
			else {
				nueva_transaccion.rollback();
				return -1;
			}
		} catch(Exception e) {
			if(nueva_transaccion != null) {
				nueva_transaccion.rollback();
			} 
			e.printStackTrace();
			return -1;
		}
	}

}
