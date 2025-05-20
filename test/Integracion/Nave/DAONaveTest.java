package Integracion.Nave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.Nave.TNave;

public class DAONaveTest {
	
	@BeforeEach
	public void setup() {
		Transaction t = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			con = (Connection) t.getResource();
			ps = con.prepareStatement("DELETE FROM personal");
			ps.execute();
			ps = con.prepareStatement("DELETE FROM nave");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE nave AUTO_INCREMENT = 1");
			ps.execute();
			t.commit();

			con.close();
			ps.close();
		}catch(Exception e) {
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testsFuncionesDAONave() {

		Transaction t = null;
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			int resultado;

			TNave n1 = new TNave(1, "Madrid", "NaveTest", 200, true);
			TNave n2 = new TNave(2, "Barcelona", "NaveTest2", 300, true);

			

			/*********************** CREATE *****************************/

			
			resultado = FactoryIntgr.getInstance().generateDAONave().create(n1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAONave().create(n2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");

			/*********************** READ *****************************/
			assertEquals(n1, FactoryIntgr.getInstance().generateDAONave().read(1));
			assertEquals(n2, FactoryIntgr.getInstance().generateDAONave().read(2));

			/*********************** READ_ALL *****************************/
			List<TNave> naves = new ArrayList<>();
			naves = FactoryIntgr.getInstance().generateDAONave().readAll();
			assertEquals(naves.size(), 2, "La lista no contiene los elementos que nosotros queremos");
			assertTrue(naves.contains(n1));
			assertTrue(naves.contains(n2));
			
			/*********************** READ BY NAME *****************************/
			
			assertEquals(n1, FactoryIntgr.getInstance().generateDAONave().readByName(n1.getNombre()));
			assertEquals(n2, FactoryIntgr.getInstance().generateDAONave().readByName(n2.getNombre()));

			/*********************** UPDATE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAONave().update(n1));
			assertEquals(1, FactoryIntgr.getInstance().generateDAONave().update(n2));

			/*********************** DELETE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAONave().delete(1));
			assertEquals(2, FactoryIntgr.getInstance().generateDAONave().delete(2));
			assertEquals(0, FactoryIntgr.getInstance().generateDAONave().delete(12));
		}catch(Exception e){
			if(t!= null) {
				t.rollback();
			}
			e.printStackTrace();
		}
	}
}
