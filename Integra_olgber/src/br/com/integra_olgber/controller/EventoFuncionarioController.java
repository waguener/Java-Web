package br.com.integra_olgber.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.autenticacao.AuthenticationContext;
import br.com.integra_olgber.model.Agencia;
import br.com.integra_olgber.model.Arquivo;
import br.com.integra_olgber.model.EventoFuncionario;
import br.com.integra_olgber.model.Funcionario;
import br.com.integra_olgber.model.Turno;
import br.com.integra_olgber.services.AgenciaServices;
import br.com.integra_olgber.services.EmailService;
import br.com.integra_olgber.services.EventoFuncionarioServices;
import br.com.integra_olgber.services.FuncionarioServices;

@ManagedBean
@Controller
@Scope("session")
public class EventoFuncionarioController implements Serializable {

	private static final long serialVersionUID = 4669457958407429327L;

	@Autowired
	private EventoFuncionarioServices eventoFuncionarioServices;
	@Autowired
	private FuncionarioServices funcionarioServices;
	@Autowired
	private AuthenticationContext authenticationContext;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AgenciaServices agenciaServices;

	private EventoFuncionario eventoFuncionario = new EventoFuncionario();
	private EventoFuncionario imprimir;
	private Funcionario funcionario = new Funcionario();
	private Agencia agencia;
	private EventoFuncionario eventoexclusao;
	private Arquivo arquivo = new Arquivo();
	private Turno turno = new Turno();
	private Funcionario funcionarioSelecionado;
	private Funcionario funcionarios;
	private List<EventoFuncionario> eventoFuncionarios;
	private List<Agencia> agencias;
	private String arquivoSelecionado;
	private String nomeFuncionario;
	private String nome;
	
	
	// Métodos

	public void init() {
		eventoFuncionario = new EventoFuncionario();
		agencias = new ArrayList<Agencia>();
		arquivo = new Arquivo();
		nome = null;
		funcionarios = new Funcionario();
		arquivoSelecionado = null;

	}
	
	public void iniciarPesquisa() {
		nomeFuncionario = null;
		eventoFuncionarios = new ArrayList<EventoFuncionario>();
	}

