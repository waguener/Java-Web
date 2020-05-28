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

import br.com.integra_olgber.autenticacao.AuthenticationContext;
import br.com.integra_olgber.model.Usuario;
import br.com.integra_olgber.services.UsuarioServices;

@ManagedBean
@Controller
@Scope("session")
public class UsuarioController implements Serializable{

	private static final long serialVersionUID = -7818850975902329713L;
	@Autowired
	private UsuarioServices usuarioServices;
	
	private Usuario usuario = new Usuario();
	
	private Usuario usuarioExclusao;
	private List<Usuario> listarUsuarios;
	private List<Usuario> usuarios;
	private String nome;
	private String confirmaSenha;
	boolean menu = false;
	private AuthenticationContext authenticationContext;
	
	//Metodos
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		confirmaSenha = null;
		
	}
	
	//Salvar
	public String salvarUsuario() {
		
		if(!confirmaSenha.equals(usuario.getSenha())) {
			FacesMessage msm = new FacesMessage("Senhas não conferem!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
		}else {

		usuarioServices.salvar(usuario);
		FacesMessage msm = new FacesMessage("Usuario salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		usuario = new Usuario();
		listarUsuarios = usuarioServices.listar();

		return "CadastroUsuario?faces-redirect=true";
		}
	}
	
	public String salvarUsuarioEdit() {

		usuarioServices.salvar(usuario);
		FacesMessage msm = new FacesMessage("Usuario editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		usuario = new Usuario();
		listarUsuarios = usuarioServices.listar();

		return "PesquisaUsuario?faces-redirect=true";

	}
	
	public void validaSenha() {
		if(!confirmaSenha.equals(usuario.getSenha())) {
			FacesMessage msm = new FacesMessage("Senhas não conferem!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			
		}
		
	}
	
	//Excluir
	public void prepararExcluirUsuario(Usuario usuario) {
	usuarioExclusao = usuario;
	}

	public void excluirUsuario() {
		usuarioServices.excluir(usuarioExclusao);
		listarUsuarios = usuarioServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		
	}
	
	//Editar
	public String editarUsuario(Usuario usuario) {
		this.usuario = usuario;
		return "UsuarioEdit?faces-redirect=true";
	}
	
	//Pesquisar
	public void porUsuario() {
		usuarios = usuarioServices.listar();
	}

	//Sessão Expirada
	
	public String finalizar() {
		
		return "Login?faces-redirect=true";
	}
	
	
	//Getters e Setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListarUsuarios() {
		return listarUsuarios;
	}

	public void setListarUsuarios(List<Usuario> listarUsuarios) {
		this.listarUsuarios = listarUsuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public Usuario getUsuarioExclusao() {
		return usuarioExclusao;
	}

	public void setUsuarioExclusao(Usuario usuarioExclusao) {
		this.usuarioExclusao = usuarioExclusao;
	}
	
}
