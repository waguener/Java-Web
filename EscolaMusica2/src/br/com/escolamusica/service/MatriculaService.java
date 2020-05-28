package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Matricula;

@Service
@Transactional
public class MatriculaService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public List<Matricula> listar(){
			
		return entityManager.createQuery("from Matricula order by numero", Matricula.class).getResultList();			
	}
	
}
