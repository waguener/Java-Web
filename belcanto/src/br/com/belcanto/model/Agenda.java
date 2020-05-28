package br.com.belcanto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Agenda implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Long id;
	private Aluno aluno;
	private Professor professor;
	private Curso curso;
	private Modulo modulo;
	private AulaComp aulaComp;
	private List<Horario> horarios = new ArrayList<Horario>();
	
	
	
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_professor")
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_aluno")
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_hora")
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_curso")
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_modulo")
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_aula")
	public AulaComp getAulaComp() {
		return aulaComp;
	}
	public void setAulaComp(AulaComp aulaComp) {
		this.aulaComp = aulaComp;
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
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
