package br.com.belcanto.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.belcanto.model.Agenda;
import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.AulaComp;
import br.com.belcanto.model.AulaCompReceber;
import br.com.belcanto.model.Contrato;
import br.com.belcanto.model.Curso;
import br.com.belcanto.model.DiasSemana;
import br.com.belcanto.model.Horario;
import br.com.belcanto.model.Matricula;
import br.com.belcanto.model.MatriculaReceber;
import br.com.belcanto.model.MensalReceber;
import br.com.belcanto.model.Modulo;
import br.com.belcanto.model.Professor;
import br.com.belcanto.model.Receber;
import br.com.belcanto.services.AgendaServices;
import br.com.belcanto.services.AlunoServices;
import br.com.belcanto.services.AulaCompReceberServices;
import br.com.belcanto.services.AulaCompServices;
import br.com.belcanto.services.ContratoServices;
import br.com.belcanto.services.CursoServices;
import br.com.belcanto.services.MatriculaReceberServices;
import br.com.belcanto.services.MatriculaServices;
import br.com.belcanto.services.MensalReceberServices;
import br.com.belcanto.services.ModuloServices;
import br.com.belcanto.services.ProfessorServices;

@ManagedBean
@Controller
@Scope("session")
@SuppressWarnings("deprecation")
public class MatriculaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MatriculaServices matriculaServices;
	@Autowired
	private AgendaServices agendaServices;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private ProfessorServices professorServices;
	@Autowired
	private ModuloServices moduloServices;
	@Autowired
	private ContratoServices contratoServices;
	@Autowired
	private AlunoServices alunoServices;
	@Autowired
	private AulaCompServices aulaCompServices;
	@Autowired
	private AulaCompReceberServices aulaCompReceberServices;
	@Autowired
	private MensalReceberServices mensalReceberServices;
	@Autowired
	private MatriculaReceberServices matriculaReceberServices;

	private Matricula matricula;
	private Matricula matSelecionada;
	private List<Matricula> matriculas;
	private Agenda agenda = new Agenda();
	private Aluno aluno;
	private Aluno alunoMatricula;
	private Aluno cursoAluno;
	private Curso curso;
	private List<Curso> cursos = new ArrayList<Curso>();
	private Modulo modulo;
	private List<Modulo> modulos = new ArrayList<Modulo>();
	private AulaComp aulaComp;
	private List<AulaComp> listarAulas;
	private Professor professor;
	private List<Professor> professores = new ArrayList<Professor>();
	private Horario horario;
	private Contrato contrato;
	private List<Contrato> contratos;
	private AulaCompReceber aulaCompReceber;
	private MatriculaReceber matriculaReceber;
	private MensalReceber mensalReceber;
	private Receber receber;
	private int indiceHorario;
	private DiasSemana diasSemana;
	private int numMatricula;
	private boolean valor;
	private Date hj = new Date();
	private String nome;
	private Double vAulaC;
	private String dtInicio;

	public void init() {
		matricula = new Matricula();
		mensalReceber = new MensalReceber();
		aulaCompReceber = new AulaCompReceber();
		matriculaReceber = new MatriculaReceber();
		receber = new Receber();
		numMatricula = 0;
		ultimaMatricula();
		agenda = new Agenda();
		matricula.setAtivo(true);
		cursos = new ArrayList<Curso>();
		cursos = cursoServices.listar();
		aulaComp = new AulaComp();
		professores = new ArrayList<Professor>();
		professores = professorServices.listar();
		contratos = new ArrayList<Contrato>();
		contratos = contratoServices.listaContrato();
		aluno = agendaServices.ultimo();
		alunoMatricula = agendaServices.pegarAluno(aluno.getId());
		agenda.setAluno(alunoMatricula);
		vAulaC = 0.0;
		valor = false;
		dtInicio = null;

	}

	public void initAdd() {
		matricula = new Matricula();
		mensalReceber = new MensalReceber();
		aulaCompReceber = new AulaCompReceber();
		matriculaReceber = new MatriculaReceber();
		receber = new Receber();
		numMatricula = 0;
		ultimaMatricula();
		agenda = new Agenda();
		agenda.setAluno(cursoAluno);
		matricula.setAtivo(true);
		cursos = new ArrayList<Curso>();
		cursos = cursoServices.listar();
		aulaComp = new AulaComp();
		professores = new ArrayList<Professor>();
		professores = professorServices.listar();
		contratos = new ArrayList<Contrato>();
		contratos = contratoServices.listaContrato();

		vAulaC = 0.0;
		valor = false;
		dtInicio = null;

	}

	public void initConsulta() {

		matriculas = new ArrayList<Matricula>();
		nome = null;
	}

	public void initAlunoEdit() {

	}

	// Salvar
	public void salvar() {

		matricula.setDtMatricula(hj);
		matricula.setNumMatricula("" + numMatricula);
		matricula.setAgenda(agenda);
		matriculaServices.salvar(matricula);

	}

	// Calcular Desconto
	public void calcularDesc() {
		double total;
		total = (agenda.getCurso().getValor() * matricula.getDesconto()) / 100;
		total = arredondar(total, 2, 1);
		total = agenda.getCurso().getValor() - total;
		total = total + vAulaC;
		matricula.setValorMensalidade(total);

	}

	// Somar Aula Complementar
	public void aulaComp() {
		vAulaC = 0.0;
		if (agenda.getAulaComp() == null) {
			vAulaC = 0.0;

		} else {
			vAulaC = agenda.getAulaComp().getValor();
		}
	}

	// Arrendondar valor
	protected Double arredondar(double valor, int casas, int ceilOrFloor) {
		double arredondado = valor;
		arredondado *= (Math.pow(10, casas));
		if (ceilOrFloor == 0) {
			arredondado = Math.ceil(arredondado);
		} else {
			arredondado = Math.floor(arredondado);
		}
		arredondado /= (Math.pow(10, casas));
		return arredondado;
	}

	// Incluir novo Horario
	public void incluirNovoHorario() {

		agenda.getHorarios().add(new Horario());
		System.out.println("teste");
	}

	// Remover Clausula
	public void prepararExclusaoHorario(int indiceHorario) {
		this.indiceHorario = indiceHorario;
	}

	public void excluirHorario() {

		agenda.getHorarios().remove(indiceHorario);
		FacesMessage msm = new FacesMessage("Horário Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	protected int ultimaMatricula() {
		numMatricula = 0;
		String id;
		matriculas = matriculaServices.listar();

		if (matriculas.isEmpty()) {
			numMatricula = 1;
			return numMatricula;
		} else {
			id = matriculaServices.ultimoId();
			matricula = matriculaServices.porId(Long.parseLong(id));
			numMatricula = Integer.parseInt(matricula.getNumMatricula());
			numMatricula++;
			matricula = new Matricula();
			return numMatricula;
		}
	}

	// Metodos Curso
	public void listarModulos() {

		Long cursoId = agenda.getCurso().getId();
		modulos = moduloServices.listar(cursoId);
		listarAulas = aulaCompServices.listar(cursoId);
		valor = true;
	}

	// Adicionar Curso
	public String addCurso(Aluno aluno) {
		this.aluno = aluno;
		cursoAluno = new Aluno();
		cursoAluno = aluno;
		return "AgendaAddCurso?faces-redirect=true";
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

	// Editar Aluno
	public String editarAluno() {
		FacesContext context = FacesContext.getCurrentInstance();

		@SuppressWarnings("unlikely-arg-type")
		String valor = context.getExternalContext().getRequestParameterMap().get(matSelecionada.getAgenda().getAluno());
		System.out.println(valor);

		return "AlunoEdit?faces-redirect=true";

	}

	// Gerar Contrato
	public void geraContrato() {

		contrato = contratoServices.contrato();
		if (contrato == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Criação do documento", "Informação"));
		} else {
			Document document = new Document();

			try {

				OutputStream outputStream = new FileOutputStream("C:\\Users\\Wagner\\Desktop\\Contrato\\contrato.pdf");

				PdfWriter.getInstance(document, outputStream);

				document.open();
				Paragraph cabecalho = new Paragraph(contrato.getCabecalho().intern());
				document.add(cabecalho);
				document.close();

			} catch (FileNotFoundException | DocumentException e) {

				System.out.println("Erro" + e);
			}

			try {
				Desktop.getDesktop().open(new File("C:\\Users\\Wagner\\Desktop\\Contrato\\contrato.pdf"));
			} catch (IOException e) {
				System.out.println("Erro" + e);
			}
		}
	}

	// Abrir tela Contrato
	public void abrirContrato() {
		SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
		dtInicio = dFormat.format(matricula.getDtInicioAula());

		matricula.setAgenda(agenda);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('contrato').show();");

	}

	// Fechar tela contrato
	public String fecharContrato() {
		salvar();
		mensalidade();
		if (agenda.getAulaComp() != null) {
			aulaCompReceber();
		}
		matriculaReceber();
		matricula = new Matricula();
		aluno = new Aluno();
		agenda = new Agenda();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('contrato').hide();");

		FacesMessage msm = new FacesMessage("Matrícula feita com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "CadAluno?faces-redirect=true";
	}

	// Abrir tela Opções
	public void abrirOpcoes(Matricula matricula) {
		this.matricula = matricula;
		matSelecionada = matricula;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('opcoes').show();");

	}

	// Calcular Mensalidade

	protected void mensalidade() {
		Date hj = new Date();

		Calendar Inicial = new GregorianCalendar();
		Inicial.setTime(hj);

		int QtdParcelas = 12;
		int dia, mes, ano = 0;

		dia = matricula.getVencMensalidade();
		mes = Inicial.getTime().getMonth();
		ano = hj.getYear() + 1900;

		try {
			Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));
		} catch (ParseException e) {
			System.out.println(e);
		}

		for (int i = 1; i <= QtdParcelas; i++) {

			Inicial.roll(Calendar.MONTH, 1);

			int Mes = Inicial.get(Calendar.MONTH);
			if (Mes == 0 && i > 0) {
				Inicial.roll(Calendar.YEAR, 1);

			}
			Matricula mat = new Matricula();
			String id = null;
			id = matriculaServices.ultimoId();
			mat = matriculaServices.porId(Long.parseLong(id));
			if (i == 1) {
				receber.setDtVencimento(mat.getDtInicioAula());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("MENSALIDADE");
				receber.setValorReceber(mat.getValorMensalidade());
				mensalReceber.setReceber(receber);
				mensalReceber.setMatricula(mat);
				mensalReceberServices.salvar(mensalReceber);
				mensalReceber = new MensalReceber();
			} else if (Inicial.getTime().getMonth() == 2) {
				 

					dia = mat.getVencMensalidade();
					mes = Inicial.getTime().getMonth() + 1;
					ano = Inicial.getTime().getYear() + 1900;

					try {
						Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));

					} catch (ParseException e) {

						e.printStackTrace();
					}
					receber.setDtVencimento(Inicial.getTime());
					receber.setParcela(i);
					receber.setStatus("PENDENTE");
					receber.setTipo("MENSALIDADE");
					receber.setValorReceber(mat.getValorMensalidade());
					mensalReceber.setReceber(receber);
					mensalReceber.setMatricula(mat);
					mensalReceberServices.salvar(mensalReceber);
					mensalReceber = new MensalReceber();
				} else {
					receber = new Receber();
					receber.setDtVencimento(Inicial.getTime());
					receber.setParcela(i);
					receber.setStatus("PENDENTE");
					receber.setTipo("MENSALIDADE");
					receber.setValorReceber(mat.getValorMensalidade());
					mensalReceber.setReceber(receber);
					mensalReceber.setMatricula(mat);
					mensalReceberServices.salvar(mensalReceber);
					mensalReceber = new MensalReceber();
				}
			}
		
	}

	// Aula Complementar Receber
	protected void aulaCompReceber() {
		receber = new Receber();
		Date hj = new Date();

		Calendar Inicial = new GregorianCalendar();
		Inicial.setTime(hj);

		int QtdParcelas = 12;
		int dia, mes, ano = 0;

		dia = matricula.getVencMensalidade();
		mes = Inicial.getTime().getMonth();
		ano = hj.getYear() + 1900;

		try {
			Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));
		} catch (ParseException e) {
			System.out.println(e);
		}

		for (int i = 1; i <= QtdParcelas; i++) {

			Inicial.roll(Calendar.MONTH, 1);

			int Mes = Inicial.get(Calendar.MONTH);
			if (Mes == 0 && i > 0) {
				Inicial.roll(Calendar.YEAR, 1);

			}
			Matricula mat = new Matricula();
			String id = null;
			id = matriculaServices.ultimoId();
			mat = matriculaServices.porId(Long.parseLong(id));
			
			if (i == 1) {
				receber.setDtVencimento(mat.getDtInicioAula());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("AULA COMPLEMENTAR");
				receber.setValorReceber(mat.getAgenda().getAulaComp().getValor());
				aulaCompReceber.setReceber(receber);
				aulaCompReceber.setMatricula(mat);
				aulaCompReceberServices.salvar(aulaCompReceber);
				aulaCompReceber = new AulaCompReceber();
				
			}else if (Inicial.getTime().getMonth() == 2) {
				
			

				dia = mat.getVencMensalidade();
				mes = Inicial.getTime().getMonth() + 1;
				ano = Inicial.getTime().getYear() + 1900;

				try {
					Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));

				} catch (ParseException e) {

					e.printStackTrace();
				}
				receber.setDtVencimento(Inicial.getTime());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("AULA COMPLEMENTAR");
				receber.setValorReceber(mat.getAgenda().getAulaComp().getValor());
				aulaCompReceber.setReceber(receber);
				aulaCompReceber.setMatricula(mat);
				aulaCompReceberServices.salvar(aulaCompReceber);
				aulaCompReceber = new AulaCompReceber();
			} else {
				receber.setDtVencimento(Inicial.getTime());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("AULA COMPLEMENTAR");
				receber.setValorReceber(mat.getAgenda().getAulaComp().getValor());
				aulaCompReceber.setReceber(receber);
				aulaCompReceber.setMatricula(mat);
				aulaCompReceberServices.salvar(aulaCompReceber);
				aulaCompReceber = new AulaCompReceber();
			}
		}
		
	}

	// Recebimento Matricula
	protected void matriculaReceber() {
		Date hj = new Date();

		Calendar Inicial = new GregorianCalendar();
		Inicial.setTime(hj);

		int QtdParcelas = matricula.getParcMatricula();
		int dia, mes, ano = 0;
		double total;
		dia = matricula.getVencMensalidade();
		mes = Inicial.getTime().getMonth();
		ano = hj.getYear() + 1900;

		try {
			Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		total = matricula.getAgenda().getCurso().getValorInscricao() / QtdParcelas;
		total = arredondar(total, 2, 1);

		for (int i = 1; i <= QtdParcelas; i++) {

			Inicial.roll(Calendar.MONTH, 1);
			
			int Mes = Inicial.get(Calendar.MONTH);
			if (Mes == 0 && i > 0) {
				Inicial.roll(Calendar.YEAR, 1);

			}
			Matricula mat = new Matricula();
			String id = null;
			id = matriculaServices.ultimoId();
			mat = matriculaServices.porId(Long.parseLong(id));

			if (i == 1) {
				receber.setDtVencimento(mat.getDtInicioAula());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("MATRICULA");
				receber.setValorReceber(total);
				matriculaReceber.setReceber(receber);
				matriculaReceber.setMatricula(mat);
				matriculaReceberServices.salvar(matriculaReceber);
				matriculaReceber = new MatriculaReceber();
			
			}else if (Inicial.getTime().getMonth() == 2) {
			

				dia = mat.getVencMensalidade();
				mes = Inicial.getTime().getMonth() + 1;
				ano = Inicial.getTime().getYear() + 1900;

				try {
					Inicial.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dia + "/" + mes + "/" + ano));

				} catch (ParseException e) {

					e.printStackTrace();
				}
				receber.setDtVencimento(Inicial.getTime());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("MATRICULA");
				receber.setValorReceber(total);
				matriculaReceber.setReceber(receber);
				matriculaReceber.setMatricula(mat);
				matriculaReceberServices.salvar(matriculaReceber);
				matriculaReceber = new MatriculaReceber();
			} else {
				receber.setDtVencimento(Inicial.getTime());
				receber.setParcela(i);
				receber.setStatus("PENDENTE");
				receber.setTipo("MATRICULA");
				receber.setValorReceber(total);
				matriculaReceber.setReceber(receber);
				matriculaReceber.setMatricula(mat);
				matriculaReceberServices.salvar(matriculaReceber);
				matriculaReceber = new MatriculaReceber();
			}
		}
		
	}

	// Buscar Aluno
	public void porNome() {
		matriculas = matriculaServices.porNome(nome);
		if (matriculas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma Matrícula encontrada!!", ""));
			RequestContext.getCurrentInstance().execute("growlColour(\".ui-growl-image-error\");");
		}
	}

	// Editar ALuno
	public String edit() {

		aluno = matSelecionada.getAgenda().getAluno();

		return "AlunoEdit?faces-redirect=true";
	}

	// Getters e Setters
	public Agenda getAgenda() {
		return agenda;
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

	public Aluno getAlunoMatricula() {
		return alunoMatricula;
	}

	public void setAlunoMatricula(Aluno alunoMatricula) {
		this.alunoMatricula = alunoMatricula;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public void setDiasSemana(DiasSemana diasSemana) {
		this.diasSemana = diasSemana;
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

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Matricula getMatSelecionada() {
		return matSelecionada;
	}

	public void setMatSelecionada(Matricula matSelecionada) {
		this.matSelecionada = matSelecionada;
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

	public Double getvAulaC() {
		return vAulaC;
	}

	public void setvAulaC(Double vAulaC) {
		this.vAulaC = vAulaC;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

}
