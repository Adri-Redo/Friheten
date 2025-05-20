package Negocio.Compra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Negocio.Cliente.*;
import Negocio.Personal.*;
import java.util.Set;
import java.util.HashSet;

public class TMostrarCompraTest {
	
	@Test
	public void testsTMostrarCompra() {
		
		TCliente cliente = new TIndividual(005, "Jaime", "P2o_", "jaimito, @gmail.com", true, 33540);
		TPersonal personal = new TJefe("12345678L", "Emilio", "Morocotudo", 4, 2, true, 2000, 3, 2);
		TCompra compra = new TCompra(1, "2024-04-16T11:50:26.084480700" , 1, 002, 1, true);

		
		Set<TLineaCompra> conjuntoLineas = new HashSet<>();
		
        TLineaCompra compra1 = new TLineaCompra(1, 2, 3, 4.6);
        TLineaCompra compra2 = new TLineaCompra(5, 6, 7, 8.9);
        TLineaCompra compra3 = new TLineaCompra(9, 10, 11, 12.3);

        conjuntoLineas.add(compra1);
        conjuntoLineas.add(compra2);
        conjuntoLineas.add(compra3);
        
		TMostrarCompra mostrarCompra = new TMostrarCompra(personal, cliente, compra, conjuntoLineas);
		
		assertEquals(personal, mostrarCompra.getPersonal());

		assertEquals(cliente, mostrarCompra.getCliente());
		
		assertEquals(compra, mostrarCompra.getCompra());
		
		assertEquals(conjuntoLineas, mostrarCompra.getMuebles());
		
	}
}
