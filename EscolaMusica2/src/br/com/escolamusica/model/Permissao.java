package br.com.escolamusica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;

import br.com.escolamusica.util.EscolaMusicaStringUtils;

@Entity
@NamedQueries({
@NamedQuery(name=Permissao.LISTAR_TODAS, query=Permissao.LISTAR_TODAS)
})
public class Permissao implements Serializable {

	private static final long serialVersionUID = -2017301145000538752L;

	private static final String SEQ_NAME = "permissao_seq";

	public static final String LISTAR_TODAS = "from Permissao";
	
	private Long id;
	private String nome;

	
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
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String obterRole() {
		return "ROLE_" + EscolaMusicaStringUtils.removerCaracteresEspeciais(nome).replaceAll("","_").toUpperCase();
	}

}
