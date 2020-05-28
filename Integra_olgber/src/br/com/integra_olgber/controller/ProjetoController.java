package br.com.integra_olgber.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.model.Projeto;
import br.com.integra_olgber.services.ProjetoServices;

@ManagedBean
@Controller
@Scope("session")
public class ProjetoController implements Serializable{

	private static final long serialVersionUID = -6713012575609033581L;

	@Autowired
	private ProjetoServices projetoServices;
	
	private Projeto projeto = new Projeto();
	private Projeto projetoExclusao;
	private List<Projeto> listarProjetos;
	private List<Projeto> projetos;
	private List<Projeto> projetoValido;
	private String nome;
	//Metodos
	@PostConstruct
	public void init() {
		projeto = new Projeto();
		
	}
	
	//Salvar
	public String salvarProjeto() {
		
		projetoServices.salvar(projeto);
		FacesMessage msm = new FacesMessage("Projeto salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		projeto = new Projeto();
		listarProjetos = projetoServices.listar();

		return "CadastroProjeto?faces-redirect=true";

	}
	
	public String salvarProjetoEdit() {
		
		projetoServices.salvar(projeto);
		FacesMessage msm = new FacesMessage("Projeto editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		projeto = new Projeto();
		projetos = projetoServices.listar();

		return "PesquisaProjeto?faces-redirect=true";

	}
	
	//Editar
	public String editarProjeto(Projeto projeto) {
		this.projeto = projeto;
		return "ProjetoEdit?faces-redirect=true";
	}

	//Excluir
	public void prepararExcluirProjeto(Projeto projeto) {
		projetoExclusao = projeto;
	}

	public void excluirProjeto() {
		projetoServices.excluir(projetoExclusao);
		listarProjetos = projetoServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	//Pesquisas
	public void pesquisar() {
		listarProjetos = projetoServices.listar();
	}
	public void porProjeto() {
		projetos = projetoServices.porNome(nome);
		if(projetos.isEmpty()) {
			FacesMessage msm = new FacesMessage("Nenhum Projeto Encontrado!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
	}
	
	public void projetoAtual() {
		
		System.out.println("Entrei no Método");
		Date hoje = new Date();
		System.out.println("Parametro "+hoje);
		
		projetoValido = projetoServices.projeto(hoje);
		
	}
	
	//iniciar Pesquisa
	public void iniciarPesquisa() {
		projetos = new ArrayList<Projeto>();
		nome = null;
	}
	
	
	//Getters e Setters
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Projeto getProjetoExclusao() {
		return projetoExclusao;
	}

	public void setProjetoExclusao(Projeto projetoExclusao) {
		this.projetoExclusao = projetoExclusao;
	}

	public List<Projeto> getListarProjetos() {
		return listarProjetos;
	}

	public void setListarProjetos(List<Projeto> listarProjetos) {
		this.listarProjetos = listarProjetos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	public List<Projeto> getProjetoValido() {
		return projetoValido;
	}
	public void setProjetoValido(List<Projeto> projetoValido) {
		this.projetoValido = projetoValido;
	}
	
}
