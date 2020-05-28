package br.com.integra_olgber.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.model.Login;
import br.com.integra_olgber.model.Usuario;
import br.com.integra_olgber.services.LoginServices;
import br.com.integra_olgber.services.UsuarioServices;

@ManagedBean
@Controller
@Scope("session")
public class LoginController implements Serializable{

	private static final long serialVersionUID = 7254119779281167482L;
	@Autowired
	private UsuarioServices usuarioServices;
	@Autowired
	private LoginServices loginServices;
	
	private Login login = new Login();
	private Usuario usuario = new Usuario();		
	private String user;
	private String senha;
	private boolean teste1;
	private boolean teste2;
	
	
	public void init() {
		usuario = new Usuario();
		user = "";
		senha = "";
	}
	
	public String sair() {
		usuario = new Usuario();
		return "Login?faces-redirect=true";
	}
	
	//Salvar
	
	
	//Login
	
	/*public String logar() {
		
		
		teste1 = usuarioServices.porUser(user);
		
		System.out.println(teste1);
		if(teste1==true) {
			
			teste2 = usuarioServices.porSenha(senha);
			
			if(teste2==true) {
				salvar();
				FacesMessage msm = new FacesMessage("Bem vindo(a) " + user);
				FacesContext.getCurrentInstance().addMessage(null, msm);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "CadastroFuncionario?faces-redirect=true";
				
			}else {
				
				FacesMessage msm = new FacesMessage("Senha está incorreta!!");
	    		FacesContext.getCurrentInstance().addMessage(null, msm);
	    		return null;
	    		
			}
			
		}else {
			FacesMessage msm = new FacesMessage("Usuário não existe!!");
    		FacesContext.getCurrentInstance().addMessage(null, msm);
    		return null;
		}
		
	}
	*/
	 
	//Getters e Setters
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
