package br.com.recibos.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.recibos.model.Recibos;

@Service
@Transactional
public class RecibosServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Recibos recibos) {
		em.merge(recibos);
		em.close();
	}

	public void excluir(Recibos recibos) {
		em.remove(em.merge(recibos));
		em.close();
	}

	public List<Recibos> listar() {
		return em.createQuery("from Recibos order by id desc", Recibos.class).getResultList();
	}

	public List<Recibos> listarNome(String funcionario) {
		return em.createQuery("from Recibos where funcionario like :funcionario order by id desc", Recibos.class)
				.setParameter("funcionario", funcionario + "%").getResultList();
	}
}
