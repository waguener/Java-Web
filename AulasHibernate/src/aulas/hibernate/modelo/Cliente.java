package aulas.hibernate.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="cli_clientes")
public class Cliente {

	private static final String SEQ_NAME = "cliente_seq";
	
	private Long id;
	private String nome;
	private Date dataNascimento;
	private TipoCliente tipo;
	private byte[] foto;
	private List<String> telefone = new ArrayList<>();
	
	@Id
	@SequenceGenerator(name=SEQ_NAME,sequenceName=SEQ_NAME, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=SEQ_NAME)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="cli_nome", nullable=false, unique=true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="cli_data_nascimento")
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Enumerated(EnumType.STRING)
	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}
	@Lob
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="cli_contato", joinColumns=@JoinColumn(name="cli_id"))
	@Column(name="cli_tel")
	public List<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}

	@Transient
	public Integer getIdade() {
		//Lógica para calcular idade
		return 30;
	}

	
	
}
