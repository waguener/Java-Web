package br.com.integra_olgber.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Agencia;

@Service
@Transactional
public class AgenciaServices {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Agencia agencia) {

		entityManager.merge(agencia);
		entityManager.close();
	}

	public List<Agencia> listar() {
		return entityManager.createQuery("from Agencia order by id desc", Agencia.class).getResultList();
		
	}

	public void excluir(Agencia agencia) {
		entityManager.remove(entityManager.merge(agencia));
		entityManager.close();
	}

	public List<Agencia> porNome(String nome) {
		return entityManager.createQuery("from Agencia where nome like :nome order by id desc", Agencia.class)
				.setParameter("nome", nome + "%").getResultList();
		
	}
	
	public Object porAgencia(String nome) {
		return entityManager.createQuery("from Agencia where nome = :nome ", Agencia.class)
				.setParameter("nome", nome).getResultList();
		
	}
	
	public Object porCodAgencia(long id) {
		return entityManager.createQuery("from Agencia where id = :id ", Agencia.class)
				.setParameter("id", id).getSingleResult();
		
	}
	
	public Agencia porAgenciaAtestado(String nome) {
		return entityManager.createQuery("from Agencia where nome = :nome ", Agencia.class)
				.setParameter("nome", nome).getSingleResult();
		
	}

}
