package br.com.cpo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.ProdEst;

@Service
@Transactional
public class ProdEstServices implements Serializable {

	private static final long serialVersionUID = -8056753488139342631L;

	@PersistenceContext
	private EntityManager em;

	// Salvar
		public void salvar(ProdEst prodEst) {
			em.merge(prodEst);
		}

		// Excluir
		public void excluir(ProdEst prodEst) {
			em.remove(em.merge(prodEst));
		}

		// Pesquisas
		public List<ProdEst> listar() {
			return em.createQuery("from ProdEst order by id desc", ProdEst.class).getResultList();
		}

		public ProdEst busca(String cod, String lote, Date data) {

			try {
				return em.createQuery("from ProdEst where cod =:cod and lote =:lote and data =:data", ProdEst.class)
						.setParameter("cod", cod).setParameter("lote", lote).setParameter("data", data).getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		}
		
		public ProdEst buscaExcluir(String cod, String lote) {

			try {
				return em.createQuery("from ProdEst where cod =:cod and lote =:lote ", ProdEst.class)
						.setParameter("cod", cod).setParameter("lote", lote).getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		}

		public List<ProdEst> hoje(Date data) {
			return em.createQuery("from ProdEst where data =:data order by id desc", ProdEst.class)
					.setParameter("data", data).getResultList();
		}
	

}
