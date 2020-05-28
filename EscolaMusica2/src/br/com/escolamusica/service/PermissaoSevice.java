package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Permissao;

@Service
@Transactional
public class PermissaoSevice {
	
	
	@PersistenceContext
	private EntityManager em;

	public void salvar(Permissao permissao) {
		em.merge(permissao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao> listar(){
		return em.createNamedQuery(Permissao.LISTAR_TODAS).getResultList();
	}
	
}
