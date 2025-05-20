/**
 * 
 */
package Integracion.FactoryIntegracion;

import Integracion.Habilidad.DAOHabilidad;
import Integracion.Mueble.DAOMueble;
import Integracion.Nave.DAONave;
import Integracion.Cliente.DAOCliente;
import Integracion.Personal.DAOPersonal;
import Integracion.Compra.DAOCompra;
import Integracion.Compra.DAOLineaCompra;
import Integracion.Habilidad.DAOHabilidadPersonal;
import Negocio.Compra.TCarrito;

public abstract class FactoryIntgr {

	private static FactoryIntgr instance;

	public static FactoryIntgr getInstance() {
		if (instance == null) {
			return new FactoryIntgrImp();
		}
		return instance;
	}

	public abstract DAOHabilidad generateDAOHabilidad();

	public abstract DAOMueble generateDAOMueble();

	public abstract DAONave generateDAONave();

	public abstract DAOCliente generateDAOCliente();

	public abstract DAOPersonal generateDAOPersonal();

	public abstract DAOCompra generateDAOCompra();

	public abstract DAOHabilidadPersonal generateDAOHabilidadPersonal();

	public abstract TCarrito generateCarrito();

	public abstract DAOLineaCompra generateDAOLineaCompra();
}