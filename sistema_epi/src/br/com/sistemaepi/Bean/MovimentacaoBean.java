package br.com.sistemaepi.Bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_movimentacao")
public class MovimentacaoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	 private Long id;
	 private FuncionarioBean funcionario;
	 private EpiBean epi;
	 private String status;
	 
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
	public FuncionarioBean getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}
	@ManyToOne
	@JoinColumn(name="id_epi")
	public EpiBean getEpi() {
		return epi;
	}
	public void setEpi(EpiBean epi) {
		this.epi = epi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
}
