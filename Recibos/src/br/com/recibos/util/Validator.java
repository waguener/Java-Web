package br.com.recibos.util;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;


@FacesValidator("moedaValidator")
public class Validator implements javax.faces.validator.Validator{

	 public void validate(FacesContext ctx, UIComponent comp, Object val) throws ValidatorException {
		   BigDecimal valor = (BigDecimal) val;
	       if(val == null)
				return;				
	        if (valor.intValue() == 0) {
	    	   FacesMessage msm = new FacesMessage("Preencha com o valor válido maior que zero");
	    	   FacesContext.getCurrentInstance().addMessage(null, msm);
	           msm.setSeverity(FacesMessage.SEVERITY_ERROR);
	           System.out.println(msm);
	           throw new ValidatorException(msm);
	           
	       }
	   }
	
}
