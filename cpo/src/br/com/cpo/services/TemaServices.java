package br.com.cpo.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Tema;

@Service
@Transactional
public class TemaServices {

	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Tema tema) {
		em.merge(tema);
	}
}
