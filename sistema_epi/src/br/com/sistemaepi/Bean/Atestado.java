package br.com.sistemaepi.Bean;

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
public class Atestado implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private PontoDiario pontoDiario;
	private Arquivo arquivo;
	private Date atestadoHoraInicio;
	private Date atestadoHoraFim;
	private Date atestadoDiaInicio;
	private Date atestadoDiaFim;
	private String cid;
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne ( cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ponto")
	public PontoDiario getPontoDiario() {
		return pontoDiario;
	}
	public void setPontoDiario(PontoDiario pontoDiario) {
		this.pontoDiario = pontoDiario;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo")
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	@Temporal(TemporalType.TIME)
	public Date getAtestadoHoraInicio() {
		return atestadoHoraInicio;
	}
	public void setAtestadoHoraInicio(Date atestadoHoraInicio) {
		this.atestadoHoraInicio = atestadoHoraInicio;
	}
	@Temporal(TemporalType.TIME)
	public Date getAtestadoHoraFim() {
		return atestadoHoraFim;
	}
	public void setAtestadoHoraFim(Date atestadoHoraFim) {
		this.atestadoHoraFim = atestadoHoraFim;
	}
	@Temporal(TemporalType.DATE)
	public Date getAtestadoDiaInicio() {
		return atestadoDiaInicio;
	}
	public void setAtestadoDiaInicio(Date atestadoDiaInicio) {
		this.atestadoDiaInicio = atestadoDiaInicio;
	}
	@Temporal(TemporalType.DATE)
	public Date getAtestadoDiaFim() {
		return atestadoDiaFim;
	}
	public void setAtestadoDiaFim(Date atestadoDiaFim) {
		this.atestadoDiaFim = atestadoDiaFim;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
		Atestado other = (Atestado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
