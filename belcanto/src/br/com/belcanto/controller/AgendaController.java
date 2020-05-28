package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Agenda;
import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.AulaComp;
import br.com.belcanto.model.Curso;
import br.com.belcanto.model.DiasSemana;
import br.com.belcanto.model.Horario;
import br.com.belcanto.model.Modulo;
import br.com.belcanto.model.Professor;
import br.com.belcanto.services.AgendaServices;
import br.com.belcanto.services.AulaCompServices;
import br.com.belcanto.services.CursoServices;
import br.com.belcanto.services.ModuloServices;
import br.com.belcanto.services.ProfessorServices;

@ManagedBean
@Controller
@Scope("session")
public class AgendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AgendaServices agendaServices;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private ProfessorServices professorServices;
	@Autowired
	private ModuloServices moduloServices;
	@Autowired
	private AulaCompServices aulaCompServices;

	private Agenda agenda = new Agenda();
	private Aluno aluno;
	private Aluno alunoMatricula;
	private Curso curso;
	private List<Curso> cursos = new ArrayList<Curso>();
	private Modulo modulo;
	private List<Modulo> modulos = new ArrayList<Modulo>();
	private AulaComp aulaComp;
	private List<AulaComp> listarAulas = new ArrayList<AulaComp>();
	private Professor professor;
	private List<Professor> professores = new ArrayList<Professor>();
	private Horario horario;
	private int indiceHorario;
	private DiasSemana diasSemana;

	public void init() {
		cursos = cursoServices.listar();
		professores = professorServices.listar();
		aluno = agendaServices.ultimo();
		alunoMatricula = agendaServices.pegarAluno(aluno.getId());
		agenda.setAluno(alunoMatricula);
	}

	// Incluir novo Horario
	public void incluirNovoHorario() {

		agenda.getHorarios().add(new Horario());
		System.out.println("teste");
	}

	// Remover horario
	public void prepararExclusaoHorario(int indiceHorario) {
		this.indiceHorario = indiceHorario;
	}
	
	public void excluirHorario() {

		agenda.getHorarios().remove(indiceHorario);
		FacesMessage msm = new FacesMessage("Horário Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Metodos Curso
	public void listarModulos() {
		/*
		 * check = matriculaServices.check(matricula.getAluno().getCpf(),
		 * matricula.getCurso().getNome()); if (check != true) { Long cursoId =
		 * matricula.getCurso().getId(); listarModulos = moduloServices.listar(cursoId);
		 * } else { listarModulos = new ArrayList<Modulo>();
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Este Aluno já está Matriculado neste Curso", "Informação")); }
		 */
		Long cursoId = agenda.getCurso().getId();
		modulos = moduloServices.listar(cursoId);
		listarAulas = aulaCompServices.listar(cursoId);
	}

	

	

	// Getters e Setters

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public int getIndiceHorario() {
		return indiceHorario;
	}

	public void setIndiceHorario(int indiceHorario) {
		this.indiceHorario = indiceHorario;
	}

	public DiasSemana[] getDiasSemana() {
		return DiasSemana.values();
	}

	public AulaComp getAulaComp() {
		return aulaComp;
	}

	public void setAulaComp(AulaComp aulaComp) {
		this.aulaComp = aulaComp;
	}

	public List<AulaComp> getListarAulas() {
		return listarAulas;
	}

	public void setListarAulas(List<AulaComp> listarAulas) {
		this.listarAulas = listarAulas;
	}
	
}
