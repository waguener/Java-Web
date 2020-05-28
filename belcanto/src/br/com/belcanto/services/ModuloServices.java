package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Modulo;

@Service
@Transactional
public class ModuloServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Modulo modulo) {
		em.merge(modulo);
		em.close();
	}

	public void excluir(Modulo modulo) {
		em.remove(em.merge(modulo));
		em.close();
	}

	public List<Modulo> listar(Long id) {
		try {
			return em
					.createQuery("select mod from Modulo mod inner join mod.curso cs where cs.id =:id ",
							Modulo.class)
					.setParameter("id", id).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public String totalMod(Long id) {
		
		String t = em.createQuery("select count(*) mod from Modulo mod inner join mod.curso cs where cs.id =:id")
				.setParameter("id", id).getSingleResult().toString();
			 return t;
	}
}
