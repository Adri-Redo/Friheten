package Integracion.Transactions;

import java.sql.SQLException;

public interface Transaction {
	public void start();
	public void commit();
	public void rollback();
	public Object getResource() throws SQLException;
}
