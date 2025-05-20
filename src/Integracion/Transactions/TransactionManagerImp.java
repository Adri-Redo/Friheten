package Integracion.Transactions;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {
	private ConcurrentHashMap<Thread,Transaction> map;
	
	public TransactionManagerImp() {
		//preguntar si hay que inicializar el mapa de esta manera
		super();
		map = new ConcurrentHashMap<Thread,Transaction>();
	
	}
	@Override
	public Transaction nuevaTransaccion() throws Exception {
		Thread thread = Thread.currentThread();
		Transaction t = map.get(thread);
		if(t != null)
		{
			throw new Exception("este hilo ya tiene una transaccion asocidada");
		}
		else
		{
			TransactionFactory tfactory = TransactionFactory.getInstance();
			t = tfactory.newTransaction();
			map.put(thread, t);
			return t;
		}
	}

	@Override
	public Transaction getTransaccion() {
		return map.get(Thread.currentThread());
	}

	@Override
	public void eliminaTransaccion() {
		map.remove(Thread.currentThread());
	}

}
