package br.com.cpo.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Carga;
import br.com.cpo.model.Recebimento;

@Service
@Transactional
public class RecebimentoServices {

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(Recebimento recebimento) {
		em.merge(recebimento);
	}

	// Excluir
	public void excluir(Recebimento recebimento) {
		em.remove(em.merge(recebimento));
	}

	// Pesquisas
	public List<Recebimento> listar() {
		return em.createQuery("from Recebimento order by id desc", Recebimento.class).getResultList();
	}

	public List<Recebimento> entreData(Date data1, Date data2) {
		return em
				.createQuery("from Recebimento where dataEntrada BETWEEN :dataEntrada and :data2 ORDER BY id desc ",
						Recebimento.class)
				.setParameter("dataEntrada", data1).setParameter("data2", data2).getResultList();
	}

	public List<Recebimento> porData(Date data1, Date data2) {
		return em.createQuery(
				"select rc from Recebimento rc inner join rc.movimentacao mov where rc.dataEntrada BETWEEN :data1 and :data2 ORDER BY rc.id desc ",
				Recebimento.class).setParameter("data1", data1).setParameter("data2", data2).getResultList();
	}

	public List<Recebimento> hoje(Date data1) {
		return em.createQuery("from Recebimento where dataEntrada =:dataEntrada ORDER BY id desc ", Recebimento.class)
				.setParameter("dataEntrada", data1).getResultList();
	}

	public List<Recebimento> numLote(String lote) {
		try {
			return em.createQuery("from Recebimento where lote =:lote ", Recebimento.class).setParameter("lote", lote)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Recebimento ultimoId() {

		String recebimento = em.createQuery("select max(id) from Recebimento ").getSingleResult().toString();
		Recebimento id = new Recebimento();

		id.setId(Long.parseLong(recebimento));

		return id;
	}

	public Recebimento porId(Long id) {
		return em.createQuery("from Recebimento where id =:id",Recebimento.class).setParameter("id", id).getSingleResult();
	}
	
	public String repetir(String cod) {
		try {
			String recebimento = em.createQuery(
					"select max(id) from Recebimento where codigo =:cod ")
					.setParameter("cod", cod).getSingleResult().toString();
			return recebimento;
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public Recebimento obterPorId(Long id) {

		return em.find(Recebimento.class, id);
	}
	public Recebimento testeUd(String ud) {
		try {
			return em.createQuery("from Recebimento where numeroRack =:ud",Recebimento.class).setParameter("ud", ud).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
}
