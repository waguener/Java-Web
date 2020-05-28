package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.AulaComp;
import br.com.belcanto.model.Curso;
import br.com.belcanto.services.AulaCompServices;

@ManagedBean
@Controller
@Scope("session")
public class AulaCompController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AulaCompServices aulaCompServices;
	private Curso curso;
	private AulaComp aulaComp = new AulaComp();	
	private List<AulaComp> listar;
	private Date hj = new Date();
	
	
	//Metodos de Inicio
	public void init() {
		aulaComp = new AulaComp();
		
	}
	
	
	
	//Editar
	public void editar(AulaComp aulaComp) {
		this.aulaComp = aulaComp;
		
	}



	
	
	
	//Getters e Setters
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public AulaComp getAulaComp() {
		return aulaComp;
	}
	public void setAulaComp(AulaComp aulaComp) {
		this.aulaComp = aulaComp;
	}
	public List<AulaComp> getListar() {
		return listar;
	}
	public void setListar(List<AulaComp> listar) {
		this.listar = listar;
	}
	public Date getHj() {
		return hj;
	}
	public void setHj(Date hj) {
		this.hj = hj;
	}
	
	
	
	
	
}
