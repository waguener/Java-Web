package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Aula;
import br.com.belcanto.model.Horario;
import br.com.belcanto.model.Matricula;
import br.com.belcanto.model.Professor;
import br.com.belcanto.model.Turma;
import br.com.belcanto.services.AlunoServices;
import br.com.belcanto.services.AulaServices;
import br.com.belcanto.services.HorarioServices;
import br.com.belcanto.services.MatriculaServices;
import br.com.belcanto.services.ProfessorServices;
import br.com.belcanto.services.TurmaServices;

@ManagedBean
@Controller
@Scope("session")
public class AulaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AulaServices aulaServices;
	@Autowired
	private TurmaServices turmaServices;
	@Autowired
	private MatriculaServices matriculaServices;
	@Autowired
	private ProfessorServices professorServices;
	@Autowired
	private HorarioServices horarioServices;
	@Autowired
	private AlunoServices alunoServices;

	private Aula aula = new Aula();
	private Matricula matricula;
	private Matricula excluirDrop;
	private Aluno aluno;
	private Turma turma;
	private List<Aula> listarAulas;
	private List<Turma> turmas;
	private List<Horario> horarios;
	private List<Professor> professores;
	private List<Matricula> matriculas;
	private List<Matricula> matriculasInseridas;
	private List<Object> horas;
	private String nome;
	private List<Matricula> listaTemp = new ArrayList<Matricula>();
	
	// Métodos
	public void init() {
		aula = new Aula();
		turmas = new ArrayList<Turma>();
		turmas = turmaServices.listarTurmasAtivas();
		horarios = new ArrayList<Horario>();
		professores = new ArrayList<Professor>();
		professores = professorServices.listarAtivos();
		matriculas = new ArrayList<Matricula>();
		matriculasInseridas = new ArrayList<Matricula>();
	}

	public void initEdit() {
		matriculasInseridas = new ArrayList<Matricula>();
		horarios = aula.getTurma().getHorarios();
		matriculasInseridas = aula.getMatriculas();
		turmas = turmaServices.listarTurmasAtivas();
		professores = professorServices.listarAtivos();
		listaTemp.addAll(matriculas);
		listaTemp.removeAll(matriculasInseridas);
		matriculas = new ArrayList<Matricula>();
		

	}

	public void initConsulta() {
		listarAulas = new ArrayList<Aula>();
		nome = null;
	}

	// Salvar
	public String salvar() {
		if(matriculasInseridas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adicione um Aluno na Sala de Aula", "Informação"));
			return null;
		}else {
		aula.setMatriculas(matriculasInseridas);
		aulaServices.salvar(aula);

		FacesMessage msm = new FacesMessage("Aula Cadastrada com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "CadAula?faces-redirect=true";
		}
	}

	public String salvarEdit() {
		
		aula.setMatriculas(matriculasInseridas);
		aulaServices.salvar(aula);

		FacesMessage msm = new FacesMessage("Aula Editada com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "ConAulas?faces-redirect=true";
	}
	
	// Montar Turmas
	public void onMatriculaDrop(DragDropEvent event) {
		matricula = (Matricula) event.getData();
		matriculas.remove(matricula);
		matriculasInseridas.add(matricula);
	}

	public void excluirDrop(Matricula matricula) {
		excluirDrop = matricula;
		matriculas.add(excluirDrop);
		matriculasInseridas.remove(excluirDrop);
		if (matriculasInseridas.isEmpty()) {
			matriculasInseridas = new ArrayList<Matricula>();
		}
	}

	// Editar
	public String editar(Aula aula) {
		this.aula = aula;

		return "EditAula?faces-redirect=true";
	}

	// Consultas
	public void buscarAlunos() {
		listaTemp = new ArrayList<Matricula>();
		matriculas = matriculaServices.porCurso(aula.getTurma().getCurso().getNome());
		listaTemp.addAll(matriculas);
		listaTemp.removeAll(matriculasInseridas);
		matriculas = new ArrayList<Matricula>();
		matriculas.addAll(listaTemp);
		professores = professorServices.listar();
		horarios = aula.getTurma().getHorarios();

	}

	public void porTurma() {
		listarAulas = aulaServices.porTurma(nome);
		if (listarAulas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma Turma Encontrada", ""));
		}
	}

	// Mostrar foto aluno
	public StreamedContent getImagemAluno() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idAlunoStr = mapaParametros.get("idAluno");
		if (idAlunoStr != null) {
			Long idAluno = new Long(idAlunoStr);
			Aluno alunoBanco = alunoServices.obterPorId(idAluno);
			return alunoBanco.getImagem();
		}
		return new DefaultStreamedContent();
	}
	// Getters e Setters

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Aula> getListarAulas() {
		return listarAulas;
	}

	public void setListarAulas(List<Aula> listarAulas) {
		this.listarAulas = listarAulas;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Object> getHoras() {
		return horas;
	}

	public void setHoras(List<Object> horas) {
		this.horas = horas;
	}

	public List<Matricula> getMatriculasInseridas() {
		return matriculasInseridas;
	}

	public void setMatriculasInseridas(List<Matricula> matriculasInseridas) {
		this.matriculasInseridas = matriculasInseridas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
