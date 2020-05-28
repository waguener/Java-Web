package br.com.cpo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Rack;

@Service
@Transactional
public class RackServices {

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(Rack rack) {
		em.merge(rack);
	}

	// Excluir
	public void excluir(Rack rack) {
		em.remove(em.merge(rack));
	}

	// Pesquisas
	public List<Rack> listar() {
		return em.createQuery("from Rack order by id desc", Rack.class).getResultList();
	}

	public Object teste() {
		return em.createQuery("from Material order by id desc").getSingleResult();
	}
	
}
