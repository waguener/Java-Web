package br.com.belcanto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clausula implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private Integer numClausula;
	private String clausula;
	
	
	//Getters e Setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumClausula() {
		return numClausula;
	}
	public void setNumClausula(Integer numClausula) {
		this.numClausula = numClausula;
	}
	@Column(columnDefinition="text", length=10485760)
	public String getClausula() {
		return clausula;
	}
	public void setClausula(String clausula) {
		this.clausula = clausula;
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
		Clausula other = (Clausula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
