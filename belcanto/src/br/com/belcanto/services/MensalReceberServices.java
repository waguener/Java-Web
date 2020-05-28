package br.com.belcanto.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.MensalReceber;

@Service
@Transactional
public class MensalReceberServices {

	@PersistenceContext
	private EntityManager em;

	public MensalReceber salvar(MensalReceber mensalReceber) {
		return em.merge(mensalReceber);

	}

	public void excluir(MensalReceber mensalReceber) {
		em.remove(em.merge(mensalReceber));
		em.close();
	}

	

}
