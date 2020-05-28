package br.com.escolamusica.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager openConnection() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("escola_musica");
		
		return factory.createEntityManager();
				
	}
	
	
}
