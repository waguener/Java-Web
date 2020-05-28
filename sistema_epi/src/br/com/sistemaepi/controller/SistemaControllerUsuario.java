 package br.com.sistemaepi.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.UsuarioBean;
import br.com.sistemaepi.Util.Mensagem;
import br.com.sistemaepi.service.UsuarioService;

@ManagedBean
@Controller

public class SistemaControllerUsuario implements Serializable{

	private static final long serialVersionUID = -3335245807602084510L;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private UsuarioBean usuarioBean = new UsuarioBean();
	
	private String confirmaSenha;
	
	//Metodos
	
	public String salvar() {
		if(!confirmaSenha.equals(usuarioBean.getSenha())) {
			Mensagem.mensagemErro("Senhas não conferem");
			return null;
		}
		usuarioService.salvar(usuarioBean);
		return "ListaUsuario?faces-redirect=true";
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
	
}
