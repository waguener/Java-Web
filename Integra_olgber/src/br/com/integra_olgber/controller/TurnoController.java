package br.com.integra_olgber.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.model.Turno;
import br.com.integra_olgber.services.TurnoServices;

@ManagedBean
@Controller
@Scope("session")
public class TurnoController implements Serializable{

	private static final long serialVersionUID = 7702046518632013265L;
	
	@Autowired
	private TurnoServices turnoServices;
	
	private Turno turno = new Turno();
	private Turno turnoExclusao;
	private List<Turno> listarTurnos;
	
	//Metodos
	@PostConstruct
	public void init() {
		turno = new Turno();
		
	}
	
	public String salvarTurno() {

		turnoServices.salvar(turno);
		FacesMessage msm = new FacesMessage("Turno cadastrado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		turno = new Turno();
		listarTurnos = turnoServices.listar();

		return "Turno?faces-redirect=true";

	}
	public String salvarTurnoEdit() {

		turnoServices.salvar(turno);
		FacesMessage msm = new FacesMessage("Turno editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		turno = new Turno();
		listarTurnos = turnoServices.listar();

		return "PesquisaTurno?faces-redirect=true";

	}

	public String editarTurno(Turno turno) {
		this.turno = turno;
		return "TurnoEdit?faces-redirect=true";
	}

	public void prepararExcluirTurno(Turno turno) {
		turnoExclusao = turno;
	}

	public void excluirTurno() {
		turnoServices.excluir(turnoExclusao);
		listarTurnos = turnoServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public void pesquisar() {
		listarTurnos = turnoServices.listar();	
	}
	
	//Getters e Setters
	
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Turno getTurnoExclusao() {
		return turnoExclusao;
	}

	public void setTurnoExclusao(Turno turnoExclusao) {
		this.turnoExclusao = turnoExclusao;
	}

	public List<Turno> getListarTurnos() {
		return listarTurnos;
	}

	public void setListarTurnos(List<Turno> listarTurnos) {
		this.listarTurnos = listarTurnos;
	}
	
}
