package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.FuncionarioBean;

public class FuncionarioDAO {

	public static void salvarFunc(FuncionarioBean funcionario) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.merge(funcionario);

		em.getTransaction().commit();

		em.close();

	}

	public static void excluirFuncionario(FuncionarioBean funcionario) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(funcionario));

		em.getTransaction().commit();

		em.close();
	}

	public static List<FuncionarioBean> listar() {
		EntityManager em = JPAUtil.openConnection();

		List<FuncionarioBean> funcionarios = em.createQuery("from FuncionarioBean order by nome", FuncionarioBean.class)
				.getResultList();
		em.close();
		return funcionarios;

	}

	public static List<FuncionarioBean> porNome(String nome) {
		EntityManager em = JPAUtil.openConnection();
		List<FuncionarioBean> busca = em
				.createQuery("from FuncionarioBean where nome like :nome order by nome", FuncionarioBean.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
		em.close();
		return busca;
	}
}
