package br.com.belcanto.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Receber {
	
	private Double valorReceber;
	private Date dtVencimento;
	private Date dtPagamento;
	private String status;
	private String tipo;
	private Integer parcela;
	
	//Getters e setters
	public Double getValorReceber() {
		return valorReceber;
	}
	public void setValorReceber(Double valorReceber) {
		this.valorReceber = valorReceber;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	
	

}
