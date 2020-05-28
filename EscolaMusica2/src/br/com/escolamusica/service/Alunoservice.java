package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Aluno;

@Service("alunoService")
@Transactional
public class Alunoservice {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Aluno aluno) {
		entityManager.merge(aluno);
	}
	
	public List<Aluno> listar(){
		return entityManager.createQuery("from Aluno ", Aluno.class).getResultList();
	}
	
	public void excluir (Aluno aluno) {
		entityManager.remove(entityManager.merge(aluno));
	}
}
