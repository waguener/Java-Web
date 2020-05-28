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

import br.com.cpo.model.ContagemOlgber;
import br.com.cpo.model.Estoque;
import br.com.cpo.model.ProdEst;
import br.com.cpo.services.ContagemOlgberServices;
import br.com.cpo.services.EstoqueServices;
import br.com.cpo.services.ProdEstServices;
@SuppressWarnings("deprecation")
@ManagedBean
@Controller
@Scope("session")
public class ContagemOlgberController implements Serializable {

	private static final long serialVersionUID = 58429903804090276L;

	@Autowired
	private ContagemOlgberServices contagemOlgberServices;
	@Autowired
	private EstoqueServices estoqueServices;
	@Autowired
	private ProdEstServices estServices;

	private ContagemOlgber contagemOlgber = new ContagemOlgber();
	private ContagemOlgber prepararExclusao;
	private Estoque estoque;
	private ProdEst prodEst = new ProdEst();
	private List<ProdEst> listaTotal;
	private ContagemOlgber excluirContagem;
	private List<ContagemOlgber> listaContagem;
	private List<ProdEst> selecionados;
	private Date hoje = new Date();
	private Date data;
	private String codigo;
	private String lote;

	// Init
	public void init() {
		codigo = null;
		lote = null;
		contagemOlgber = new ContagemOlgber();
		listaContagem = contagemOlgberServices.hoje(hoje);
	}

	public void initTotal() {
		listaTotal = new ArrayList<ProdEst>();
		selecionados = new ArrayList<ProdEst>();
		data = null;
	}

	// Salvar
	public void salvar() {

		contagemOlgber.setData(hoje);
		addEstoque();
		contagemOlgberServices.salvar(contagemOlgber);
		listaContagem = contagemOlgberServices.hoje(hoje);
		FacesMessage msm = new FacesMessage("Produto Salvo com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		codigo = null;
		lote = null;
		contagemOlgber = new ContagemOlgber();

	}

	// Editar
	public String editar(ContagemOlgber contagem) {
		this.contagemOlgber = contagem;
		return "ContagemOlgber?faces-redirect";
	}

	// Excluir

	public void PrepararExcluirContagem(ContagemOlgber contagem) {
		excluirContagem = contagem;
	}

	public void excluirContagem() {
		contagemOlgberServices.excluir(excluirContagem);
		listaContagem = contagemOlgberServices.listar();
		FacesMessage msm = new FacesMessage("Produto Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Pesquisas
	public void codigo() {

		estoque = estoqueServices.controle(codigo, lote);
		if (estoque == null) {

			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {
			contagemOlgber.setEstoque(estoque);
		}
	}

	public void buscaHoje() {
		listaContagem = contagemOlgberServices.hoje(hoje);
	}

	public void buscaDiario() {
		listaTotal = estServices.hoje(data);
		selecionados = new ArrayList<ProdEst>();
	}

	// Soma Estoque
	public void addEstoque() {
		String cod, lote;
		int valor1, valor2, soma, total, t;
		Long id;
		cod = contagemOlgber.getEstoque().getRecebimento().getCodigo();
		lote = contagemOlgber.getEstoque().getRecebimento().getLote();
		prodEst = new ProdEst();
		prodEst = estServices.busca(cod, lote, hoje);

		if (prodEst == null) {
			prodEst = new ProdEst();
			prodEst.setCod(contagemOlgber.getEstoque().getRecebimento().getCodigo());
			prodEst.setProduto(contagemOlgber.getEstoque().getRecebimento().getProduto());
			prodEst.setLote(contagemOlgber.getEstoque().getRecebimento().getLote());
			prodEst.setQtdArmazem(contagemOlgber.getEstoque().getTotal());
			prodEst.setUniMedida(contagemOlgber.getEstoque().getRecebimento().getUniMedida());
			prodEst.setQtdProducao(contagemOlgber.getQtd());
			prodEst.setData(hoje);
			t = prodEst.getQtdArmazem() + prodEst.getQtdProducao();
			prodEst.setTotal(t);
			estServices.salvar(prodEst);

		} else {

			valor1 = prodEst.getQtdProducao();
			valor2 = contagemOlgber.getQtd();
			soma = valor1 + valor2;
			id = prodEst.getId();
			prodEst.setQtdProducao(soma);
			total = soma + prodEst.getQtdArmazem();
			prodEst.setTotal(total);
			prodEst.setId(id);
			estServices.salvar(prodEst);
		}

	}

	// Excluir
	public void prepararExclusao(ContagemOlgber contagemOlgber) {
		prepararExclusao = contagemOlgber;
	}

	public void excluir() {
		int valor1, valor2, sub, total, t;
		valor1 = prepararExclusao.getQtd();
		prodEst = estServices.buscaExcluir(prepararExclusao.getEstoque().getRecebimento().getCodigo(),
		prepararExclusao.getEstoque().getRecebimento().getLote());
		if(prodEst != null) {
		valor2 = prodEst.getQtdProducao();
		sub = valor2 - valor1;
		prodEst.setQtdProducao(sub);
		t = prodEst.getTotal() - valor1;
		prodEst.setTotal(t);
		Long id = prodEst.getId();
		prodEst.setId(id);
		estServices.salvar(prodEst);
		}
		contagemOlgberServices.excluir(prepararExclusao);
		listaContagem = contagemOlgberServices.hoje(hoje);
		FacesMessage msm = new FacesMessage("Produto Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	
	//Relatorio
	
	public void abrirRelatorio() {
		hoje = new Date();
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verTotal').show();");
	}
	
	public void fecharRelatorio() {
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verTotal').hide();");
	}
	
	// Getters e Setters

	public ContagemOlgber getExcluirContagem() {
		return excluirContagem;
	}

	public void setExcluirContagem(ContagemOlgber excluirContagem) {
		this.excluirContagem = excluirContagem;
	}

	public List<ContagemOlgber> getListaContagem() {
		return listaContagem;
	}

	public void setListaContagem(List<ContagemOlgber> listaContagem) {
		this.listaContagem = listaContagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public ContagemOlgber getContagemOlgber() {
		return contagemOlgber;
	}

	public void setContagemOlgber(ContagemOlgber contagemOlgber) {
		this.contagemOlgber = contagemOlgber;
	}

	public List<ProdEst> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<ProdEst> selecionados) {
		this.selecionados = selecionados;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ProdEst> getListaTotal() {
		return listaTotal;
	}

	public void setListaTotal(List<ProdEst> listaTotal) {
		this.listaTotal = listaTotal;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}
	
	

}
