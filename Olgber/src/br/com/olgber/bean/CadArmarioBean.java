package br.com.olgber.bean;

public class CadArmarioBean {

	int id,narmario;
	String status,chavereserva,tipo,data,Situacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNarmario() {
		return narmario;
	}
	public void setNarmario(int narmario) {
		this.narmario = narmario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getChavereserva() {
		return chavereserva;
	}
	public void setChavereserva(String chavereserva) {
		this.chavereserva = chavereserva;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSituacao() {
		return Situacao;
	}
	public void setSituacao(String situacao) {
		Situacao = situacao;
	}
	
	
}
