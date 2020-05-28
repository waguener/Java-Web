package br.com.belcanto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String tipoPgt;
	private Date dtMovimentacao;
	private Double valorRecebido;
	private Double troco;
	private Double valorTotal;
	private String formaPgt;
	private String pagante;
	private String recebidor;
	private String aluno;
	
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoPgt() {
		return tipoPgt;
	}
	public void setTipoPgt(String tipoPgt) {
		this.tipoPgt = tipoPgt;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtMovimentacao() {
		return dtMovimentacao;
	}
	public void setDtMovimentacao(Date dtMovimentacao) {
		this.dtMovimentacao = dtMovimentacao;
	}
			
	public String getFormaPgt() {
		return formaPgt;
	}
	public void setFormaPgt(String formaPgt) {
		this.formaPgt = formaPgt;
	}
	
	public String getPagante() {
		return pagante;
	}
	public void setPagante(String pagante) {
		this.pagante = pagante;
	}
	public String getRecebidor() {
		return recebidor;
	}
	public void setRecebidor(String recebidor) {
		this.recebidor = recebidor;
	}
	
	public Double getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public Double getTroco() {
		return troco;
	}
	public void setTroco(Double troco) {
		this.troco = troco;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
