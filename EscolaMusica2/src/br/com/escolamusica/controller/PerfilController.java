package br.com.escolamusica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Perfil;
import br.com.escolamusica.model.Permissao;
import br.com.escolamusica.service.PerfilService;
import br.com.escolamusica.service.PermissaoSevice;
import br.com.escolamusica.util.Mensagem;

@ManagedBean
@Controller
@Scope("view")
public class PerfilController implements Serializable {

	
	private static final long serialVersionUID = 2261633419670455771L;
			
	private Perfil perfil;
	private List<Perfil> perfis;
	private List<Permissao> permissoesSource = new ArrayList<Permissao>();
	private List<Permissao> permissoesTarget = new ArrayList<Permissao>();
	private DualListModel<Permissao> permissoes = new DualListModel<Permissao>();
	
	@Autowired
	private PerfilService perfilSevice;
	@Autowired
	private PermissaoSevice permissaoSevice;
	
	public void init() {
		perfis = perfilSevice.listar();
		
	}
	
	private void atualizarPickList() {
		permissoesSource = permissaoSevice.listar();
		permissoesSource.removeAll(perfil.getPermissoes());
		permissoesTarget.addAll(perfil.getPermissoes());
		permissoes = new DualListModel<Permissao>(permissoesSource,permissoesTarget);
	}
	public void novoPerfil() {
		perfil = new Perfil();
		atualizarPickList();
	}
	
	public void salvar() {
		perfil.setPermissoes(permissoes.getTarget());
		perfilSevice.salvar(perfil);
		Mensagem.mensagemInfo("Permissão cadastrada com sucesso");
		init();
		perfil = null;
	}
	
	
	
	//Getters e Setters
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPermissoes(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Permissao> getPermissoesSource() {
		return permissoesSource;
	}

	public void setPermissoesSource(List<Permissao> permissoesSource) {
		this.permissoesSource = permissoesSource;
	}

	public List<Permissao> getPermissoesTarget() {
		return permissoesTarget;
	}

	public void setPermissoesTarget(List<Permissao> permissoesTarget) {
		this.permissoesTarget = permissoesTarget;
	}

	public DualListModel<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(DualListModel<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	

}
