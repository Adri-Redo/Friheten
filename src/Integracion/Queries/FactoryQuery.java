package Integracion.Queries;

public abstract class FactoryQuery {
	private static FactoryQuery instance;

    public static FactoryQuery getInstance() {

        if (instance == null)
            instance = new FactoryQueryImp();

        return instance;
    }
    
    public abstract Query nuevaQuery(String nombre);
}
