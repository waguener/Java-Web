package br.com.escolamusica.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@NamedQueries({
	@NamedQuery(name=Usuario.LISTAR,query=Usuario.LISTAR)
})
public class Usuario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String SEQ_NAME = "usuario_seq";
	
	public static final String LISTAR = "from Usuario";
 	
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private boolean ativo = true;
	private Set<Perfil> perfis = new HashSet<Perfil>();
	
	
	
	public Usuario() {
		super();
	}
	
	

	public Usuario( String nome, String login, String senha) {
		super();
		
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		
	}


	@Id
	@SequenceGenerator(name=SEQ_NAME , sequenceName=SEQ_NAME , allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator=SEQ_NAME)
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<Perfil> getPerfis() {
		return perfis;
	}



	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
	@Transient
	public boolean isPossuePermissao(String nomePermissao) {
		for(Perfil perfil : perfis) {
			for(Permissao permissao: perfil.getPermissoes()) {
				if(nomePermissao.equals(permissao.obterRole())){
					return true;
				}				
			}
		}
		return false;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		for(Perfil p : perfis) { 
			for(Permissao permissao : p.getPermissoes()) {
				authorities.add(new SimpleGrantedAuthority(permissao.obterRole()));
			}
		}
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
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ativo;
	}
	
	

}
