package br.com.sistemaepi.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.primefaces.event.ScheduleEntryMoveEvent;

import br.com.sistemaepi.Bean.EventoBean;

public class EventoDAO {

	

	public static void salvarEvento(EventoBean evento) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.merge(evento);

		em.getTransaction().commit();

		em.close();
	}
	
	public static void salvarEventoMov(ScheduleEntryMoveEvent eventos) {

		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.merge(eventos);

		em.getTransaction().commit();

		em.close();
	}
	
	public static void excluirEvento(EventoBean evento) {
		EntityManager em = JPAUtil.openConnection();

		em.getTransaction().begin();

		em.remove(em.merge(evento));

		em.getTransaction().commit();

		em.close();

	}

	public static List<EventoBean> listarEvento() {
		EntityManager em = JPAUtil.openConnection();

		List<EventoBean> evento = em.createQuery("from EventoBean", EventoBean.class).getResultList();
		em.close();
		System.out.println(" Retorno "+ evento);
		return evento;

	}
	
	public static EventoBean obterPorId(Long id) {
		EntityManager em = JPAUtil.openConnection();

		EventoBean evento = em.find(EventoBean.class, id);
		
		em.close();

		return evento;
	}

}
