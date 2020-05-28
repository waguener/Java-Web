package br.com.escolamusica.model;

public enum TipoCurso {

	CORDAS("Cordas"), METAIS("Metais"), MADEIRAS("Madeiras"), PERCUSSAO("Percuss�o");
	
	private String label;
	
	TipoCurso(String label){
		this.label = label;
		
	}

	public String getLabel() {
		return label;
	};
}
