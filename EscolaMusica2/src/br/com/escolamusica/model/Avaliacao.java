package br.com.escolamusica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Avaliacao {

	private static final String SEQ_NAME = "avaliacao_seq";
	
	
	private Long id;
	private Matricula matricula;
	private Bimestre bimestre;
	private Integer Ano;
	private Date data;
	private Arquivo arquivo;
	private Double nota;
	
	@Id
	@SequenceGenerator(name=SEQ_NAME , sequenceName=SEQ_NAME , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=SEQ_NAME)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@NotNull(message="Selecione uma matrícula")
	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Selecione o bimestre")
	public Bimestre getBimestre() {
		return bimestre;
	}

	public void setBimestre(Bimestre bimestre) {
		this.bimestre = bimestre;
	}

	public Integer getAno() {
		return Ano;
	}

	public void setAno(Integer ano) {
		Ano = ano;
	}

	@Temporal(TemporalType.DATE)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@OneToOne
	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	@Transient
	public String getBimestreAno() {
		return bimestre.getLabel()+"/"+Ano;
	}
	
}
