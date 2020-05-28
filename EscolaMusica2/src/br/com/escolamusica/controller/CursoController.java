package br.com.escolamusica.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Curso;
import br.com.escolamusica.model.TipoCurso;
import br.com.escolamusica.service.CursoService;

@Controller
@Scope("session")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
				
	private Curso curso = new Curso();
	private List<Curso> listaCursos;
	private Curso cursoExclusao;
	
	@PostConstruct
	public void init() {
		listaCursos = cursoService.listar();
	}
	
	public String salvarCurso() {
				
		cursoService.salvar(curso);
		listaCursos = cursoService.listar();
		curso = new Curso();
		return "ListaCursos?faces-redirect=true";
	}
	
	public String editar(Curso curso) {
		this.curso = curso;
		return "CadastroCurso?faces-redirect=true";
	}
	
	public void prepararExclusao(Curso curso) {
		cursoExclusao = curso;
	}
	
	public void excluir() {
		cursoService.excluir(cursoExclusao);
		listaCursos = cursoService.listar();
		
	}
	
	public String cancelar() {
		this.curso = new Curso();
		return "ListaCursos?faces-redirect = true";
	}
	
	public TipoCurso[] getTiposCurso() {
		return TipoCurso.values();
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;

	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	

	

	
	
}
