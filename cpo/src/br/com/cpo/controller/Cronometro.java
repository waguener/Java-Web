package br.com.cpo.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@ManagedBean
@Controller
@Scope("session")
public class Cronometro implements Serializable{

	private static final long serialVersionUID = 537700563700605243L;

	private String resultado;
	private Timer cronometro;
	private DateFormat formato = new SimpleDateFormat("HH:mm:ss");
	private Calendar calendario = Calendar.getInstance();
	private final byte contagem;
	public static final byte Progressiva = 1;
	private String tempo;
	
	//Métodos
	
	public Cronometro(int ano, int mes, int dia, int horas, int minutos, int segundos, byte tipoContagem) {
		this.cronometro = new Timer();
		calendario.set(ano, mes, dia, horas, minutos, segundos);
		this.contagem = tipoContagem;
		
	}
	
	public void cronometro() {
		
		TimerTask tarefa = new TimerTask() {
			
			@Override
			public void run() {
			
				
				tempo = getTime();
				System.out.println(tempo);
			}
			
			
		};
			
		cronometro.scheduleAtFixedRate(tarefa, 0, 1000);	
		this.cronometro = null;
	}
	 public String getTime() {
		 calendario.add(Calendar.SECOND, contagem);
		 return formato.format(calendario.getTime());
	 }
	
	 public void inicia() {
		 Cronometro cronometro = new Cronometro(0, 0, 0, 00, 00, 00, Progressiva);
		 cronometro.cronometro();
		 
	
	 }
	 
	 
	
	
	
	//Getters e Setters
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
	
}
