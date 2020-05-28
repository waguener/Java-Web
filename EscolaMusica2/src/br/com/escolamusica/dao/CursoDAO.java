package br.com.escolamusica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.escolamusica.model.Curso;

public class CursoDAO {
	
	public static void salvar(Curso curso) {
						
		EntityManager em = JPAUtil.openConnection();
		
		em.getTransaction().begin();
		
		//em.persist(curso);
		
		em.merge(curso);
						
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static List<Curso> Listar(){
		EntityManager em = JPAUtil.openConnection();
		
		List<Curso> cursos = em.createQuery("from Curso order by nome desc", Curso.class).getResultList();
		em.close();
		return cursos;
		
	}
	
	public static void excluir(Curso curso) {
		EntityManager em = JPAUtil.openConnection();
		
		em.getTransaction().begin();
		
		em.remove(em.merge(curso));
		
		em.getTransaction().commit();
		
		em.close();
	}

}
