package br.com.sistemaepi.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager openConnection() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistema_epi");
		
		return factory.createEntityManager();
			
	}
	
	
}
