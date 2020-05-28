package br.com.cpo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Estoque;

@Service
@Transactional
public class EstoqueServices {

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(Estoque estoque) {
		em.merge(estoque);
	}

	// Excluir
	public void excluir(Estoque estoque) {
		em.remove(em.merge(estoque));
	}

	// Pesquisas
	public List<Estoque> listar() {
		return em.createQuery("from Estoque where total > '0' order by id desc", Estoque.class).getResultList();
	}

	public Estoque controle(String cod, String lote) {
		try {
			return em.createQuery(
					"select est from Estoque est inner join est.recebimento rc where rc.lote =:lote and rc.codigo =:codigo order by rc.produto",
					Estoque.class).setParameter("codigo", cod).setParameter("lote", lote).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

}
