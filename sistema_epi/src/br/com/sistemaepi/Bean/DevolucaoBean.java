package br.com.sistemaepi.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_devolucao")
public class DevolucaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpf;
	private String epi;
	private String ca;
	private String lote;
	private Date dataEntrega;
	private String horaEntrega;
	private Integer quantidadeEntregada;
	private String protocoloEntrega;
	private Integer qtdDevolvida;
	private Date dataDevolucao;
	private String horaDevolucao;
	private String estado;
	private String status;
	private int totalDias;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEpi() {
		return epi;
	}
	public void setEpi(String epi) {
		this.epi = epi;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	@Temporal(TemporalType.DATE)
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getHoraEntrega() {
		return horaEntrega;
	}
	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	public Integer getQuantidadeEntregada() {
		return quantidadeEntregada;
	}
	public void setQuantidadeEntregada(Integer quantidadeEntregada) {
		this.quantidadeEntregada = quantidadeEntregada;
	}
	public String getProtocoloEntrega() {
		return protocoloEntrega;
	}
	public void setProtocoloEntrega(String protocoloEntrega) {
		this.protocoloEntrega = protocoloEntrega;
	}
	public Integer getQtdDevolvida() {
		return qtdDevolvida;
	}
	public void setQtdDevolvida(Integer qtdDevolvida) {
		this.qtdDevolvida = qtdDevolvida;
	}
	@Temporal(TemporalType.DATE)
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getHoraDevolucao() {
		return horaDevolucao;
	}
	public void setHoraDevolucao(String horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public int getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}
	
	
	
}
