package Negocio.Compra;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Negocio.FactoryNegocio.FactoryNeg;


public class SACompraTest {
	
	@Test
	public void testsSACompra() {
		
		TCompra compra1 = new TCompra(1, "2024-04-16T11:50:26.084480700" , 1, 002, 2, true);
		
		TCarrito resultado = FactoryNeg.getInstance().generateSACompra().createCarrito(compra1);
		assertNotNull(resultado, "El carrito no debería ser nulo");

        /*UPDATE*/
        //FactoryNeg.getInstance().generateSACompra().update(compra1, "Mueble");
        

        //*READALL*/
        List<TMostrarCompra> compras = FactoryNeg.getInstance().generateSACompra().readAll();
        assertNotNull(compras, "La lista de compras no debería ser nula");

        /*READ*/
        TMostrarCompra mostrarCompra = FactoryNeg.getInstance().generateSACompra().read(1); // Suponiendo que hay una compra con ID 1
        assertNotNull(mostrarCompra, "La compra mostrada no debería ser nula");

        /*READBYIDCLIENTE*/
        List<TMostrarCompra> comprasCliente = FactoryNeg.getInstance().generateSACompra().readByIDCliente(1); // Suponiendo que hay un cliente con ID 1
        assertNotNull(comprasCliente, "La lista de compras del cliente no debería ser nula");

        /*ADD_PRODUCT*/
        TCarrito updatedCarrito = FactoryNeg.getInstance().generateSACompra().addProduct(resultado, 2, 2, "Mueble2");
        assertNotNull(updatedCarrito, "El carrito actualizado no debería ser nulo");

        /*DELETE_PRODUCT*/
        TCarrito updatedCarrito2 = FactoryNeg.getInstance().generateSACompra().deleteProduct(resultado, "Mueble");
        assertNotNull(updatedCarrito2, "El carrito actualizado no debería ser nulo");

        /*CLOSE*/
        FactoryNeg.getInstance().generateSACompra().close(resultado);
		
		
	}

}
