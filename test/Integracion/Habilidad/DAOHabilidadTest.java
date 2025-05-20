package Integracion.Habilidad;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Habilidad.THabilidad;

class DAOHabilidadTest {

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
			ps = con.prepareStatement("ALTER TABLE habilidad AUTO_INCREMENT = 1");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE personal AUTO_INCREMENT = 1");
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
	public void testsFuncionesDAOHabilidad() {
		Transaction t = null;
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			int resultado;

			THabilidad h1 = new THabilidad(1, "Bondadoso", true);
			THabilidad h2 = new THabilidad(2, "Atento", true);

			/*********************** CREATE *****************************/

			/* ASSERT EQUALS TE DICE SI ES IGUAL */
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

			/*********************** READ *****************************/
			assertEquals(h1, FactoryIntgr.getInstance().generateDAOHabilidad().read(1));
			assertEquals(h2, FactoryIntgr.getInstance().generateDAOHabilidad().read(2));

			/*********************** READALL *****************************/
			List<THabilidad> habilidades = new ArrayList<>();
			habilidades = FactoryIntgr.getInstance().generateDAOHabilidad().readAll();
			assertEquals(habilidades.size(), 2, "La lista no contiene los elementos que nosotros queremos");
			assertTrue(habilidades.contains(h1));
			assertTrue(habilidades.contains(h2));

			/*********************** UPDATE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAOHabilidad().update(h1));
			assertEquals(2, FactoryIntgr.getInstance().generateDAOHabilidad().update(h2));

			/*********************** DELETE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAOHabilidad().delete(1));
			assertEquals(1, FactoryIntgr.getInstance().generateDAOHabilidad().delete(2));
			assertEquals(0, FactoryIntgr.getInstance().generateDAOHabilidad().delete(12));
			/*********************** READBYNAME *****************************/
			assertEquals(null, FactoryIntgr.getInstance().generateDAOHabilidad().readByName("Bondadoso"));
			assertEquals(null, FactoryIntgr.getInstance().generateDAOHabilidad().readByName("Gracioso"));
			t.commit();

		}catch(Exception e)
		{
			if(t != null)
			{
				t.rollback();
			}
			e.printStackTrace();
		}
		
	}

}
