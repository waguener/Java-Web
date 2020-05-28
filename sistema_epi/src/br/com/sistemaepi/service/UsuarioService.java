package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.UsuarioBean;

@Service
@Transactional
public class UsuarioService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(UsuarioBean usuario) {
		entityManager.merge(usuario);
	}

	public UsuarioBean obterPorLogin(String login) {
		List<UsuarioBean> usuarios = entityManager.createQuery("from UsuarioBean where login =:login", UsuarioBean.class)
				.setParameter("login", login).getResultList();
		if(usuarios.isEmpty()) {
			return null;
		}
		return usuarios.get(0);
	}
	

	public UsuarioBean obterPorId(Long id) {

		return entityManager.find(UsuarioBean.class, id);
	}
}
