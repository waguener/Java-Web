package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.DevolucaoBean;
import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.Bean.EpiBean;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.Bean.SaldoMovBean;
import br.com.sistemaepi.Util.GerarProtocolo;
import br.com.sistemaepi.service.DevolucaoService;
import br.com.sistemaepi.service.EntregaService;
import br.com.sistemaepi.service.EpiService;
import br.com.sistemaepi.service.FuncionarioService;

@ManagedBean
@Controller
@Scope
public class SistemaControllerEntrega implements Serializable{
	
	
	private static final long serialVersionUID = 8727663908668486088L;
	
	@Autowired
	private EntregaService entregaService;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private EpiService epiService;
	@Autowired
	private DevolucaoService devolucaoService;
	
	//CLASSES
	private EntregaBean estatisticaFunc;
	private EntregaBean entrega = new EntregaBean(GerarProtocolo.gerarProtocolo());
	private EntregaBean entregaExclusao;
	private EntregaBean entregaDialog;
	private DevolucaoBean devolucaoBean = new DevolucaoBean();
	private DevolucaoBean excluirDevolucao;
	private SaldoMovBean saldoMov = new SaldoMovBean();
	private EpiBean epi = new EpiBean();
	private FuncionarioBean colaborador;
	
	// LISTAS 
	
	private List<EntregaBean> listaEstatistica;
	private List<EntregaBean> listaEntrega;
	private List<EntregaBean> funcFiltrados;
	private List<EntregaBean> entregasFiltradas;
	private List<EntregaBean>linhas;
	private List<EntregaBean> nomeEntrega;
	private List<DevolucaoBean>linhasDev;
	private List<EntregaBean> entregas;
	private List<DevolucaoBean> devolucoes;
	private List<EntregaBean> todos;
	private List<EntregaBean> teste;
	private List<EntregaBean> EntregaNome;
	private List<FuncionarioBean> funcionarios;	
	private List<FuncionarioBean> funcionariosFiltrados;
	private List<EpiBean> epis;	
	private List<EpiBean> episFiltrados;
	
	
	//OBJETOS
	private String estatisticaNome;
	private EpiBean qtdEpi;
	private String func;
	private String descricao;	
	private String protocolo;
	private String tipo;
	private String nome;
	private Integer epiQtd;	
	private Integer qtdEntrega;
	private Integer result;	
	private Long idFunc;
	private int dataTotal;
	private Date data;
	private String devNome;
	private Boolean testeLogico;
	private Boolean testeEntrega;
	private Integer resultado;
	private Integer excluirSaldo;
	private Integer valorEdit;
	private Integer novoValor;
	private Date hoje = new Date();
	private String name;
	private String nomeDevo;
	private String nomeFuncEstat;
	private String dataFormatada;
	// Métodos

	public void init() {
		
		
		funcionarios = funcionarioService.listar();
		epis = epiService.listarEpi();
		entregaDialog = entrega;
	}
	
	public void novo() {
		entrega = new EntregaBean(GerarProtocolo.gerarProtocolo());
		epiQtd = 0;
		epi = new EpiBean();
	//	return "Entrega?faces-redirect=true";
	
	}
	
	public String checarLinha() {
		if(linhas.equals(null)) {
			FacesMessage msm = new FacesMessage("Selecione uma ou mais linhas da tabela para imprimir!!");
			FacesContext.getCurrentInstance().addMessage(null,msm);
			return null;
		}
		return null;
	}
	
	public void novaImpressao() {
		entrega = new EntregaBean();
		entregaDialog = entrega;
	}
	public String novo2() {
		entrega = new EntregaBean();
		listaEntrega = entregaService.listar();
		entrega = new EntregaBean(GerarProtocolo.gerarProtocolo());
		entregaDialog = entrega;
		
		return "Entrega?faces-redirect=true";
	
	}
	
	public String novo3() {
		entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		entrega = new EntregaBean(GerarProtocolo.gerarProtocolo());
		entregaDialog = entrega;
		
		return "Home?faces-redirect=true";
	
	}
	public void novaLista() {
		
		EntregaNome = new ArrayList<EntregaBean>();
		nomeEntrega = new ArrayList<EntregaBean>();
		name = null;
		func = null;
	}
	
	public void limparSelecao() {
		this.linhas = null;
		linhas = new ArrayList<EntregaBean>();
	}	
	
