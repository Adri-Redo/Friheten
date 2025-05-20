/**
 * 
 */
package Negocio.FactoryNegocio;

import Negocio.Personal.SAPersonal;
import Negocio.Personal_Cafeteria.SAPersonalCafeteria;
import Negocio.Compra.SACompra;
import Negocio.Compra_Cafeteria.SACompraCafeteria;
import Negocio.Habilidad.SAHabilidades;
import Negocio.Marca_Cafeteria.SAMarca;
import Negocio.Producto_Cafeteria.SAProductoCafeteria;
import Negocio.Turno_Cafeteria.SATurno;
import Negocio.Alergeno_Cafeteria.SAAlergeno;
import Negocio.Cliente.SACliente;
import Negocio.Mueble.SAMueble;
import Negocio.Nave.SANave;

public abstract class FactoryNeg {

	private static FactoryNeg instance;

	public static FactoryNeg getInstance() {
		if (instance == null) {
			return new FactoryNegImp();
		}
		return instance;
	}

	public abstract SAHabilidades generateSAHabilidad();

	public abstract SAPersonal generateSAPersonal();

	public abstract SACompra generateSACompra();

	public abstract SACliente generateSACliente();

	public abstract SAMueble generateSAMueble();

	public abstract SANave generateSANave();
	
	
	public abstract SACompraCafeteria generateSACompraCafeteria();
	
	public abstract SAMarca generateSAMarca();
	
	public abstract SAProductoCafeteria generateSAProducto();
	
	public abstract SAAlergeno generateSAAlergeno();
	
	public abstract SAPersonalCafeteria generateSAPersonalCafeteria();

	public abstract SATurno generateSATurno();
}