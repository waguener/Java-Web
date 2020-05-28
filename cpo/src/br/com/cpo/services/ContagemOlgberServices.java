package br.com.cpo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.ContagemOlgber;

@Service
@Transactional
public class ContagemOlgberServices implements Serializable {

	private static final long serialVersionUID = -8056753488139342631L;

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(ContagemOlgber contagem) {
		em.merge(contagem);
	}

	// Excluir
	public void excluir(ContagemOlgber contagem) {
		em.remove(em.merge(contagem));
	}

	// Pesquisas
	public List<ContagemOlgber> listar() {
		return em.createQuery("from ContagemOlgber order by id desc", ContagemOlgber.class).getResultList();
	}

	public ContagemOlgber busca(String cod, String lote) {

		try {
			return em.createQuery(
					"select co from ContagemOlgber co inner join co.estoque e where e.recebimento.codigo =:cod and e.recebimento.lote =:lote",
					ContagemOlgber.class).setParameter("cod", cod).setParameter("lote", lote).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ContagemOlgber produto(String codigo) {

		try {
			return em.createQuery("from ContagemOlgber where codigo =:codigo", ContagemOlgber.class)
					.setParameter("codigo", codigo).getSingleResult();
		} catch (NoResultException e) {

			return null;

		}

	}

	public List<ContagemOlgber> hoje(Date data) {
		
		try {
		return	em.createQuery("from ContagemOlgber where data =:data order by id desc", ContagemOlgber.class)
			.setParameter("data", data).getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
}
