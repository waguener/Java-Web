package br.com.cpo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.CadFuncionario;
import br.com.cpo.services.CadFuncionarioServices;

@ManagedBean
@Controller
@Scope("session")
public class CadFuncionarioController implements Serializable{

	private static final long serialVersionUID = 4173208714507918835L;

	@Autowired
	private CadFuncionarioServices cadFuncionarioServices;
	
	private CadFuncionario cadFuncionario = new CadFuncionario();
	private List<CadFuncionario> listaFuncionarios;
	private CadFuncionario excluirFunc;
	//Métodos
	
	public void init() {
		cadFuncionario = new CadFuncionario();
		listaFuncionarios = cadFuncionarioServices.listar();
	}
	
	public void salvar() {
		
		cadFuncionarioServices.salvar(cadFuncionario);
		
		FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Funcionário Salvo com Sucesso!!","");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	public void prepararExclusao(CadFuncionario cadFuncionario) {
		excluirFunc = cadFuncionario;
	}
	
	public void excluir() {
		cadFuncionarioServices.excluir(excluirFunc);
		listaFuncionarios = cadFuncionarioServices.listar();
		
		FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Funcionario Excluido com Sucesso!!","");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	
	//Getters e Setters
	public CadFuncionario getCadFuncionario() {
		return cadFuncionario;
	}

	public void setCadFuncionario(CadFuncionario cadFuncionario) {
		this.cadFuncionario = cadFuncionario;
	}

	public List<CadFuncionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<CadFuncionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public CadFuncionario getExcluirFunc() {
		return excluirFunc;
	}

	public void setExcluirFunc(CadFuncionario excluirFunc) {
		this.excluirFunc = excluirFunc;
	}
	
	
}
