package br.com.integra_olgber.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EventoFuncionario implements Serializable{

	private static final long serialVersionUID = -7474404574339143502L;
	
	private Long id;
	private Funcionario funcionario;
	private String tipo;
	private Date dataEvento;
	private Date dataFalta;
	private Date dataAtestado;
	private String horaFalta;
	private String horaAtestado;
	private int falta;
	private String obsfalta;
	private String obsAtestado;
	private Arquivo arquivo;
	private Agencia agencia;
	
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
	@JoinColumn(name="funcionario_id")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_evento")
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_falta")
	public Date getDataFalta() {
		return dataFalta;
	}
	public void setDataFalta(Date dataFalta) {
		this.dataFalta = dataFalta;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_atestado")
	public Date getDataAtestado() {
		return dataAtestado;
	}
	public void setDataAtestado(Date dataAtestado) {
		this.dataAtestado = dataAtestado;
	}
	public String getObsfalta() {
		return obsfalta;
	}
	public void setObsfalta(String obsfalta) {
		this.obsfalta = obsfalta;
	}
	public String getObsAtestado() {
		return obsAtestado;
	}
	public void setObsAtestado(String obsAtestado) {
		this.obsAtestado = obsAtestado;
	}
	@OneToOne
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	public int getFalta() {
		return falta;
	}
	public void setFalta(int falta) {
		this.falta = falta;
	}
	public String getHoraFalta() {
		return horaFalta;
	}
	public void setHoraFalta(String horaFalta) {
		this.horaFalta = horaFalta;
	}
	public String getHoraAtestado() {
		return horaAtestado;
	}
	public void setHoraAtestado(String horaAtestado) {
		this.horaAtestado = horaAtestado;
	}
	
	@ManyToOne
	@JoinColumn(name="agencia_id")
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	
}
