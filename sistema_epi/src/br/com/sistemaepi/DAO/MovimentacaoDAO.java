package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.MovimentacaoBean;

public class MovimentacaoDAO {

	public static void salvar(MovimentacaoBean movimentacao) {

		EntityManager em = JPAUtil.openConnection();
		em.getTransaction().begin();

		em.merge(movimentacao);

		em.getTransaction().commit();

		em.close();

	}
	
	

	public static void excluirMovimentacao(MovimentacaoBean movimentacao) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(movimentacao));

		em.getTransaction().commit();

		em.close();

	}

	public static List<MovimentacaoBean> listarMovimentacao() {
		EntityManager em = JPAUtil.openConnection();

		List<MovimentacaoBean> movimentacao = em.createQuery("from MovimentacaoBean order by id desc", MovimentacaoBean.class)
				.getResultList();
		em.close();
		return movimentacao;

	}

}