	public String salvarFalta() {
		
		if(eventoFuncionario.getFuncionario().getNome() == null) {
			
			FacesMessage msm = new FacesMessage("Precisa escolher um Funcionário Para Salvar!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			
			return null;
		}else {
		eventoFuncionario.setTipo("FALTA");
		eventoFuncionario.setHoraFalta(new SimpleDateFormat("HH:mm").format(new Date()));
		eventoFuncionario.setDataEvento(new Date());
		eventoFuncionario.setFalta(1);
		eventoFuncionarioServices.salvar(eventoFuncionario);
		FacesMessage msm = new FacesMessage("Falta Cadastrada com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		eventoFuncionario = new EventoFuncionario();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verFalta').hide();");

		return "CadastroEvento?faces-redirect=true";
		}
	}

	public String salvarAtestado() {

		if (eventoFuncionario.getArquivo() == null) {
			FacesMessage msm = new FacesMessage("Escolha um Arquivo!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
		}
		if(eventoFuncionario.getFuncionario().getNome() == null) {
			
			FacesMessage msm = new FacesMessage("Precisa escolher um Funcionário Para Salvar!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			
			return null;
		}else {
		
		FacesMessage msmg = new FacesMessage("Salvando e Enviando Atestado!!");
		FacesContext.getCurrentInstance().addMessage(null, msmg);

		eventoFuncionario.setTipo("ATESTADO");
		eventoFuncionario.setDataEvento(new Date());
		eventoFuncionario.setHoraAtestado(new SimpleDateFormat("HH:mm").format(new Date()));

		eventoFuncionarioServices.salvarAtestado(eventoFuncionario);
		
		emailService.enviarEmailAtestado(eventoFuncionario);
		FacesMessage msm = new FacesMessage("Atestado Cadastrado com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		eventoFuncionario = new EventoFuncionario();
		arquivoSelecionado = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verAtestado').hide();");

		return "cadastroEvento?faces-redirect=true";
		}
	}

	public String salvarEdit() {
		
		eventoFuncionarioServices.salvar(eventoFuncionario);
		FacesMessage msm = new FacesMessage("Evento Editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		eventoFuncionario = new EventoFuncionario();
		eventoFuncionarios = eventoFuncionarioServices.porNome(nomeFuncionario);
		
		return "PesquisaEvento?faces-redirect=true";
	}
	
	//Enviar Arquivo
	public void fileUpLoaderHandler(FileUploadEvent event) throws IOException {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome(event.getFile().getFileName());
		arquivo.setStream(event.getFile().getInputstream());
		eventoFuncionario.setArquivo(arquivo);
		arquivoSelecionado = arquivo.getNome();
	}

	public StreamedContent baixarArquivo(EventoFuncionario eventoFuncionario) throws FileNotFoundException {
		File file = eventoFuncionarioServices.obterArquivoDaEventos(eventoFuncionario);
		FileInputStream is = new FileInputStream(file);

		return new DefaultStreamedContent(is, "application/pdf", eventoFuncionario.getArquivo().getNome());
	}

	//Editar
	public String editarFuncionarioFalta(EventoFuncionario eventoFuncionario) {
		this.eventoFuncionario = eventoFuncionario;	
		return "EventoFuncionarioEdit?faces-redirect=true";
	}
	
	public String editarFuncionarioAtestado(EventoFuncionario eventoFuncionario) {
		this.eventoFuncionario = eventoFuncionario;
		arquivoSelecionado = eventoFuncionario.getArquivo().getNome();
		return "EventoFuncionarioEdit?faces-redirect=true";
	}
	// Pesquisas

	public void porNome() {
				
		funcionarios = eventoFuncionarioServices.porNomeFuncionario(nome);
		eventoFuncionario.setFuncionario(funcionarios);
		
	}

	public void buscar() {

		eventoFuncionarios = eventoFuncionarioServices.porNome(nomeFuncionario);
		if(eventoFuncionarios.isEmpty()) {
			FacesMessage msm = new FacesMessage("Nenhum Evento Encontrado para este Funcionário!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}

	}

	public void verAgencia() {

		agencias = agenciaServices.listar();

	}
	//Excluir
	
	public void prepararExcluirEvento(EventoFuncionario eventoFuncionario) {
		eventoexclusao = eventoFuncionario;
	}

	public void excluirEvento() {
		eventoFuncionarioServices.excluir(eventoexclusao);
		eventoFuncionarios = eventoFuncionarioServices.porNome(nomeFuncionario);
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Abrir Falta

	public void imprimirFalta(Funcionario funcionario) {
		
		this.funcionario = funcionario;
		eventoFuncionario.setFuncionario(funcionario);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verFalta').show();");
	}
	
	//Fechar Falta
	
	public void fecharFalta() {
		
		eventoFuncionario = new EventoFuncionario();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verFalta').hide();");
	}

	// Abrir Atestado
	public void imprimirAtestado(Funcionario funcionario) {
		this.funcionario = funcionario;
		eventoFuncionario.setFuncionario(funcionario);
		String nome = eventoFuncionario.getFuncionario().getAgencia();
		agencia = agenciaServices.porAgenciaAtestado(nome);
		eventoFuncionario.setAgencia(agencia);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verAtestado').show();");
	}
	
	//Fechar Atestado
	public void fecharAtestado() {
		eventoFuncionario = new EventoFuncionario();
		eventoFuncionario.setArquivo(null);
		arquivoSelecionado = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verAtestado').hide();");
	}

	// Pesquisas

	// Autocomplet
	/*
	 * public List<Funcionario> completaNome(String query) {
	 * 
	 * funcionarios = eventoFuncionarioServices.porCPFTodos(); List<Funcionario>
	 * sugestoes = new ArrayList<Funcionario>();
	 * 
	 * for(Funcionario j : funcionarios) { if(j.getNome().startsWith(query)) {
	 * 
	 * sugestoes.add(j);
	 * 
	 * } } return sugestoes; }
	 */
	
	//Mensagem Sessão Encerrada
	
	public void onIdle() {
		System.out.println("Sessão Encerrada");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "Sessão Encerrada.", "Clique em Atualizar o Navegador ou a Tecla F5"));
    }

	// Getters e Setters
	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public EventoFuncionario getEventoFuncionario() {
		return eventoFuncionario;
	}

	public void setEventoFuncionario(EventoFuncionario eventoFuncionario) {
		this.eventoFuncionario = eventoFuncionario;
	}

	public Funcionario getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionario funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(String arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public List<EventoFuncionario> getEventoFuncionarios() {
		return eventoFuncionarios;
	}

	public void setEventoFuncionarios(List<EventoFuncionario> eventoFuncionarios) {
		this.eventoFuncionarios = eventoFuncionarios;
	}

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	
}
