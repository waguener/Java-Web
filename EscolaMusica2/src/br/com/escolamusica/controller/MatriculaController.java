package br.com.escolamusica.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.dao.AlunoDAO;
import br.com.escolamusica.dao.MatriculaDAO;
import br.com.escolamusica.model.Aluno;
import br.com.escolamusica.model.Curso;
import br.com.escolamusica.model.Matricula;
import br.com.escolamusica.service.CursoService;

@Controller
@Scope("session")
public class MatriculaController {
	
	@Autowired
	private CursoService cursoService;

	private List<Aluno> alunos = AlunoDAO.listar();
	private List<Curso> cursos;
	
	private Matricula matricula = new Matricula();
	private List<Matricula>matriculas = MatriculaDAO.Listar();
	private Matricula matriculaExclusao;
	
	@PostConstruct
	public void init() {
		cursos = cursoService.listar();
	}
	
	public String salvar() {
		MatriculaDAO.salvar(matricula);
		matricula = new Matricula();
		FacesMessage mensagem = new FacesMessage("Matricula cadastrada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null,mensagem);
		return "ListaMatriculas?faces-redirect=true";
	}
	
	public String Editar(Matricula matricula) {
		this.matricula = matricula;
		return "CadastroMatricula?faces-redirect=true";
	}
	
	public void prepararExclusao(Matricula matricula) {
		matriculaExclusao = matricula;
	}
	
	public void Excluir() {
		MatriculaDAO.Excluir(matriculaExclusao);;
		matriculas = MatriculaDAO.Listar();
	}
	
	public String cancelar() {
		this.matricula = new Matricula();
		return "ListaMatriculas?faces-redirect=true";
	}
	
	
	//Getters e Setters
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public List<Matricula> getMatriculas() {
		return matriculas;
	}


	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula getMatriculaExclusao() {
		return matriculaExclusao;
	}

	public void setMatriculaExclusao(Matricula matriculaExclusao) {
		this.matriculaExclusao = matriculaExclusao;
	}
	
	
	
	
	
	
}   
