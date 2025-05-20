/**
 * 
 */
package Integracion.FactoryIntegracion;

import Integracion.Cliente.DAOCliente;
import Integracion.Cliente.DAOClienteImp;
import Integracion.Compra.DAOCompra;
import Integracion.Compra.DAOCompraImp;
import Integracion.Compra.DAOLineaCompra;
import Integracion.Compra.DAOLineaCompraImp;
import Integracion.Habilidad.DAOHabilidad;
import Integracion.Habilidad.DAOHabilidadImp;
import Integracion.Habilidad.DAOHabilidadPersonal;
import Integracion.Habilidad.DAOHabilidadPersonalImp;
import Integracion.Mueble.DAOMueble;
import Integracion.Mueble.DAOMuebleImp;
import Integracion.Nave.DAONave;
import Integracion.Nave.DAONaveImp;
import Integracion.Personal.DAOPersonal;
import Integracion.Personal.DAOPersonalImp;
import Negocio.Compra.TCarrito;

public class FactoryIntgrImp extends FactoryIntgr {

	@Override
	public DAOCompra generateDAOCompra() {
		return new DAOCompraImp();
	}

	@Override
	public DAOHabilidad generateDAOHabilidad() {
		return new DAOHabilidadImp();
	}

	@Override
	public DAOMueble generateDAOMueble() {
		return new DAOMuebleImp();
	}

	@Override
	public DAONave generateDAONave() {
		return new DAONaveImp();
	}

	@Override
	public DAOCliente generateDAOCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOPersonal generateDAOPersonal() {
		return new DAOPersonalImp();
	}

	@Override
	public DAOHabilidadPersonal generateDAOHabilidadPersonal() {
		return new DAOHabilidadPersonalImp();
	}

	@Override
	public TCarrito generateCarrito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOLineaCompra generateDAOLineaCompra() {
		
		return new DAOLineaCompraImp();
	}
}