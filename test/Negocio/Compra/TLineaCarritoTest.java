package Negocio.Compra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TLineaCarritoTest {
	
	
	@Test
	public void testsTLineaCarrito() {
		
		TLineaCarrito lineaCarrito = new TLineaCarrito("nombre", 4, 2);
		
		assertEquals("nombre", lineaCarrito.getNombre());
		lineaCarrito.setNombreProducto("cambio");
		assertEquals("cambio", lineaCarrito.getNombre());
		
		assertEquals(4, lineaCarrito.getIdProducto());
		lineaCarrito.setIdProducto(3);
		assertEquals(3, lineaCarrito.getIdProducto());
		
		assertEquals(2, lineaCarrito.getUnidades());
		lineaCarrito.setUnidades(3);
		assertEquals(3, lineaCarrito.getUnidades());
		
		
	}

}
