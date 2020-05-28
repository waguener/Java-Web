package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.DevolucaoBean;
import br.com.sistemaepi.Bean.EpiBean;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.dto.EquipamentosEmUsoDTO;
import br.com.sistemaepi.dto.VencimentosDTO;
import br.com.sistemaepi.service.DevolucaoService;
import br.com.sistemaepi.service.EntregaService;
import br.com.sistemaepi.service.EpiService;
import br.com.sistemaepi.service.FuncionarioService;

@ManagedBean
@Controller
@Scope
public class SistemaControllerEstatistica implements Serializable{

	private static final long serialVersionUID = -5011720637516683998L;
	
	//Services
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private EpiService epiService;
	@Autowired
	private EntregaService entregaService;
	@Autowired
	private DevolucaoService devolucaoService;
	
	//Classes
	private FuncionarioBean funcionario = new FuncionarioBean();	
	private EpiBean epi = new EpiBean();	
	private MeterGaugeChartModel meterGaugeModel1;
	
	//Listas
	private List<FuncionarioBean> listaFuncionario;
	private List<DevolucaoBean> devolucoes;
	private List<EquipamentosEmUsoDTO> entrega;
	private List<VencimentosDTO> venc;
	//Objetos
	private String nomeFunc;
	 
	//Métodos
	
	public void init() {
		nomeFunc = null;
		entrega = new ArrayList<>();
		devolucoes = new ArrayList<>();
		createMeterGaugeModels();
	}
	
	 @PostConstruct
	    public void inicio() {
	        createAnimatedModels();
	        
	    }
	 
	 public void iniciaVenc() {
		 venc = new ArrayList<>();
	 }
	
	public void estatsticaFunc() {
		listaFuncionario = funcionarioService.porNome(nomeFunc);
	}
	
	public void novo() {
		listaFuncionario = new ArrayList<>();
		nomeFunc = null;
	}
	
	public String estatiscaFunc(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
		return "Estatistica?faces-redirect=true";
	}
	
	public void mostrar() {
		nomeFunc = funcionario.getNome();
		devolucoes = devolucaoService.porNomeEstatistica(nomeFunc);
		entrega = entregaService.obterEqpEmUso(nomeFunc);
		
	}
	
	public void vencimento() {
		venc = entregaService.vencimentosEpi();
	}
	
	//Gráficos
	
	private BarChartModel grafico;
	
	 public BarChartModel getAnimatedModel2() {
	        return grafico;
	    }
	
	 private void createAnimatedModels() {
	         
		 grafico = initBarModel();
		 
		 grafico.setAnimate(true);
		 grafico.setLegendPosition("ne");
	        Axis yAxis = grafico.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(100);
	    }
	 
	 private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	 
	        ChartSeries epi = new ChartSeries();
	        epi.setLabel("Protetor Auricular");
	        epi.set("Janeiro",100);
	        
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("Girls");
	        girls.set("2004", 52);
	        
	 
	        model.addSeries(epi);
	        model.addSeries(girls);
	         
	        return model;
	    }
	 //Meter Gauge
	 
	 private MeterGaugeChartModel initMeterGaugeModel() {
	        List<Number> intervals = new ArrayList<Number>() {
	            {
	                add(20);
	                add(50);
	                add(120);
	                add(220);
	            }
	        };
	 
	        return new MeterGaugeChartModel(140, intervals);
	    }
	 
	    private void createMeterGaugeModels() {
	        meterGaugeModel1 = initMeterGaugeModel();
	        meterGaugeModel1.setTitle("MeterGauge Chart");
	        meterGaugeModel1.setGaugeLabel("km/h");
	        meterGaugeModel1.setGaugeLabelPosition("bottom");
	 
	    }
	 //Abrir Grafico
	    
	    public void abrirTela() {
	    	
	    	meterGaugeModel1 = new MeterGaugeChartModel();
	    	RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialogGrafico').show();");
	    }
	
	 
	//Getters e Setters
	public List<FuncionarioBean> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<FuncionarioBean> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

	public FuncionarioBean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}
	public List<EquipamentosEmUsoDTO> getEntrega() {
		return entrega;
	}

	public void setEntrega(List<EquipamentosEmUsoDTO> entrega) {
		this.entrega = entrega;
	}

	public List<DevolucaoBean> getDevolucoes() {
		return devolucoes;
	}



	public void setDevolucoes(List<DevolucaoBean> devolucoes) {
		this.devolucoes = devolucoes;
	}

	public BarChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(BarChartModel grafico) {
		this.grafico = grafico;
	}

	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}

	public List<VencimentosDTO> getVenc() {
		return venc;
	}

	public void setVenc(List<VencimentosDTO> venc) {
		this.venc = venc;
	}
	
	
	
	
	
}
