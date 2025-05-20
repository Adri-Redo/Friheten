package Negocio.Nave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Negocio.FactoryNegocio.FactoryNeg;

public class SANaveTest {
	
	
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
	
			} catch (Exception e) {
				if (t != null)
					t.rollback();
				e.printStackTrace();
			}
		}
	
		@Test
		public void testsFuncionesSANave() {
			
				int resultado;
				
				TNave n1 = new TNave(1, "Madrid", "NaveTest", 200, true);
				TNave n2 = new TNave(2, "Barcelona", "NaveTest2", 250, true);
				TNave n3 = new TNave(3, "Valencia", "NaveTest3", 300, true);

				
				/*********************** CREATE *****************************/

				resultado = FactoryNeg.getInstance().generateSANave().create(n1);
				assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
				
				resultado = FactoryNeg.getInstance().generateSANave().create(n2);
				assertEquals(2, resultado, "Deberia de dar fallo");
				
				resultado = FactoryNeg.getInstance().generateSANave().create(n3);
				assertEquals(3, resultado, "Deberia de dar fallo, la nave ya existe");

				/*********************** READ *****************************/
				assertEquals(n1, FactoryNeg.getInstance().generateSANave().read(1));
				assertEquals(n2, FactoryNeg.getInstance().generateSANave().read(2));
				assertEquals(n3, FactoryNeg.getInstance().generateSANave().read(3));
				
				/*********************** READ_ALL *****************************/
				List<TNave> naves = new ArrayList<>();
				naves = FactoryNeg.getInstance().generateSANave().readAll();
				assertEquals(3, naves.size(), "La lista no contiene los elementos que nosotros queremos");
				assertTrue(naves.contains(n1));
				assertTrue(naves.contains(n2));
				assertTrue(naves.contains(n3));
				
				/*********************** UPDATE *****************************/
				n1.setActivo(false);
				n1.setCapacidad(300);
				assertEquals(1, FactoryNeg.getInstance().generateSANave().update(n1));
				
				/*********************** DELETE *****************************/
				assertEquals(1, FactoryNeg.getInstance().generateSANave().delete(1));
				
			
		}
}
