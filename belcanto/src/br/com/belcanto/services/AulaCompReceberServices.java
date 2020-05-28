package br.com.belcanto.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.AulaCompReceber;

@Service
@Transactional
public class AulaCompReceberServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(AulaCompReceber aulaCompReceber) {
		em.merge(aulaCompReceber);
		em.close();

	}

	public void excluir(AulaCompReceber aulaCompReceber) {
		em.remove(em.merge(aulaCompReceber));
		em.close();
	}

	

}
