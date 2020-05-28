package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.criteria.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Turma;

@Service
@Transactional
public class TurmaServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Turma turma) {
		em.merge(turma);
		em.close();
	}

	public void excluir(Turma turma) {
		em.remove(turma);
		em.close();
	}

	public List<Turma> listarTodas() {
		try {
			return em.createQuery("from Turma", Turma.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Turma> listarTurmasAtivas() {
		try {
			return em.createQuery("from Turma where ativo = 'true' order by nome", Turma.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Turma> porNome(String nome) {
		try {
			return em.createQuery("from Turma where nome like :nome order by id desc", Turma.class)
					.setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
