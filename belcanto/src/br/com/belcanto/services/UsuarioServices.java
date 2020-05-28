package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Usuario;

@Service
@Transactional
public class UsuarioServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Usuario usuario) {
		em.merge(usuario);
		em.close();
	}

	public void excluir(Usuario usuario) {
		em.remove(em.merge(usuario));
		em.close();
	}

	public List<Usuario> listar() {
		try {
			return em.createQuery("from Usuario", Usuario.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario obterPorLogin(String login) {

		try {
			return em.createQuery("from Usuario where login = :login", Usuario.class).setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}

	}
	
	public Usuario obterPorUser(String nome) {

		try {
			return em.createQuery("from Usuario where nome = :nome", Usuario.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}

	}
	public Usuario obterPorId(Long id) {
		try {
			return em.find(Usuario.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}
}
