package br.com.recibos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Recibos implements Serializable{

	private static final long serialVersionUID = -8271527800505918681L;

	
	private Long id;
	private String funcionario;
	private Date dataRecibo;
	private String rg;
	private String cpf;
	private Double vlr;
	private String valorExtenso;
	private String referente;
	private Date dataPagamento;
	private Double total;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Double getVlr() {
		return vlr;
	}
	public void setVlr(Double vlr) {
		this.vlr = vlr;
	}
	public String getValorExtenso() {
		return valorExtenso;
	}
	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="dataRecibo", nullable=false)
	public Date getDataRecibo() {
		return dataRecibo;
	}
	public void setDataRecibo(Date dataRecibo) {
		this.dataRecibo = dataRecibo;
	}
	
	public String getReferente() {
		return referente;
	}
	public void setReferente(String referente) {
		this.referente = referente;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="dataPagamento", nullable=false)
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
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
		Recibos other = (Recibos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
		
}
