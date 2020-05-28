package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Banco;
import br.com.belcanto.services.BancoServices;

@ManagedBean
@Controller
@Scope("session")
public class BancoController implements Serializable{

	private static final long serialVersionUID = 1L;
	@Autowired
	private BancoServices bancoServices;
	
	private Banco banco = new Banco();
	private Banco excluirBanco;
	private List<Banco> listar;
	
	//Metodos de Inicio
	public void init() {
		banco = new Banco();
		listar = bancoServices.listar();
	}
	
	//Salvar
	public String salvar() {
		bancoServices.salvar(banco);
		FacesMessage msm = new FacesMessage("Banco salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);	
		
		return "CadBanco?faces-redirect=true";
	}
	
	//Exclusão
		public void prepararExclusao(Banco banco) {
			excluirBanco = banco;
			
		}

		public void excluir() {
			bancoServices.excluir(excluirBanco);
			listar= bancoServices.listar();
			FacesMessage mensagem = new FacesMessage("Banco excluído com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}

	public Banco getExcluirBanco() {
			return excluirBanco;
		}

		public void setExcluirBanco(Banco excluirBanco) {
			this.excluirBanco = excluirBanco;
		}

		public List<Banco> getListar() {
			return listar;
		}

		public void setListar(List<Banco> listar) {
			this.listar = listar;
		}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	
	//Getters e Setters
	
	
	
	
}
