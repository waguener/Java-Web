package br.com.escolamusica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.escolamusica.model.Matricula;

public class MatriculaDAO {
	
	public static void salvar(Matricula matricula) {
						
		EntityManager em = JPAUtil.openConnection();
		
		em.getTransaction().begin();
		
		//em.persist(curso);
		
		em.merge(matricula);
						
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static List<Matricula> Listar(){
		EntityManager em = JPAUtil.openConnection();
		
		List<Matricula> matriculas = em.createQuery("from Matricula order by numero", Matricula.class).getResultList();
		em.close();
		return matriculas;
		
	}
	public static void Excluir(Matricula matricula) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(matricula));

		em.getTransaction().commit();

		em.close();
	}


}
