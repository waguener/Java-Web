package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Perfil;

@Service
@Transactional
public class PerfilService {
	
	
	@PersistenceContext
	private EntityManager em;

	public void salvar(Perfil perfil) {
		em.merge(perfil);
	}
	
	@SuppressWarnings("unchecked")
	public List<Perfil> listar(){
		return em.createNamedQuery(Perfil.LISTAR_TODOS).getResultList();
	}
	
}
