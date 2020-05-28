package br.com.escolamusica.model;

import java.io.File;
import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Arquivo {

	private static final String SEQ_NAME = "arquivo_seq";
	
	
	private Long id;
	private String nome;
	private String caminho;
	private InputStream stream;
	private File file;

	@Id
	@SequenceGenerator(name=SEQ_NAME , sequenceName=SEQ_NAME , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=SEQ_NAME)
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	@Transient
	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	@Transient
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
