package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Matricula;
import br.com.belcanto.query.TotalCursos;
import br.com.belcanto.services.AlunoServices;
import br.com.belcanto.services.MatriculaServices;

@ManagedBean
@Controller
@Scope("session")
public class HomeController implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlunoServices alunoServices;
	@Autowired
	private MatriculaServices matriculaServices;
	
	
	private Aluno aluno;
	private Matricula matricula = new Matricula();
	private List<Matricula> listarMat = new ArrayList<Matricula>();
	private List<TotalCursos> totalCursos = new ArrayList<TotalCursos>();
	private String total;
	private PieChartModel torta;
	private String valorTotal;
	
	//Metodos
	@PostConstruct
	public void init() {
		total = null;
		total = alunoServices.totalAluno();
		totalCursos = new ArrayList<TotalCursos>();
	
		torta = new PieChartModel();
		createAnimatedModels();
	}

	public String deslogar() {
		return "Login?faces-redirect=true";
	}
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

	//Consultas
/*	public void cursos() {
		totalCursos = matriculaServices.totalCursos();
		int valor = 0;
		for(TotalCursos c : totalCursos) {
			valor += c.getValor();
		}
		valorTotal = "R$" + valor + ",00";
		}*/
	//Getters e Setters
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	


	public PieChartModel getTorta() {
		return torta;
	}

	public void setTorta(PieChartModel torta) {
		this.torta = torta;
	}

	public List<Matricula> getListarMat() {
		return listarMat;
	}

	public void setListarMat(List<Matricula> listarMat) {
		this.listarMat = listarMat;
	}

	public BarChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(BarChartModel grafico) {
		this.grafico = grafico;
	}

	public List<TotalCursos> getTotalCursos() {
		return totalCursos;
	}

	public void setTotalCursos(List<TotalCursos> totalCursos) {
		this.totalCursos = totalCursos;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	
	
	
}
