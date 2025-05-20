package Integracion.Habilidad;

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
import Negocio.Habilidad.THabilidad;
import Negocio.Habilidad.THabilidadPersonal;
import Negocio.Nave.TNave;
import Negocio.Personal.TEmpleado;
import Negocio.Personal.TJefe;
import Negocio.Personal.TPersonal;

class DAOHabilidadPersonalTest {

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
			
			ps.close();
			
			t.commit();

			
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			
			
			e.printStackTrace();
		}
	}

	@Test
	public void testsFuncionesDAOHabilidadPersonal() {

		Transaction t = null;
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			int resultado;

			/*********************** CREACION DE HABILIDADES,PERSONAL Y NAVES (PARA LAS CONSTRAINTS) *******************/
			THabilidad h1 = new THabilidad(1, "Bondadoso", true);
			THabilidad h2 = new THabilidad(2, "Atento", true);
			THabilidad h3 = new THabilidad(3, "Rapido", true);
			THabilidad h4 = new THabilidad(4, "Eficaz", true);
			THabilidad h5 = new THabilidad(5, "Eficiente", true);

			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h3);
			assertEquals(3, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h4);
			assertEquals(4, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidad().create(h5);
			assertEquals(5, resultado, "No ha devuelto el id que acaba de crear");
			
			TNave n1 = new TNave(1,"Madrid","NaveTest", 200, true);
			TNave n2 = new TNave(2,"Madrid","NaveTest2", 260, true);

			resultado = FactoryIntgr.getInstance().generateDAONave().create(n1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAONave().create(n2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

			TPersonal p1 = new TEmpleado("09230854S", "Sergio", "Sanchez", 1, 1, "Ingeniero Software", true, 2000, 1);
			TPersonal p2 = new TJefe("78940641G", "Rosa", "Melano", 2, 1, true, 4000, 1, 2);
			TPersonal p3 = new TEmpleado("12380641C", "Fula", "Nito", 3, 2,"Obrero", true, 2000, 1);
			TPersonal p4 = new TJefe("30956949Z", "Curro", "Jimenez", 4, 2, true, 4000, 1, 2);

			resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p3);
			assertEquals(3, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOPersonal().create(p4);
			assertEquals(4, resultado, "No ha devuelto el id que acaba de crear");
			
			

			/*********************** CREATE_HAB_PERSONAL *****************************/

			THabilidadPersonal hP1 = new THabilidadPersonal(1, 2, true);
			THabilidadPersonal hP2 = new THabilidadPersonal(2, 1, true);
			THabilidadPersonal hP3 = new THabilidadPersonal(4, 3, true);
			THabilidadPersonal hP4 = new THabilidadPersonal(1, 5, true);

			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().createHabilidadPersonal(hP1);
			assertEquals(1, resultado, "No ha devuelto el id de la persona correcta");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().createHabilidadPersonal(hP2);
			assertEquals(2, resultado, "No ha devuelto el id de la persona correcta");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().createHabilidadPersonal(hP3);
			assertEquals(4, resultado, "No ha devuelto el id de la persona correcta");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().createHabilidadPersonal(hP4);
			assertEquals(1, resultado, "No ha devuelto el id de la persona correcta");

			/*********************** DELETE_HAB_PERSONAL *****************************/
			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().deleteHabilidadPersonal(hP3);
			assertEquals(1, resultado, "No se ha podido eliminar al personal");
			resultado = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().deleteHabilidadPersonal(hP4);
			assertEquals(1, resultado, "No se ha podido eliminar al personal");

			/*********************** READ_HABILIDAD_PERSONAL *****************************/

			assertEquals(hP1, FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(1, 2));
			assertEquals(hP2, FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(2, 1));
			assertEquals(1, FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(1, 2)
					.getIdPersonal());
			assertEquals(2, FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readHabilidadPersonal(2, 1)
					.getIdPersonal());

			/*********************** READ_ALL *****************************/
			List<THabilidadPersonal> habilidadespersonal = new ArrayList<>();
			habilidadespersonal = FactoryIntgr.getInstance().generateDAOHabilidadPersonal().readAll();
			assertEquals(habilidadespersonal.size(), 4, "La lista no contiene los elementos que nosotros queremos");
			assertTrue(habilidadespersonal.contains(hP1));
			assertTrue(habilidadespersonal.contains(hP2));

			/*********************** READ_HAB_PER_VINCULADA *****************************/
			habilidadespersonal = FactoryIntgr.getInstance().generateDAOHabilidadPersonal()
					.readHabilidadVinculadaPersonal(2);
			assertEquals(habilidadespersonal.size(), 1, "La lista no contiene los elementos que nosotros queremos");
			assertTrue(habilidadespersonal.contains(hP1));
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		}

	}
}
