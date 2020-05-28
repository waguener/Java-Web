package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.sistemaepi.Bean.EventoBean;
import br.com.sistemaepi.DAO.EventoDAO;

@ManagedBean(name = "sistemaControllerEventos")
@ViewScoped
public class SistemaControllerEventos implements Serializable{


	private static final long serialVersionUID = 7610532175558291546L;
	
	private EventoBean eventos = new EventoBean() ;
	private ScheduleModel agenda;
	private ScheduleEvent scheduleEvent = new DefaultScheduleEvent();
	private EventoBean eventoExclusao;
	private List<EventoBean> listaEvento = EventoDAO.listarEvento();
	
@PostConstruct
	public void init() {
		agenda = new DefaultScheduleModel();
		List<EventoBean> eventos = EventoDAO.listarEvento();
		for (EventoBean eventoBean : eventos) {
			DefaultScheduleEvent event = new DefaultScheduleEvent();
			event.setTitle(eventoBean.getTitulo());
			event.setStartDate(eventoBean.getInicio());
			event.setEndDate(eventoBean.getFim());
			event.setDescription(eventoBean.getDescricao());
			event.setAllDay(eventoBean.getConclusao());
			event.setId(eventoBean.getId().toString());
			agenda.addEvent(event);
			
		}
	}

	// Métodos

	public void SalvarAgenda() {
		
		eventos = new EventoBean();

	}
	 
	public String home() {
		eventos = new EventoBean();
		return "Home?faces-redirect=true";
	}

	public void novo(SelectEvent event) {

		eventos = new EventoBean();
		Date inicio = (Date) event.getObject();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inicio);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		eventos.setInicio(calendar.getTime());

	}

	public void onEventSelect(SelectEvent selectEvent) {
		scheduleEvent =  (ScheduleEvent) selectEvent.getObject();
		eventos = EventoDAO.obterPorId(new Long(scheduleEvent.getId()));
		
	}

	public void prepararParaExcluirEvento(EventoBean evento) {
		eventoExclusao = eventos;
	}

	public void excluir() {
  		EventoDAO.excluirEvento(eventoExclusao);
		listaEvento = EventoDAO.listarEvento();
	}

	public void onEventMove(ScheduleEntryMoveEvent eventos) {
		EventoDAO.salvarEventoMov(eventos);
		
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Alterado",
				"Dia:" + eventos.getDayDelta() + ", Minute delta:" + eventos.getMinuteDelta());

		addMessage(mensagem);
		
	}
	
	

	private void addMessage(FacesMessage mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	// Getters e Setters
	public EventoBean getEventos() {
		return eventos;
	}

	public void setEventos(EventoBean eventos) {
		this.eventos = eventos;
	}

	public ScheduleModel getAgenda() {
		return agenda;
	}

	public void setAgenda(ScheduleModel agenda) {
		this.agenda = agenda;
	}

	@PostConstruct
	public void listar() {
		agenda = new DefaultScheduleModel();
	}

	public EventoBean getEventoExclusao() {
		return eventoExclusao;
	}

	public void setEventoExclusao(EventoBean eventoExclusao) {
		this.eventoExclusao = eventoExclusao;
	}

	public List<EventoBean> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<EventoBean> listaEvento) {
		this.listaEvento = listaEvento;
	}

}
