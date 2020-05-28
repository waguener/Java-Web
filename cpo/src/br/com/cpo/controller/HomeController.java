package br.com.cpo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.Carga;
import br.com.cpo.model.Movimentacao;
import br.com.cpo.services.CargaServices;
import br.com.cpo.services.MovimentacaoServices;

@ManagedBean
@Controller
@Scope("session")
public class HomeController implements Serializable{


	private static final long serialVersionUID = -5972075176598082052L;

	@Autowired
	private MovimentacaoServices movimentacaoServices;
	@Autowired
	private CargaServices cargaServices;
	
	private Movimentacao movimentacao;
	private List<Movimentacao> listaMov;
	private List<Movimentacao> listaRet;
	private List<Movimentacao> listaDev;
	private List<Movimentacao> listaDevCliente;
	private Carga carga;
	
	private Date hoje = new Date();
	
	//Métodos
	public void init() {
		
		
		listaMov = new ArrayList<Movimentacao>();
		listaRet = new ArrayList<Movimentacao>();
		listaDev = new ArrayList<Movimentacao>();
		listaDevCliente = new ArrayList<Movimentacao>();
		listaMov = movimentacaoServices.ultimasMov(hoje);
		listaRet = movimentacaoServices.ultimasRetiradas(hoje);
		listaDev = movimentacaoServices.ultimasDev(hoje);
		listaDevCliente = movimentacaoServices.ultimasDevCliente(hoje);
		Carga id = cargaServices.ultimoId();
		if(id == null) {
			carga = new Carga();
		}else {
		carga = cargaServices.obterPorId(Long.parseLong(id.getId().toString()));
		
		if (carga.getStatus().equals("FINALIZADO")) {
			carga = new Carga();
		
		}
		}
	}
	
	
	//Getters e Setters
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	public Carga getCarga() {
		return carga;
	}
	public void setCarga(Carga carga) {
		this.carga = carga;
	}


	public List<Movimentacao> getListaMov() {
		return listaMov;
	}


	public void setListaMov(List<Movimentacao> listaMov) {
		this.listaMov = listaMov;
	}


	public List<Movimentacao> getListaRet() {
		return listaRet;
	}


	public void setListaRet(List<Movimentacao> listaRet) {
		this.listaRet = listaRet;
	}


	public List<Movimentacao> getListaDev() {
		return listaDev;
	}


	public void setListaDev(List<Movimentacao> listaDev) {
		this.listaDev = listaDev;
	}


	public List<Movimentacao> getListaDevCliente() {
		return listaDevCliente;
	}


	public void setListaDevCliente(List<Movimentacao> listaDevCliente) {
		this.listaDevCliente = listaDevCliente;
	}
	
	
	
	
	
	
}
