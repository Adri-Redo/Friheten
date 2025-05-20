package Negocio.Compra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TLineaCompraTest {

	
	
	
	@Test
	public void testsTLineaCompra() {
		
		TLineaCompra compra = new TLineaCompra(1, 2, 3, 4.6);
		
		
		assertEquals(1, compra.getIdMueble());
		compra.setIdMueble(7);
		assertEquals(7, compra.getIdMueble());

		assertEquals(2, compra.getIdCompra());
		compra.setIdCompra(8);
		assertEquals(8, compra.getIdCompra());
		
		assertEquals(3, compra.getUnidades());
		compra.setUnidades(9);
		assertEquals(9, compra.getUnidades());
		
		assertEquals(4.6, compra.getPrecio());
		compra.setPrecio(10.0);
		assertEquals(10.0, compra.getPrecio());
		
	}
}
