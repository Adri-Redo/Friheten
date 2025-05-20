package Negocio.Compra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TCompraTest {
	
	@Test
	public void testsTCompra() {
		
		TCompra compra = new TCompra(1, "2024-04-16T11:50:26.084480700" , 2, 1, 1, true);
		
		assertEquals(1, compra.getId());
		compra.setId(2);
		assertEquals(2, compra.getId());

		assertEquals("2024-04-16T11:50:26.084480700", compra.getFecha());
		compra.setFecha("2024-04-18T11:50:26.084480700");
		assertEquals("2024-04-18T11:50:26.084480700", compra.getFecha());

		assertEquals(true, compra.getActivo());
		
		assertEquals(2, compra.getIdCliente());
		compra.setIdCliente(3);
		assertEquals(3, compra.getIdCliente());
		
		assertEquals(1, compra.getIdPersonal());
		compra.setIdPersonal(2);
		assertEquals(2, compra.getIdPersonal());
		
	}

}
