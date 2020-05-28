package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Curso;

@Service
@Transactional
public class CursoServices {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Curso curso) {
		em.merge(curso);
		em.close();
	}
	
	public void excluir(Curso curso) {
		em.remove(em.merge(curso));
		em.close();
	}
	
	public List<Curso> listar(){
		try {
			return em.createQuery("from Curso where ativo = 'true' order by id desc",Curso.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Curso> listarCursos(){
		try {
			return em.createQuery("from Curso order by id desc",Curso.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public Curso peloNome(String nome) {
		try {
			return em.createQuery("from Curso where nome =:nome",Curso.class).setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
