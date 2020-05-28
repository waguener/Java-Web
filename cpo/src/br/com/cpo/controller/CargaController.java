package br.com.cpo.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.Arquivo;
import br.com.cpo.model.Carga;
import br.com.cpo.model.EnderecosEmail;
import br.com.cpo.model.EnvioCarga;
import br.com.cpo.model.EnvioInsumo;
import br.com.cpo.services.ArquivoServices;
import br.com.cpo.services.CargaServices;
import br.com.cpo.services.EmailService;
import br.com.cpo.services.EnderecosEmailServices;

@ManagedBean
@Controller
@Scope("session")
public class CargaController implements Serializable {

	private static final long serialVersionUID = 1961139495728555669L;

	@Autowired
	private CargaServices cargaServices;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EnderecosEmailServices enderecosEmailServices;
	@Autowired
	private ArquivoServices arquivoServices;

	private Carga carga = new Carga();
	private Carga objeto;
	private Carga cargaParalela;
	private EnderecosEmail enderecosEmail = new EnderecosEmail();
	private List<File> arquivos = new ArrayList<File>();
	private List<Carga> cargas;
	private List<Carga> cargasPesquisadas;
	private List<Carga> relatorio;
	private List<EnderecosEmail> emails;
	private List<EnderecosEmail> emailsSelecionados;
	private List<EnderecosEmail> enviarEmailSelecionado;
	private List<Arquivo> nomeArquivo;
	private Date hoje = new Date();
	private String operacao;
	private Carga imprimr;
	private Carga id;
	private Carga obj;

	private Integer numeroCarga;
	private Boolean addCarga1;
	private Boolean addCarga2;
	private Boolean addCarga3;
	private Boolean addCarga4;
	private Boolean addCarga5;
	private Boolean addCargaEnvio1;
	private Boolean addCargaEnvio2;
	private Boolean addCargaEnvio3;
	private Boolean addCargaEnvio4;
	private Boolean addCargaEnvio5;
	private Boolean botaoSalvarCarga;
	private Boolean botaoSalvarCargaEnvio;
	private Boolean addInsumo1;
	private Boolean addInsumo2;
	private Boolean addInsumo3;
	private Boolean addInsumo4;
	private Boolean addInsumo5;
	private Boolean addInsumoEnvio1;
	private Boolean addInsumoEnvio2;
	private Boolean addInsumoEnvio3;
	private Boolean addInsumoEnvio4;
	private Boolean addInsumoEnvio5;
	private Boolean botaoMenu;
	private Boolean botaoMenuEnvio;
	private Boolean botaoSalvarSaida = false;

	private String totalTempoParado;
	private String totalTempoDescarregamento;
	private String totalTempoCarregamento;
	private String totalTempoOlgber;
	private String arquivoSelecionado;
	private String recuperarArquivoEmail;
	private String arquivoNome;
	private List<File> file = new ArrayList<File>();
	private String mes;
	private String hj;
	private Date data1;
	private Date data2;
	// Métodos

	public void init() {

		cargas = cargaServices.porData(hoje);
		
		Carga id = cargaServices.ultimoId();
		carga = cargaServices.obterPorId(Long.parseLong(id.getId().toString()));
		numeroCarga = Integer.parseInt(carga.getId().toString());

		if (carga.getStatus().equals("FINALIZADO")) {

			carga.setBotaoInicioOperacao(false);
			carga.setBotaoTerminoOperacao(false);
			carga.setBotaoInicioCarregamento(false);
			carga.setBotaoExcluirEntrada1(false);
			carga.setBotaoExcluirEntrada2(false);
			carga.setBotaoExcluirEntrada3(false);
			carga.setBotaoContinuarCarga(false);
			carga.setBotaoTerminoCarregamento(false);
			carga.setBotaoCargaVazia(false);
			numeroCarga = null;
			totalTempoParado = null;
			totalTempoDescarregamento = null;
			totalTempoCarregamento = null;
			carga = new Carga();

		}

	}

	public void initParalelo() {
		
		carga.setBotaoInicioOperacao(false);
		carga.setBotaoTerminoOperacao(false);
		carga.setBotaoInicioCarregamento(false);
		carga.setBotaoExcluirEntrada1(false);
		carga.setBotaoExcluirEntrada2(false);
		carga.setBotaoExcluirEntrada3(false);
		carga.setBotaoTerminoCarregamento(false);
		carga.setBotaoCargaVazia(false);
		numeroCarga = null;
		totalTempoParado = null;
		totalTempoDescarregamento = null;
		totalTempoCarregamento = null;

	}

	public void initPesquisar() {

		data1 = null;
		data2 = null;
		cargasPesquisadas = new ArrayList<Carga>();

	}
	// Salvar

	public String salvarChegada() {
		carga.setBotaoExcluirEntrada1(true);
		carga.setBotaoInicioOperacao(true);
		carga.setStatus("CHEGADA");
		carga.setData_chegada(new Date());
		carga.setHora_chegada(new SimpleDateFormat("HH:mm").format(new Date()));
		carga.setBotaoContinuarCarga(true);
		if (carga.getTipoCarga().equals("EXTERNO")) {
			carga.setTempo_retorno(" - - - - ");
		}

		cargaServices.salvar(carga);
		carga.setBotaoCargaVazia(false);
		FacesMessage msm = new FacesMessage("Carga Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').hide();");
		pesquisarChegada();
		cargaNumero();
		return "Kanban?faces-redirect=true";
	}

