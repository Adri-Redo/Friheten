package Integracion.Compra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import Integracion.FactoryIntegracion.FactoryIntgr;
import Negocio.Compra.*;

public class DAOCompraTest {
	
	@Test
	public void testsFuncionesDAOCompra() {
		
		
		Integer resultado;
		TCompra compra1 = new TCompra(1, "2024-04-16T11:50:26.084480700" , 1, 002, 1, true);
		TCompra compra2 = new TCompra(2, "2024-04-16T11:51:26.321803200" , 1, 003, 2, true);
		
		/*CREATE*/
		
		resultado = FactoryIntgr.getInstance().generateDAOCompra().create(compra1);
		
		assertEquals(11,resultado, "No ha devuelto el id que acaba de crear");
		
		resultado = FactoryIntgr.getInstance().generateDAOCompra().create(compra2);
		assertEquals(12,resultado, "No ha devuelto el id que acaba de crear");
		
		/*READ*/
		assertEquals(compra1, FactoryIntgr.getInstance().generateDAOCompra().read(11));
		assertEquals(compra2, FactoryIntgr.getInstance().generateDAOCompra().read(12));
		
		/*READ_ALL*/
		
		Set<TCompra> compras;
		compras = FactoryIntgr.getInstance().generateDAOCompra().readAll();
		assertEquals(compras.size(), 12, "El conjunto no contiene los elementos que nosotros queremos");
		assertTrue(compras.contains(compra1));
		assertTrue(compras.contains(compra2));
		
		/*READ_BY_ID_CLIENTE*/
		
		Set<TCompra> comprasID;
		comprasID = FactoryIntgr.getInstance().generateDAOCompra().readByIDCliente(002);
		assertTrue(comprasID.contains(compra1));
		
		comprasID = FactoryIntgr.getInstance().generateDAOCompra().readByIDCliente(003);
		assertTrue(comprasID.contains(compra2));
		
		
	}
	
	

}
