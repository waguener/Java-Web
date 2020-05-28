package br.com.cpo.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import br.com.cpo.model.Estoque;
import br.com.cpo.model.Movimentacao;
import br.com.cpo.model.Recebimento;

import br.com.cpo.services.EstoqueServices;
import br.com.cpo.services.MovimentacaoServices;
import br.com.cpo.services.RecebimentoServices;
import br.com.cpo.util.CodBar;
import br.com.cpo.util.ConnectionFactory;
@SuppressWarnings("deprecation")
@ManagedBean
@Controller
@Scope("session")
public class RecebimentoController implements Serializable {

	private static final long serialVersionUID = -3782228864362239249L;

	@Autowired
	private RecebimentoServices recebimentoServices;
	
	@Autowired
	private EstoqueServices estoqueServices;
	@Autowired
	private MovimentacaoServices movimentacaoServices;

	private Recebimento recebimento = new Recebimento();
	private Recebimento teste;
	private Estoque estoque = new Estoque();
	private Movimentacao movimentacao = new Movimentacao();
	private Recebimento excluirMov;
	private List<Movimentacao> listaRecebimento;
	private List<Movimentacao> selecionados;

	private Recebimento matEstoque;
	private Recebimento rec;
	private String rep;
	private Recebimento repetir;
	
	private String cod;
	private Movimentacao recebimentoExclusao;
	private int totPesoFisico;
	private int totPesoFicha;
	private int totDiferenca;
	private Date hoje = new Date();
	private Date data1;
	private Date data2;
	private Boolean botaoSalvar;
	private String id;
	private Boolean botaoRepetir;
	private Boolean botaoExcluir;
	private Boolean campo1 = false;
	private Boolean campo2 = false;
	private Boolean campo3 = false;
	private Boolean campo4 = false;
	private Boolean campo5 = false;
	private Boolean campo6 = false;
	private Boolean campo7 = false;
	private Boolean campo8 = false;
	private Boolean campo9 = false;
	private Boolean alertaUd = false;
	private Integer campos = 0;
	private String msg;
	// Metodos
	
	public void init() {

		listaRecebimento = movimentacaoServices.buscaHoje(hoje);
		selecionados = new ArrayList<Movimentacao>();

		data1 = null;
		data2 = null;
		botaoSalvar = false;
		botaoExcluir = false;

	}

	public void inicioRecebimento() {
		teste = new Recebimento();
		recebimento = new Recebimento();
		
		estoque = new Estoque();
		cod = null;
		botaoRepetir = false;
		botaoSalvar = false;
		campo1 = false;
		campo2 = false;
		campo3 = false;
		campo4 = false;
		campo5 = false;
		campo6 = false;
		campo7 = false;
		campo8 = false;
		campo9 = false;
		alertaUd = false;
		campos = 0;
		msg = "";
	}

	// Salvar

	public void salvar() {

		
		
		addEstoque();
		recebimento.setStatus("RECEBIDO");
		recebimento.setQtdAtual(recebimento.getPesoFicha());
		recebimentoServices.salvar(recebimento);

		matEstoque = recebimentoServices.ultimoId();
		rec = recebimentoServices.obterPorId(matEstoque.getId());
		movimentacao = new Movimentacao();
		movimentacao.setRecebimento(rec);
		movimentacao.setExcluir(false);
		movimentacao.setCodbar(CodBar.gerarCodBar());
		movimentacaoServices.salvar(movimentacao);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verCodBar').show();");

	}

	// Teste de U.D.
	public void testeUd() {
		
		teste = recebimentoServices.testeUd(recebimento.getNumeroRack());
		if (teste != null) {
			campo8 = false;
			alertaUd = true;
			msg = "*U.D. já Cadastrado!!!";
			recebimento.setNumeroRack(null);
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Produto já Cadastrado com essa U.D!!",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			System.out.println("Alerta "+ alertaUd);
		}else {
			campo8 = true;
			alertaUd = false;
			msg = "";
			System.out.println("campo8 "+ campo8);
		}
		
	}

	// imprimir codbar
	public String imprimirCodBar() {

		return "Recebimento?faces-redirect=true";
	}

