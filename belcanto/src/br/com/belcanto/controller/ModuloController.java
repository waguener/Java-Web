package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Curso;
import br.com.belcanto.model.Modulo;
import br.com.belcanto.services.ModuloServices;

@ManagedBean
@Controller
@Scope("session")
public class ModuloController implements Serializable{

	private static final long serialVersionUID = 1L;
	@Autowired
	private ModuloServices moduloServices;
	private Curso curso;
	private Modulo modulo = new Modulo();	
	private List<Modulo> listar;
	private Date hj = new Date();
	//Metodos de Inicio
	public void init() {
		modulo = new Modulo();
		
	}
	
	
	
	//Editar
	public void editar(Modulo modulo) {
		this.modulo = modulo;
		
	}
	
	
	
	//Getters e Setters
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Modulo> getListar() {
		return listar;
	}

	public void setListar(List<Modulo> listar) {
		this.listar = listar;
	}
	
	
	
}
