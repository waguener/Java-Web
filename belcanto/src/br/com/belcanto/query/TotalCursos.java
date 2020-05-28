package br.com.belcanto.query;

public class TotalCursos {

	
	private String curso;
	private Integer total;
	private Integer valor;
	
	//Init
	public TotalCursos() {
		super();
	}
	
	
	//Método
	public TotalCursos(String curso, Integer total, Integer valor) {
		super();
		this.curso = curso;
		this.total = total;
		this.valor = valor;
	}

	
	//Getters e Setters
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public Integer getValor() {
		return valor;
	}


	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
}
