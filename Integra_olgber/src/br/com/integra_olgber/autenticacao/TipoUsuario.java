package br.com.integra_olgber.autenticacao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.model.Usuario;
import br.com.integra_olgber.services.UsuarioServices;

@ManagedBean
@Controller
@Scope("session")
public class TipoUsuario implements Serializable{

	private static final long serialVersionUID = -4440262433909064040L;

	@Autowired
	private UsuarioServices usuarioServices;
	private String tipo;
	private AuthenticationContext authenticationContext = new AuthenticationContext();
	private String nome;
	 
	private Usuario usuario;
	
	public void renderizarMenus() {
		
		tipo = authenticationContext.getUsuarioLogado().getTipo();
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
