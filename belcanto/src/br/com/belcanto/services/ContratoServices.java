package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Contrato;

@Service
@Transactional
public class ContratoServices {

	@PersistenceContext
	private EntityManager em;

	public Contrato salvar(Contrato contrato) {
		return em.merge(contrato);

	}

	public void excluir(Contrato contrato) {
		em.remove(em.merge(contrato));
		em.close();
	}

	public List<Contrato> listaContrato(){
		try {
			return em.createQuery("from Contrato order by nome ", Contrato.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	public Contrato contrato(){
		try {
			return em.createQuery("from Contrato where nome = 'ALUNO' ", Contrato.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
