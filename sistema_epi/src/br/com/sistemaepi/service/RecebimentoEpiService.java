package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.RecebimentoEpiBean;

@Service
@Transactional
public class RecebimentoEpiService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(RecebimentoEpiBean recebimento) {

		entityManager.merge(recebimento);
		entityManager.close();
	}

	public List<RecebimentoEpiBean> listar() {
		return entityManager.createQuery("from RecebimentoEpiBean order by id desc", RecebimentoEpiBean.class).getResultList();
	}

	public List<RecebimentoEpiBean> porNome(String nome) {
		return entityManager.createQuery("from RecebimentoEpiBean where descricao like :descricao order by id desc", RecebimentoEpiBean.class)
				.setParameter("descricao", nome + "%").getResultList();
	}

}
