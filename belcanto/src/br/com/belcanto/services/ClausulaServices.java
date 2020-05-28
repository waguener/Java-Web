package br.com.belcanto.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Clausula;

@Service
@Transactional
public class ClausulaServices {

	@PersistenceContext
	private EntityManager em;

	public Clausula salvar(Clausula clausula) {
		return em.merge(clausula);

	}

	public void excluir(Clausula clausula) {
		em.remove(em.merge(clausula));
		em.close();
	}

	

}
