package Integracion.Compra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Negocio.Compra.*;

public class DAOLineaCompraTest {
	
	@Test
	public void testsFuncionesDAOLineaCompra() {
		
		
		Integer resultado;
		TLineaCompra compra1 = new TLineaCompra (3 , 4, 1, 10.5);
		TLineaCompra compra2 = new TLineaCompra(4 , 3, 2, 5.5 );
		
		/*CREATE*/
		
		resultado = FactoryIntgr.getInstance().generateDAOLineaCompra().create(compra1);
		assertEquals(1,resultado, "No ha devuelto el id que acaba de crear");
		
		resultado = FactoryIntgr.getInstance().generateDAOLineaCompra().create(compra2);
		assertEquals(1,resultado, "No ha devuelto el id que acaba de crear");
		
		/*READ*/
		assertEquals(compra1, FactoryIntgr.getInstance().generateDAOLineaCompra().read(3, 4));
		assertEquals(compra2, FactoryIntgr.getInstance().generateDAOLineaCompra().read(4, 3));
		
		/*READ_ALL*/
		
		Set<TLineaCompra> compras;
		compras = FactoryIntgr.getInstance().generateDAOLineaCompra().readAll();
		assertEquals(compras.size(), 2, "El conjunto no contiene los elementos que nosotros queremos");
		assertTrue(compras.contains(compra1));
		assertTrue(compras.contains(compra2));
		
		/*READ_BY_ID_COMPRA*/
		
		Set<TLineaCompra> comprasID;
		comprasID = FactoryIntgr.getInstance().generateDAOLineaCompra().readLineasCompraPorCompra(4);
		assertTrue(comprasID.contains(compra1));
		
		comprasID = FactoryIntgr.getInstance().generateDAOLineaCompra().readLineasCompraPorCompra(3);
		assertTrue(comprasID.contains(compra2));
		
		/*READ_BY_ID_MUEBLE*/
		
		Set<TLineaCompra> mueblesID;
		mueblesID = FactoryIntgr.getInstance().generateDAOLineaCompra().readLineasCompraPorMueble(3);
		assertTrue(mueblesID.contains(compra1));
		
		mueblesID = FactoryIntgr.getInstance().generateDAOLineaCompra().readLineasCompraPorMueble(4);
		assertTrue(mueblesID.contains(compra2));
		
		/*DELETE*/
		
		assertEquals(1, FactoryIntgr.getInstance().generateDAOLineaCompra().delete(3,4));
		assertEquals(1, FactoryIntgr.getInstance().generateDAOLineaCompra().delete(4,3));
		assertEquals(-1, FactoryIntgr.getInstance().generateDAOLineaCompra().delete(12, 4));
		
		
		
		
		
		
	}

}
