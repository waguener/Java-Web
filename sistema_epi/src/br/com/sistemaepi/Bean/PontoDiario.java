package br.com.sistemaepi.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PontoDiario implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private FuncionarioBean funcionarioBean;

	private Date dtEntrada;
	private String diaSemana;
	private String batida;
	private String obs;
	private String situacao;
	private String turno;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	public FuncionarioBean getFuncionarioBean() {
		return funcionarioBean;
	}
	public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
		this.funcionarioBean = funcionarioBean;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDtEntrada() {
		return dtEntrada;
	}
	
	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public String getObs() {
		return obs;
	}
	
	
	public String getBatida() {
		return batida;
	}
	public void setBatida(String batida) {
		this.batida = batida;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
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
		PontoDiario other = (PontoDiario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
