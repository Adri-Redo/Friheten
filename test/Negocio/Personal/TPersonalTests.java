package Negocio.Personal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TPersonalTests {

	@Test
	void testsFuncionesTPersonal() {

		TEmpleado p1 = new TEmpleado("09230854S", "Sergio", "Sanchez", 1, 1, "Ingeniero Software", true, 2000, 2);
		TPersonal p2 = new TJefe("78940641G", "Rosa", "Melano", 2, 1, true, 6000, 1, 2);

		assertEquals("09230854S", p1.getNif());
		p1.setNif("08450654D");
		assertEquals("08450654D", p1.getNif());

		assertEquals("Sergio", p1.getNombre());
		p1.setNombre("Paco");
		assertEquals("Paco", p1.getNombre());

		assertEquals("Sanchez", p1.getApellido());
		p1.setApellido("Jones");
		assertEquals("Jones", p1.getApellido());

		assertEquals(1, p1.getId());

		assertEquals(1, p1.getIdNave());
		p1.setIdNave(2);
		assertEquals(2, p1.getIdNave());

		assertEquals("Ingeniero Software", p1.getCargo());
		p1.setCargo("PseudoIngeniero");
		assertEquals("PseudoIngeniero", p1.getCargo());

		assertEquals("78940641G", p2.getNif());
		p2.setNif("08540654D");
		assertEquals("08540654D", p2.getNif());

		assertEquals("Rosa", p2.getNombre());
		p2.setNombre("Aitor");
		assertEquals("Aitor", p2.getNombre());

		assertEquals("Melano", p2.getApellido());
		p2.setApellido("Menta");
		assertEquals("Menta", p2.getApellido());

		assertEquals(2, p2.getId());

		assertEquals(1, p2.getIdNave());
		p2.setIdNave(2);
		assertEquals(2, p2.getIdNave());

	}

}
