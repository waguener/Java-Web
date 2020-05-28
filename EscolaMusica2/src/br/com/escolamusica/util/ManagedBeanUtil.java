package br.com.escolamusica.util;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

public class ManagedBeanUtil {

	
	
	public static void invalidarCampo(String idComponente, String mensagemErro) {
		UIInput input = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(idComponente);
		input.setValid(false);
		if(mensagemErro != null) {
			Mensagem.mensagemErro(mensagemErro);
		}
	}
	
	
}
