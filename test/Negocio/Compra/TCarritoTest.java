package Negocio.Compra;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TCarritoTest {

    @Test
    public void testTCarrito() {
    	
    	
        TCarrito carrito = new TCarrito(1, 2);

        TLineaCarrito linea1 = new TLineaCarrito("producto1", 4, 2);
        TLineaCarrito linea2 = new TLineaCarrito("producto2", 5, 3);

        carrito.addLineaCarrito(linea1);
        carrito.addLineaCarrito(linea2);

        assertEquals(2, carrito.getLineasCarrito().size());

        TLineaCarrito lineaRecuperada = carrito.get_LineaCarrito_por_nombre("producto1");
        assertNotNull(lineaRecuperada);
        assertEquals("producto1", lineaRecuperada.getNombre());

        carrito.deleteLineaCarrito(linea1);
        assertEquals(1, carrito.getLineasCarrito().size());

        assertEquals(1, carrito.getIdCliente());
        carrito.setIdCliente(3);
        assertEquals(3, carrito.getIdCliente());

        assertEquals(2, carrito.getIdPersonal());
        carrito.setIdPersonal(4);
        assertEquals(4, carrito.getIdPersonal());
    }
}