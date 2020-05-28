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
public class Recebimento implements Serializable{

	private static final long serialVersionUID = -1734706098199242798L;

	private Long id;
	private String codigo;
	private String produto;
	private Date dataEntrada;
	private String status;
	private Date dataValidade;
	private String horaEntrada;
	private String delivery;
	private String lote;
	private String notaFiscal;
	private int pesoFisico;
	private int pesoFicha;
	private int diferenca;
	private Integer qtdAtual;
	private String tipo;
	private String uniMedida;
	private String numeroRack;
	private String responsavel;
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	@Temporal(TemporalType.DATE)
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataValidade() {
		return dataValidade;
	}
	
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public int getPesoFisico() {
		return pesoFisico;
	}
	public void setPesoFisico(int pesoFisico) {
		this.pesoFisico = pesoFisico;
	}
	public int getPesoFicha() {
		return pesoFicha;
	}
	public void setPesoFicha(int pesoFicha) {
		this.pesoFicha = pesoFicha;
	}
	
	public int getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(int diferenca) {
		this.diferenca = diferenca;
	}
	
	
	public Integer getQtdAtual() {
		return qtdAtual;
	}
	public void setQtdAtual(Integer qtdAtual) {
		this.qtdAtual = qtdAtual;
	}
	public String getNumeroRack() {
		return numeroRack;
	}
	public void setNumeroRack(String numeroRack) {
		this.numeroRack = numeroRack;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	
	public String getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		Recebimento other = (Recebimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
