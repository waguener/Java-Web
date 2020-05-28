package br.com.cpo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProdEst implements Serializable{


	private static final long serialVersionUID = -4254977101516249372L;

	
	private Long id;
	private String cod;
	private String produto;
	private String lote;
	private Integer qtdArmazem;
	private Integer qtdProducao;
	private Integer total;
	private String uniMedida;
	private Date data;
	
	
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Integer getQtdArmazem() {
		return qtdArmazem;
	}
	public void setQtdArmazem(Integer qtdArmazem) {
		this.qtdArmazem = qtdArmazem;
	}
	public Integer getQtdProducao() {
		return qtdProducao;
	}
	public void setQtdProducao(Integer qtdProducao) {
		this.qtdProducao = qtdProducao;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Temporal(TemporalType.DATE)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
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
		ProdEst other = (ProdEst) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
