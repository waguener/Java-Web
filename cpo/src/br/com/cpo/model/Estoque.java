package br.com.cpo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Estoque implements Serializable{

	private static final long serialVersionUID = 8102738851504975838L;
	
	
	private Long id;
	private Recebimento recebimento;
	private int total;
	

	//Getters e Setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_recebimento")
	public Recebimento getRecebimento() {
		return recebimento;
	}


	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}


	


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
