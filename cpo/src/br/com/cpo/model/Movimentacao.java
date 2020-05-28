package br.com.cpo.model;

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
public class Movimentacao implements Serializable{

	private static final long serialVersionUID = 7451901607898099545L;
	
	private Long id;
	private Recebimento recebimento;	
	private Date dataRetirada;
	private String horaSaida;
	private Integer qtdRetirada;
	private String resp;
	private Integer qtdDevCliente;
	private Date dataRetorno;
	private String horaRetorno;
	private Integer qtdRetorno;
	private String respRetorno;
	private String nfDevCliente;
	private Date dataDevCliente;
	private Boolean excluir = false;
	private String codbar;
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne
	@JoinColumn(name= "id_recebimento")
	public Recebimento getRecebimento() {
		return recebimento;
	}
	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public Integer getQtdRetirada() {
		return qtdRetirada;
	}
	public void setQtdRetirada(Integer qtdRetirada) {
		this.qtdRetirada = qtdRetirada;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public String getHoraRetorno() {
		return horaRetorno;
	}
	public void setHoraRetorno(String horaRetorno) {
		this.horaRetorno = horaRetorno;
	}
	public Integer getQtdRetorno() {
		return qtdRetorno;
	}
	public void setQtdRetorno(Integer qtdRetorno) {
		this.qtdRetorno = qtdRetorno;
	}
	public String getRespRetorno() {
		return respRetorno;
	}
	public void setRespRetorno(String respRetorno) {
		this.respRetorno = respRetorno;
	}
	public Integer getQtdDevCliente() {
		return qtdDevCliente;
	}
	public void setQtdDevCliente(Integer qtdDevCliente) {
		this.qtdDevCliente = qtdDevCliente;
	}
	public String getNfDevCliente() {
		return nfDevCliente;
	}
	public void setNfDevCliente(String nfDevCliente) {
		this.nfDevCliente = nfDevCliente;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataDevCliente() {
		return dataDevCliente;
	}
	public void setDataDevCliente(Date dataDevCliente) {
		this.dataDevCliente = dataDevCliente;
	}
	
	
	public Boolean getExcluir() {
		return excluir;
	}
	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}
	
	public String getCodbar() {
		return codbar;
	}
	public void setCodbar(String codbar) {
		this.codbar = codbar;
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
