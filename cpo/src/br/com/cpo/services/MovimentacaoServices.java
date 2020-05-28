package br.com.cpo.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Movimentacao;

@Service
@Transactional
public class MovimentacaoServices {

	@PersistenceContext
	private EntityManager em;

	// Salvar
	public void salvar(Movimentacao movimentacao) {
		em.merge(movimentacao);
	}

	// Excluir
	public void excluir(Movimentacao movimentacao) {
		em.remove(em.merge(movimentacao));
	}

	// Pesquisas
	public List<Movimentacao> listar() {
		return em.createQuery("from Movimentacao order by id desc", Movimentacao.class).getResultList();
	}

	public List<Movimentacao> devolucao(String lote, String cod) {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where rc.lote =:lote and rc.codigo =:cod and rc.status = 'RETIRADO' order by mov.id",
					Movimentacao.class).setParameter("lote", lote).setParameter("cod", cod).getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Movimentacao> saida(String lote, String cod) {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where rc.lote =:lote and rc.codigo =:cod and rc.status = 'RECEBIDO' order by mov.id",
					Movimentacao.class).setParameter("lote", lote).setParameter("cod", cod).getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Movimentacao> mov(String cod, String lote) {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where rc.lote =:lote and rc.codigo =:cod order by rc.lote ",
					Movimentacao.class).setParameter("cod", cod).setParameter("lote", lote).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Movimentacao> porData(Date dataInicial, Date dataFinal) {
		return em.createQuery(
				"select mov from Movimentacao mov inner join mov.recebimento rc where rc.dataEntrada between :dataInicial and :dataFinal order by mov.id desc",
				Movimentacao.class).setParameter("dataInicial", dataInicial).setParameter("dataFinal", dataFinal)
				.getResultList();
	}

	public List<Movimentacao> buscaHoje(Date data1) {
		return em.createQuery(
				"select mov from Movimentacao mov inner join mov.recebimento rc where rc.dataEntrada =:data1 order by mov.id desc ",
				Movimentacao.class).setParameter("data1", data1).getResultList();
	}

	public List<Movimentacao> buscaDev(Date data) {
		return em.createQuery("from Movimentacao where dataDevCliente =:data order by id ", Movimentacao.class)
				.setParameter("data", data).getResultList();
	}

	public List<Movimentacao> ultimasMov(Date data1) {
		return em.createQuery(
				"select mov from Movimentacao mov inner join mov.recebimento rc where rc.dataEntrada =:data1 order by mov.id ",
				Movimentacao.class).setParameter("data1", data1).getResultList();
	}

	public List<Movimentacao> ultimasRetiradas(Date data) {
		return em.createQuery("from Movimentacao where dataRetirada =:data order by dataRetirada desc",
				Movimentacao.class).setParameter("data", data).getResultList();
	}

	public List<Movimentacao> ultimasDev(Date data) {
		return em
				.createQuery("from Movimentacao where dataRetorno =:data order by dataRetorno desc", Movimentacao.class)
				.setParameter("data", data).getResultList();
	}

	public List<Movimentacao> ultimasDevCliente(Date data) {
		return em.createQuery("from Movimentacao where dataDevCliente =:data order by dataDevCliente desc",
				Movimentacao.class).setParameter("data", data).getResultList();
	}

	public Movimentacao porId(Long id) {
		return em.createQuery("from Movimentacao where id =:id", Movimentacao.class).setParameter("id", id)
				.getSingleResult();
	}

	public Movimentacao porIdExcluir(Long id) {
		return em.createQuery(
				"select mov from Movimentacao mov inner join mov.recebimento rc where mov.recebimento.id =:id",
				Movimentacao.class).setParameter("id", id).getSingleResult();
	}

	public Movimentacao porCodBar(String codbar) {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where mov.codbar =:codbar and rc.status = 'RECEBIDO' ",
					Movimentacao.class).setParameter("codbar", codbar).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	public Movimentacao porCodBarDev(String codbar) {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where mov.codbar =:codbar and rc.status = 'RETIRADO' ",
					Movimentacao.class).setParameter("codbar", codbar).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	public List<Movimentacao> buscarRack() {
		try {
			return em.createQuery(
					"select mov from Movimentacao mov inner join mov.recebimento rc where rc.tipo = 'INSUMO' order by mov.id desc ",
					Movimentacao.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

}
