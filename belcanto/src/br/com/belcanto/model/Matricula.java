package br.com.belcanto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Matricula implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numMatricula;
	private Date dtMatricula;
	private Date dtInicioAula;
	private Agenda agenda;
	private Contrato contrato;
	private Boolean ativo;
	private Integer desconto;
	private Double valorMensalidade;
	private Integer vencMensalidade;
	private Integer parcMatricula;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtMatricula() {
		return dtMatricula;
	}
	public void setDtMatricula(Date dtMatricula) {
		this.dtMatricula = dtMatricula;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtInicioAula() {
		return dtInicioAula;
	}
	public void setDtInicioAula(Date dtInicioAula) {
		this.dtInicioAula = dtInicioAula;
	}
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "id_agenda")
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contrato")
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	
	public Double getValorMensalidade() {
		return valorMensalidade;
	}
	public void setValorMensalidade(Double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}
	
	
	public Integer getVencMensalidade() {
		return vencMensalidade;
	}
	public void setVencMensalidade(Integer vencMensalidade) {
		this.vencMensalidade = vencMensalidade;
	}
	
	public Integer getParcMatricula() {
		return parcMatricula;
	}
	public void setParcMatricula(Integer parcMatricula) {
		this.parcMatricula = parcMatricula;
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
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
