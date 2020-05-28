package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.AulaComp;
import br.com.belcanto.model.Modulo;

@Service
@Transactional
public class AulaCompServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(AulaComp aulaComp) {
		em.merge(aulaComp);
		em.close();
	}

	public void excluir(AulaComp aulaComp) {
		em.remove(em.merge(aulaComp));
		em.close();
	}

	public List<AulaComp> listar(Long id) {
		try {
			return em
					.createQuery("select mod from AulaComp mod inner join mod.curso cs where cs.id =:id ",
							AulaComp.class)
					.setParameter("id", id).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
}
