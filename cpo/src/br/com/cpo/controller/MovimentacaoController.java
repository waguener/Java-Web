package br.com.cpo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.CadFuncionario;
import br.com.cpo.model.Estoque;
import br.com.cpo.model.Movimentacao;
import br.com.cpo.model.Recebimento;
import br.com.cpo.services.CadFuncionarioServices;
import br.com.cpo.services.EstoqueServices;
import br.com.cpo.services.MovimentacaoServices;
import br.com.cpo.services.RecebimentoServices;
@SuppressWarnings("deprecation")
@ManagedBean
@Controller
@Scope("session")
public class MovimentacaoController implements Serializable {

	private static final long serialVersionUID = -8231380765854841251L;

	@Autowired
	private MovimentacaoServices movimentacaoServices;
	@Autowired
	private RecebimentoServices recebimentoServices;
	@Autowired
	private EstoqueServices estoqueServices;
	@Autowired
	private CadFuncionarioServices cadFuncionarioServices;
	

	private Movimentacao movimentacao = new Movimentacao();
	private Movimentacao mov1;
	private Movimentacao mov2;


	private List<Movimentacao> movimentacoes;
	private List<CadFuncionario> listaFunc;
	private List<Movimentacao> selecionados;
	private List<Movimentacao> escolha;
	private Recebimento recebimento = new Recebimento();
	private Recebimento selecionado;
	private Recebimento teste;
	private List<Movimentacao> listaDevolucao;
	private List<Movimentacao> listaSaida;
	private List<Movimentacao> listaDevCliente;
	private Estoque estoque = new Estoque();
	private String numLote;
	private String numCodigo;
	private Date hoje = new Date();
	private int totalEstoque;
	private String uniMedida;
	private Date data1;
	private Date data2;
	private int totRecebido;
	private int totRetirado;
	private int totDevolvido;
	private String senha;
	private int novaQtdRetirada;
	private int novaQtdDevolucao;
	private String produto;
	private int qtd;
	private String uni;
	private Integer t;
	private String nf;
	private String codigoBarras;
	private Integer numeros;
	
	// Metodos
	public void init() {
		listaFunc = cadFuncionarioServices.listar();
		movimentacao = new Movimentacao();
		recebimento = new Recebimento();
		estoque = new Estoque();
		totalEstoque = 0;
		uniMedida = null;
		numLote = null;
		numCodigo = null;
		codigoBarras = null;
	}

	public void initEditar() {
		listaFunc = cadFuncionarioServices.listar();
		novaQtdDevolucao = 0;
		novaQtdRetirada = 0;
	}

	public void initDevolucao() {
		listaFunc = cadFuncionarioServices.listar();
		movimentacao = new Movimentacao();
		
		numLote = null;
		numCodigo = null;
	}

	public void initMov() {

		movimentacoes = new ArrayList<Movimentacao>();
		movimentacoes = movimentacaoServices.buscaHoje(hoje);
		selecionados = new ArrayList<Movimentacao>();
		data1 = null;
		data2 = null;
	}

	public void initDevCliente() {
		movimentacao = new Movimentacao();
		recebimento = new Recebimento();
		listaDevCliente = movimentacaoServices.buscaDev(hoje);
		nf = null;
		produto = null;
		totalEstoque = 0;
		uniMedida = null;
		t = 0;
		numLote = null;
		numCodigo = null;
		listaSaida = new ArrayList<Movimentacao>();
		selecionados = new ArrayList<Movimentacao>();
		escolha = new ArrayList<Movimentacao>();
		estoque = new Estoque();
	}

