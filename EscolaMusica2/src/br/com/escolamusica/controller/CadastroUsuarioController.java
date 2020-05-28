package br.com.escolamusica.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Perfil;
import br.com.escolamusica.model.Usuario;
import br.com.escolamusica.service.PerfilService;
import br.com.escolamusica.service.UsuarioService;
import br.com.escolamusica.util.ManagedBeanUtil;

@ManagedBean
@Controller

public class CadastroUsuarioController implements Serializable{


	private static final long serialVersionUID = 5175154914893937372L;
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	
	private List<Perfil> perfis;
	
	private Usuario usuario = new Usuario();
	
	private String confirmarSenha;
	
	public  void init() {
		perfis = perfilService.listar();
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null) {
			usuario = usuarioService.obterPorId(new Long (id));
			usuario.setPerfis(new HashSet<Perfil>(usuario.getPerfis()));
		}
	}
	
	public String salvar() {
		if(usuario.getId() == null && !confirmarSenha.equals(usuario.getSenha())) {
			ManagedBeanUtil.invalidarCampo("cadastro-usuarios-form:confirmar-senha-input","Senhas não conferem");
			RequestContext.getCurrentInstance().update("cadastro-usuarios-form:confirmar-senha-input");
			return null;
		}
		usuarioService.salvar(usuario);
		usuario = new Usuario();
		return "ListaUsuarios?faces-redirect=true";
	}
	
	public String novoUsuario() {
		usuario = new Usuario();
		return "CadastroUsuario?faces-redirect=true";
	}
	
	//Getters e Setters

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	

}
