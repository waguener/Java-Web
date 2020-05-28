package br.com.integra_olgber.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Login;

@Service
@Transactional
public class LoginServices {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Login login ) {

		entityManager.merge(login);
		entityManager.close();
	}

	
	
}
