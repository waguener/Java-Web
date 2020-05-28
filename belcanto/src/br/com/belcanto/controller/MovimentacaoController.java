package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.AulaCompReceber;
import br.com.belcanto.model.MatriculaReceber;
import br.com.belcanto.model.MensalReceber;
import br.com.belcanto.model.Movimentacao;
import br.com.belcanto.services.AlunoServices;
import br.com.belcanto.services.AulaCompReceberServices;
import br.com.belcanto.services.MatriculaReceberServices;
import br.com.belcanto.services.MensalReceberServices;
import br.com.belcanto.services.MovimentacaoServices;
import sun.swing.BakedArrayList;

@ManagedBean
@Controller
@Scope("session")

public class MovimentacaoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MovimentacaoServices movimentacaoServices;
	@Autowired
	private AlunoServices alunoServices;
	@Autowired
	private AulaCompReceberServices aulaCompReceberServices;
	@Autowired
	private MensalReceberServices mensalReceberServices;
	@Autowired
	private MatriculaReceberServices matriculaReceberServices;

	private Movimentacao movimentacao;
	private List<Movimentacao> listarMovimentacao;
	private AulaCompReceber aulaCompReceber;
	private AulaCompReceber aulaPago;
	private List<AulaCompReceber> listarAulasComp;
	private MensalReceber mensalReceber;
	private MensalReceber mesPago;
	private List<MensalReceber> listarMensalidades;
	private List<MensalReceber> mensalidades;
	private MatriculaReceber matriculaReceber;
	private MatriculaReceber matPago;
	private List<MatriculaReceber> listarMatReceber;
	private List<MensalReceber> mensalSelecionada;
	private List<AulaCompReceber> aulaSelecionada;
	private List<MatriculaReceber> matSelecionada;
	private String nome;
	private Double valorReceber;
	private Double troco;
	private Date hj = new Date();
	private Date dtInicial;
	private Date dtFinal;
	private BarChartModel grafMovimentacao; 

	// Métodos
	public void init() {
		listarMensalidades = new ArrayList<MensalReceber>();
		mensalReceber = new MensalReceber();
		nome = null;
	}
	
	public void initMov() {
		dtFinal = null;
		dtFinal = null;
		grafMovimentacao = new BarChartModel();
		listarMovimentacao = new ArrayList<Movimentacao>();	}
	

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

	// Pagamento
	public String pagar(MensalReceber mensalReceber) {
		this.mensalReceber = mensalReceber;

		return "Pagamento?faces-redirect=true";
	}

	public void initPagamento() {
		valorReceber = 0.0;
		matSelecionada = new ArrayList<MatriculaReceber>();
		aulaSelecionada = new ArrayList<AulaCompReceber>();
		mensalSelecionada = new ArrayList<MensalReceber>();
		movimentacao = new Movimentacao();
		mensalidades = new ArrayList<MensalReceber>();
		listarAulasComp = new ArrayList<AulaCompReceber>();
		listarMatReceber = new ArrayList<MatriculaReceber>();
		mensalidades = movimentacaoServices.mensalidades(mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());
		listarAulasComp = movimentacaoServices.aulaCompReceber(
				mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());
		listarMatReceber = movimentacaoServices.matReceber(
				mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());

		if (mensalidades.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Aluno Encontrado!", "Erro"));
		}
	}

	// Salvar Pagamento
	public String salvar() {
		Movimentacao movUtil = new Movimentacao();
		List<String> tipo = new ArrayList<String>();
		String t = "";
		
		if (!mensalSelecionada.isEmpty()) {
			movUtil = new Movimentacao();
			movUtil = movimentacao;
			movUtil.setPagante(movimentacao.getPagante());
			movUtil.setRecebidor(movimentacao.getRecebidor());
			movUtil.setTroco(movimentacao.getTroco());
			movUtil.setValorRecebido(movimentacao.getValorRecebido());
			movUtil.setValorTotal(movimentacao.getValorTotal());
			movUtil.setFormaPgt(movimentacao.getFormaPgt());
			movUtil.setDtMovimentacao(hj);
			movUtil.setTipoPgt("MENSALIDADE");
			movUtil.setAluno(mensalReceber.getMatricula().getAgenda().getAluno().getNome());
			movimentacaoServices.salvar(movUtil);
			
		}
		if (!aulaSelecionada.isEmpty()) {
			movUtil = new Movimentacao();
			movUtil = movimentacao;
			movUtil.setPagante(movimentacao.getPagante());
			movUtil.setRecebidor(movimentacao.getRecebidor());
			movUtil.setTroco(movimentacao.getTroco());
			movUtil.setValorRecebido(movimentacao.getValorRecebido());
			movUtil.setValorTotal(movimentacao.getValorTotal());
			movUtil.setFormaPgt(movimentacao.getFormaPgt());
			movUtil.setDtMovimentacao(hj);
			movUtil.setTipoPgt("AULA COMPLEMENTAR");
			movUtil.setAluno(mensalReceber.getMatricula().getAgenda().getAluno().getNome());
			movimentacaoServices.salvar(movUtil);
			
		}
		if (!matSelecionada.isEmpty()) {
			movUtil = new Movimentacao();
			movUtil = movimentacao;
			movUtil.setPagante(movimentacao.getPagante());
			movUtil.setRecebidor(movimentacao.getRecebidor());
			movUtil.setTroco(movimentacao.getTroco());
			movUtil.setValorRecebido(movimentacao.getValorRecebido());
			movUtil.setValorTotal(movimentacao.getValorTotal());
			movUtil.setFormaPgt(movimentacao.getFormaPgt());
			movUtil.setDtMovimentacao(hj);
			movUtil.setTipoPgt("MATRÍCULA");
			movUtil.setAluno(mensalReceber.getMatricula().getAgenda().getAluno().getNome());
			movimentacaoServices.salvar(movUtil);
		}
		tipo.remove(null);

		

		for (MensalReceber m : mensalSelecionada) {

			mesPago = new MensalReceber();
			mesPago = movimentacaoServices.mesPago(m.getId());
			mesPago.getReceber().setStatus("PAGO");
			mensalReceberServices.salvar(mesPago);

		}

		for (AulaCompReceber a : aulaSelecionada) {
			aulaPago = new AulaCompReceber();
			aulaPago = movimentacaoServices.aulaPago(a.getId());
			aulaPago.getReceber().setStatus("PAGO");
			aulaCompReceberServices.salvar(aulaPago);
		}

		for (MatriculaReceber mat : matSelecionada) {
			matPago = new MatriculaReceber();
			matPago = movimentacaoServices.matpago(mat.getId());
			matPago.getReceber().setStatus("PAGO");
			matriculaReceberServices.salvar(matPago);
		}

		FacesMessage msm = new FacesMessage("Recebimento Salvo com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "Pagamento?faces-redirect=true";
	}

	// Consultas
	public void buscarAluno() {
		listarMensalidades = movimentacaoServices.porNome(nome);
		if (listarMensalidades.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Aluno Encontrado!", "Erro"));
		}
	}

	public void buscar() {
		listarMatReceber = movimentacaoServices.peloNome(nome);

		if (listarMensalidades.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Aluno Encontrado!", "Erro"));
		}

	}

	// Abrir Recebimento
	public void abrirRecebimento() {

		double v1, v2, v3 = 0.0;
		v1 = somarMensal();
		v2 = somarAulas();
		v3 = somarMat();
		valorReceber = v1 + v2 + v3;
		valorReceber = arredondar(valorReceber, 2, 1);
		System.out.println(valorReceber);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('recebimento').show();");

	}

	// Calcula Recebimento
	protected Double somarMensal() {
		double total = 0.0;

		for (MensalReceber mens : mensalSelecionada) {
			total += mens.getMatricula().getValorMensalidade();
		}

		return total;
	}

	protected Double somarAulas() {
		double total = 0.0;

		for (AulaCompReceber a : aulaSelecionada) {
			total += a.getMatricula().getAgenda().getAulaComp().getValor();
		}

		return total;
	}

	protected Double somarMat() {
		double total = 0.0;

		for (MatriculaReceber m : matSelecionada) {
			total += m.getReceber().getValorReceber();
		}

		return total;
	}

	// Calcular Total Recebimento
	public void total() {
		double t = 0.0;
		t = movimentacao.getValorRecebido() - valorReceber;
		t = arredondar(t, 2, 1);
		if (movimentacao.getValorRecebido() < valorReceber) {
			t = 0.0;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Recebido é Menor que o Total!!", ""));
			RequestContext.getCurrentInstance().execute("growlColour(\".ui-growl-image-error\");");
		} else {
			movimentacao.setTroco(t);
			movimentacao.setValorTotal(valorReceber);
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

	// Fechar Recebimento
	public void fecharRecebimento() {
		valorReceber = 0.0;
		movimentacao = new Movimentacao();
		matSelecionada = new ArrayList<MatriculaReceber>();
		aulaSelecionada = new ArrayList<AulaCompReceber>();
		mensalSelecionada = new ArrayList<MensalReceber>();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('recebimento').hide();");
	}

	// Abrir Carnê
	public void abrirCarne(MensalReceber mensalReceber) {
		this.mensalReceber = mensalReceber;
		mensalidades = new ArrayList<MensalReceber>();
		listarAulasComp = new ArrayList<AulaCompReceber>();
		listarMatReceber = new ArrayList<MatriculaReceber>();
		mensalidades = movimentacaoServices.mensalidades(mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());
		listarAulasComp = movimentacaoServices.aulaCompReceber(
				mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());
		listarMatReceber = movimentacaoServices.matReceber(
				mensalReceber.getMatricula().getAgenda().getAluno().getNome(),
				mensalReceber.getMatricula().getAgenda().getCurso().getNome());

		if (mensalidades.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Aluno Encontrado!", "Erro"));
		} else {

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('carne').show();");
		}
	}

	// Fechar Carnê
	public String fecharCarne() {

		return "ConMensalidades?faces-redirect=true";
	}
	
	//Consultas
	public void porDatas() {
		listarMovimentacao = movimentacaoServices.entreData(dtInicial, dtFinal);
		if(listarMovimentacao.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma movimentação Encontrada!", "Erro"));
		}else {
			grafMovimentacao = new BarChartModel();
			for(int i=0;i<listarMovimentacao.size();i++) {
				ChartSeries series = new BarChartSeries();
				
				series.setLabel(listarMovimentacao.get(i).getTipoPgt());
				series.set(listarMovimentacao.get(i).getAluno(), listarMovimentacao.get(i).getValorTotal());
				grafMovimentacao.addSeries(series);
				
			}
			grafMovimentacao.setTitle("Movimentações");
			grafMovimentacao.setLegendPosition("ne");
			grafMovimentacao.setAnimate(true);
			
			
			Axis xAxis = grafMovimentacao.getAxis(AxisType.X);
			xAxis.setLabel("Eventos");
			Axis yAxis = grafMovimentacao.getAxis(AxisType.Y);
			yAxis.setLabel("Datas");
			yAxis.setMin(5000);
			yAxis.setMax(50000);
			
		}
	}

	
	// Getters e Setters
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getListarMovimentacao() {
		return listarMovimentacao;
	}

	public void setListarMovimentacao(List<Movimentacao> listarMovimentacao) {
		this.listarMovimentacao = listarMovimentacao;
	}

	public AulaCompReceber getAulaCompReceber() {
		return aulaCompReceber;
	}

	public void setAulaCompReceber(AulaCompReceber aulaCompReceber) {
		this.aulaCompReceber = aulaCompReceber;
	}

	public List<AulaCompReceber> getListarAulasComp() {
		return listarAulasComp;
	}

	public void setListarAulasComp(List<AulaCompReceber> listarAulasComp) {
		this.listarAulasComp = listarAulasComp;
	}

	public MensalReceber getMensalReceber() {
		return mensalReceber;
	}

	public void setMensalReceber(MensalReceber mensalReceber) {
		this.mensalReceber = mensalReceber;
	}

	public List<MensalReceber> getListarMensalidades() {
		return listarMensalidades;
	}

	public void setListarMensalidades(List<MensalReceber> listarMensalidades) {
		this.listarMensalidades = listarMensalidades;
	}

	public MatriculaReceber getMatriculaReceber() {
		return matriculaReceber;
	}

	public void setMatriculaReceber(MatriculaReceber matriculaReceber) {
		this.matriculaReceber = matriculaReceber;
	}

	public List<MatriculaReceber> getListarMatReceber() {
		return listarMatReceber;
	}

	public void setListarMatReceber(List<MatriculaReceber> listarMatReceber) {
		this.listarMatReceber = listarMatReceber;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<MensalReceber> getMensalidades() {
		return mensalidades;
	}

	public void setMensalidades(List<MensalReceber> mensalidades) {
		this.mensalidades = mensalidades;
	}

	public MovimentacaoServices getMovimentacaoServices() {
		return movimentacaoServices;
	}

	public void setMovimentacaoServices(MovimentacaoServices movimentacaoServices) {
		this.movimentacaoServices = movimentacaoServices;
	}

	public List<MensalReceber> getMensalSelecionada() {
		return mensalSelecionada;
	}

	public void setMensalSelecionada(List<MensalReceber> mensalSelecionada) {
		this.mensalSelecionada = mensalSelecionada;
	}

	public List<AulaCompReceber> getAulaSelecionada() {
		return aulaSelecionada;
	}

	public void setAulaSelecionada(List<AulaCompReceber> aulaSelecionada) {
		this.aulaSelecionada = aulaSelecionada;
	}

	public List<MatriculaReceber> getMatSelecionada() {
		return matSelecionada;
	}

	public void setMatSelecionada(List<MatriculaReceber> matSelecionada) {
		this.matSelecionada = matSelecionada;
	}

	public Double getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(Double valorReceber) {
		this.valorReceber = valorReceber;
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public BarChartModel getGrafMovimentacao() {
		return grafMovimentacao;
	}

	public void setGrafMovimentacao(BarChartModel grafMovimentacao) {
		this.grafMovimentacao = grafMovimentacao;
	}
	
}
