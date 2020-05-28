package br.com.belcanto.controller;

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

import br.com.belcanto.model.Agenda;
import br.com.belcanto.model.Curso;
import br.com.belcanto.model.DiasSemana;
import br.com.belcanto.model.Horario;
import br.com.belcanto.model.Hora;
import br.com.belcanto.model.Turma;
import br.com.belcanto.services.CursoServices;
import br.com.belcanto.services.TurmaServices;

@ManagedBean
@Controller
@Scope("session")
public class TurmaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TurmaServices turmaServices;

	@Autowired
	private CursoServices cursoServices;

	private Turma turma = new Turma();
	private List<Turma> turmas;
	private List<Curso> cursos;
	private Horario horario;
	private DiasSemana diasSemana;
	private Hora hora;
	private Horario horarioExclusao;
	private int indiceHorario;
	private String nome;
	private Date HoraInicial;
	private Boolean botaoSalvar;
	private Agenda agenda;

	// Metodos de Inicio
	public void init() {
		botaoSalvar = false;
		turma = new Turma();
		horario = new Horario();
		cursos = cursoServices.listar();
		turma.setAtivo(true);
	}

	public void initCons() {
		turmas = new ArrayList<Turma>();
		nome = null;
	}
	
	public void initEdit() {
		cursos = cursoServices.listar();
	}
	// Salvar

	public String salvar() {

		if (turma.getHorarios().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adicione um Horário para essa Turma", "Informação"));
			return null;
		} else {
			turmaServices.salvar(turma);
			FacesMessage msm = new FacesMessage("Turma salva com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

			return "Horarios?faces-rediredt=true";
		}
	}

	public String salvarEdit() {

		turmaServices.salvar(turma);

		FacesMessage msm = new FacesMessage("Turma editada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "ConHorario?faces-rediredt=true";
	}

	// Incluir Horario
	public void incluirNovoHorario() {

		turma.getHorarios().add(new Horario());

	}

	// verificar horafinal
	
	public void horaInicio(Date ini ) {
		HoraInicial = ini;
	}
	public void horaFinal(Date hFin) {

		if(HoraInicial.after(hFin)) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"A Hora final não pode ser menor que a Hora Inicial", "Informação"));
			botaoSalvar = false;
		}else {
			botaoSalvar = true;
		}
	}

	// Remover horario
	public void prepararExclusaoHorario(int indiceHorario) {
		this.indiceHorario = indiceHorario;
	}

	public void excluirHorario() {

		turma.getHorarios().remove(indiceHorario);
		FacesMessage msm = new FacesMessage("Horário Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Editar
	public String editar(Turma turma) {
		this.turma = turma;

		return "EditHorarios?faces-redirect=true";
	}
	// Consultas

	public void porNome() {
		turmas = turmaServices.porNome(nome);
		if (turmas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum Horário Encontrado!!", "Informação"));
		}
	}
	
	

	// Getters e Setters
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public DiasSemana[] getDiasSemana() {
		return DiasSemana.values();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(Boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public Hora[] getHora() {
		return Hora.values();
	}

	

	
	
}
