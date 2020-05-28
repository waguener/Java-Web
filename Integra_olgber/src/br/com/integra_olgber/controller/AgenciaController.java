package br.com.integra_olgber.controller;

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

import br.com.integra_olgber.model.Agencia;
import br.com.integra_olgber.services.AgenciaServices;

@ManagedBean
@Controller
@Scope("session")
public class AgenciaController implements Serializable{

	private static final long serialVersionUID = 603764511904604880L;
	
	@Autowired
	private AgenciaServices agenciaServices;
	
	private Agencia agencia = new Agencia();
	private Agencia agenciaExclusao;
	private List<Agencia> listarAgencias;
	private List<Agencia> agencias;
	private String nome;
	
	//Metodos
	@PostConstruct
	public void init() {
		agencia = new Agencia();
		
	}
	
	//Salvar
	public String salvarAgencia() {

		agenciaServices.salvar(agencia);
		FacesMessage msm = new FacesMessage("Agência salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		agencia = new Agencia();
		listarAgencias = agenciaServices.listar();

		return "CadastroAgencia?faces-redirect=true";

	}
	
	public String salvarAgenciaEdit() {

		agenciaServices.salvar(agencia);
		FacesMessage msm = new FacesMessage("Agência editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		agencia = new Agencia();
		agencias = agenciaServices.listar();

		return "PesquisaAgencia?faces-redirect=true";

	}
	
	//Excluir
	public void prepararExcluirAgencia(Agencia agencia) {
	agenciaExclusao = agencia;
	}

	public void excluirAgencia() {
		agenciaServices.excluir(agenciaExclusao);
		agencias = agenciaServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		
	}
	
	//Editar
	public String editarAgencia(Agencia agencia) {
		this.agencia = agencia;
		return "AgenciaEdit?faces-redirect=true";
	}
	
	//Pesquisar
	public void porAgencia() {
		agencias = agenciaServices.porNome(nome);
		if(agencias.isEmpty()) {
			FacesMessage msm = new FacesMessage("Nenhuma Agência Encontrada!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
	}
	
	public void agencias() {
		listarAgencias = agenciaServices.listar();
	}
	//Iniciar Pesquisa
	public void iniciarPesquisa() {
		agencias = new ArrayList<Agencia>();
		nome = null;
	}
	//Getters e Setters
	
	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Agencia getAgenciaExclusao() {
		return agenciaExclusao;
	}

	public void setAgenciaExclusao(Agencia agenciaExclusao) {
		this.agenciaExclusao = agenciaExclusao;
	}

	public List<Agencia> getListarAgencias() {
		return listarAgencias;
	}

	public void setListaAgencia(List<Agencia> listarAgencias) {
		this.listarAgencias = listarAgencias;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}
	
}
