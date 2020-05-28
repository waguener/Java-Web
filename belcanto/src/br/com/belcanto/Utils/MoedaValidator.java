package br.com.belcanto.Utils;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("MoedaValidator")
public class MoedaValidator implements Validator{

	public void validate(FacesContext ctx, UIComponent comp, Object val) throws ValidatorException {
	       
		   BigDecimal valor = (BigDecimal) val;
	       
	       if(val == null)
				return;				
	       
	        if (valor.intValue() == 0) {
	    	   FacesMessage message = new FacesMessage("Preencha com o valor válido maior que zero");
	           message.setSeverity(FacesMessage.SEVERITY_ERROR);
	           throw new ValidatorException(message);
	       }
	       
	   }
}
