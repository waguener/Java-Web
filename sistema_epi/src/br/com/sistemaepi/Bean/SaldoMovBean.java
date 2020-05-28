package br.com.sistemaepi.Bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="tbl_saldo")
public class SaldoMovBean implements Serializable{

	private static final long serialVersionUID = 6041780259674637718L;
	
	

	private Long id;
	private EntregaBean protocolo;
	private FuncionarioBean codFunc;
	private EpiBean codEpi;
	private Integer saldo;
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne
	@JoinColumn(name="id_protocolo")
	public EntregaBean getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(EntregaBean protocolo) {
		this.protocolo = protocolo;
	}
	
	@ManyToOne
	public FuncionarioBean getCodFunc() {
		return codFunc;
	}
	public void setCodFunc(FuncionarioBean codFunc) {
		this.codFunc = codFunc;
	}
	
	@ManyToOne
	public EpiBean getCodEpi() {
		return codEpi;
	}
	public void setCodEpi(EpiBean codEpi) {
		this.codEpi = codEpi;
	}
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
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
		SaldoMovBean other = (SaldoMovBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
