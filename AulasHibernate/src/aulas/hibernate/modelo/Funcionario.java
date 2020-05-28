package aulas.hibernate.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cli_funcionario")
@NamedQueries({
	@NamedQuery(name = Funcionario.LISTAR_TODOS, query = Funcionario.LISTAR_TODOS)
})
public class Funcionario {

	private static final String SEQ_NAME = "funcionario_seq";
	
	public static final String LISTAR_TODOS = "from Funcionario order by nome";

	private Long id;
	private String nome;
	private Double salario;
	private VagasEstacionamento Vagas;
	private List<Telefone> telefones = new ArrayList<>();
	private List<Projeto> projetos = new ArrayList<>();
	private String cpf;
	private Date dataAdmissao;
	private Departamento departamento;

	

	public Funcionario() {
		super();
	}

	
	
	public Funcionario(String nome, Date dataAdmissao, String nomeDepartamento) {
		super();
		this.nome = nome;
		this.dataAdmissao = dataAdmissao;
		this.departamento = new Departamento(nomeDepartamento);
	}



	public Funcionario(String nome, Double salario, Date dataAdmissao, Departamento departamento) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
		this.departamento = departamento;
	}

	@Id
	@SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario")
	public VagasEstacionamento getVagas() {
		return Vagas;
	}

	public void setVagas(VagasEstacionamento vagas) {
		Vagas = vagas;
	}

	@OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name="id_funcionario")
	/*
	 * @JoinTable(name = "func_tel", joinColumns = @JoinColumn(name = "id_func"),
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "id_tel") })
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@ManyToMany(mappedBy = "funcionarios", fetch = FetchType.EAGER)
	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	@ManyToOne
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
