package Integracion.Transactions;

public abstract class TransactionFactory {
	private static TransactionFactoryImp instance;
	
	public static synchronized TransactionFactory getInstance()
	{
		if(instance == null)
		{
			instance = new TransactionFactoryImp();
		}
		return instance;
	}
	
	public abstract Transaction newTransaction();
}
