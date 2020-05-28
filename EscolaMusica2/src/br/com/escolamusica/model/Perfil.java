package br.com.escolamusica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@NamedQueries({
@NamedQuery(name=Perfil.LISTAR_TODOS, query=Perfil.LISTAR_TODOS)
})
public class Perfil implements Serializable {

	private static final long serialVersionUID = 3194564874957924901L;

	private static final String SEQ_NAME = "perfil_seq";

	public static final String LISTAR_TODOS = "from Perfil";
	
	private Long id;
	private String nome;
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	
	@Id
	@SequenceGenerator(name=SEQ_NAME , sequenceName=SEQ_NAME , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=SEQ_NAME)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank(message="Informe o nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
