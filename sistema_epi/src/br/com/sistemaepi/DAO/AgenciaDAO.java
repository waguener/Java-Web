package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.AgenciaBean;

public class AgenciaDAO {

	public static void salvar(AgenciaBean agencia) {

		EntityManager em = JPAUtil.openConnection();
		em.getTransaction().begin();

		em.merge(agencia);

		em.getTransaction().commit();

		em.close();

	}

	public static void excluirAgencia(AgenciaBean agencia) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(agencia));

		em.getTransaction().commit();

		em.close();

	}

	public static List<AgenciaBean> listarAgencias() {
		EntityManager em = JPAUtil.openConnection();

		List<AgenciaBean> agencia = em.createQuery("from AgenciaBean order by id desc", AgenciaBean.class)
				.getResultList();
		em.close();
		return agencia;

	}

}
