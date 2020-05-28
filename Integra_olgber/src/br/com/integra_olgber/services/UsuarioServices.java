package br.com.integra_olgber.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Usuario;

@Service
@Transactional
public class UsuarioServices {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Usuario usuario) {

		entityManager.merge(usuario);
		entityManager.close();
	}

	public List<Usuario> listar() {
		return entityManager.createQuery("from Usuario order by id desc", Usuario.class).getResultList();

	}

	public void excluir(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
		entityManager.close();
	}

	public List<Usuario> porNome(String nome) {
		return entityManager.createQuery("from Usuario where nome like :nome order by id desc", Usuario.class)
				.setParameter("nome", nome + "%").getResultList();

	}

	public List<Usuario> login(String nome) {
		return entityManager.createQuery("from Usuario where nome = :nome order by id desc", Usuario.class)
				.setParameter("nome", nome).getResultList();

	}

	public Usuario obterPorLogin(String login) {
		
		Usuario teste = null; 
		try {
		teste = entityManager.createQuery("from Usuario where login = :login", Usuario.class)
				.setParameter("login", login).getSingleResult();
		}catch (Exception e) {
			
			return teste;
		}
		
		
		return teste;
		
	}
	
public Usuario buscarTipo(String login) {
		
		Usuario agenciaUsuario = null; 
		try {
		agenciaUsuario = entityManager.createQuery("select agencia from Usuario where login = :login", Usuario.class)
				.setParameter("login", login).getSingleResult();
		}catch (Exception e) {
			
			return agenciaUsuario;
		}
		
		
		return agenciaUsuario;
		
	}

	
}
