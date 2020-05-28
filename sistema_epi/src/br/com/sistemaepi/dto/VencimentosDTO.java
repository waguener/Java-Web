package br.com.sistemaepi.dto;

import java.util.Date;

public class VencimentosDTO {

	private String nomeFunc;
	private String nomeEpi;
	private Date dataEntrega;
	private Integer diasEmUso;
	private String hora;
	private Integer quantidade;
	
	public VencimentosDTO() {
		super();
	}

	public VencimentosDTO(String nomeFunc,String nomeEpi, Date dataEntrega, String hora, Integer quantidade, Integer diasEmUso) {
		super();
		this.nomeFunc = nomeFunc;
		this.nomeEpi = nomeEpi;
		this.dataEntrega = dataEntrega;
		this.hora = hora;
		this.quantidade = quantidade;
		this.diasEmUso = diasEmUso;
	}
	
	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getDiasEmUso() {
		return diasEmUso;
	}

	public void setDiasEmUso(Integer diasEmUso) {
		this.diasEmUso = diasEmUso;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getNomeEpi() {
		return nomeEpi;
	}

	public void setNomeEpi(String nomeEpi) {
		this.nomeEpi = nomeEpi;
	}
	
	
	
}
