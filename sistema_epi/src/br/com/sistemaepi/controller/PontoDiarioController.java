package br.com.sistemaepi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import br.com.sistemaepi.Bean.AgenciaBean;
import br.com.sistemaepi.Bean.Arquivo;
import br.com.sistemaepi.Bean.Atestado;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.Bean.PontoDiario;
import br.com.sistemaepi.service.AgenciaService;
import br.com.sistemaepi.service.AtualCadServices;
import br.com.sistemaepi.service.FuncionarioService;
import br.com.sistemaepi.service.PontoDiarioServices;

@ManagedBean
@Controller
@Scope("session")
public class PontoDiarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PontoDiarioServices diarioServices;
	@Autowired
	private AgenciaService agenciaService;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private AtualCadServices atestadoServices;

	private PontoDiario pontoDiario = new PontoDiario();
	private PontoDiario pontoDiarioFalta = new PontoDiario();
	private PontoDiario pontoDiarioFaltaAtestado = new PontoDiario();
	private PontoDiario pontoDiarioEditOk = new PontoDiario();
	private PontoDiario pontoDiarioEditExtra = new PontoDiario();
	private PontoDiario pontoDiarioEditFolga = new PontoDiario();
	private PontoDiario pontoExclusao;
	private List<PontoDiario> listaPontos = new ArrayList<PontoDiario>();
	private List<PontoDiario> listaDiaria;
	private Atestado atestado = new Atestado();
	private Atestado atestadoExclusao;
	private Arquivo arquivo = new Arquivo();
	private List<FuncionarioBean> listaTemp = new ArrayList<FuncionarioBean>();
	private List<FuncionarioBean> tempSelecionado;
	private List<Atestado> listaAtestados = new ArrayList<Atestado>();
	private FuncionarioBean funcEdit = new FuncionarioBean();
	private List<AgenciaBean> agencias = new ArrayList<AgenciaBean>();
	private Date hoje = new Date();
	private String agencia;
	private Calendar calendar = Calendar.getInstance();
	private Date data;
	private String dataAtual;
	private String arquivoSelecionado;
	private String nome;
	private Date dataInicial;
	private Date dataFinal;
	private Date dataFeriado;
	private String total;
	private String turno;
	private String resp;
	private String duracao;
	private String tipo;

	// Metodos
	public void init() {

		pontoDiario = new PontoDiario();
		total = null;
		agencias = agenciaService.listarAgencias();
		listaDiaria = new ArrayList<PontoDiario>();
		agencia = null;
		turno = "1º Turno";
		dataAtual = data();
		data = null;
	}

	public void initPeriodo() {
		listaDiaria = new ArrayList<PontoDiario>();

		dataFinal = null;
		dataInicial = null;
	}

	public void tempEdit() {
		listaDiaria = new ArrayList<PontoDiario>();
		dataInicial = null;
		dataFinal = null;
		nome = null;

	}

	public void initAtestado() {
		listaAtestados = diarioServices.buscarAtestados();
	}

	public void reg() {
		listaTemp = funcionarioService.tempReg();
		tempSelecionado = new ArrayList<FuncionarioBean>();
		data = null;
		resp = null;
		duracao = null;
	}

	public void falta() {
		listaDiaria = new ArrayList<PontoDiario>();

		dataInicial = null;
		dataFinal = null;
	}

	// Editar Batidas
	public String editarBatida(FuncionarioBean funcionarioBean) {
		this.funcEdit = funcionarioBean;

		return "EditarBatidas?faces-redirect=true";

	}

	// Salvar
	public void salvarOk(PontoDiario pontoDiario) throws java.text.ParseException {

		pontoDiario.setBatida("OK");
		pontoDiario.setObs("Batida Normal");
		pontoDiario.setDiaSemana(weekDay(calendar));
		pontoDiario.setSituacao("OK");
		diarioServices.salvar(pontoDiario);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		pontoDiario = new PontoDiario();

	}

	public void salvarExtra(PontoDiario pontoDiario) throws java.text.ParseException {

		pontoDiario.setBatida("Extra");
		pontoDiario.setObs("Hora Extra");
		pontoDiario.setDiaSemana(weekDay(calendar));
		pontoDiario.setSituacao("Extra");
		diarioServices.salvar(pontoDiario);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		pontoDiario = new PontoDiario();

	}

	public void abrirFeriado() {
		pontoDiario = new PontoDiario();
		dataFeriado = null;
		tipo = null;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('feriado').show();");
	}

	public void salvarFeriado() throws java.text.ParseException {
		listaTemp = new ArrayList<FuncionarioBean>();
		listaTemp = diarioServices.feriados(dataFeriado);

		if (listaTemp.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado!!", ""));
			listaDiaria = new ArrayList<PontoDiario>();

		} else {

			for (FuncionarioBean f : listaTemp) {
				boolean teste;
				teste = diarioServices.check(f.getId(), dataFeriado);
				pontoDiario = new PontoDiario();
				if (teste == true) {

					pontoDiario.setDiaSemana(weekDayFeriado(calendar));
					pontoDiario.setFuncionarioBean(f);
					pontoDiario.setTurno(f.getTurno());
					pontoDiario.setDtEntrada(dataFeriado);
					pontoDiario.setBatida(tipo);
					pontoDiario.setObs(tipo);
					pontoDiario.setSituacao(tipo);
					pontoDiario.setTurno(f.getTurno());
					diarioServices.salvar(pontoDiario);
				} else {

					pontoDiario = diarioServices.todosTemp(dataFeriado, f.getId());

					pontoDiario.setBatida(tipo);
					pontoDiario.setObs(tipo);
					pontoDiario.setSituacao(tipo);

					diarioServices.salvar(pontoDiario);

				}
			}

			FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('feriado').hide();");

		}
	}

	public void fecharFeriado() {
		tipo = null;
		dataFeriado = null;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('feriado').hide();");
	}

	public void prepararFalta(PontoDiario pontoDiario) {
		pontoDiarioFalta = pontoDiario;

	}

	public void salvarFalta() throws java.text.ParseException {

		pontoDiarioFalta.setBatida("Falta");
		pontoDiario.setObs("Falta");
		pontoDiarioFalta.setDiaSemana(weekDay(calendar));
		pontoDiarioFalta.setSituacao("Falta");
		diarioServices.salvar(pontoDiarioFalta);

		FacesMessage msm = new FacesMessage("Falta Adicionada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		pontoDiario = new PontoDiario();

	}

	// Salvar Edit Batidas
	public void salvarOkEdit() {

		pontoDiarioEditOk.setBatida("OK");
		pontoDiarioEditOk.setSituacao("OK");

		diarioServices.salvar(pontoDiarioEditOk);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void prepararPontoEditOk(PontoDiario pontoDiario) {
		pontoDiarioEditOk = pontoDiario;

	}
	
	public void salvarFolgaEdit() {

		pontoDiarioEditFolga.setBatida("Folga");
		pontoDiarioEditFolga.setSituacao("Folga");
		pontoDiarioEditFolga.setObs("Folga");

		diarioServices.salvar(pontoDiarioEditFolga);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void prepararPontoEditFolga(PontoDiario pontoDiario) {
		pontoDiarioEditFolga = pontoDiario;

	}
	
	public void salvarExtraEdit() {

		pontoDiarioEditExtra.setBatida("Extra");
		pontoDiarioEditExtra.setSituacao("Extra");

		diarioServices.salvar(pontoDiarioEditExtra);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void prepararPontoEditExtra(PontoDiario pontoDiario) {
		pontoDiarioEditExtra = pontoDiario;

	}

	public void salvarFaltaEdit() {
		pontoDiarioFalta.setBatida("Falta");
		pontoDiarioFalta.setSituacao("Falta");

		diarioServices.salvar(pontoDiarioFalta);

		FacesMessage msm = new FacesMessage("Ponto salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void salvarFaltaAtestadoEdit() {

		pontoDiarioFaltaAtestado.setSituacao("Atestado");
		diarioServices.salvar(pontoDiarioFaltaAtestado);
		atestado.setPontoDiario(pontoDiarioFaltaAtestado);

		diarioServices.salvarAtestado(atestado);

		FacesMessage msm = new FacesMessage("Atestado salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		fecharAtestado();
	}

	// carregar Imagens
	public StreamedContent getImagemPonto() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idPontoStr = mapaParametros.get("idPonto");
		if (idPontoStr != null) {
			Long idPonto = new Long(idPontoStr);
			PontoDiario pontoBd = diarioServices.obterPorId(idPonto);
			return pontoBd.getFuncionarioBean().getImagem();
		}
		return new DefaultStreamedContent();
	}

	public StreamedContent getImagemAtestado() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idAtestadoStr = mapaParametros.get("idAtestado");
		if (idAtestadoStr != null) {
			Long idAtestado = new Long(idAtestadoStr);
			Atestado atestadoBd = diarioServices.obterPorIdAtestado(idAtestado);
			return atestadoBd.getPontoDiario().getFuncionarioBean().getImagem();
		}
		return new DefaultStreamedContent();
	}

	// Consultas
	public void buscarHoje() {
		listaTemp = new ArrayList<FuncionarioBean>();

		listaTemp = diarioServices.temp(agencia, turno, data);
		total = diarioServices.totalTemp(agencia, turno, data);

		if (listaTemp.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado", ""));
			listaDiaria = new ArrayList<PontoDiario>();

		} else {

			for (FuncionarioBean f : listaTemp) {
				boolean teste;
				teste = diarioServices.check(f.getId(), data);

				if (teste == true) {
					pontoDiario = new PontoDiario();
					pontoDiario.setDiaSemana(weekDay(calendar));
					pontoDiario.setFuncionarioBean(f);
					pontoDiario.setTurno(f.getTurno());
					pontoDiario.setDtEntrada(data);

					if (pontoDiario.getDiaSemana().equals("Sábado") || pontoDiario.getDiaSemana().equals("Domingo")) {

						pontoDiario.setBatida("Folga");
						pontoDiario.setSituacao("Folga");
						pontoDiario.setObs("Folga");
					} else {
						pontoDiario.setBatida("OK");
						pontoDiario.setSituacao("OK");
						pontoDiario.setObs("Batida Normal");
					}
					diarioServices.salvar(pontoDiario);
				}

			}

			listaDiaria = new ArrayList<PontoDiario>();
			listaDiaria = diarioServices.hoje(agencia, turno, data);

			if (listaDiaria.isEmpty()) {
				listaDiaria = new ArrayList<PontoDiario>();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado", ""));
			}
		}

	}

	// buscar faltas
	public void buscarFaltas() {
		listaDiaria = diarioServices.faltas(dataInicial, dataFinal);
		if (listaDiaria.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado", ""));
		}
	}

	// metodo para atulizar funcionarios
	public void atualizar() {
		listaTemp = new ArrayList<FuncionarioBean>();

		listaTemp = funcionarioService.listar();

		for (FuncionarioBean f : listaTemp) {
			if (f.getAgencia().equals("OLGBER ESPECIALIDADES")) {
				f.setSituacao("Efetivo");
				funcionarioService.salvarFunc(f);
				System.out.println("Concluido Efetivo");
			} else {
				f.setSituacao("Temporario");
				funcionarioService.salvarFunc(f);
				System.out.println("Concluido Temporario");
			}
		}
		FacesMessage msm = new FacesMessage("Atualização Concluída!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public void atualizarAgencia() {
		listaTemp = new ArrayList<FuncionarioBean>();

		listaTemp = funcionarioService.listar();

		for (FuncionarioBean f : listaTemp) {
			if (f.getSituacao().equals("Efetivo")) {
				f.setAgencia("OLGBER ESPECIALIDADES");
				;
				funcionarioService.salvarFunc(f);
				System.out.println("Concluido Efetivo");
			}
		}
		FacesMessage msm = new FacesMessage("Atualização Concluída!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public void deletar() {
		listaDiaria = new ArrayList<PontoDiario>();
		listaDiaria = diarioServices.delCompensar();

		for (PontoDiario p : listaDiaria) {
			diarioServices.remove(p);
		}

		FacesMessage msm = new FacesMessage("base deletada com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public void dataBatidas() {
		listaDiaria = new ArrayList<PontoDiario>();
		listaDiaria = diarioServices.porData(dataInicial, dataFinal, funcEdit.getNome());

		if (listaDiaria.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma Batida Encontrada neste Período", ""));
		}
	}

	public void batidas() {
		listaDiaria = new ArrayList<PontoDiario>();
		listaDiaria = diarioServices.periodo(dataInicial, dataFinal);
	}

	// dia da semana
	protected String weekDay(Calendar cal) {
		cal.setTime(data);
		return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
	}

	protected String weekDayFeriado(Calendar cal) {
		cal.setTime(dataFeriado);
		return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
	}

	protected String data() {
		String d;
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		d = formato.format(hoje);
		return d;
	}

	// Abrir Atestado
	public void abrirAtestado(PontoDiario pontoDiario) {
		pontoDiarioFaltaAtestado = new PontoDiario();
		pontoDiarioFaltaAtestado = pontoDiario;
		arquivoSelecionado = null;
		arquivo = new Arquivo();
		atestado = new Atestado();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('atestado').show();");
	}

	// Fechar Atestado
	public void fecharAtestado() {
		pontoDiarioFaltaAtestado = new PontoDiario();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('atestado').hide();");
	}

	// liberar botao ok em editar batida
	public void ok(PontoDiario diario) {
		diario.setSituacao("");
	}

	// Gerar registro de treinamento
	public void regTrein() {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('treinamento').show();");
	}

	public void regTreinFechar() {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('treinamento').hide();");
	}

	// Enviar Arquivo
	public void fileUpLoaderHandler(FileUploadEvent event) throws IOException {

		arquivo = new Arquivo();
		arquivo.setNome(event.getFile().getFileName());
		arquivo.setStream(event.getFile().getInputstream());
		atestado.setArquivo(arquivo);
		arquivoSelecionado = arquivo.getNome();

	}

	// Excluir ponto
	public void preparaExclusao(PontoDiario pontoDiario) {
		pontoExclusao = pontoDiario;
	}

	public void excluir() {
		diarioServices.remove(pontoExclusao);
		listaDiaria = diarioServices.porData(dataInicial, dataFinal, funcEdit.getNome());
		FacesMessage msm = new FacesMessage("Ponto Excluído com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Baixar Atestados
	public StreamedContent baixarArquivo(Atestado atestado) throws FileNotFoundException {
		File file = diarioServices.obterArquivoDaEventos(atestado);
		FileInputStream is = new FileInputStream(file);

		return new DefaultStreamedContent(is, "application/pdf", atestado.getArquivo().getNome());
	}
	
	//Excluir Atestado
	public void preparaExclusaoAtestado(Atestado atestado) {
		atestadoExclusao = atestado;
	}
	
	public void excluirAtestado() {
		atestadoExclusao.setPontoDiario(null);
		diarioServices.removeAtestado(atestadoExclusao);
		initAtestado();
		FacesMessage msm = new FacesMessage("Atestado Excluído com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Getters e Setters
	public PontoDiario getPontoDiario() {
		return pontoDiario;
	}

	public void setPontoDiario(PontoDiario pontoDiario) {
		this.pontoDiario = pontoDiario;
	}

	public List<PontoDiario> getListaPontos() {
		return listaPontos;
	}

	public void setListaPontos(List<PontoDiario> listaPontos) {
		this.listaPontos = listaPontos;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public List<AgenciaBean> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<AgenciaBean> agencias) {
		this.agencias = agencias;
	}

	public List<FuncionarioBean> getListaTemp() {
		return listaTemp;
	}

	public void setListaTemp(List<FuncionarioBean> listaTemp) {
		this.listaTemp = listaTemp;
	}

	public List<PontoDiario> getListaDiaria() {
		return listaDiaria;
	}

	public void setListaDiaria(List<PontoDiario> listaDiaria) {
		this.listaDiaria = listaDiaria;
	}

	public PontoDiario getPontoDiarioFaltaAtestado() {
		return pontoDiarioFaltaAtestado;
	}

	public void setPontoDiarioFaltaAtestado(PontoDiario pontoDiarioFaltaAtestado) {
		this.pontoDiarioFaltaAtestado = pontoDiarioFaltaAtestado;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public FuncionarioBean getFuncEdit() {
		return funcEdit;
	}

	public void setFuncEdit(FuncionarioBean funcEdit) {
		this.funcEdit = funcEdit;
	}

	public String getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(String arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public Atestado getAtestado() {
		return atestado;
	}

	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public List<FuncionarioBean> getTempSelecionado() {
		return tempSelecionado;
	}

	public void setTempSelecionado(List<FuncionarioBean> tempSelecionado) {
		this.tempSelecionado = tempSelecionado;
	}

	public PontoDiario getPontoExclusao() {
		return pontoExclusao;
	}

	public void setPontoExclusao(PontoDiario pontoExclusao) {
		this.pontoExclusao = pontoExclusao;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(Date dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Atestado> getListaAtestados() {
		return listaAtestados;
	}

	public void setListaAtestados(List<Atestado> listaAtestados) {
		this.listaAtestados = listaAtestados;
	}

}
