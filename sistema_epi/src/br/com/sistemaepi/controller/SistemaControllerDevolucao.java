package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.DevolucaoBean;
import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.service.DevolucaoService;
import br.com.sistemaepi.service.EntregaService;

@ManagedBean
@Controller
@Scope("session")
public class SistemaControllerDevolucao implements Serializable{

	private static final long serialVersionUID = -1574511835081425187L;
	
	
	@Autowired
	private DevolucaoService devolucaoService;
	@Autowired
	private EntregaService entregaService;
	
	//Listas
	private List<DevolucaoBean> listar;
	
	
	private DevolucaoBean devolucaoBean = new DevolucaoBean();
	private EntregaBean entrega = new EntregaBean();
	private Boolean testeEntrega;
	private Integer resultado;
	private String nome;
	
	

	
	
	//Getters e Setters
	
	public DevolucaoBean getDevolucaoBean() {
		return devolucaoBean;
	}


	public void setDevolucaoBean(DevolucaoBean devolucaoBean) {
		this.devolucaoBean = devolucaoBean;
	}



	public List<DevolucaoBean> getListar() {
		return listar;
	}



	public void setListar(List<DevolucaoBean> listar) {
		this.listar = listar;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
