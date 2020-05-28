package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Clausula;
import br.com.belcanto.model.Contrato;
import br.com.belcanto.services.ClausulaServices;
import br.com.belcanto.services.ContratoServices;
import br.com.belcanto.services.MatriculaServices;

/**
 * 
 * @author Wagner
 *
 */

@ManagedBean
@Controller
@Scope("session")
public class ContratoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContratoServices contratoServices;
	@Autowired
	private ClausulaServices clausulaServices;
	@Autowired
	private MatriculaServices matriculaServices;

	private Contrato contrato;
	private Contrato contratoexclusao;
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private Clausula clausula;
	private List<Clausula> clausulas = new ArrayList<Clausula>();
	private int indiceAtual = 0;
	private String cabecalho;

	// Metodos
	public void init() {

		contrato = new Contrato();
		clausula = new Clausula();
		cabecalho = null;
	}

	public void consultaInit() {
		contrato = new Contrato();
		contratos = contratoServices.listaContrato();
		
	}

	public String salvar() {

		contratoServices.salvar(contrato);
		FacesMessage msm = new FacesMessage("Contrato Salvo com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "CadContrato?faces-redirect=true";
	}

	public String salvarEdit() {

		contratoServices.salvar(contrato);
		FacesMessage msm = new FacesMessage("Contrato Editado com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "ConContrato?faces-redirect=true";
	}

	public void incluirClausula() {
		contrato.getClausulas().add(new Clausula());

	}

	// Excluir Clausula
	public void prepararExclusaoClausula(int indiceAtual) {
		
		this.indiceAtual = indiceAtual;
	}

	public void excluirClausula() {
		contrato.getClausulas().remove(indiceAtual);
		FacesMessage msm = new FacesMessage("Clausula Excluida com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Editar Contrato
	public String editar(Contrato contrato) {
		this.contrato = contrato;

		return "EditContrato?faces-redirect=true";
	}

	//Excluir contrato
	public void prepararExclusao(Contrato contrato) {
		
			contratoexclusao = contrato;
	}
		
		
	
	

	
	public void excluirContrato() {
		boolean teste = false;
		teste = matriculaServices.excluirCont(contratoexclusao.getNome());
		System.out.println(teste);
		
		if(teste == true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossivel Excluir!! Para Excluir este Contrato é necessário Excluir sua Matrícula", ""));
			RequestContext.getCurrentInstance().execute("growlColour(\".ui-growl-image-error\");");
		}else {
			
		for(Clausula clausula : contratoexclusao.getClausulas()) {
			clausulaServices.excluir(clausula);
		}
		contratoServices.excluir(contratoexclusao);
		contratos = contratoServices.listaContrato();
		FacesMessage msm = new FacesMessage("Contrato Excluído com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	}
	// Getters e Setters
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Clausula getClausula() {
		return clausula;
	}

	public void setClausula(Clausula clausula) {
		this.clausula = clausula;
	}

	public List<Clausula> getClausulas() {
		return clausulas;
	}

	public void setClausulas(List<Clausula> clausulas) {
		this.clausulas = clausulas;
	}

	public int getIndiceAtual() {
		return indiceAtual;
	}

	public void setIndiceAtual(int indiceAtual) {
		this.indiceAtual = indiceAtual;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

}
