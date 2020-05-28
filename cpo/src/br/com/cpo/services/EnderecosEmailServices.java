package br.com.cpo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.EnderecosEmail;

@Service
@Transactional
public class EnderecosEmailServices {

	@PersistenceContext
	private EntityManager em;


	// Salvar
	public void salvar(EnderecosEmail enderecosEmail ) {
		em.merge(enderecosEmail);
	}
	
	
	// Excluir
	public void excluir(EnderecosEmail enderecosEmail) {
		em.remove(em.merge(enderecosEmail));
	}
	
	// Pesquisas
	public List<EnderecosEmail> listar() {
		return em.createQuery("from EnderecosEmail order by id desc", EnderecosEmail.class).getResultList();
	}

	public List<EnderecosEmail> porNome(String nome) {
		return em.createQuery("from EnderecosEmail where nome like =:nome order by id desc", EnderecosEmail.class)
				.setParameter("nome", nome).getResultList();
	}

	
	
	

}
