package Integracion.Transactions;



public abstract class TransactionManager {
	private static TransactionManager instance;
	
	public static synchronized TransactionManager getInstance() {
		if (instance == null) {
			instance = new TransactionManagerImp();
		}
		return instance;
	}
	public abstract Transaction nuevaTransaccion() throws Exception;
	public abstract Transaction getTransaccion();
	public abstract void eliminaTransaccion();
}
