package br.com.cpo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.CadFuncionario;

@Service
@Transactional
public class CadFuncionarioServices {

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(CadFuncionario cadFuncionario) {
		em.merge(cadFuncionario);
	}

	// Excluir
	public void excluir(CadFuncionario cadFuncionario) {
		em.remove(em.merge(cadFuncionario));
	}

	// Pesquisas
	public List<CadFuncionario> listar() {
		return em.createQuery("from CadFuncionario order by nome ", CadFuncionario.class).getResultList();
	}

	
}
