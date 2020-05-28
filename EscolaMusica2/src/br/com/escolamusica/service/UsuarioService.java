package br.com.escolamusica.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Usuario;

@Service
@Transactional
public class UsuarioService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	public Usuario obterPorLogin(String login) {
		return (Usuario) entityManager.createQuery("from Usuario where login =:login").setParameter("login", login)
				.getSingleResult();
	}

	public List<Usuario> listar() {
		return entityManager.createNamedQuery(Usuario.LISTAR, Usuario.class).getResultList();
	}

	public Usuario obterPorId(Long id) {

		return entityManager.find(Usuario.class, id);
	}

}
