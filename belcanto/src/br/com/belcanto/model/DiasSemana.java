package br.com.belcanto.model;

public enum DiasSemana {

	SEGUNDA("Segunda - Feira"), 
	TER�A("Ter�a - Feira"), 
	QUARTA("Quarta - Feira"), 
	QUINTA("Quinta - Feira"), 
	SEXTA("Sexta - Feira"), 
	SABADO("S�bado");
	
	private String label;

	private DiasSemana(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
