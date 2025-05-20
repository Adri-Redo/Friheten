package Negocio.Habilidad;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.FactoryNegocio.FactoryNeg;
import Negocio.Nave.TNave;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

class SAHabilidadTest {

	@BeforeEach
	public void setup() {
		Transaction t = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			con = (Connection) t.getResource();
			ps = con.prepareStatement("DELETE FROM habilidadPersonal");
			ps.execute();
			ps = con.prepareStatement("DELETE FROM habilidad");
			ps.execute();
			ps = con.prepareStatement("DELETE FROM personal");
			ps.execute();
			ps = con.prepareStatement("DELETE FROM nave");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE habilidad AUTO_INCREMENT = 1");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE personal AUTO_INCREMENT = 1");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE nave AUTO_INCREMENT = 1");
			ps.execute();
			t.commit();

			con.close();
			ps.close();
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testsFuncionesSAHabilidad() {
		
		
		int resultado;

		THabilidad h1 = new THabilidad(1, "Talentoso", true);
		THabilidad h2 = new THabilidad(2, "Generoso", true);

		/*********************** CREATE *****************************/

		/* ASSERT EQUALS TE DICE SI ES IGUAL */
		resultado = FactoryNeg.getInstance().generateSAHabilidad().create(h1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().create(h2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

		/*********************** READ *****************************/
		assertEquals(h1, FactoryNeg.getInstance().generateSAHabilidad().read(1));
		assertEquals(h2, FactoryNeg.getInstance().generateSAHabilidad().read(2));

		/*********************** READALL *****************************/
		List<THabilidad> habilidades = new ArrayList<>();
		habilidades = FactoryNeg.getInstance().generateSAHabilidad().readAll();
		assertEquals(habilidades.size(), 2, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(habilidades.contains(h1));
		assertTrue(habilidades.contains(h2));

		/*********************** UPDATE *****************************/
		assertEquals(-2, FactoryNeg.getInstance().generateSAHabilidad().update(h1));
		assertEquals(-2, FactoryNeg.getInstance().generateSAHabilidad().update(h2));

		/*********************** DELETE *****************************/
		assertEquals(1, FactoryNeg.getInstance().generateSAHabilidad().delete(1));
		assertEquals(1, FactoryNeg.getInstance().generateSAHabilidad().delete(2));
		assertEquals(-1, FactoryNeg.getInstance().generateSAHabilidad().delete(12));

		resultado = FactoryNeg.getInstance().generateSAHabilidad().create(h1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().create(h2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
		
		TNave n1 = new TNave(1,"Madrid","NaveTest", 200, true);
		TNave n2 = new TNave(2,"Madrid","NaveTest2", 260, true);
		resultado = FactoryNeg.getInstance().generateSANave().create(n1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryNeg.getInstance().generateSANave().create(n2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
		
		TPersonal p1 = new TEmpleado("09230854S", "Sergio", "Sanchez", 1, 1, "Ingeniero Software", true, 1000, 2);
		TPersonal p2 = new TJefe("78940641G", "Rosa", "Melano", 2, 1, true, 2000, 2, 2);
		resultado = FactoryNeg.getInstance().generateSAPersonal().create(p1);
		assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
		resultado = FactoryNeg.getInstance().generateSAPersonal().create(p2);
		assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
		
		
		
		
		THabilidadPersonal hP1 = new THabilidadPersonal(1, 2, true);
		THabilidadPersonal hP2 = new THabilidadPersonal(2, 1, true);
		THabilidadPersonal hP3 = new THabilidadPersonal(4, 2, true);
		THabilidadPersonal hP4 = new THabilidadPersonal(1, 5, true);

		/*********************** CREATE_HAB_PERSONAL *****************************/
		resultado = FactoryNeg.getInstance().generateSAHabilidad().createHabilidadPersonal(hP1);
		assertEquals(1, resultado, "No ha devuelto el id de la persona correcta");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().createHabilidadPersonal(hP2);
		assertEquals(2, resultado, "No ha devuelto el id de la persona correcta");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().createHabilidadPersonal(hP3);
		assertEquals(-3, resultado, "No ha devuelto el id de la persona correcta");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().createHabilidadPersonal(hP4);
		assertEquals(-2, resultado, "No ha devuelto el id de la persona correcta");

		/*********************** DELETE_HAB_PERSONAL *****************************/
		resultado = FactoryNeg.getInstance().generateSAHabilidad().deleteHabilidadPersonal(hP2);
		assertEquals(1, resultado, "No se ha podido eliminar al personal");
		resultado = FactoryNeg.getInstance().generateSAHabilidad().deleteHabilidadPersonal(hP4);
		assertEquals(-2, resultado, "No se ha podido eliminar al personal");


		/*********************** READ_HAB_PER_VINCULADA *****************************/
		List<THabilidadPersonal> habilidadespersonal = new ArrayList<>();
		habilidadespersonal = FactoryNeg.getInstance().generateSAHabilidad().readHabilidadVinculadaPersonal(2);
		assertEquals(habilidadespersonal.size(), 1, "La lista no contiene los elementos que nosotros queremos");
		assertTrue(habilidadespersonal.contains(hP1));

	}
}
