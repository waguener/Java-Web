package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Professor;
import br.com.belcanto.model.Responsavel;

@Service
@Transactional
public class ResponsavelServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Responsavel responsavel) {
		em.merge(responsavel);
		em.close();
	}

	public void excluir(Responsavel responsavel) {
		em.remove(em.merge(responsavel));
		em.close();
	}

	public List<Responsavel> listar() {
		try {
			return em.createQuery("from Responsavel", Responsavel.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Responsavel obterPorId(Long id) {
		try {
			return em.find(Responsavel.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Responsavel> porNome(String nome) {
		try {
			return em.createQuery("from Responsavel where nome like :nome order by nome", Responsavel.class)
					.setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
