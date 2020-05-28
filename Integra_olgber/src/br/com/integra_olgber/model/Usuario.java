package br.com.integra_olgber.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = -8807068630375051510L;

	public static final String LISTAR = "from Usuario";
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private Boolean ativo = true;
	private String tipo;
	private Agencia agencia;
	
	// Setters e Getters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@ManyToOne
	@JoinColumn(name="agencia_id")
	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return authorities;
	}

	@Override
	@Transient
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	@Transient
	public String getUsername() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Boolean usuarioNome() {
		
		boolean teste = false; 
		
		if ( getAgencia().getNome().equals("OLGBER")	) {
			teste = true ; 
		}
			
		return teste ; 
		
		
	}
	
public Boolean agenciaRhMaior() {
		
		boolean teste = false; 
		
		if ( getAgencia().getNome().equals("RH MAIOR")	) {
			teste = true ; 
		}
			
		return teste ; 
		
		
	}

public Boolean agenciaNovaEra() {
	
	boolean teste = false; 
	
	if (getAgencia().getNome().equals("NOVA ERA")) {
		teste = true ; 
	}
		
	return teste ; 
	
	
}


	

}
