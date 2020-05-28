package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.DevolucaoBean;

public class DevolucaoDAO {

	public static void salvar(DevolucaoBean devolucao) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.merge(devolucao);

		em.getTransaction().commit();

		em.close();

	}

	public static void excluir(DevolucaoBean devolucao) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(devolucao));

		em.getTransaction().commit();

		em.close();
	}

	public static List<DevolucaoBean> listar() {
		EntityManager em = JPAUtil.openConnection();

		List<DevolucaoBean> devolucao = em.createQuery("from DevolucaoBean ", DevolucaoBean.class)
				.getResultList();
		em.close();
		return devolucao;

	}

	

}
