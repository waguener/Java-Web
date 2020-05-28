package br.com.recibos.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.recibos.model.Recibos;
import br.com.recibos.services.RecibosServices;

@ManagedBean
@Controller
@Scope("session")
public class RecibosController implements Serializable {

	private static final long serialVersionUID = -7263685017418736868L;

	@Autowired
	private RecibosServices recibosServices;

	private Recibos recibos;
	private List<Recibos> listaRecibos;
	private String funcionario;
	private Double vlr;
	private Recibos recibosDialog;
	private String data;
	private Recibos pesquisaDialog;
	private Recibos reciboExclusao;
	
	// Métodos

	public void init() {
		recibos = new Recibos();
		listaRecibos = new ArrayList<Recibos>();
		funcionario = null;
	}
	
	public void pesquisaIniciar() {
		pesquisaDialog = recibos;
	}
	public String hoje() {
		Date data3 = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		data = formato.format(data3);
		return data;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String fecharImpressao() {
		recibos = new Recibos();
		return "Recibos?faces-redirect=true";
	}
	
	public String fecharRecibo() {
		recibos = new Recibos();
		return "Pesquisa?faces-redirect=true";
	}

	
	
	// Valor por Extenso
	
	public static String valorPorExtenso(double vlr) {
		
	    if (vlr == 0)
	       return("zero");
	 
	    long inteiro = (long)Math.abs(vlr); // parte inteira do valor
	    double resto = vlr - inteiro;       // parte fracionária do valor
	 
	    String vlrS = String.valueOf(inteiro);
	    if (vlrS.length() > 15)
	       return("Erro: valor superior a 999 trilhões.");
	 
	    String s = "", saux, vlrP;
	    String centavos = String.valueOf((int)Math.round(resto * 100));
	 
	    String[] unidade = {"", "Um", "Dois", "Três", "Quatro", "Cinco",
	             "Seis", "Sete", "Oito", "Nove", "Dez", "Onze",
	             "Doze", "Treze", "Quatorze", "Quinze", "Dezesseis",
	             "Dezessete", "Dezoito", "Dezenove"};
	    String[] centena = {"", "Cento", "Duzentos", "Trezentos",
	             "Quatrocentos", "Quinhentos", "Seiscentos",
	             "Setecentos", "Oitocentos", "Novecentos"};
	    String[] dezena = {"", "", "Vinte", "Trinta", "Quarenta", "Cinquenta",
	             "Sessenta", "Setenta", "Oitenta", "Noventa"};
	    String[] qualificaS = {"", "Mil", "Milhão", "Bilhão", "Trilhão"};
	    String[] qualificaP = {"", "Mil", "Milhões", "Bilhões", "Trilhões"};
	 
	// definindo o extenso da parte inteira do valor
	    int n, unid, dez, cent, tam, i = 0;
	    boolean umReal = false, tem = false;
	    while (!vlrS.equals("0")) {
	      tam = vlrS.length();
	// retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
	// 1a. parte = 789 (centena)
	// 2a. parte = 456 (mil)
	// 3a. parte = 123 (milhões)
	      if (tam > 3) {
	         vlrP = vlrS.substring(tam-3, tam);
	         vlrS = vlrS.substring(0, tam-3);
	      }
	      else { // última parte do valor
	        vlrP = vlrS;
	        vlrS = "0";
	      }
	      if (!vlrP.equals("000")) {
	         saux = "";
	         if (vlrP.equals("100"))
	            saux = "cem";
	         else {
	           n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
	           cent = n / 100;                  // cent = 3 (centena trezentos)
	           dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
	           unid = (n % 100) % 10;           // unid = 1 (unidade um)
	           if (cent != 0)
	              saux = centena[cent];
	           if ((n % 100) <= 19) {
	              if (saux.length() != 0)
	                 saux = saux + " e " + unidade[n % 100];
	              else saux = unidade[n % 100];
	           }
	           else {
	              if (saux.length() != 0)
	                 saux = saux + " e " + dezena[dez];
	              else saux = dezena[dez];
	              if (unid != 0) {
	                 if (saux.length() != 0)
	                    saux = saux + " e " + unidade[unid];
	                 else saux = unidade[unid];
	              }
	           }
	         }
	         if (vlrP.equals("1") || vlrP.equals("001")) {
	            if (i == 0) // 1a. parte do valor (um real)
	               umReal = true;
	            else saux = saux + " " + qualificaS[i];
	         }
	         else if (i != 0)
	                 saux = saux + " " + qualificaP[i];
	         if (s.length() != 0)
	            s = saux + ", " + s;
	         else s = saux;
	      }
	      if (((i == 0) || (i == 1)) && s.length() != 0)
	         tem = true; // tem centena ou mil no valor
	      i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
	    }
	 
	    if (s.length() != 0) {
	       if (umReal)
	          s = s + " Real";
	       else if (tem)
	               s = s + " Reais";
	            else s = s + " de Reais";
	    }
	 
	// definindo o extenso dos centavos do valor
	    if (!centavos.equals("0")) { // valor com centavos
	       if (s.length() != 0) // se não é valor somente com centavos
	          s = s + " e ";
	       if (centavos.equals("1"))
	          s = s + "Um Centavo";
	       else {
	         n = Integer.parseInt(centavos, 10);
	         if (n <= 19)
	            s = s + unidade[n];
	         else {             // para n = 37, tem-se:
	           unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
	           dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
	           s = s + dezena[dez];
	           if (unid != 0)
	              s = s + " e " + unidade[unid];
	         }
	         s = s + " Centavos";
	         System.out.println(s);
	       }
	    }
	    return(s);
	   
	  }
	// Salvar

	public String salvar() {
		data =hoje();
		vlr = recibos.getVlr();
		
		if(vlr == 0) {
		
			FacesMessage msm = new FacesMessage("Insira um valor maior que zero!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			
			return null;
		}else {
			recibos.setValorExtenso("("+valorPorExtenso(vlr)+")");
			
			recibosServices.salvar(recibos);
			recibosDialog = recibos;
			System.out.println(recibosDialog);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('imprimirDialog').show();");
		}
		recibos = new Recibos();
		
		return null;
	}
	
	public String salvarEditar() {
		data =hoje();
		vlr = recibos.getVlr();
		
		if(vlr == 0) {
		
			FacesMessage msm = new FacesMessage("Insira um valor maior que zero!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			
			return null;
		}else {
			recibos.setValorExtenso("("+valorPorExtenso(vlr)+")");
			
			recibosServices.salvar(recibos);
			FacesMessage msm = new FacesMessage("Recibo editado com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		recibos = new Recibos();
		
		return "Pesquisa?faces-redirect=true";
	}

	//imprimir pesquisa
	
	public void imprimir(Recibos recibos) {
		this.recibos = recibos;
		data = hoje();
		pesquisaDialog = recibos;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('imprimirDialog').show();");
	}
	
	// Excluir
	
	public void PrepararExcluirRecibo(Recibos recibos) {
		reciboExclusao = recibos;
	}

	public void ExcluiRecibo() {
		recibosServices.excluir(reciboExclusao);
		listaRecibos = recibosServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	//Editar
	public String editar(Recibos recibos) {
		this.recibos = recibos;
		return "Editar?faces-redirect=true";
	}
	//Pesquisas
	
	public Recibos getPesquisaDialog() {
		return pesquisaDialog;
	}

	public void setPesquisaDialog(Recibos pesquisaDialog) {
		this.pesquisaDialog = pesquisaDialog;
	}

	public void buscarRecibo() {
		listaRecibos = recibosServices.listarNome(funcionario);
	}

	// Getters e setters

	

	public Recibos getRecibos() {
		return recibos;
	}

	public Recibos getRecibosDialog() {
		return recibosDialog;
	}

	public void setRecibosDialog(Recibos recibosDialog) {
		this.recibosDialog = recibosDialog;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public void setRecibos(Recibos recibos) {
		this.recibos = recibos;
	}

	public List<Recibos> getListaRecibos() {
		return listaRecibos;
	}

	public void setListaRecibos(List<Recibos> listaRecibos) {
		this.listaRecibos = listaRecibos;
	}

	
}
