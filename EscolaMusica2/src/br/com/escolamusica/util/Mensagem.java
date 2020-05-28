package br.com.escolamusica.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void mensagemErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public static void mensagemAlerta(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public static void mensagemInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
}
