package Integracion.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Negocio.Cliente.*;
import Integracion.FactoryIntegracion.FactoryIntgr;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;


public class DAOClienteTest {
	
	
	@BeforeEach
	public void setUp(){
		Transaction ts = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			System.out.println("TRANSACCION?");
			ts = TransactionManager.getInstance().nuevaTransaccion();
			ts.start();
			con = (Connection) ts.getResource();
			ps = con.prepareStatement("DELETE FROM cliente");
			ps.execute();
			ps = con.prepareStatement("ALTER TABLE cliente AUTO_INCREMENT = 1");
			ps.execute();
			ts.commit();
			con.close();
			ps.close();
		}catch(Exception e)
		{
			if (ts != null)
				ts.rollback();
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testsDAOCliente() {
		Transaction ts = null;
		try {
			ts = TransactionManager.getInstance().nuevaTransaccion();
			ts.start();
			// *********************** CREATE *****************************
			TCliente c1 = new TEmpresa(1, "Paco", "4321bB!0", "hola@mail.es", true, "NATILLAS");
			TCliente c2 = new TIndividual(2, "Pepe", "1234Aa*5", "correo@mail.es", true, 11111);
			
			int resultado;
			resultado = FactoryIntgr.getInstance().generateDAOCliente().create(c1);
			assertEquals(1, resultado, "No ha devuelto el id que acaba de crear");
			resultado = FactoryIntgr.getInstance().generateDAOCliente().create(c2);
			assertEquals(2, resultado, "No ha devuelto el id que acaba de crear");
			
			/*********************** READ *****************************/
			assertEquals(c1, FactoryIntgr.getInstance().generateDAOCliente().read(1));
			assertEquals(c2, FactoryIntgr.getInstance().generateDAOCliente().read(2));
			
			/*********************** READALL *****************************/
			List<TCliente> Clientes = new ArrayList<>();
			Clientes = FactoryIntgr.getInstance().generateDAOCliente().readAll();
			assertEquals(Clientes.size(), 2, "La lista no contiene los elementos que nosotros queremos");
			assertTrue(Clientes.contains(c1));
			assertTrue(Clientes.contains(c2));
			/*********************** UPDATE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAOCliente().update(c1));
			assertEquals(2, FactoryIntgr.getInstance().generateDAOCliente().update(c2));
			/*********************** DELETE *****************************/
			assertEquals(1, FactoryIntgr.getInstance().generateDAOCliente().delete(1));
			assertEquals(2, FactoryIntgr.getInstance().generateDAOCliente().delete(2));	
			assertEquals(0, FactoryIntgr.getInstance().generateDAOCliente().delete(12));
			ts.commit();
		}	catch(Exception e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
			e.printStackTrace();
		}
		
	}
}