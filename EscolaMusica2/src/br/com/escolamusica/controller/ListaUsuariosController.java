package br.com.escolamusica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Usuario;
import br.com.escolamusica.service.UsuarioService;

@ManagedBean
@Controller

public class ListaUsuariosController implements Serializable{



	private static final long serialVersionUID = 5671143057326382183L;
	@Autowired
	private UsuarioService usuarioService;
	
	private List<Usuario> usuarios;
	
	public  void init() {
		usuarios = usuarioService.listar();
		
	}
	public String editar(Usuario usuario) {
		return "CadastroUsuario?faces-redirect=true&id=" + usuario.getId();
	}
	
	
	//Getters e Setters

	public List<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	
	

}
