package br.com.sistemaepi.dto;

import java.util.Date;

public class EquipamentosEmUsoDTO {

	private String nomeEquipamento;
	private Date dataEntrega;
	private Integer diasEmUso;
	private String hora;
	private Integer quantidade;
	
	public EquipamentosEmUsoDTO() {
		super();
	}

	public EquipamentosEmUsoDTO(String nomeEquipamento, Date dataEntrega, String hora, Integer quantidade, Integer diasEmUso) {
		super();
		this.nomeEquipamento = nomeEquipamento;
		this.dataEntrega = dataEntrega;
		this.hora = hora;
		this.quantidade = quantidade;
		this.diasEmUso = diasEmUso;
	}
	
	
	
	
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}

	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
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
	
	
	
}