	public String salvarEdicao() {
		

		recebimentoServices.salvar(recebimento);

		FacesMessage msm = new FacesMessage("Recebimento Editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "Recebimento?faces-redirect=true";
	}

	// Controle do Estoque
	public void addEstoque() {
		String cod, lote;
		int valor1, valor2, soma;
		Long id;
		cod = recebimento.getCodigo();
		lote = recebimento.getLote();
		estoque = new Estoque();
		estoque = estoqueServices.controle(cod, lote);

		if (estoque == null) {
			estoque = new Estoque();
			estoque.setRecebimento(recebimento);
			estoque.setTotal(recebimento.getPesoFicha());
			estoqueServices.salvar(estoque);

		} else {

			valor1 = estoque.getTotal();
			valor2 = recebimento.getPesoFicha();
			soma = valor1 + valor2;
			id = estoque.getId();
			estoque.setTotal(soma);
			estoque.setId(id);
			estoqueServices.salvar(estoque);
		}

	}
	// Buscar Produto
	
	public Connection getConnetion() {
		return ConnectionFactory.getConnection("APOLO");
	}

	private String buscarProd = "select * from PRODUTO where ProdCodAlt = ";
	
	public void buscarProduto() throws ClassNotFoundException, SQLException {
		recebimento = new Recebimento();
		try {
		PreparedStatement stmt = getConnetion().prepareStatement(buscarProd + "'" + cod + "'");
		ResultSet result = stmt.executeQuery();
		System.out.println("teste" + result);
		if (result.next()) {
		
			
			botaoRepetir = true;
			recebimento.setCodigo(result.getString(3));
			recebimento.setProduto(result.getString(5));
			recebimento.setDataEntrada(new Date());
			recebimento.setHoraEntrada(new SimpleDateFormat("HH:mm").format(new Date()));
		
			
		}else {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Nenhum Produto Encontrado com este Código!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			
		}
		
		
		stmt.close();
		result.close();
		}catch (NoResultException e) {
			System.out.println(e);
			
		}
	}

	public void repetir() {
		repetir = new Recebimento();
		rep = null;
		rep = recebimentoServices.repetir(cod);

		if (rep == null) {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhuma Entrada!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {
			repetir = recebimentoServices.obterPorId(Long.parseLong(rep));

			recebimento.setDelivery(repetir.getDelivery());
			recebimento.setLote(repetir.getLote());
			recebimento.setUniMedida(repetir.getUniMedida());
			recebimento.setDataValidade(repetir.getDataValidade());
			recebimento.setNotaFiscal(repetir.getNotaFiscal());
			recebimento.setTipo(repetir.getTipo());
			recebimento.setResponsavel(repetir.getResponsavel());

		}

	}
	// Novo Recebimemto

	public String recebimento() {
		return "CadastroRecebimento?faces-redirect=true";
	}

	// Pesquisar

	public void porData() {
		listaRecebimento = new ArrayList<Movimentacao>();
		listaRecebimento = movimentacaoServices.porData(data1, data2);

		if (listaRecebimento.isEmpty()) {

			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nenhum Produto Encontrado!!", "");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
	}

	// Subtrair diferença

	public void subtrair() {
		botaoSalvar = true;
		int resul, v1, v2;
		v1 = recebimento.getPesoFisico();
		v2 = recebimento.getPesoFicha();
		resul = v2 - v1;
		recebimento.setDiferenca(resul);
	}

	// Subtrair Data

	/*
	 * public void calculodata() { int resul = diasUso(); if(resul < 0) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public int diasUso() { LocalDate dataInicial =
	 * recebimento.getDataFabricacao().toInstant().atZone(ZoneId.systemDefault()).
	 * toLocalDate(); LocalDate dataFinal =
	 * recebimento.getDataValidade().toInstant().atZone(ZoneId.systemDefault()).
	 * toLocalDate(); Long periodo = Duration.between(dataInicial.atStartOfDay(),
	 * dataFinal.atStartOfDay()).toDays(); int dias =
	 * Integer.valueOf(periodo.toString()); return dias;
	 * 
	 * }
	 */

	// Editar
	public String editar(Recebimento recebimento) {
		this.recebimento = recebimento;
		cod = recebimento.getCodigo();
		
		return "EditarRecebimento?faces-redirect=true";
	}

	// Abrir Relatorio

	
	public void relatorio() {
		totDiferenca = 0;
		totPesoFicha = 0;
		totPesoFisico = 0;

		for (Movimentacao r : selecionados) {
			totPesoFisico += r.getRecebimento().getPesoFisico();
			totPesoFicha += r.getRecebimento().getPesoFicha();
			totDiferenca += r.getRecebimento().getDiferenca();
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verRecebimento').show();");
	}

	// Fechar Relatorio

	public void fechar() {
		totDiferenca = 0;
		totPesoFicha = 0;
		totPesoFisico = 0;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verRecebimento').hide();");
	}

	// Excluir

	public void prepararExclusao(Movimentacao movimentacao) {

		recebimentoExclusao = movimentacao;
		excluirMov = recebimentoServices.obterPorId(recebimentoExclusao.getRecebimento().getId());

	}

	public void excluirRecebimento() {
		String cod, lote;
		int valor1, valor2, soma;
		Long id;
		cod = recebimentoExclusao.getRecebimento().getCodigo();
		lote = recebimentoExclusao.getRecebimento().getLote();
		estoque = new Estoque();
		estoque = estoqueServices.controle(cod, lote);
		valor1 = estoque.getTotal();
		valor2 = recebimentoExclusao.getRecebimento().getPesoFicha();
		soma = valor1 - valor2;
		id = estoque.getId();
		estoque.setTotal(soma);
		estoque.setId(id);
		estoqueServices.salvar(estoque);
		movimentacaoServices.excluir(recebimentoExclusao);
		recebimentoServices.excluir(excluirMov);

		listaRecebimento = movimentacaoServices.buscaHoje(hoje);

		FacesMessage msm = new FacesMessage("Recebimento Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Controle dos campos
	public void controleCampos1() {
		campo1 = true;
		System.out.println("campo1 " + campo1);
	}

	public void controleCampos2() {
		campo2 = true;
		System.out.println("campo2 " + campo2);
	}

	public void controleCampos3() {
		campo3 = true;
		System.out.println("campo3 " + campo3);
	}

	public void controleCampos4() {
		campo4 = true;
		System.out.println("campo4 " + campo4);
	}

	public void controleCampos5() {
		
		if(recebimento.getPesoFisico() == 0) {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Insira o valor correto para o Campo Fisico ",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
			campo5 = true;
			System.out.println("campo5 " + campo5);
		}
	}

	public void controleCampos6() {
		
		if(recebimento.getPesoFicha() == 0) {
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Insira o valor correto para o Campo Ficha ",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
			subtrair();
			campo6 = true;
			System.out.println("campo6 " + campo6);
		}
	}

	public void controleCampos7() {
		campo7 = true;
		System.out.println("campo7 " + campo7);
	}

	

	public void controleCampos9() {
		campo9 = true;
		System.out.println("campo9 " + campo9);
	}

	public void controleCampos10() {
		
		if (campo1 == true && campo2 == true && campo3 == true && campo4 == true && campo5 == true && campo6 == true
				&& campo7 == true && campo8 == true && campo9 == true) {
			
			botaoSalvar = true;
		}else {
			botaoSalvar = false;
			FacesMessage msm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Você precisa clicar em todos os campos ",
					"");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
		System.out.println("Botao " + botaoSalvar);
	}

	// Getters e setters

	

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public List<Movimentacao> getListaRecebimento() {
		return listaRecebimento;
	}

	public void setListaRecebimento(List<Movimentacao> listaRecebimento) {
		this.listaRecebimento = listaRecebimento;
	}

	public List<Movimentacao> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Movimentacao> selecionados) {
		this.selecionados = selecionados;
	}

	public int getTotPesoFisico() {
		return totPesoFisico;
	}

	public void setTotPesoFisico(int totPesoFisico) {
		this.totPesoFisico = totPesoFisico;
	}

	public int getTotPesoFicha() {
		return totPesoFicha;
	}

	public void setTotPesoFicha(int totPesoFicha) {
		this.totPesoFicha = totPesoFicha;
	}

	public int getTotDiferenca() {
		return totDiferenca;
	}

	public void setTotDiferenca(int totDiferenca) {
		this.totDiferenca = totDiferenca;
	}

	public Movimentacao getRecebimentoExclusao() {
		return recebimentoExclusao;
	}

	public void setRecebimentoExclusao(Movimentacao recebimentoExclusao) {
		this.recebimentoExclusao = recebimentoExclusao;
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

	public Boolean getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(Boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public Boolean getBotaoRepetir() {
		return botaoRepetir;
	}

	public void setBotaoRepetir(Boolean botaoRepetir) {
		this.botaoRepetir = botaoRepetir;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Boolean getCampo1() {
		return campo1;
	}

	public void setCampo1(Boolean campo1) {
		this.campo1 = campo1;
	}

	public Boolean getCampo2() {
		return campo2;
	}

	public void setCampo2(Boolean campo2) {
		this.campo2 = campo2;
	}

	public Boolean getCampo3() {
		return campo3;
	}

	public void setCampo3(Boolean campo3) {
		this.campo3 = campo3;
	}

	public Boolean getCampo4() {
		return campo4;
	}

	public void setCampo4(Boolean campo4) {
		this.campo4 = campo4;
	}

	public Boolean getCampo5() {
		return campo5;
	}

	public void setCampo5(Boolean campo5) {
		this.campo5 = campo5;
	}

	public Boolean getCampo6() {
		return campo6;
	}

	public void setCampo6(Boolean campo6) {
		this.campo6 = campo6;
	}

	public Boolean getCampo7() {
		return campo7;
	}

	public void setCampo7(Boolean campo7) {
		this.campo7 = campo7;
	}

	public Boolean getCampo8() {
		return campo8;
	}

	public void setCampo8(Boolean campo8) {
		this.campo8 = campo8;
	}

	public Boolean getCampo9() {
		return campo9;
	}

	public void setCampo9(Boolean campo9) {
		this.campo9 = campo9;
	}

	public Boolean getAlertaUd() {
		return alertaUd;
	}

	public void setAlertaUd(Boolean alertaUd) {
		this.alertaUd = alertaUd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
