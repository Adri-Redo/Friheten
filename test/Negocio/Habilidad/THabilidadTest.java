package Negocio.Habilidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class THabilidadTest {

	@Test
	public void testsFuncionesTHabilidad() {

		THabilidad hab = new THabilidad(2, "Carisma", true);

		assertEquals(2, hab.getId());
		hab.setId(1);
		assertEquals(1, hab.getId());

		assertEquals("Carisma", hab.getNombre());
		hab.setNombre("Carismatico");
		assertEquals("Carismatico", hab.getNombre());

		assertEquals(true, hab.getActivo());
	}

}
