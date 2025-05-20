/**
 * 
 */
package Integracion.Cliente;

import Negocio.Cliente.TCliente;

import java.util.List;

public interface DAOCliente {
	
	public int create(TCliente tCliente);
	
	public int delete(Integer id);
	
	public int update(TCliente tCliente);
	
	public TCliente read(Integer id);
	
	public List<TCliente> readAll();
	
	public TCliente readByUser(String user);
}