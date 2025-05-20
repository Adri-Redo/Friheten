/**
 * 
 */
package Negocio.FactoryNegocio;

import Negocio.Alergeno_Cafeteria.SAAlergeno;
import Negocio.Alergeno_Cafeteria.SAAlergenoImp;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Compra.SACompra;
import Negocio.Compra.SACompraImp;
import Negocio.Compra_Cafeteria.SACompraCafeteria;
import Negocio.Compra_Cafeteria.SACompraCafeteriaImp;
import Negocio.Habilidad.SAHabilidades;
import Negocio.Habilidad.SAHabilidadesImp;
import Negocio.Marca_Cafeteria.SAMarca;
import Negocio.Marca_Cafeteria.SAMarcaImp;
import Negocio.Mueble.SAMueble;
import Negocio.Mueble.SAMuebleImp;
import Negocio.Nave.SANave;
import Negocio.Nave.SANaveImp;
import Negocio.Personal.SAPersonal;
import Negocio.Personal.SAPersonalImp;
import Negocio.Personal_Cafeteria.SAPersonalCafeteria;
import Negocio.Personal_Cafeteria.SAPersonalCafeteriaImpl;
import Negocio.Producto_Cafeteria.SAProductoCafeteria;
import Negocio.Producto_Cafeteria.SAProductoCafeteriaImp;
import Negocio.Turno_Cafeteria.SATurno;
import Negocio.Turno_Cafeteria.SATurnoImp;


public class FactoryNegImp extends FactoryNeg {

	@Override
	public SACompra generateSACompra() {
		return new SACompraImp();
	}

	@Override
	public SAMueble generateSAMueble() {
		return new SAMuebleImp();
	}

	@Override
	public SAHabilidades generateSAHabilidad() {
		
		return new SAHabilidadesImp();
	}

	@Override
	public SAPersonal generateSAPersonal() {

		return new SAPersonalImp();
	}

	@Override
	public SACliente generateSACliente() {
		
		
		return new SAClienteImp();
	}

	@Override
	public SANave generateSANave() {
		
		return new SANaveImp();
	}

	@Override
	public SAMarca generateSAMarca() {
		return new SAMarcaImp();
	}

	@Override
	public SAProductoCafeteria generateSAProducto() {
		return new SAProductoCafeteriaImp();
	}

	@Override
	public SACompraCafeteria generateSACompraCafeteria() {
		return new SACompraCafeteriaImp();
	}

	@Override
	public SAAlergeno generateSAAlergeno() {
		// TODO Auto-generated method stub
		return new SAAlergenoImp();
	}

	@Override
	public SAPersonalCafeteria generateSAPersonalCafeteria() {
		return new SAPersonalCafeteriaImpl();
	}
	
	@Override 
	public SATurno generateSATurno() {
		return new SATurnoImp();
	}

}