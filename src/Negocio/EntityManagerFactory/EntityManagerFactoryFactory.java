package Negocio.EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryFactory {
	private static EntityManagerFactory instance;
	
	public static synchronized EntityManagerFactory getInstance() {
		if(instance == null) {
			instance = Persistence.createEntityManagerFactory("FrihetenUML");
		}
		return instance;
	}
}
