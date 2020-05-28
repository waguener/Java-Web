package br.com.cpo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.Estoque;
import br.com.cpo.model.Movimentacao;
import br.com.cpo.services.EstoqueServices;
import br.com.cpo.services.MovimentacaoServices;
@SuppressWarnings("deprecation")
@ManagedBean
@Controller
@Scope("session")
public class EstoqueController implements Serializable{

	private static final long serialVersionUID = 2240745775833987632L;
	
	@Autowired
	private EstoqueServices estoqueServices;
	@Autowired
	private MovimentacaoServices movimentacaoServices;
	
	
	private Estoque estoque = new Estoque();
	private Movimentacao movimentacao = new Movimentacao();
	private List<Movimentacao> listaMov;
	private List<Estoque> estoques;
	private List<Estoque> selecionados;
	private Date hoje = new Date();
	//Metodos
	public void init() {
		estoques = estoqueServices.listar();
		selecionados = new ArrayList<Estoque>();
	}
	
	public void buscar() {
		estoques = estoqueServices.listar();
	}
	//Abrir Estoque
	
	public void relatorio() {
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verEstoque').show();");
	}
	//Fechar Estoque
	public void fecharRelatorio() {
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verEstoque').hide();");
	}

	//Abrir Movimentações
	public void mov(Estoque estoque) {
		this.estoque = estoque;
		String cod = estoque.getRecebimento().getCodigo();
		listaMov = movimentacaoServices.mov(cod, estoque.getRecebimento().getLote());
		
		if(listaMov.isEmpty()) {
			
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Nenhuma Movimentação!!","");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('verMov').show();");
		}
		
	}
	//Getters e Setters

	public Estoque getEstoque() {
		return estoque;
	}



	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}



	public List<Estoque> getEstoques() {
		return estoques;
	}



	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public List<Estoque> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Estoque> selecionados) {
		this.selecionados = selecionados;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public List<Movimentacao> getListaMov() {
		return listaMov;
	}

	public void setListaMov(List<Movimentacao> listaMov) {
		this.listaMov = listaMov;
	}
	
	
}
