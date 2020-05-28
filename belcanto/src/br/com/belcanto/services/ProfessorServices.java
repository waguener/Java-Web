package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Professor;

@Service
@Transactional
public class ProfessorServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Professor professor) {
		em.merge(professor);
		em.close();
	}

	public void excluir(Professor professor) {
		em.remove(em.merge(professor));
		em.close();
	}

	public List<Professor> listar() {
		try {
			return em.createQuery("from Professor", Professor.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Professor> listarAtivos() {
		try {
			return em.createQuery("from Professor where ativo = 'true' order by nome", Professor.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Professor> porNome(String nome) {
		try {
			return em.createQuery("from Professor where nome like :nome order by nome", Professor.class)
					.setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public String totalProf() {
			
		String t = em.createQuery("select count(*) from Professor where ativo = 'true'").getSingleResult().toString();
			 return t;
	}

	public Professor obterPorId(Long id) {
		try {
			return em.find(Professor.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}
}
