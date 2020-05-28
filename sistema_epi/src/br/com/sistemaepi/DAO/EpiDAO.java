package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.EpiBean;

public class EpiDAO {

	public static void salvarEpi(EpiBean epi) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.merge(epi);

		em.getTransaction().commit();

		em.close();

	}

	public static void ExcluirEpi(EpiBean epi) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(epi));

		em.getTransaction().commit();

		em.close();

	}

	public static List<EpiBean> ListarEpi() {
		EntityManager em = JPAUtil.openConnection();

		List<EpiBean> epi = em.createQuery("from EpiBean order by id desc", EpiBean.class).getResultList();
		em.close();
		return epi;

	}
	
	public static List<EpiBean> porDesc(String descricao) {
		EntityManager em = JPAUtil.openConnection();
		List<EpiBean> busca = em
				.createQuery("from EpiBean where descricao like :descricao order by data desc", EpiBean.class)
				.setParameter("descricao", "%" + descricao + "%").getResultList();
		em.close();
		return busca;
	}
	
	

}
