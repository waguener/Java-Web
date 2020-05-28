package br.com.sistemaepi.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class AtuCadastral implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private FuncionarioBean funcionarioBean;
	private Endereco endereco = new Endereco();
	private Contato contato = new Contato();
	private List<Dependentes> dependentes = new ArrayList<Dependentes>();
	private List<Cursos> cursos = new ArrayList<Cursos>();
	private Date dtNascimento;
	private String civil;
	private Boolean filhos;
	private String idade;
	private String re;
	private String pis;
	private String cnh;
	private String cnhNum;
	private Date validadeCnh;
	private String instrucao;
	private String statusInstrucao;
	
	
	//getters e Setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="id_func")
	public FuncionarioBean getFuncionarioBean() {
		return funcionarioBean;
	}
	public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
		this.funcionarioBean = funcionarioBean;
	}
	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Embedded
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="id_dependentes")
	public List<Dependentes> getDependentes() {
		return dependentes;
	}
	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	
	@JoinColumn(name="id_cursos")
	public List<Cursos> getCursos() {
		return cursos;
	}
	public void setCursos(List<Cursos> cursos) {
		this.cursos = cursos;
	}
	@Temporal(TemporalType.DATE)
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getCivil() {
		return civil;
	}
	public void setCivil(String civil) {
		this.civil = civil;
	}
	public Boolean getFilhos() {
		return filhos;
	}
	public void setFilhos(Boolean filhos) {
		this.filhos = filhos;
	}
	
	
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public String getRe() {
		return re;
	}
	public void setRe(String re) {
		this.re = re;
	}
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	
	public Date getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(Date validadeCnh) {
		this.validadeCnh = validadeCnh;
	}
	public String getInstrucao() {
		return instrucao;
	}
	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}
	public String getStatusInstrucao() {
		return statusInstrucao;
	}
	public void setStatusInstrucao(String statusInstrucao) {
		this.statusInstrucao = statusInstrucao;
	}
	
	public String getCnhNum() {
		return cnhNum;
	}
	public void setCnhNum(String cnhNum) {
		this.cnhNum = cnhNum;
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
		AtuCadastral other = (AtuCadastral) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
