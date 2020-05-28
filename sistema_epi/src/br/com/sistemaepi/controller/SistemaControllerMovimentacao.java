package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sistemaepi.Bean.MovimentacaoBean;
import br.com.sistemaepi.DAO.MovimentacaoDAO;

@ManagedBean(name="sistemaControllerMovimentacao")
@SessionScoped
public class SistemaControllerMovimentacao implements Serializable{

	private static final long serialVersionUID = -63687587514219829L;
	
	private MovimentacaoBean movimentacao;
	private List<MovimentacaoBean> listaMovimentacoes = MovimentacaoDAO.listarMovimentacao();
	private MovimentacaoBean movimentacaoExclusao;
	//Métodos
	
	public void salvar() {

		MovimentacaoDAO.salvar(movimentacao);	
		movimentacao = new MovimentacaoBean();
		listaMovimentacoes = MovimentacaoDAO.listarMovimentacao();
		
	}
	
	public void PrepararExcluirMov(MovimentacaoBean movimentacao) {
		movimentacaoExclusao = movimentacao;
	}

	public void ExcluirMov() {
		MovimentacaoDAO.excluirMovimentacao(movimentacaoExclusao);
		listaMovimentacoes = MovimentacaoDAO.listarMovimentacao();
	}
	
	//Getters e Setters
	
	public MovimentacaoBean getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacaoBean movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<MovimentacaoBean> getListaMovimentacoes() {
		return listaMovimentacoes;
	}

	public void setListaMovimentacoes(List<MovimentacaoBean> listaMovimentacoes) {
		this.listaMovimentacoes = listaMovimentacoes;
	}

	public MovimentacaoBean getMovimentacaoExclusao() {
		return movimentacaoExclusao;
	}

	public void setMovimentacaoExclusao(MovimentacaoBean movimentacaoExclusao) {
		this.movimentacaoExclusao = movimentacaoExclusao;
	}
	
	
}
