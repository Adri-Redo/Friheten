package Negocio.Personal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;

class SAPersonalTest {
	
	
	@Test
	public void testCrearPersonal() {
		TPersonal p1 = new TEmpleado("09230854S", "Sergio", "Sanchez", 1, 1, "Ingeniero Software", true, 2000, 2);
		TPersonal p2 = new TJefe("78940641G", "Rosa", "Melano", 2, 1, true, 2000, 3, 2);
		int resultado;
		resultado = FactoryNeg.getInstance().generateSAPersonal().create(p1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryNeg.getInstance().generateSAPersonal().create(p2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

		
	}
	
	@Test
	public void testLeerEmpleado() {
		TPersonal p3 = new TEmpleado("12345678a", "A", "A", 3, 1, "Ingeniero Software", true, 2500, 3);
		FactoryNeg.getInstance().generateSAPersonal().create(p3);
		assertTrue(p3.equals(FactoryNeg.getInstance().generateSAPersonal().read(3)));
	}
	
	@Test
	public void testLeerJefe() {
		TPersonal p4 = new TJefe("11111111a", "B", "B", 4, 1, true, 1900, 2, 5);
		FactoryNeg.getInstance().generateSAPersonal().create(p4);
		assertTrue(p4.equals(FactoryNeg.getInstance().generateSAPersonal().read(4)));
	}
	
	@Test
	public void testLeerTodo() {
		TPersonal p5 = new TEmpleado("22222222a", "C", "C", 5, 1, "Ingeniero Software", true, 1500, 3);
		TPersonal p6 = new TJefe("33333333a", "D", "D", 6, 1, true, 4500, 1, 1);
		FactoryNeg.getInstance().generateSAPersonal().create(p5);
		FactoryNeg.getInstance().generateSAPersonal().create(p6);
		List<TPersonal> personales = new ArrayList<>();
		personales = FactoryNeg.getInstance().generateSAPersonal().readAll();
		assertEquals(personales.size(), 6, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(personales.contains(p5));
		assertTrue(personales.contains(p6));
	}
	
	@Test
	public void testActualizarPersonal() {
		TPersonal p7 = new TJefe("44444444a", "E", "E", 7, 1, true, 3700, 2, 3);
		FactoryNeg.getInstance().generateSAPersonal().create(p7);
		assertTrue(0!=FactoryNeg.getInstance().generateSAPersonal().update(p7));
	}
	
	@Test
	public void testLeerPorNave() {
		TPersonal p8 = new TEmpleado("55555555a", "F", "F", 8, 1, "Ingeniero Software", true, 2000, 3);
		TPersonal p9 = new TJefe("66666666a", "G", "G", 9, 1, true, 1300, 2, 3);
//		FactoryNeg.getInstance().generateSAPersonal().create(p8);
//		FactoryNeg.getInstance().generateSAPersonal().create(p9);
		List<TPersonal> personalesByNave = new ArrayList<>();
		personalesByNave = FactoryNeg.getInstance().generateSAPersonal().readPersonalByNave(1);
		assertEquals(personalesByNave.size(), 9, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(personalesByNave.contains(p8));
		assertTrue(personalesByNave.contains(p9));

	}
	
	@Test
	public void testEliminarPersonal() {
		TPersonal p10 = new TEmpleado("77777777a", "F", "F", 8, 1, "Ingeniero Software", true, 2000, 3);
		TPersonal p11 = new TJefe("88888888a", "G", "G", 9, 1, true, 1200, 2, 5);
		FactoryNeg.getInstance().generateSAPersonal().create(p10);
		FactoryNeg.getInstance().generateSAPersonal().create(p11);
		assertEquals(8, FactoryNeg.getInstance().generateSAPersonal().delete(8));
		assertEquals(9, FactoryNeg.getInstance().generateSAPersonal().delete(9));

	}
	
}
