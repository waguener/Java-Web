package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.AgenciaBean;
import br.com.sistemaepi.service.AgenciaService;

@ManagedBean
@Controller
@Scope("session")
public class SistemaControllerAgencia implements Serializable{
	
	private static final long serialVersionUID = -2818010606701942003L;

	// Agencia
	@Autowired
	private AgenciaService agenciaService;

	private AgenciaBean agencia = new AgenciaBean();
	private AgenciaBean agenciaExclusao;
	private List<AgenciaBean> listAgencia;
	private String nome;

	// Metodos
	@PostConstruct
	public void init() {
		agencia = new AgenciaBean();
		listAgencia = agenciaService.listar();
	}

	public void novaAgencia() {
		listAgencia = new ArrayList<AgenciaBean>();
		nome = null;
	}
	
	
	public String salvarAgencia() {

		agenciaService.salvar(agencia);
		FacesMessage msm = new FacesMessage("Agência salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		agencia = new AgenciaBean();
		listAgencia = agenciaService.listar();

		return "agencia?faces-redirect=true";

	}

	public void porAgencia() {
		listAgencia = agenciaService.porNome(nome);
	}

	public String editarAgencia(AgenciaBean agencia) {
		this.agencia = agencia;
		return "agenciaEdit?faces-redirect=true";
	}

	public void PrepararExcluirAgencia(AgenciaBean agencia) {
		agenciaExclusao = agencia;
	}

	public void ExcluirAgencia() {
		agenciaService.excluir(agenciaExclusao);
		listAgencia = agenciaService.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public String pesquisarAgencia() {
		this.agencia = new AgenciaBean();
		return "ListaAgencia?faces-redirect=true";
	}

	public void removeBean() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(agencia);
	}

	// Getters e Setters

	public AgenciaBean getAgencia() {
		return agencia;
	}

	public List<AgenciaBean> getlistAgencia() {
		return listAgencia;
	}

	public void setlistAgencia(List<AgenciaBean> listAgencia) {
		this.listAgencia = listAgencia;
	}

	public void setAgencia(AgenciaBean agencia) {
		this.agencia = agencia;
	}

	public AgenciaBean getAgenciaExclusao() {
		return agenciaExclusao;
	}

	public void setAgenciaExclusao(AgenciaBean agenciaExclusao) {
		this.agenciaExclusao = agenciaExclusao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
