package Negocio.Cliente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Negocio.FactoryNegocio.FactoryNeg;


public class SAClienteTest {

	@Test
	public void testsCrearCliente() {
		
		TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		TCliente c2 = new TIndividual(2, "Pepe", "1234Aa*5", "correo@mail.es", true, 11111);
		int resultado;
		
		resultado = FactoryNeg.getInstance().generateSACliente().create(c1);
		assertEquals(1, resultado, "No ha devuelto el id que deberia");
		resultado = FactoryNeg.getInstance().generateSACliente().create(c2);
		assertEquals(2, resultado, "No ha devuelto el id que deberia");

		TCliente c3 = new TEmpresa(3, "Aitor", "2020qW3?", "asd@asd.is", true, "EMPRESA");
		resultado = FactoryNeg.getInstance().generateSACliente().create(c3);
		assertEquals(3, resultado, "No ha devuelto el id esperado");

		TCliente c4 = new TEmpresa(4, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		resultado = FactoryNeg.getInstance().generateSACliente().create(c4);
		assertEquals(-1, resultado, "No ha devuelto el resultado esperado");
	}

	@Test
	public void testReadCliente() {
		TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		assertEquals(c1, FactoryNeg.getInstance().generateSACliente().read(2));
	}

	@Test
	public void testReadAllClientes() {
		TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		TCliente c2 = new TIndividual(2, "Pepe", "1234Aa*5", "correo@mail.es", true, 11111);
		TCliente c3 = new TEmpresa(3, "Aitor", "2020qW3?", "asd@asd.is", true, "EMPRESA");
		TCliente c4 = new TEmpresa(4, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		List<TCliente> clientes = new ArrayList<>();
		clientes = FactoryNeg.getInstance().generateSACliente().readAll();
		assertEquals(clientes.size(), 3, "La lista no contiene los elementos que nosotros queremos");
		assertFalse(clientes.contains(c4));
		assertTrue(clientes.contains(c1));
		assertTrue(clientes.contains(c2));
		assertTrue(clientes.contains(c3));
	}
	
	@Test
	public void testUpdateCliente(){
		TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		TCliente c2 = new TIndividual(2, "Pepe", "1234Aa*5", "correo@mail.es", true, 11111);
		TCliente c3 = new TEmpresa(3, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		assertEquals(1, FactoryNeg.getInstance().generateSACliente().update(c1));
		assertEquals(2, FactoryNeg.getInstance().generateSACliente().update(c2));
		assertEquals(-1, FactoryNeg.getInstance().generateSACliente().update(c3));
	}
	
	@Test
	public void testDeleteCliente(){
		assertEquals(1, FactoryNeg.getInstance().generateSACliente().delete(1));
		assertEquals(1, FactoryNeg.getInstance().generateSACliente().delete(2));
		assertEquals(-1, FactoryNeg.getInstance().generateSACliente().delete(1));
		assertEquals(-1, FactoryNeg.getInstance().generateSACliente().delete(7));
	}

}
