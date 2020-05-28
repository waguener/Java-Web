package br.com.sistemaepi.Util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "gerarProtocolo")
@ViewScoped
public class GerarProtocolo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String gerarProtocolo() {
		String protocolo2 = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
		String protocoloFinal2 = ""+protocolo2;
		return protocoloFinal2;
	}
	
	private String protocolo = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
	private String protocoloFinal = ""+protocolo;
	
	public String getProtocoloFinal() {
		return protocoloFinal;
	}

	public void setProtocoloFinal(String protocoloFinal) {
		this.protocoloFinal = protocoloFinal;
	}

}
