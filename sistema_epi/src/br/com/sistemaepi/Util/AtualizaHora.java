package br.com.sistemaepi.Util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="atualizaHora")
@ViewScoped
public class AtualizaHora implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private String hoje = new SimpleDateFormat("HH:mm").format(new Date());

	    public String getHoje() {
	        return hoje;
	    }

	    public void setHoje(String hoje) {
	        this.hoje = hoje;
	    }
	    //este método atualiza a string
	    public void atualizarHora(){
	       this.hoje = new SimpleDateFormat("HH:mm").format(new Date());
	    }

	    
	
	
}
