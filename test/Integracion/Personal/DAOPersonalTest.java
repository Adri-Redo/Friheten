package Integracion.Personal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Nave.TNave;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

class DAOPersonalTest {
	
	private Transaction ts = null;
	
	@BeforeEach
	public void setUp(){
		try {
			System.out.println("TRANSACCION?");
			ts = TransactionManager.getInstance().nuevaTransaccion();
			ts.start();
		}catch(Exception e)
		{
			if(ts != null) ts.rollback();
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCrearPersonal() {
		TPersonal p1 = new TEmpleado("09230854S", "Sergio", "Sanchez", 1, 1, "Ingeniero Software", true, 2000, 5);
		TPersonal p2 = new TJefe("78940641G", "Rosa", "Melano", 2, 1, true, 4000, 1, 2);
		int resultado;
		resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

	}
	
	@Test
	public void testLeerEmpleado() {
		TPersonal p3 = new TEmpleado("12345678a", "A", "A", 3, 1, "Ingeniero Software", true, 2000, 5);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p3);
		assertTrue(p3.equals(FactoryIntgr.getInstance().generateDAOPersonal().read(3)));
		
	}
	
	@Test
	public void testLeerJefe() {
		TPersonal p4 = new TJefe("11111111a", "B", "B", 4, 1, true, 4000, 1, 2);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p4);
		assertTrue(p4.equals(FactoryIntgr.getInstance().generateDAOPersonal().read(4)));
	}
	
	@Test
	public void testLeerTodo() {
		TPersonal p5 = new TEmpleado("22222222a", "C", "C", 5, 1, "Ingeniero Software", true, 2000, 5);
		TPersonal p6 = new TJefe("33333333a", "D", "D", 6, 1, true, 4000, 1, 2);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p5);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p6);
		List<TPersonal> personales = new ArrayList<>();
		personales = FactoryIntgr.getInstance().generateDAOPersonal().readAll();
		assertEquals(personales.size(), 2, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(personales.contains(p5));
		assertTrue(personales.contains(p6));
	}
	
	@Test
	public void testActualizarPersonal() {
		TPersonal p7 = new TJefe("44444444a", "E", "E", 7, 1, true, 4000, 1, 2);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p7);
		assertTrue(0!=FactoryIntgr.getInstance().generateDAOPersonal().update(p7));
	}
	
	@Test
	public void testLeerPorNave() {
		TPersonal p8 = new TEmpleado("55555555a", "F", "F", 8, 1, "Ingeniero Software", true, 2000, 5);
		TPersonal p9 = new TJefe("66666666a", "G", "G", 9, 1, true, 4000, 1, 2);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p8);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p9);
		List<TPersonal> personalesByNave = new ArrayList<>();
		personalesByNave = FactoryIntgr.getInstance().generateDAOPersonal().readPersonalByNave(1);
		assertEquals(personalesByNave.size(), 2, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(personalesByNave.contains(p8));
		assertTrue(personalesByNave.contains(p9));

	}
	
	@Test
	public void testEliminarPersonal() {
		TPersonal p10 = new TEmpleado("77777777a", "F", "F", 8, 1, "Ingeniero Software", true, 2000, 5);
		TPersonal p11 = new TJefe("88888888a", "G", "G", 9, 1, true, 4000, 1, 2);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p10);
		FactoryIntgr.getInstance().generateDAOPersonal().create(p11);
		assertEquals(10, FactoryIntgr.getInstance().generateDAOPersonal().delete(10));
		assertEquals(11, FactoryIntgr.getInstance().generateDAOPersonal().delete(11));

	}
	
	@AfterEach
	public void end()
	{
		if(ts != null)
		{
			ts.commit();
		}
	}
}
