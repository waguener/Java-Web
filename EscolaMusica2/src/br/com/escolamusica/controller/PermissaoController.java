package br.com.escolamusica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Permissao;
import br.com.escolamusica.service.PermissaoSevice;
import br.com.escolamusica.util.Mensagem;

@ManagedBean
@Controller
@Scope("session")
public class PermissaoController implements Serializable {

	private static final long serialVersionUID = -3012484152994762081L;
	
	private Permissao permissao;
	private List<Permissao> permissoes;

	@Autowired
	private PermissaoSevice permissaoSevice;
	
	
	public void init() {
		permissoes = permissaoSevice.listar();
		permissao = new Permissao();
	}
	
	public void novaPermissao() {
		permissao = new Permissao();
	}
	
	public void salvar() {
		permissaoSevice.salvar(permissao);
		init();
		Mensagem.mensagemInfo("Permissão cadastrada com sucesso");
	}
	
	
	
	//Getters e Setters
	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}
