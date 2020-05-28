package br.com.escolamusica.model;

public enum Bimestre {

	PRIMEIRO("Primeiro Bimestre"),
	SEGUNDO("Segundo Bimestre"),
	TERCEIRO("Terceiro Bimestre"),
	QUARTO("Quarto Bimestre");
	
	private String label;

	private Bimestre(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
