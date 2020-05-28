package br.com.integra_olgber.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Turno;

@Service
@Transactional
public class TurnoServices {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Turno turno) {

		entityManager.merge(turno);
		entityManager.close();
	}

	public List<Turno> listar() {
		return entityManager.createQuery("from Turno order by turno asc", Turno.class).getResultList();
		
	}

	public void excluir(Turno turno) {
		entityManager.remove(entityManager.merge(turno));
		entityManager.close();
	}

	
}
