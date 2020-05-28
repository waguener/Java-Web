package br.com.sistemaepi.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="tbl_entrega")
public class EntregaBean implements Serializable{

	
	private static final long serialVersionUID = -2170166250521792523L;
	
	
	private Long id;
	private FuncionarioBean funcionario;
	private EpiBean epi;
	private Date data;
	private String protocolo;
	private Integer quantidade;
	private String estado;
	private String status;
	private String hora;	
	private Integer qtdEntregas;
	private String devolucao;
	private String situacao;
	private Integer validade;
	
	
	public EntregaBean(String protocolo) {
		super();
		this.protocolo = protocolo;
	}
	public EntregaBean() {
		super();
	}
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_criacao", nullable=false)
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	public Integer getQtdEntregas() {
		return qtdEntregas;
	}
	public void setQtdEntregas(Integer qtdEntregas) {
		this.qtdEntregas = qtdEntregas;
	}
	public String getDevolucao() {
		return devolucao;
	}
	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Integer getValidade() {
		return validade;
	}
	public void setValidade(Integer validade) {
		this.validade = validade;
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
		EntregaBean other = (EntregaBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
			
	
	
	
	
	
	
}
