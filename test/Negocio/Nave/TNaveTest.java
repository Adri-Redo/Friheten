package Negocio.Nave;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TNaveTest {
	@Test
	void testsFuncionesTNave() {
		
		TNave nave = new TNave(2, "Madrid", "NaveTest", 200, false);
		
		assertEquals(2,nave.getId());
		nave.setId(1);
		assertEquals(1, nave.getId());
		

		assertEquals("NaveTest", nave.getNombre());
		nave.setNombre("Estanteria Hoiffron");
		assertEquals("Estanteria Hoiffron", nave.getNombre());

		assertEquals(200, nave.getCapacidad());
		nave.setCapacidad(300);
		assertEquals(300, nave.getCapacidad());

		assertEquals("Madrid", nave.getLocalizacion());
		nave.setLocalizacion("Alcorcon");
		assertEquals("Alcorcon", nave.getLocalizacion());

		assertEquals(false, nave.getActivo());
		nave.setActivo(true);
		assertEquals(true, nave.getActivo());
	}
}
