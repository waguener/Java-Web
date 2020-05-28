package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Curso;

@Service("cursoService")
@Transactional
public class CursoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Curso curso) {
		
		entityManager.merge(curso);
		
	}
	
	public List<Curso> listar(){
		
		return entityManager.createQuery("from Curso order by nome desc", Curso.class).getResultList();
		
	}
	
	public void excluir(Curso curso) {
		entityManager.remove(entityManager.merge(curso));
	}

}
