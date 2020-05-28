package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sistemaepi.Bean.EntregaBean;

public class EntregaDAO {

	
	public static void salvar(EntregaBean entrega) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();
  
		em.merge(entrega);

		em.getTransaction().commit();

		em.close();

	}
	
	public static void excluir(EntregaBean entrega) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(entrega));

		em.getTransaction().commit();

		em.close();
	}
	
	
	
	public static List<EntregaBean> listar() {
		EntityManager em = JPAUtil.openConnection();

		List<EntregaBean> entregas = em
				.createQuery("from EntregaBean order by id", EntregaBean.class).getResultList();
		em.close();
		return entregas;

	}
	
	public static List<EntregaBean> protocolo(String protocolo) {
		EntityManager em = JPAUtil.openConnection();
		List<EntregaBean> busca = em
				.createQuery("from EntregaBean where protocolo like :protocolo order by id_nome", EntregaBean.class)
				.setParameter("protocolo", "%" + protocolo + "%").getResultList();
		em.close();
		return busca;
	}
	
	public static List<EntregaBean> MaxId() {
		EntityManager em = JPAUtil.openConnection();
		List<EntregaBean> busca = em
				.createQuery("select MAX(id)from EntregaBean", EntregaBean.class).getResultList();
		em.close();
		return busca;
	}
	
}
