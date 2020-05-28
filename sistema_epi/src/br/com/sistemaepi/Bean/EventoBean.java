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
@Table(name="tbl_eventos")
public class EventoBean implements Serializable{

	private static final long serialVersionUID = 2275013093673687331L;
	
	private Long id;
	private String titulo;
	private Date inicio;
	private Date fim;
	private String descricao;
	private Boolean conclusao;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Temporal(TemporalType.TIMESTAMP )
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getConclusao() {
		return conclusao;
	}
	public void setConclusao(Boolean conclusao) {
		this.conclusao = conclusao;
	}
	
}
