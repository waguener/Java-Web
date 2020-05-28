package br.com.cpo.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CodBar implements Serializable{

	private static final long serialVersionUID = 5468408652395989332L;
	

	public static String gerarCodBar() {
		String codBar2 = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
		String codBarFinal2 = ""+codBar2;
		return codBarFinal2;
	}
	
	private String codBar = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
	private String codBarFinal = ""+codBar;

	public String getCodBarFinal() {
		return codBarFinal;
	}
	public void setCodBarFinal(String codBarFinal) {
		this.codBarFinal = codBarFinal;
	}
	
	
}