	public void excluirChegada() {
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setBotaoExcluirEntrada1(false);
		carga.setBotaoInicioOperacao(false);
		carga.setStatus("CHEGADA");
		carga.setData_chegada(null);
		carga.setHora_chegada(null);
		carga.setTempo_retorno(null);

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Entrada Excluida com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public void excluirChegadaParalelo() {

		carga.setId(cargaParalela.getId());
		carga.setBotaoExcluirEntrada1(false);
		carga.setBotaoInicioOperacao(false);
		carga.setStatus("CHEGADA");
		carga.setData_chegada(null);
		carga.setHora_chegada(null);
		carga.setTempo_retorno(null);

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Entrada Excluida com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	public String salvarEdicao() {

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Carga Editada Com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "Pesquisar?faces-redirect=true";
	}

	public void salvarCargaVazia() {
		carga.setBotaoExcluirEntrada2(true);
		carga.setProdRecebido("CARGA VAZIA");
		carga.setNfRecebimento("CARGA VAZIA");
		carga.setCheckRecebimento("CARGA VAZIA");
		carga.setTemperatura("00º");
		carga.setBotaoInicioCarregamento(true);
		carga.setBotaoCargaVazia(true);
		carga.setStatus("CHEGADA CARGA VAZIA");
		carga.setData_chegada(new Date());
		carga.setHora_inicio_descarregamento("- - - -");
		carga.setHora_termino_descarregamento("- - - -");
		carga.setHora_total_descarregamento("- - - -");
		carga.setHora_chegada(new SimpleDateFormat("HH:mm").format(new Date()));

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Carga Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').hide();");

		cargaNumero();

	}

	public String salvarCargaVaziaParalelo() {

		
		carga.setBotaoExcluirEntrada2(true);
		carga.setProdRecebido("CARGA VAZIA");
		carga.setNfRecebimento("CARGA VAZIA");
		carga.setCheckRecebimento("CARGA VAZIA");
		carga.setTemperatura("00º");
		carga.setBotaoCargaVazia(true);
		carga.setStatus("CHEGADA CARGA VAZIA");
		carga.setData_chegada(new Date());
		carga.setHora_inicio_descarregamento("- - - -");
		carga.setHora_termino_descarregamento("- - - -");
		carga.setHora_total_descarregamento("- - - -");
		carga.setHora_chegada(new SimpleDateFormat("HH:mm").format(new Date()));

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Carga Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').hide();");

		cargaNumero();
		return "Kanban?faces-redirect=true";
	}

	public void salvarInicioOp() {

		carga.setBotaoInicioOperacao(false);
		carga.setBotaoTerminoOperacao(true);
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("DESCARREGANDO");
		carga.setBotaoExcluirEntrada1(false);
		carga.setHora_inicio_descarregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoParado = subtrair(carga.getHora_inicio_descarregamento(), carga.getHora_chegada());
		carga.setHora_total_parado(totalTempoParado);
		carga.setBotaoExcluirEntrada2(true);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Inicio do Descarregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		pesquisarChegada();

	}

	public void salvarInicioOpParalela() {

		carga.setId(cargaParalela.getId());
		carga.setBotaoInicioOperacao(false);
		carga.setBotaoTerminoOperacao(true);

		carga.setStatus("DESCARREGANDO");
		carga.setBotaoExcluirEntrada1(false);
		carga.setHora_inicio_descarregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoParado = subtrair(carga.getHora_inicio_descarregamento(), carga.getHora_chegada());
		carga.setHora_total_parado(totalTempoParado);
		carga.setBotaoExcluirEntrada2(true);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Inicio do Descarregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

	}

	public void excluirOperacao() {

		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("CHEGADA");
		carga.setBotaoInicioOperacao(true);
		carga.setBotaoTerminoOperacao(false);
		carga.setBotaoExcluirEntrada1(true);
		carga.setBotaoInicioCarregamento(false);
		carga.setHora_inicio_descarregamento(null);
		carga.setHora_total_parado(null);
		carga.setHora_termino_descarregamento(null);
		carga.setHora_total_descarregamento(null);
		carga.setBotaoExcluirEntrada2(false);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Operação Excluida com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}
	

	public void excluirOperacaoParalelo() {

		carga.setId(cargaParalela.getId());
		carga.setStatus("CHEGADA");
		carga.setBotaoInicioOperacao(true);
		carga.setBotaoTerminoOperacao(false);
		carga.setBotaoExcluirEntrada1(true);
		carga.setBotaoInicioCarregamento(false);
		carga.setHora_inicio_descarregamento(null);
		carga.setHora_total_parado(null);
		carga.setHora_termino_descarregamento(null);
		carga.setHora_total_descarregamento(null);
		carga.setBotaoExcluirEntrada2(false);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Operação Excluida com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	public void salvarTerminoOp() {

		carga.setBotaoInicioCarregamento(true);
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("DESCARREGADO");

		carga.setHora_termino_descarregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoDescarregamento = subtrair(carga.getHora_termino_descarregamento(),
				carga.getHora_inicio_descarregamento());
		String tempo = totalTempoDescarregamento.replace("-", "");
		carga.setHora_total_descarregamento(tempo);
		carga.setBotaoCargaVazia(false);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Termino do Descarregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

	}

	public void salvarTerminoOpParalela() {
		carga.setId(cargaParalela.getId());
		carga.setBotaoInicioCarregamento(true);

		carga.setStatus("DESCARREGADO");

		carga.setHora_termino_descarregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoDescarregamento = subtrair(carga.getHora_termino_descarregamento(),
				carga.getHora_inicio_descarregamento());
		String tempo = totalTempoDescarregamento.replace("-", "");
		carga.setHora_total_descarregamento(tempo);
		carga.setBotaoCargaVazia(false);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Termino do Descarregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

	}

	public void salvarInicioCarregamento() {
		boolean teste;

		carga.setBotaoTerminoCarregamento(true);

		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("CARREGANDO");
		carga.setBotaoExcluirEntrada2(false);
		carga.setBotaoExcluirEntrada3(true);
		carga.setHora_inicio_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		teste = carga.getBotaoCargaVazia();
		if (teste == true) {
			carga.setBotaoCargaVazia(false);
			String tempoOlgber = subtrair(carga.getHora_chegada(), carga.getHora_inicio_carregamento());
			carga.setHora_total_parado(tempoOlgber);
		}

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Carregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').hide();");

	}

	public void salvarInicioCarregamentoParalelo() {
		boolean teste;
		carga.setId(cargaParalela.getId());
		carga.setBotaoTerminoCarregamento(true);

		carga.setStatus("CARREGANDO");
		carga.setBotaoExcluirEntrada2(false);
		carga.setBotaoExcluirEntrada3(true);
		carga.setHora_inicio_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		teste = carga.getBotaoCargaVazia();
		if (teste == true) {
			carga.setBotaoCargaVazia(false);
			String tempoOlgber = subtrair(carga.getHora_chegada(), carga.getHora_inicio_carregamento());
			carga.setHora_total_parado(tempoOlgber);
		}

		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Inicio do Carregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').hide();");

	}

	public void salvarCargaEnvioVazia() {
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("SAIDA CARGA VAZIA");
		carga.setBotaoExcluirEntrada3(true);
		
		carga.setHora_termino_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		carga.setHora_total_carregamento("CARGA VAZIA");
		carga.setBotaoTerminoCarregamento(true);
		String hora = new SimpleDateFormat("HH:mm").format(new Date());
		String tempoOlgber = subtrair(hora, carga.getHora_chegada());
		String tempo = tempoOlgber.replace("-", "");
		carga.setHora_total_olgber(tempo);
		carga.setProdEnviado("CARGA VAZIA");
		cargaServices.salvar(carga);

		FacesMessage msm = new FacesMessage("Carga Vazia Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		pesquisarChegada();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').hide();");
	}
	
	public void salvarCargaEnvioVaziaParalelo() {
		
		carga.setId(cargaParalela.getId());
		carga.setStatus("SAIDA CARGA VAZIA");
		carga.setBotaoExcluirEntrada3(true);
		carga.setHora_inicio_carregamento(" - - - -");
		carga.setHora_termino_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		carga.setHora_total_carregamento("CARGA VAZIA");
		carga.setBotaoTerminoCarregamento(true);
		String hora = new SimpleDateFormat("HH:mm").format(new Date());
		String tempoOlgber = subtrair(hora, carga.getHora_chegada());
		String tempo = tempoOlgber.replace("-", "");
		carga.setHora_total_olgber(tempo);
		carga.setProdEnviado("CARGA VAZIA");
		cargaServices.salvar(carga);

		FacesMessage msm = new FacesMessage("Carga Enviada Vazia Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		pesquisarChegada();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').hide();");
	}

	public void salvarTerminoCarregamento() {

		carga.setBotaoInicioCarregamento(false);
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("CARREGADO");

		carga.setHora_termino_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoCarregamento = subtrair(carga.getHora_termino_carregamento(), carga.getHora_inicio_carregamento());
		carga.setHora_total_carregamento(totalTempoCarregamento);
		String tempoOlgber = subtrair(carga.getHora_termino_carregamento(), carga.getHora_chegada());
		String tempo = tempoOlgber.replace("-", "");
		carga.setHora_total_olgber(tempo);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Inicio do Carregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

	}

	public void salvarTerminoCarregamentoParalelo() {
		carga.setId(cargaParalela.getId());
		carga.setBotaoInicioCarregamento(false);

		carga.setStatus("CARREGADO");

		carga.setHora_termino_carregamento(new SimpleDateFormat("HH:mm").format(new Date()));
		totalTempoCarregamento = subtrair(carga.getHora_termino_carregamento(), carga.getHora_inicio_carregamento());
		carga.setHora_total_carregamento(totalTempoCarregamento);
		String tempoOlgber = subtrair(carga.getHora_termino_carregamento(), carga.getHora_chegada());
		String tempo = tempoOlgber.replace("-", "");
		carga.setHora_total_olgber(tempo);
		cargaServices.salvar(carga);
		FacesMessage msm = new FacesMessage("Inicio do Carregamento Salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

		cargas = cargaServices.porData(hoje);

	}

	public void excluirCarregamento() {

		carga.setBotaoTerminoCarregamento(false);
		carga.setBotaoInicioCarregamento(true);
		Carga id = cargaServices.ultimoId();
		carga.setBotaoExcluirEntrada2(true);
		carga.setBotaoExcluirEntrada3(false);
		carga.setId(id.getId());

		carga.setStatus("DESCARREGADO");
		carga.setHora_inicio_carregamento(null);
		carga.setHora_termino_carregamento(null);
		carga.setHora_total_carregamento(null);
		carga.setHora_total_olgber(null);
		
		cargaServices.salvar(carga);

		FacesMessage msm = new FacesMessage("Carregamento Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}
	
	public void excluirCarregamentoParalelo() {
		
		carga.setId(cargaParalela.getId());
		carga.setBotaoTerminoCarregamento(false);
		carga.setBotaoInicioCarregamento(true);
		Carga id = cargaServices.ultimoId();
		carga.setBotaoExcluirEntrada2(true);
		carga.setBotaoExcluirEntrada3(false);
		carga.setId(id.getId());

		carga.setStatus("DESCARREGADO");
		carga.setHora_inicio_carregamento(null);
		carga.setHora_termino_carregamento(null);
		carga.setHora_total_carregamento(null);
		carga.setHora_total_olgber(null);

		cargaServices.salvar(carga);

		FacesMessage msm = new FacesMessage("Carregamento Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	public String salvarSaida() {
		String hora;
		Carga id = cargaServices.ultimoId();

		carga.setId(id.getId());
		carga.setStatus("FINALIZADO");
		carga.setBotaoContinuarCarga(false);
		carga.setData_saida(hoje);
		hora = subtrair(carga.getHora_saida_carga(), carga.getHora_chegada());
		String tempo = hora.replace("-", " ");
		carga.setHora_total_olgber(tempo);
		cargaServices.salvarSaida(carga);
		System.out.println("Hora_olgber:" + hora);
		emailService.enviarEmailCarga(carga);
		carga = new Carga();
		fecharCarga();
		FacesMessage msm = new FacesMessage("Saída Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "Kanban?faces-redirect=true";

	}

	public String salvarSaidaParalelo() {
		String hora;
		carga.setId(cargaParalela.getId());
		carga.setStatus("FINALIZADO");
		carga.setBotaoContinuarCarga(false);
		carga.setHora_saida_carga(new SimpleDateFormat("HH:mm").format(new Date()));
		carga.setData_saida(hoje);
		hora = subtrair(carga.getHora_saida_carga(), carga.getHora_chegada());
		String tempo = hora.replace("-", " ");
		carga.setHora_total_olgber(tempo);
		cargaServices.salvarSaida(carga);
		System.out.println("Hora_olgber:" + hora);
		emailService.enviarEmailCarga(carga);
		carga = new Carga();
		fecharCarga();
		FacesMessage msm = new FacesMessage("Saída Salva com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "Kanban?faces-redirect=true";

	}
	
	//Calcular Saída
	
	public void calcularSaida() {
		String hora;
		
		hora = subtrair(carga.getHora_saida_carga(),carga.getHora_termino_carregamento());
		carga.setHoraTotalsaida(hora);
		botaoSalvarSaida = true;
	}

	public void enviarEmail() {
		emailService.enviarEmailCarga(carga);
	}

	// Pesquisas
	public String pesquisarChegada() {

		cargas = cargaServices.porData(hoje);
		return "Kanban?faces-redirect=true";
	}

	public void pesquisarChegadaHoje(Date hoje) {

		cargas = cargaServices.porData(hoje);

	}

	public void pesquisar() {

		cargasPesquisadas = cargaServices.listar();

	}

	public void porData() {

		cargasPesquisadas = cargaServices.entreData(data1, data2);

	}

	public void cargaNumero() {

		id = cargaServices.ultimoId();
		operacao = id.getId().toString();
		numeroCarga = Integer.parseInt(operacao);

	}

	public void baixarArquivos(Carga carga) {

		objeto = carga;
		arquivoNome = null;

		recuperarArquivoEmail = null;
		String id = carga.getId().toString();
		String nome = "";
		List<String> t = new ArrayList<String>();
		nomeArquivo = arquivoServices.buscarArquivo(id);

		for (Arquivo d : nomeArquivo) {
			t.add(d.getNome());
		}
		nome = t.toString().replaceAll("\\[", " ");
		nome = nome.replaceAll("\\]", ", ");
		arquivoNome = nome;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('baixarArquivos').show();");

	}

	public String novaCarga() {
		carga = new Carga();
		botaoSalvarCarga = false;
		numeroCarga = null;
		return "KanbanParalelo?faces-redirect=true";
	}

	public void recuperar() {

		objeto.setAssunto("Arquivos Recuperados da Carga Nº: " + objeto.getId());
		objeto.setSaudacao("");
		objeto.setCorpo("Segue em anexo os Arquivos ");

		carga.setRemetentes(recuperarArquivoEmail);

		emailService.enviarEmailCarga(objeto);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('baixarArquivos').hide();");
	}

	public void fecharBaixarArquivos() {
		carga = new Carga();
		recuperarArquivoEmail = "";
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('baixarArquivos').hide();");
	}

	// Calcular Retorno

	public String retorno() {

		String hora = new SimpleDateFormat("HH:mm").format(new Date());
		String horaAnterior = horaSaida();
		if (horaAnterior != null) {
			String total = subtrair(horaAnterior, hora);

			String ok = total.replace("-", "");
			Carga id = cargaServices.ultimoId();
			carga.setId(id.getId());
			carga.setTempo_retorno(ok);
			carga.setBotaoCalculoRetorno(false);
			cargaServices.salvar(carga);
			FacesMessage msm = new FacesMessage("Retorno Calculado com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);

		} else {
			FacesMessage msm = new FacesMessage("Não encontrado!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
		}
		return null;
	}

	public String horaSaida() {
		String id = null;
		Long ide;
		id = cargaServices.retorno(carga.getPlaca_carreta());
		if (id == null) {
			
			return null;
		} else {
			ide = Long.parseLong(id);
			obj = cargaServices.ultimaCargaLong(ide);

			String ultimaHora = obj.getHora_saida_carga();
			return ultimaHora;
		}
	}

	// Editar
	public String editar(Carga carga) {
		this.carga = carga;
		return "EditarCarga?faces-redirect=true";
	}

	// Carga Paralela

	public String paralelo(Carga carga) {
		this.carga = carga;
		cargaParalela = carga;
		numeroCarga = Integer.parseInt(carga.getId().toString());
		return "KanbanParalelo?faces-redirect=true";
	}

	// Enum

	public EnvioCarga[] getEnvioCarga() {
		return EnvioCarga.values();
	}

	public EnvioInsumo[] getEnvioInsumo() {
		return EnvioInsumo.values();
	}
	// Incluir Carga

	public void abrirCarga() {

		regraCalculoRetorno();
		botaoMenu = true;
		addCarga1 = false;
		addCarga2 = false;
		addCarga3 = false;
		addCarga4 = false;
		addCarga5 = false;
		addInsumo1 = false;
		addInsumo2 = false;
		addInsumo3 = false;
		addInsumo4 = false;
		addInsumo5 = false;
		botaoSalvarCarga = false;
		carga.setCheckRecebimento(null);
		carga.setNfRecebimento(null);
		carga.setTemperatura(null);
		carga.setProdRecebido(null);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').show();");

	}

	public void addCarga1() {
		addCarga1 = true;
		botaoSalvarCarga = true;
		botaoMenu = false;
	}

	public void removerCarga1() {
		addCarga1 = false;
		botaoSalvarCarga = false;
		botaoMenu = true;
		carga.setCheckRecebimento(null);

		carga.setNfRecebimento(null);
		carga.setTemperatura(null);
		carga.setProdRecebido(null);
	}

	public void addCarga2() {
		addCarga2 = true;

	}

	public void removerCarga2() {
		addCarga2 = false;
		carga.setCheckRecebimento2(null);

		carga.setNfRecebimento2(null);
		carga.setTemperatura2(null);
		carga.setProdRecebido2(null);
	}

	public void addCarga3() {
		addCarga3 = true;

	}

	public void removerCarga3() {
		addCarga3 = false;
		carga.setCheckRecebimento3(null);

		carga.setNfRecebimento3(null);
		carga.setTemperatura3(null);
		carga.setProdRecebido3(null);
	}

	public void addCarga4() {
		addCarga4 = true;

	}

	public void removerCarga4() {
		addCarga4 = false;
		carga.setCheckRecebimento4(null);

		carga.setNfRecebimento4(null);
		carga.setTemperatura4(null);
		carga.setProdRecebido4(null);
	}

	public void addCarga5() {
		addCarga5 = true;

	}

	public void removerCarga5() {
		addCarga5 = false;
		carga.setCheckRecebimento5(null);

		carga.setNfRecebimento5(null);
		carga.setTemperatura5(null);
		carga.setProdRecebido5(null);
	}

	public void fecharCarga() {

		removerCarga1();
		removerCarga2();
		removerCarga3();
		removerCarga4();
		removerCarga5();

		removerInsumo1();
		removerInsumo2();
		removerInsumo3();
		removerInsumo4();
		removerInsumo5();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirProduto').hide();");
	}

	// Incluir Insumo

	public void addInsumo1() {
		addInsumo1 = true;
		botaoSalvarCarga = true;
		botaoMenu = false;
	}

	public void removerInsumo1() {
		addInsumo1 = false;
		botaoSalvarCarga = true;
		botaoMenu = true;
		carga.setInsumoRecebido(null);
		carga.setNfInsumoRecebido(null);
	}

	public void addInsumo2() {
		addInsumo2 = true;

	}

	public void removerInsumo2() {
		addInsumo2 = false;
		carga.setInsumoRecebido2(null);
		carga.setNfInsumoRecebido2(null);
	}

	public void addInsumo3() {
		addInsumo3 = true;

	}

	public void removerInsumo3() {
		addInsumo3 = false;
		carga.setInsumoRecebido3(null);
		carga.setNfInsumoRecebido3(null);
	}

	public void addInsumo4() {
		addInsumo4 = true;

	}

	public void removerInsumo4() {
		addInsumo4 = false;
		carga.setInsumoRecebido4(null);
		carga.setNfInsumoRecebido4(null);
	}

	public void addInsumo5() {
		addInsumo5 = true;

	}

	public void removerInsumo5() {
		addInsumo5 = false;
		carga.setInsumoRecebido5(null);
		carga.setNfInsumoRecebido5(null);
	}

	// IncluirEnvio
	public void abrirEnvio() {

		addCargaEnvio1 = false;
		addCargaEnvio2 = false;
		addCargaEnvio3 = false;
		addCargaEnvio4 = false;
		addCargaEnvio5 = false;
		botaoMenuEnvio = true;
		addInsumoEnvio1 = false;
		addInsumoEnvio2 = false;
		addInsumoEnvio3 = false;
		addInsumoEnvio4 = false;
		addInsumoEnvio5 = false;
		botaoSalvarCargaEnvio = false;
		removerCargaEnvio1();
		removerCargaEnvio2();
		removerCargaEnvio3();
		removerCargaEnvio4();
		removerCargaEnvio5();
		
		removerInsumoEnvio1();
		removerInsumoEnvio2();
		removerInsumoEnvio3();
		removerInsumoEnvio4();
		removerInsumoEnvio5();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').show();");
	}

	public void abrirCargaVaziaEnvio() {

		addCargaEnvio1 = false;
		addCargaEnvio2 = false;
		addCargaEnvio3 = false;
		addCargaEnvio4 = false;
		addCargaEnvio5 = false;
		botaoMenuEnvio = true;
		addInsumoEnvio1 = false;
		addInsumoEnvio2 = false;
		addInsumoEnvio3 = false;
		addInsumoEnvio4 = false;
		addInsumoEnvio5 = false;
		botaoSalvarCargaEnvio = false;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').show();");
	}

	public void addCargaEnvio1() {
		addCargaEnvio1 = true;
		botaoSalvarCargaEnvio = true;
		botaoMenuEnvio = false;
	}

	public void removerCargaEnvio1() {
		addCargaEnvio1 = false;
		botaoSalvarCargaEnvio = false;
		botaoMenuEnvio = true;
		carga.setProdEnviado(null);
		carga.setCheckEnviado(null);
		carga.setNfExpedicao(null);
		carga.setTemperaturaEnvio(null);
		carga.setTotalEnviado(null);
		carga.setTotalPallets(null);
		carga.setTipo(null);
		carga.setObs(null);
	}

	public void addCargaEnvio2() {
		addCargaEnvio2 = true;

	}

	public void removerCargaEnvio2() {
		addCargaEnvio2 = false;
		carga.setProdEnviado2(null);
		carga.setCheckEnviado2(null);
		carga.setNfExpedicao2(null);
		carga.setTemperaturaEnvio2(null);
		carga.setTotalEnviado2(null);
		carga.setTotalPallets2(null);
		carga.setTipo2(null);
		carga.setObs2(null);

	}

	public void addCargaEnvio3() {
		addCargaEnvio3 = true;

	}

	public void removerCargaEnvio3() {
		addCargaEnvio3 = false;
		carga.setProdEnviado3(null);
		carga.setCheckEnviado3(null);
		carga.setNfExpedicao3(null);
		carga.setTemperaturaEnvio3(null);
		carga.setTotalEnviado3(null);
		carga.setTotalPallets3(null);
		carga.setTipo3(null);
		carga.setObs3(null);
	}

	public void addCargaEnvio4() {
		addCargaEnvio4 = true;

	}

	public void removerCargaEnvio4() {
		addCargaEnvio4 = false;
		carga.setProdEnviado4(null);
		carga.setCheckEnviado4(null);
		carga.setNfExpedicao4(null);
		carga.setTemperaturaEnvio4(null);
		carga.setTotalEnviado4(null);
		carga.setTotalPallets4(null);
		carga.setTipo4(null);
		carga.setObs4(null);
	}

	public void addCargaEnvio5() {
		addCargaEnvio5 = true;

	}

	public void removerCargaEnvio5() {
		addCargaEnvio5 = false;
		carga.setProdEnviado5(null);
		carga.setCheckEnviado5(null);
		carga.setNfExpedicao5(null);
		carga.setTemperaturaEnvio5(null);
		carga.setTotalEnviado5(null);
		carga.setTotalPallets5(null);
		carga.setTipo5(null);
		carga.setObs5(null);
	}

	public void fecharCargaEnvio() {
		removerCargaEnvio1();
		removerCargaEnvio2();
		removerCargaEnvio3();
		removerCargaEnvio4();
		removerCargaEnvio5();

		removerInsumoEnvio1();
		removerInsumoEnvio2();
		removerInsumoEnvio3();
		removerInsumoEnvio4();
		removerInsumoEnvio5();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('incluirEnvio').hide();");
	}
	// Incluir Insumo

	public void addInsumoEnvio1() {
		addInsumoEnvio1 = true;
		botaoSalvarCargaEnvio = true;
		botaoMenuEnvio = false;
	}

	public void removerInsumoEnvio1() {
		addInsumoEnvio1 = false;
		botaoSalvarCargaEnvio = true;
		botaoMenuEnvio = true;
		carga.setProdInsumoEnviado(null);
		carga.setNfExpedicaoInsumo(null);
		carga.setTotalInsumoEnviado(null);
		carga.setTipoInsumo(null);
		carga.setObsInsumo(null);
	}

	public void addInsumoEnvio2() {
		addInsumoEnvio2 = true;

	}

	public void removerInsumoEnvio2() {
		addInsumoEnvio2 = false;
		carga.setProdInsumoEnviado2(null);
		carga.setNfExpedicaoInsumo2(null);
		carga.setTotalInsumoEnviado2(null);
		carga.setTipoInsumo2(null);
		carga.setObsInsumo2(null);
	}

	public void addInsumoEnvio3() {
		addInsumoEnvio3 = true;

	}

	public void removerInsumoEnvio3() {
		addInsumoEnvio3 = false;
		carga.setProdInsumoEnviado3(null);
		carga.setNfExpedicaoInsumo3(null);
		carga.setTotalInsumoEnviado3(null);
		carga.setTipoInsumo3(null);
		carga.setObsInsumo3(null);
	}

	public void addInsumoEnvio4() {
		addInsumoEnvio4 = true;

	}

	public void removerInsumoEnvio4() {
		addInsumoEnvio4 = false;
		carga.setProdInsumoEnviado4(null);
		carga.setNfExpedicaoInsumo4(null);
		carga.setTotalInsumoEnviado4(null);
		carga.setTipoInsumo4(null);
		carga.setObsInsumo4(null);
	}

	public void addInsumoEnvio5() {
		addInsumoEnvio5 = true;

	}

	public void removerInsumoEnvio5() {
		addInsumoEnvio5 = false;
		carga.setProdInsumoEnviado5(null);
		carga.setNfExpedicaoInsumo5(null);
		carga.setTotalInsumoEnviado5(null);
		carga.setTipoInsumo5(null);
		carga.setObsInsumo5(null);
	}

	// var Carga

	public void verCarga(Carga carga) {
		this.carga = carga;
		imprimr = carga;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verCarga').show();");
	}

	public void fecharVerCarga() {

		Carga id = cargaServices.ultimoId();

		carga = cargaServices.obterPorId(Long.parseLong(id.getId().toString()));

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verCarga').hide();");
	}

	// Finalizar Carga

	public void finalizarCarga() {
		botaoSalvarSaida = false;
		arquivoSelecionado = null;
		carga.setAssunto("Check List de Faturamento / Carregamento");
		carga.setSaudacao("Bom Dia,");
		carga.setCorpo(
				"Segue em anexo Check List de Faturamento, foto do lote + data de validade \n e NFs referente ao carregamento ");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('finalizarCarga').show();");
	}

	// Carregar e-mails

	public void carregarEmails() {

		emails = enderecosEmailServices.listar();
	}

	public void fecharFinalizarCarga() {
		botaoSalvarSaida = false;
		emailsSelecionados = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('finalizarCarga').hide();");
	}

	// Enviar Arquivo
	public void fileUpLoaderHandler(FileUploadEvent event) throws IOException {
		Arquivo arquivo = new Arquivo();

		try {
			arquivo.setNome(event.getFile().getFileName());
			arquivo.setStream(event.getFile().getInputstream());
			arquivo.setNumCarga(carga.getId().toString());
			arquivoServices.inserirArquivoNoSistema(arquivo, "carga/");

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
			System.out.println("Erro - " + e);
		}

	}
	// Subtrair Tempo

	public String subtrair(String hora1, String hora2) {

		String[] arrayFirst = hora1.split(":");
		int h = Integer.parseInt(arrayFirst[0]);
		int m = Integer.parseInt(arrayFirst[1]);

		Period primeiro = new Period(h, m, 0, 0);

		String[] arraySecond = hora2.split(":");
		int h2 = Integer.parseInt(arraySecond[0]);
		int m2 = Integer.parseInt(arraySecond[1]);
		Period segundo = new Period(h2, m2, 0, 0);

		primeiro = primeiro.minus(segundo);

		primeiro = primeiro.normalizedStandard(PeriodType.time());

		PeriodFormatter pf = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours()
				.appendSeparator(":").appendMinutes().toFormatter();
		return primeiro.toString(pf);
	}

	// E-mails selecionados

	public void selecionados() {

		String emailteste = "";

		List<String> t = new ArrayList<String>();

		for (EnderecosEmail email : emailsSelecionados) {
			t.add(email.getEmail());

		}
		emailteste = t.toString().replaceAll("\\[", "");
		emailteste = emailteste.replaceAll("\\]", "");

		carga.setRemetentes(emailteste);

	}

	// Mes Atual
	public void mes() {

		@SuppressWarnings("deprecation")
		int dt = hoje.getMonth();
		mes = NomeDoMes(dt, 0);
		hj = (new SimpleDateFormat("dd/MM/YYYY").format(hoje));
		String data = hj;
		mes = mes + " - " + data;

		System.out.println("Mes - " + mes);

	}

	public static String NomeDoMes(int i, int tipo) {
		String mes[] = { "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO",
				"OUTUBRO", "NOVEMBRO", "DEZEMBRO" };
		if (tipo == 0)
			return (mes[i]); // extenso
		else
			return (mes[i].substring(0, 3)); // abreviado
	}

	// Regras do Sistema

	public boolean regraCalculoRetorno() {

		if (carga.getTipoCarga().equals("INTERNO")) {
			carga.setBotaoCalculoRetorno(true);

		} else {
			carga.setBotaoCalculoRetorno(false);
		}
		return carga.getBotaoCalculoRetorno();
	}

	// Getters e Setters

	public Date getHoje() {
		return hoje;
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public Integer getNumeroCarga() {
		return numeroCarga;
	}

	public void setNumeroCarga(Integer numeroCarga) {
		this.numeroCarga = numeroCarga;
	}

	public Boolean getAddCarga2() {
		return addCarga2;
	}

	public void setAddCarga2(Boolean addCarga2) {
		this.addCarga2 = addCarga2;
	}

	public Boolean getAddCarga1() {
		return addCarga1;
	}

	public void setAddCarga1(Boolean addCarga1) {
		this.addCarga1 = addCarga1;
	}

	public Boolean getAddInsumo1() {
		return addInsumo1;
	}

	public void setAddInsumo1(Boolean addInsumo1) {
		this.addInsumo1 = addInsumo1;
	}

	public Boolean getBotaoSalvarCarga() {
		return botaoSalvarCarga;
	}

	public void setBotaoSalvarCarga(Boolean botaoSalvarCarga) {
		this.botaoSalvarCarga = botaoSalvarCarga;
	}

	public Boolean getAddCarga3() {
		return addCarga3;
	}

	public void setAddCarga3(Boolean addCarga3) {
		this.addCarga3 = addCarga3;
	}

	public Boolean getAddCarga4() {
		return addCarga4;
	}

	public void setAddCarga4(Boolean addCarga4) {
		this.addCarga4 = addCarga4;
	}

	public Boolean getAddCarga5() {
		return addCarga5;
	}

	public void setAddCarga5(Boolean addCarga5) {
		this.addCarga5 = addCarga5;
	}

	public Boolean getBotaoMenu() {
		return botaoMenu;
	}

	public void setBotaoMenu(Boolean botaoMenu) {
		this.botaoMenu = botaoMenu;
	}

	public CargaServices getCargaServices() {
		return cargaServices;
	}

	public void setCargaServices(CargaServices cargaServices) {
		this.cargaServices = cargaServices;
	}

	public Boolean getAddInsumo2() {
		return addInsumo2;
	}

	public void setAddInsumo2(Boolean addInsumo2) {
		this.addInsumo2 = addInsumo2;
	}

	public Boolean getAddInsumo3() {
		return addInsumo3;
	}

	public void setAddInsumo3(Boolean addInsumo3) {
		this.addInsumo3 = addInsumo3;
	}

	public Boolean getAddInsumo4() {
		return addInsumo4;
	}

	public void setAddInsumo4(Boolean addInsumo4) {
		this.addInsumo4 = addInsumo4;
	}

	public Boolean getAddInsumo5() {
		return addInsumo5;
	}

	public void setAddInsumo5(Boolean addInsumo5) {
		this.addInsumo5 = addInsumo5;
	}

	public String getTotalTempoParado() {
		return totalTempoParado;
	}

	public void setTotalTempoParado(String totalTempoParado) {
		this.totalTempoParado = totalTempoParado;
	}

	public String getTotalTempoDescarregamento() {
		return totalTempoDescarregamento;
	}

	public void setTotalTempoDescarregamento(String totalTempoDescarregamento) {
		this.totalTempoDescarregamento = totalTempoDescarregamento;
	}

	public String getTotalTempoCarregamento() {
		return totalTempoCarregamento;
	}

	public void setTotalTempoCarregamento(String totalTempoCarregamento) {
		this.totalTempoCarregamento = totalTempoCarregamento;
	}

	public String getTotalTempoOlgber() {
		return totalTempoOlgber;
	}

	public void setTotalTempoOlgber(String totalTempoOlgber) {
		this.totalTempoOlgber = totalTempoOlgber;
	}

	public Boolean getAddCargaEnvio1() {
		return addCargaEnvio1;
	}

	public void setAddCargaEnvio1(Boolean addCargaEnvio1) {
		this.addCargaEnvio1 = addCargaEnvio1;
	}

	public Boolean getAddCargaEnvio2() {
		return addCargaEnvio2;
	}

	public void setAddCargaEnvio2(Boolean addCargaEnvio2) {
		this.addCargaEnvio2 = addCargaEnvio2;
	}

	public Boolean getAddCargaEnvio3() {
		return addCargaEnvio3;
	}

	public void setAddCargaEnvio3(Boolean addCargaEnvio3) {
		this.addCargaEnvio3 = addCargaEnvio3;
	}

	public Boolean getAddCargaEnvio4() {
		return addCargaEnvio4;
	}

	public void setAddCargaEnvio4(Boolean addCargaEnvio4) {
		this.addCargaEnvio4 = addCargaEnvio4;
	}

	public Boolean getAddCargaEnvio5() {
		return addCargaEnvio5;
	}

	public void setAddCargaEnvio5(Boolean addCargaEnvio5) {
		this.addCargaEnvio5 = addCargaEnvio5;
	}

	public Boolean getAddInsumoEnvio1() {
		return addInsumoEnvio1;
	}

	public void setAddInsumoEnvio1(Boolean addInsumoEnvio1) {
		this.addInsumoEnvio1 = addInsumoEnvio1;
	}

	public Boolean getAddInsumoEnvio2() {
		return addInsumoEnvio2;
	}

	public void setAddInsumoEnvio2(Boolean addInsumoEnvio2) {
		this.addInsumoEnvio2 = addInsumoEnvio2;
	}

	public Boolean getAddInsumoEnvio3() {
		return addInsumoEnvio3;
	}

	public void setAddInsumoEnvio3(Boolean addInsumoEnvio3) {
		this.addInsumoEnvio3 = addInsumoEnvio3;
	}

	public Boolean getAddInsumoEnvio4() {
		return addInsumoEnvio4;
	}

	public void setAddInsumoEnvio4(Boolean addInsumoEnvio4) {
		this.addInsumoEnvio4 = addInsumoEnvio4;
	}

	public Boolean getAddInsumoEnvio5() {
		return addInsumoEnvio5;
	}

	public void setAddInsumoEnvio5(Boolean addInsumoEnvio5) {
		this.addInsumoEnvio5 = addInsumoEnvio5;
	}

	public Boolean getBotaoSalvarCargaEnvio() {
		return botaoSalvarCargaEnvio;
	}

	public void setBotaoSalvarCargaEnvio(Boolean botaoSalvarCargaEnvio) {
		this.botaoSalvarCargaEnvio = botaoSalvarCargaEnvio;
	}

	public Boolean getBotaoMenuEnvio() {
		return botaoMenuEnvio;
	}

	public void setBotaoMenuEnvio(Boolean botaoMenuEnvio) {
		this.botaoMenuEnvio = botaoMenuEnvio;
	}

	public Carga getImprimr() {
		return imprimr;
	}

	public void setImprimr(Carga imprimr) {
		this.imprimr = imprimr;
	}

	public String getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(String arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public List<EnderecosEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<EnderecosEmail> emails) {
		this.emails = emails;
	}

	public EnderecosEmail getEnderecosEmail() {
		return enderecosEmail;
	}

	public void setEnderecosEmail(EnderecosEmail enderecosEmail) {
		this.enderecosEmail = enderecosEmail;
	}

	public List<EnderecosEmail> getEnviarEmailSelecionado() {
		return enviarEmailSelecionado;
	}

	public void setEnviarEmailSelecionado(List<EnderecosEmail> enviarEmailSelecionado) {
		this.enviarEmailSelecionado = enviarEmailSelecionado;
	}

	public List<EnderecosEmail> getEmailsSelecionados() {
		return emailsSelecionados;
	}

	public void setEmailsSelecionados(List<EnderecosEmail> emailsSelecionados) {
		this.emailsSelecionados = emailsSelecionados;
	}

	public List<File> getFile() {
		return file;
	}

	public List<File> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<File> arquivos) {
		this.arquivos = arquivos;
	}

	public String getRecuperarArquivoEmail() {
		return recuperarArquivoEmail;
	}

	public void setRecuperarArquivoEmail(String recuperarArquivoEmail) {
		this.recuperarArquivoEmail = recuperarArquivoEmail;
	}

	public List<Arquivo> getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(List<Arquivo> nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getArquivoNome() {
		return arquivoNome;
	}

	public void setArquivoNome(String arquivoNome) {
		this.arquivoNome = arquivoNome;
	}

	public List<Carga> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<Carga> relatorio) {
		this.relatorio = relatorio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getHj() {
		return hj;
	}

	public void setHj(String hj) {
		this.hj = hj;
	}

	public List<Carga> getCargasPesquisadas() {
		return cargasPesquisadas;
	}

	public void setCargasPesquisadas(List<Carga> cargasPesquisadas) {
		this.cargasPesquisadas = cargasPesquisadas;
	}

	public Date getData1() {
		return data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public Date getData2() {
		return data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}

	public Carga getCargaParalela() {
		return cargaParalela;
	}

	public void setCargaParalela(Carga cargaParalela) {
		this.cargaParalela = cargaParalela;
	}

	public Boolean getBotaoSalvarSaida() {
		return botaoSalvarSaida;
	}

	public void setBotaoSalvarSaida(Boolean botaoSalvarSaida) {
		this.botaoSalvarSaida = botaoSalvarSaida;
	}

	
}
