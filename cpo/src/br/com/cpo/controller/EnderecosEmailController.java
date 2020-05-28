package br.com.cpo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.EnderecosEmail;
import br.com.cpo.services.EnderecosEmailServices;


@ManagedBean
@Controller
@Scope("session")
public class EnderecosEmailController implements Serializable{

	private static final long serialVersionUID = 7513847592994283390L;
	
	@Autowired
	private EnderecosEmailServices enderecosEmailServices;
	
	private EnderecosEmail enderecosEmail = new EnderecosEmail();
	private List<EnderecosEmail> emails;
	
	private EnderecosEmail emailExclusao;

	//Salvar
	public void salvar() {
		boolean emailValido;
		emailValido = validEmail(enderecosEmail.getEmail());
		if(emailValido == true) {
		
		enderecosEmailServices.salvar(enderecosEmail);
		FacesMessage msm = new FacesMessage("E-Mail Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		listar();
		enderecosEmail = new EnderecosEmail();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').hide();");
		}else {
			FacesMessage msm = new FacesMessage("E-mail inválido");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
		
	}
	
	//Validar email
	public boolean validEmail(String email) {
	    
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	     
	      return true;
	    }
	    else{
	      return false;
	    }  
	 }
	
	//Excluir
	public void prepararExclusao(EnderecosEmail enderecosEmail) {
		emailExclusao = enderecosEmail;
		excluir();
		
	}

	public void excluir() {
		enderecosEmailServices.excluir(emailExclusao);
		
		emails = enderecosEmailServices.listar();
	}
	
	//Listar
	
	public void listar() {
		
		emails = enderecosEmailServices.listar();
		
	}

	//Abrir E-mail
	public void abrirEmail() {
		emails = enderecosEmailServices.listar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('email').show();");
	}
	
	//Fechar E-mail
	public void fecharEmail() {
		emails = enderecosEmailServices.listar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('email').hide();");
	}
	

	
	//Getters e Setters
	public List<EnderecosEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<EnderecosEmail> emails) {
		this.emails = emails;
	}

	public EnderecosEmail getEnderecosEmail() {
		return enderecosEmail;
	}

	public void setEnderecosEmail(EnderecosEmail enderecosEmail) {
		this.enderecosEmail = enderecosEmail;
	}


	

	
	

}
