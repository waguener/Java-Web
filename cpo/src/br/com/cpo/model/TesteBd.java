package br.com.cpo.model;

import javax.persistence.Id;

public class TesteBd {

	
	private String codAlt;
	private String nomeProd;
	private String codEstr;
	private String qtd;
	private String uniMedida;
	
	//Getters e Setters
	@Id
	public String getCodAlt() {
		return codAlt;
	}
	public void setCodAlt(String codAlt) {
		this.codAlt = codAlt;
	}
	
	public String getNomeProd() {
		return nomeProd;
	}
	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}
	public String getCodEstr() {
		return codEstr;
	}
	public void setCodEstr(String codEstr) {
		this.codEstr = codEstr;
	}
	public String getQtd() {
		return qtd;
	}
	public void setQtd(String qtd) {
		this.qtd = qtd;
	}
	public String getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}
	
	
}
