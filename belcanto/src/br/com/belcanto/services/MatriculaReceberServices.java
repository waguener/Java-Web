package br.com.belcanto.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.MatriculaReceber;

@Service
@Transactional
public class MatriculaReceberServices {

	@PersistenceContext
	private EntityManager em;

	public MatriculaReceber salvar(MatriculaReceber matriculaReceber) {
		return em.merge(matriculaReceber);

	}

	public void excluir(MatriculaReceber matriculaReceber) {
		em.remove(em.merge(matriculaReceber));
		em.close();
	}

	

}