	public void limparDevolucao() {
		this.linhasDev = null;
		linhasDev = new ArrayList<DevolucaoBean>();
	}
	public String cadastrar() {
		Long id;
		if(entrega.getEpi().getQuantidadeTotal() < entrega.getQuantidade() ) {
			FacesMessage msm = new FacesMessage("Não há saldo no estoque!!");
			FacesContext.getCurrentInstance().addMessage(null,msm);
						
			return null;
		
		}else {
			entrega.setSituacao("Válido");
			entrega.setDevolucao("Em uso");	
			entrega.setStatus("Entrega");
			entrega.setQtdEntregas(1);
			entrega.setHora(new SimpleDateFormat("HH:mm").format(new Date()));						
			calcularEstoque();	
			entregaService.salvar(entrega);
			entregaDialog = entrega;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('imprimirDialog').show();");
			
			
		}
		entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		epiQtd = 0;
		return null;
										
	}
	
	private void calcularEstoque() {
		epiQtd = entrega.getEpi().getQuantidadeTotal();		
		qtdEntrega = entrega.getQuantidade();
		result = epiQtd - qtdEntrega;
		
		epi.setId(entrega.getEpi().getId());
		epi.setCa(entrega.getEpi().getCa());
		epi.setData(entrega.getEpi().getData());
		epi.setDescricao(entrega.getEpi().getDescricao());
		epi.setLote(entrega.getEpi().getLote());
		epi.setStatus(entrega.getEpi().getStatus());
		epi.setValorTotal(entrega.getEpi().getValorTotal());
		epi.setValorUnitario(entrega.getEpi().getValorUnitario());
		epi.setQuantidadeTotal(result);
		
		epiService.salvarEpi(epi);
	}	
	
	
	
	
	public String cadastrar2() {
		novoValor = (entrega.getQuantidade() - valorEdit) + epi.getQuantidadeTotal() ;
		epi.setQuantidadeTotal(novoValor);
		epiService.salvarEpi(epi);
		entrega.setHora(new SimpleDateFormat("HH:mm").format(new Date()));
		entregaService.salvar(entrega);		
		FacesMessage msm = new FacesMessage("Cadastro efetuado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		
		
		return "ListaEntrega?faces-redirect=true";
	}
	public String cadastrar3() {
		
		if(entrega.getQuantidade() < devolucaoBean.getQtdDevolvida()) {
			
			FacesMessage msm = new FacesMessage("Cheque a quantidade de itens devolvidos!!");
			FacesContext.getCurrentInstance().addMessage(null,msm);
			RequestContext.getCurrentInstance().update("");
			return null;
			
			
		}else if (tipo.equals("Reuso")){
			{
				atualizarEpiDevolucao();
				
				entregaService.salvar(entrega);		
				salvarDevolucao();	
				FacesMessage msm = new FacesMessage("Devolução efetuado com sucesso!!");
				FacesContext.getCurrentInstance().addMessage(null, msm);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				entrega = new EntregaBean();
				devolucaoBean = new DevolucaoBean();
				funcFiltrados = entregaService.porData(hoje);
				nomeEntrega = null;
				tipo = null;
			}
		}else {
		
		atualizarEntrega();
		entregaService.salvar(entrega);		
		salvarDevolucao();	
		FacesMessage msm = new FacesMessage("Devolução efetuado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		entrega = new EntregaBean();
		devolucaoBean = new DevolucaoBean();
		funcFiltrados = entregaService.porData(hoje);
		nomeEntrega = null;
		tipo = null;
		}
		return "Devolucao?faces-redirect=true";	
		
	}
	
	public void atualizarEntrega() {
		resultado = entrega.getQuantidade() - devolucaoBean.getQtdDevolvida();				
		entrega.setQuantidade(resultado);
	}
	
	public void salvarDevolucao() {
		devolucaoBean.setNome(entrega.getFuncionario().getNome());
		devolucaoBean.setCpf(entrega.getFuncionario().getCpf());
		devolucaoBean.setEpi(entrega.getEpi().getDescricao());
		devolucaoBean.setCa(entrega.getEpi().getCa());
		devolucaoBean.setLote(entrega.getEpi().getLote());
		devolucaoBean.setDataEntrega(entrega.getData());
		devolucaoBean.setHoraEntrega(entrega.getHora());
		devolucaoBean.setQuantidadeEntregada(entrega.getQuantidade());
		devolucaoBean.setProtocoloEntrega(entrega.getProtocolo());
		devolucaoBean.setHoraDevolucao(new SimpleDateFormat("HH:mm").format(new Date()));
		devolucaoBean.setEstado("Usado");
		devolucaoBean.setStatus(tipo);	
		dataTotal = diasUso();
		devolucaoBean.setTotalDias (dataTotal);		
		devolucaoService.salvar(devolucaoBean);
		System.out.println(dataTotal);
	}
	
	public int diasUso() {
		LocalDate dataInicial = entrega.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataFinal = devolucaoBean.getDataDevolucao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Long periodo = Duration.between(dataInicial.atStartOfDay(), dataFinal.atStartOfDay()).toDays();		
		int dias = Integer.valueOf(periodo.toString());
		return dias;
		
	}
	public void atualizarEpiDevolucao() {
		Long id = entrega.getEpi().getId();
		int soma = devolucaoBean.getQtdDevolvida() + entrega.getEpi().getQuantidadeTotal();
		epi.setId(id);
		epi.setQuantidadeTotal(soma);
		epi.setCa(entrega.getEpi().getCa());
		epi.setData(entrega.getData());
		epi.setDescricao(entrega.getEpi().getDescricao());
		epi.setLote(entrega.getEpi().getLote());
		epi.setStatus("Ativo");
		epi.setValorTotal(entrega.getEpi().getValorTotal());
		epi.setValorUnitario(entrega.getEpi().getValorUnitario());
		epiService.salvarEpi(epi);
	}
	
	public void devolucaoNome() {
		
		funcFiltrados = entregaService.entregaFeitaNome(devNome);
	}
	
	public void novaDevolucao() {
		Date dia = new Date();
		funcFiltrados = entregaService.porData(dia);
		devNome = null;
	}
	
	public String atualizar(EntregaBean entrega) {
		this.entrega = entrega;
		devolucaoBean = new DevolucaoBean();
		return "RegistrarDevolucao?faces-redirect=true";
		
	}
	
	public void prepararExcluirEntrega(EntregaBean entrega) {
		entregaExclusao = entrega;
	}

	public void ExcluirEntrega() {
		atualizarEpi();
		entregaService.excluir(entregaExclusao);
		EntregaNome = entregaService.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	
	public void atualizarEpi() {
		excluirSaldo = entregaExclusao.getEpi().getQuantidadeTotal() + entregaExclusao.getQuantidade();
		Long id = entregaExclusao.getEpi().getId();
		epi.setId(id);
		epi.setCa(entregaExclusao.getEpi().getCa());
		epi.setData(entregaExclusao.getEpi().getData());
		epi.setDescricao(entregaExclusao.getEpi().getDescricao());
		epi.setLote(entregaExclusao.getEpi().getLote());
		epi.setStatus(entregaExclusao.getEpi().getStatus());
		epi.setValorTotal(entregaExclusao.getEpi().getValorTotal());
		epi.setValorUnitario(entregaExclusao.getEpi().getValorUnitario());
		epi.setQuantidadeTotal(excluirSaldo);
		
		epiService.salvarEpi(epi);
	}

	public String editarEntrega(EntregaBean entrega) {
		this.entrega = entrega;
		valorEdit = 0;
		return "EntregaEdit?faces-redirect=true";
	}
	
	public String entregaRelatorio(EntregaBean entrega) {
		this.entrega = entrega;
		return "EntregaCanhoto?faces-redirect=true";
	}
	
	
	public String pesquisaEntrega() {
		this.entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		return "ListaEntrega?faces-redirect=true";
	}
	
	public String pesquisaControle() {
		this.entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		return "Controle?faces-redirect=true";
	}
	
	
	public String cancelarRelatorio() {
		this.entrega = new EntregaBean();
		EntregaNome = entregaService.listar();
		return "ListaEntrega?faces-redirect=true";
	}
	public String cancelarRegistro() { 
		this.entrega = new EntregaBean();
		funcFiltrados = entregaService.peloNome();
		return "Devolucao?faces-redirect=true";
	}
	public void iniciar() { 
		this.entrega = new EntregaBean();
		funcFiltrados = entregaService.peloNome();
		
	}
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 380);
		funcionariosFiltrados = new ArrayList<FuncionarioBean>();
		RequestContext.getCurrentInstance().openDialog("BuscarFuncionario", opcoes, null);

	}
	
	public void novaBusca() {
		nome = null;
		funcFiltrados = new ArrayList<>();
	}
	
	
	// Pesquisa e seleção de funcionario

	public void pesquisar() {
		funcionariosFiltrados = funcionarioService.porNome(nome);
		System.out.println("" + funcionariosFiltrados);
	}
	
	public void pesquisa() {
		this.funcFiltrados = new ArrayList<EntregaBean>();
		funcFiltrados = entregaService.entrega();
		
	}
	public void listaEntregaNome() {
		EntregaNome = entregaService.entregaFeitaNome(func);
	}
	public void listaNome() {
		nomeEntrega =  entregaService.entregaPeloFunc(name);
	}
	
	public void porEntrega2() {
		
		entregas = entregaService.entrega();
		
	}
	
	public String  porTodos() {
		this.todos = new ArrayList<EntregaBean>();
		todos = entregaService.listarTodos();
		return "Controle?faces-redirect=true";
		
	}
	
	public String porEntrega() {
		this.entregas = new ArrayList<EntregaBean>();
		entregas = entregaService.entrega();
		return "Controle2?faces-redirect=true";
	}
	
	
	public void  porDevolucao() {
		devolucoes = new ArrayList<>();
		nomeDevo = null;
				
	}
	
	public void devolucoes() {
		devolucoes = devolucaoService.porNome(nomeDevo);
	}
	
	public void selecionar(FuncionarioBean funcionario) {
		RequestContext.getCurrentInstance().closeDialog(funcionario);
		
		
	}

	public void funcionarioSelecionado(SelectEvent event) {
		FuncionarioBean funcionario = (FuncionarioBean) event.getObject();
		entrega.setFuncionario(funcionario);
	}
	
	public void abrirDialogoEpi() {
		
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 380);
		opcoes.put("contentWidth", 850);
		episFiltrados = new ArrayList<EpiBean>();
		RequestContext.getCurrentInstance().openDialog("BuscarEpi", opcoes, null);

	}