	// salvar
	public String salvar() {

		int sub = totalEstoque - movimentacao.getQtdRetirada();
		if (sub < 0) {

			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Quantidade Insuficiente no Estoque!!",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
			
		}else {	
			estoque.setTotal(sub);
			Integer v;
			estoqueServices.salvar(estoque);
			movimentacao.setHoraSaida(new SimpleDateFormat("HH:mm").format(new Date()));
			movimentacao.setDataRetirada(hoje);
			
			
			movimentacao.setExcluir(true);
			recebimento = recebimentoServices.obterPorId(movimentacao.getRecebimento().getId());
			recebimento.setStatus("RETIRADO");
			v = qtdAtual();
			recebimento.setQtdAtual(v);
			recebimentoServices.salvar(recebimento);
			movimentacaoServices.salvar(movimentacao);

			FacesMessage msm = new FacesMessage("Retirada Salva com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "Retirar?faces-redirect=true";
		}
	
	}
	// Calcula qtd Atual do Pallet
	public int qtdAtual() {
	  Integer valor1 = recebimento.getQtdAtual();
	  Integer valor2 = movimentacao.getQtdRetirada();
	  Integer total = valor2 - valor1;
	  return total;
	 	
	}
	public String salvarProducao() {

		int sub = totalEstoque - movimentacao.getQtdRetirada();

		if (sub < 0) {

			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Quantidade Insuficiente no Estoque!!",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
		} else {
			estoque.setTotal(sub);

			estoqueServices.salvar(estoque);
			movimentacao.setHoraSaida(new SimpleDateFormat("HH:mm").format(new Date()));
			movimentacao.setDataRetirada(hoje);
			movimentacao.setExcluir(true);
			
			recebimento = recebimentoServices.obterPorId(movimentacao.getRecebimento().getId());
			recebimento.setStatus("RETIRADO");
			
			recebimentoServices.salvar(recebimento);
			movimentacaoServices.salvar(movimentacao);

			FacesMessage msm = new FacesMessage("Retirada Salva com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "MenuProducao?faces-redirect=true";
		}

	}

	public String salvarDevolucao() {

		int sub = totalEstoque + movimentacao.getQtdRetorno();
		Integer v;
		estoque.setTotal(sub);

		estoqueServices.salvar(estoque);
		movimentacao.setHoraRetorno(new SimpleDateFormat("HH:mm").format(new Date()));
		movimentacao.setDataRetorno(hoje);
		
		recebimento = recebimentoServices.obterPorId(movimentacao.getRecebimento().getId());
		recebimento.setStatus("RECEBIDO");
		v = qtdAtualDevolvida();
		recebimento.setQtdAtual(v);
		
		recebimentoServices.salvar(recebimento);
		movimentacaoServices.salvar(movimentacao);

		FacesMessage msm = new FacesMessage("Devolução Salva com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "Devolucao?faces-redirect=true";

	}
	
	// Calcula qtd Atual do Pallet
		public int qtdAtualDevolvida() {
		  Integer valor1 = recebimento.getQtdAtual();
		  Integer valor2 = movimentacao.getQtdRetorno();
		  Integer total = valor1 + valor2;
		  return total;
		 	
		}
	public String devolucaoProducao() {

		int sub = totalEstoque + movimentacao.getQtdRetorno();

		estoque.setTotal(sub);

		estoqueServices.salvar(estoque);
		movimentacao.setHoraRetorno(new SimpleDateFormat("HH:mm").format(new Date()));
		movimentacao.setDataRetorno(hoje);
		
		recebimento = recebimentoServices.obterPorId(movimentacao.getRecebimento().getId());
		recebimento.setStatus("RECEBIDO");
		
		recebimentoServices.salvar(recebimento);
		movimentacaoServices.salvar(movimentacao);

		FacesMessage msm = new FacesMessage("Devolução Salva com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "MenuProducao?faces-redirect=true";

	}

	public String salvarEdicao() {

		String cod, lote;
		int valor1, valor2, soma1, soma2, soma3, soma4 = 0;
		Long id;
		cod = movimentacao.getRecebimento().getCodigo();
		lote = movimentacao.getRecebimento().getLote();
		estoque = new Estoque();
		estoque = estoqueServices.controle(cod, lote);

		valor1 = estoque.getTotal();
		valor2 = movimentacao.getQtdRetirada();
		soma1 = valor1 + valor2;
		soma2 = soma1 - novaQtdRetirada;

		if (novaQtdDevolucao > 0) {
			soma3 = soma2 - movimentacao.getQtdRetorno();
			soma4 = soma3 + novaQtdDevolucao;
			id = estoque.getId();
			estoque.setTotal(soma4);
			estoque.setId(id);
			estoqueServices.salvar(estoque);

			movimentacao.setQtdRetorno(novaQtdDevolucao);
			
			movimentacaoServices.salvar(movimentacao);

			FacesMessage msm = new FacesMessage("Movimentação Editada com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

			return "PesquisarMov?faces-redirect=true";
		} else if (novaQtdRetirada > 0) {

			id = estoque.getId();
			estoque.setTotal(soma2);
			estoque.setId(id);
			estoqueServices.salvar(estoque);

			movimentacao.setQtdRetirada(novaQtdRetirada);
			
			movimentacaoServices.salvar(movimentacao);

			FacesMessage msm = new FacesMessage("Movimentação Editada com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

			return "PesquisarMov?faces-redirect=true";
		}
		return null;
	}

	// Pesquisa

	public void buscar() {
		
		movimentacoes = movimentacaoServices.listar();
		
	}

	public void porData() {

		movimentacoes = movimentacaoServices.porData(data1, data2);
	}

	public void retirar() {

		listaSaida = movimentacaoServices.saida(numLote, numCodigo);

		if (listaSaida.isEmpty()) {
			movimentacao = new Movimentacao();
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('retirarProduto').show();");

		}
	}

	public void escolher(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;

		String cod, lote;
		cod = movimentacao.getRecebimento().getCodigo();
		lote = movimentacao.getRecebimento().getLote();
		estoque = estoqueServices.controle(cod, lote);

		totalEstoque = estoque.getTotal();
		uniMedida = estoque.getRecebimento().getUniMedida();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('retirarProduto').hide();");
	}

	// Devolução
	public void devolucao() {

		listaDevolucao = movimentacaoServices.devolucao(numLote, numCodigo);

		if (listaDevolucao.isEmpty()) {
			movimentacao = new Movimentacao();
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('devolverProduto').show();");

		}
	}

	public void escolherDevolucao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;

		estoque = estoqueServices.controle(movimentacao.getRecebimento().getCodigo(),
				movimentacao.getRecebimento().getLote());
		totalEstoque = estoque.getTotal();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('devolverProduto').hide();");
	}

	// Abrir Relatório
	public void relatorio() {
		totRecebido = 0;
		totRetirado = 0;
		totDevolvido = 0;

		for (Movimentacao r : selecionados) {
			if (r.getQtdRetirada() == null) {
				r.setQtdRetirada(0);
			}
			if (r.getQtdRetorno() == null) {
				r.setQtdRetorno(0);
			}
			totRecebido += r.getRecebimento().getPesoFicha();
			totRetirado += r.getQtdRetirada();
			totDevolvido += r.getQtdRetorno();
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verMovimentacao').show();");
	}

	// Fechar Relatório
	public void fecharRelatorio() {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verMovimentacao').hide();");
	}

	// Editar
	public void abrirSenha(Movimentacao movimentacao) {

		mov1 = movimentacao;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verSenha').show();");
	}

	public String editar() {

		if (senha.equals("olgber")) {
			movimentacao = mov1;
			return "EditarMov?faces-redirect=true";
		} else {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Senha Incorreta!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
		return null;

	}

	// Devolução Cliente

	public void salvarDevCliente() {
		int v1, v2, s;

		v1 = estoque.getTotal();
		v2 = t;
		s = v1 - v2;
		estoque.setTotal(s);
		estoqueServices.salvar(estoque);

		
		recebimento = recebimentoServices.porId(movimentacao.getRecebimento().getId());
		recebimento.setStatus("DEV. CLIENTE");
		movimentacao.setRecebimento(recebimento);
		movimentacao.setDataDevCliente(hoje);
		movimentacao.setNfDevCliente(nf);
		movimentacao.setQtdDevCliente(t);
		movimentacao.setNfDevCliente(nf);
		recebimentoServices.salvar(recebimento);
		Long id = movimentacao.getId();
		movimentacao.setId(id);
		movimentacaoServices.salvar(movimentacao);
		listaDevCliente = movimentacaoServices.buscaDev(hoje);
		FacesMessage msm = new FacesMessage("Devolução Feita com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	public void buscarDev() {
		listaSaida = movimentacaoServices.saida(numLote, numCodigo);

		if (listaSaida.isEmpty()) {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {

			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('verDevCliente').show();");
		}

	}

	public void selecionado(Movimentacao mov) {
		
		estoque = estoqueServices.controle(numCodigo, numLote);
		produto = estoque.getRecebimento().getProduto();
		totalEstoque = estoque.getTotal();
		uniMedida = estoque.getRecebimento().getUniMedida();
		
		movimentacao = mov;
		t = movimentacao.getRecebimento().getPesoFicha();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verDevCliente').hide();");

	}
	
	public String voltar() {
		
		return "MenuProducao?faces-redirect=true";
	}

	
	//Abrir CodBar
	
	
	public void codbar(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verCodBar').show();");

	}
	
	//pesquisa CodBar retirar
	
	public void buscarCodBar() {
		String cod1;
		String lote1;
		mov2 = movimentacaoServices.porCodBar(codigoBarras);
		if(mov2 == null) {
			codigoBarras = null;
			totalEstoque = 0;
			uniMedida = null;
			movimentacao = new Movimentacao();
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
			movimentacao = mov2;
			cod1 = mov2.getRecebimento().getCodigo();
			lote1 = mov2.getRecebimento().getLote();
			estoque = estoqueServices.controle(cod1, lote1);
			
			totalEstoque = estoque.getTotal();
			uniMedida = estoque.getRecebimento().getUniMedida();
			
			System.out.println(movimentacao.getRecebimento().getQtdAtual());
			codigoBarras = null;
			
		}
	}
	
	public void buscarCodBarDev() {
		String cod1;
		String lote1;
		mov2 = movimentacaoServices.porCodBarDev(codigoBarras);
		if(mov2 == null) {
			codigoBarras = null;
			totalEstoque = 0;
			uniMedida = null;
			movimentacao = new Movimentacao();
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
			movimentacao = mov2;
			cod1 = mov2.getRecebimento().getCodigo();
			System.out.println("codigo "+mov2.getRecebimento().getCodigo());
			lote1 = mov2.getRecebimento().getLote();
			estoque = estoqueServices.controle(cod1, lote1);
			
			totalEstoque = estoque.getTotal();
			uniMedida = estoque.getRecebimento().getUniMedida();
			
			codigoBarras = null;
			
		}
	}
	
	//Exemplo sql 
	
	/*private String testeConnection = "SELECT PB.PRODCODALT,PB.PRODNOME,FTP.FICTECPRODCODESTR,FTP.FICTECPRODQTD,FTP.FICTECPRODUNIDMEDCODDIG FROM FIC_TEC_PROD FTP "
			+ "INNER JOIN PRODUTO P ON (FTP.PRODCODESTR = P.PRODCODESTR) "
			+ "INNER JOIN PRODUTO PB ON (PB.PRODCODESTR = FTP.FICTECPRODCODESTR) "
			+ "WHERE FTP.FICTECPRODACESSORIO = 'SIM' order by PB.PRODCODALT desc ";

	private String buscaProd = "select * from PRODUTO order by PRODNOME";
	public List<TesteBd> teste() throws SQLException {

		PreparedStatement stmt = getConnetion().prepareStatement(testeConnection);
		totalReg = 0;
		ResultSet result = stmt.executeQuery();
		listTesteBd = new ArrayList<TesteBd>();
		
		while (result.next()) {
			testeBd = new TesteBd();
			testeBd.setCodAlt(result.getString(1));
			testeBd.setNomeProd(result.getString(2));
			testeBd.setCodEstr(result.getString(3));
			testeBd.setQtd(result.getString(4));
			testeBd.setUniMedida(result.getString(5));
			totalReg ++;
			listTesteBd.add(testeBd);
			stmt.close();
		}
		
		System.out.println("Result -" + listTesteBd);
		return listTesteBd;
	}*/
	
	
	// Getters e Setters
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public String getNumLote() {
		return numLote;
	}

	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	public int getTotalEstoque() {
		return totalEstoque;
	}

	public void setTotalEstoque(int totalEstoque) {
		this.totalEstoque = totalEstoque;
	}

	public String getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

	public List<CadFuncionario> getListaFunc() {
		return listaFunc;
	}

	public void setListaFunc(List<CadFuncionario> listaFunc) {
		this.listaFunc = listaFunc;
	}

	public List<Movimentacao> getListaDevolucao() {
		return listaDevolucao;
	}

	public void setListaDevolucao(List<Movimentacao> listaDevolucao) {
		this.listaDevolucao = listaDevolucao;
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

	public List<Movimentacao> getListaSaida() {
		return listaSaida;
	}

	public void setListaSaida(List<Movimentacao> listaSaida) {
		this.listaSaida = listaSaida;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public List<Movimentacao> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Movimentacao> selecionados) {
		this.selecionados = selecionados;
	}

	public int getTotRecebido() {
		return totRecebido;
	}

	public void setTotRecebido(int totRecebido) {
		this.totRecebido = totRecebido;
	}

	public int getTotRetirado() {
		return totRetirado;
	}

	public void setTotRetirado(int totRetirado) {
		this.totRetirado = totRetirado;
	}

	public int getTotDevolvido() {
		return totDevolvido;
	}

	public void setTotDevolvido(int totDevolvido) {
		this.totDevolvido = totDevolvido;
	}

	public String getNumCodigo() {
		return numCodigo;
	}

	public void setNumCodigo(String numCodigo) {
		this.numCodigo = numCodigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNovaQtdRetirada() {
		return novaQtdRetirada;
	}

	public void setNovaQtdRetirada(int novaQtdRetirada) {
		this.novaQtdRetirada = novaQtdRetirada;
	}

	public int getNovaQtdDevolucao() {
		return novaQtdDevolucao;
	}

	public void setNovaQtdDevolucao(int novaQtdDevolucao) {
		this.novaQtdDevolucao = novaQtdDevolucao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getUni() {
		return uni;
	}

	public void setUni(String uni) {
		this.uni = uni;
	}

	public List<Movimentacao> getEscolha() {
		return escolha;
	}

	public void setEscolha(List<Movimentacao> escolha) {
		this.escolha = escolha;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

	public List<Movimentacao> getListaDevCliente() {
		return listaDevCliente;
	}

	public void setListaDevCliente(List<Movimentacao> listaDevCliente) {
		this.listaDevCliente = listaDevCliente;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	
	
	
	
	
	
}
