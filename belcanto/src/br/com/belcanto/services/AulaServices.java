package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Aula;

@Service
@Transactional
public class AulaServices {

	@PersistenceContext
	private EntityManager em;

	public Aula salvar(Aula aula) {
		return em.merge(aula);

	}

	public void excluir(Aula aula) {
		em.remove(aula);
		em.close();
	}

	public List<Aula> listar() {
		try {
			return em.createQuery("from Aula", Aula.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Aula> porTurma(String nome) {
		try {
			return em.createQuery(
					"select aul from Aula aul inner join aul.turma tur where tur.nome like :nome order by tur.nome",
					Aula.class).setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
