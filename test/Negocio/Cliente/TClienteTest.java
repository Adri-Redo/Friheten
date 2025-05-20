package Negocio.Cliente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TClienteTest {

	@Test
	void testsFuncionesTCliente() {
		
		TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
		TCliente c2 = new TIndividual(2, "Pepe", "1234Aa*5", "correo@mail.es", true, 11111);
		
		assertEquals(1, c1.getId());
		
		assertEquals("Paco", c1.getUsuario());
		c1.setUsuario("Carlos");
		assertEquals("Carlos", c1.getUsuario());
		
		assertEquals("4321bB!0", c1.getContrasena());
		c1.setContrasena("12345Qq!");
		assertEquals("12345Qq!", c1.getContrasena());
		
		assertEquals("hola@mail.es", c1.getCorreo());
		c1.setCorreo("ola@mail.es");
		assertEquals("ola@mail.es", c1.getCorreo());
		
		assertEquals(true, c1.getActivo());
		c1.setActivo(false);
		assertEquals(false, c1.getActivo());
		
		assertEquals("NATILLAS", ((TEmpresa)c1).getNombre());
		((TEmpresa)c1).setNombre("yogures");
		assertEquals("yogures", ((TEmpresa)c1).getNombre());
		
		assertEquals(2, c2.getId());
		
		assertEquals("Pepe", c2.getUsuario());
		c2.setUsuario("Pablo");
		assertEquals("Pablo", c2.getUsuario());
		
		assertEquals("1234Aa*5", c2.getContrasena());
		c2.setContrasena("4321bB!0");
		assertEquals("4321bB!0", c2.getContrasena());
		
		assertEquals("correo@mail.es", c2.getCorreo());
		c2.setCorreo("p@mal.esp");
		assertEquals("p@mal.esp", c2.getCorreo());
		
		assertEquals(true, c2.getActivo());
		c2.setActivo(false);
		assertEquals(false, c2.getActivo());
		
		assertEquals(11111, ((TIndividual)c2).getCodigoPostal());
		((TIndividual)c2).setCodigoPostal(22222);
		assertEquals(22222, ((TIndividual)c2).getCodigoPostal());
	}

}