	public void nomeFunc() {
		teste = entregaService.entregaPeloFunc(nome);
	}
	
	// pesquisa e seleção de epi

	public void pesquisarEpi() {
		episFiltrados = epiService.porDesc(descricao);
		
	}
	
	public void novoEpi() {
		descricao = null;
		episFiltrados = new ArrayList<EpiBean>();
	}
	public void buscarEpi() {
		episFiltrados = epiService.listarEpiEstoque();
	}

	public void selecionarEpi(EpiBean epi) {
		epiQtd = epi.getQuantidadeTotal();
		
		RequestContext.getCurrentInstance().closeDialog(epi);
		

	}

	public void epiSelecionado(SelectEvent event) {
		EpiBean descricao = (EpiBean) event.getObject();
		entrega.setEpi(descricao);
	}
	//Estatisticas
	
	public String estatiscaFunc(EntregaBean estatisticaFunc) {
		this.estatisticaFunc =  estatisticaFunc;
		return "Estatistica?faces-redirect=true";
	}
	
	//excluir devoluçao
	
	public void PrepararExcluirDevolucao(DevolucaoBean devolucaoBean) {
		excluirDevolucao = devolucaoBean;
	}
	
	public void excluirDevolucao() {
		devolucaoService.excluir(excluirDevolucao);
		
		devolucoes = devolucaoService.porNome(nomeDevo);
		
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	
	

	// Getters e Setters

	public EntregaBean getEntrega() {
		return entrega;
	}

	public void setEntrega(EntregaBean entrega) {
		this.entrega = entrega;
	}

	public List<EntregaBean> getListaEntrega() {
		return listaEntrega;
	}

	public void setListaEntrega(List<EntregaBean> listaEntrega) {
		this.listaEntrega = listaEntrega;
	}

	public EntregaBean getEntregaExclusao() {
		return entregaExclusao;
	}

	public void setEntregaExclusao(EntregaBean entregaExclusao) {
		this.entregaExclusao = entregaExclusao;
	}

	public List<FuncionarioBean> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioBean> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<EpiBean> getEpis() {
		return epis;
	}

	public void setEpis(List<EpiBean> epis) {
		this.epis = epis;
	}

	public List<FuncionarioBean> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<FuncionarioBean> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<EpiBean> getEpisFiltrados() {
		return episFiltrados;
	}

	public void setEpisFiltrados(List<EpiBean> episFiltrados) {
		this.episFiltrados = episFiltrados;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<EntregaBean> getEntregasFiltradas() {
		return entregasFiltradas;
	}

	public void setEntregasFiltradas(List<EntregaBean> entregasFiltradas) {
		this.entregasFiltradas = entregasFiltradas;
	}
	
	

	public List<EntregaBean> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<EntregaBean> linhas) {
		this.linhas = linhas;
	}

	public List<EntregaBean> getFuncFiltrados() {
		return funcFiltrados;
	}

	public void setFuncFiltrados(List<EntregaBean> funcFiltrados) {
		this.funcFiltrados = funcFiltrados;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public List<EntregaBean> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<EntregaBean> entregas) {
		this.entregas = entregas;
	}

	

	public List<DevolucaoBean> getDevolucoes() {
		return devolucoes;
	}

	public void setDevolucoes(List<DevolucaoBean> devolucoes) {
		this.devolucoes = devolucoes;
	}

	public List<EntregaBean> getTodos() {
		return todos;
	}

	public void setTodos(List<EntregaBean> todos) {
		this.todos = todos;
	}

	public EntregaBean getEntregaDialog() {
		return entregaDialog;
	}

	public void setEntregaDialog(EntregaBean entregaDialog) {
		this.entregaDialog = entregaDialog;
	}

	public List<EntregaBean> getTeste() {
		return teste;
	}

	public void setTeste(List<EntregaBean> teste) {
		this.teste = teste;
	}

	public List<EntregaBean> getEntregaNome() {
		return EntregaNome;
	}

	public void setEntregaNome(List<EntregaBean> entregaNome) {
		EntregaNome = entregaNome;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getDevNome() {
		return devNome;
	}

	public void setDevNome(String devNome) {
		this.devNome = devNome;
	}

	public Integer getEpiQtd() {
		return epiQtd;
	}

	public void setEpiQtd(Integer epiQtd) {
		this.epiQtd = epiQtd;
	}

	public Boolean getTesteLogico() {
		return testeLogico;
	}

	public void setTesteLogico(Boolean testeLogico) {
		this.testeLogico = testeLogico;
	}

	public DevolucaoBean getDevolucaoBean() {
		return devolucaoBean;
	}

	public void setDevolucaoBean(DevolucaoBean devolucaoBean) {
		this.devolucaoBean = devolucaoBean;
	}

	public List<DevolucaoBean> getLinhasDev() {
		return linhasDev;
	}

	public void setLinhasDev(List<DevolucaoBean> linhasDev) {
		this.linhasDev = linhasDev;
	}

	public Integer getExcluirSaldo() {
		return excluirSaldo;
	}

	public void setExcluirSaldo(Integer excluirSaldo) {
		this.excluirSaldo = excluirSaldo;
	}

	public Integer getValorEdit() {
		return valorEdit;
	}

	public void setValorEdit(Integer valorEdit) {
		this.valorEdit = valorEdit;
	}

	public List<EntregaBean> getNomeEntrega() {
		return nomeEntrega;
	}

	public void setNomeEntrega(List<EntregaBean> nomeEntrega) {
		this.nomeEntrega = nomeEntrega;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNomeDevo() {
		return nomeDevo;
	}

	public void setNomeDevo(String nomeDevo) {
		this.nomeDevo = nomeDevo;
	}

	

	public EntregaBean getEstatisticaFunc() {
		return estatisticaFunc;
	}

	public void setEstatisticaFunc(EntregaBean estatisticaFunc) {
		this.estatisticaFunc = estatisticaFunc;
	}

	public String getEstatisticaNome() {
		return estatisticaNome;
	}

	public void setEstatisticaNome(String estatisticaNome) {
		this.estatisticaNome = estatisticaNome;
	}

	public String getNomeFuncEstat() {
		return nomeFuncEstat;
	}

	public void setNomeFuncEstat(String nomeFuncEstat) {
		this.nomeFuncEstat = nomeFuncEstat;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

	
	
	
	
	

	
	
	
	
}
