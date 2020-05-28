package br.com.sistemaepi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.DevolucaoBean;

@Service
@Transactional
public class DevolucaoService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(DevolucaoBean devolucaoBean) {
		entityManager.merge(devolucaoBean);
		entityManager.close();
	}

	public List<DevolucaoBean> listar() {
		return entityManager.createQuery("from DevolucaoBean", DevolucaoBean.class).getResultList();
	}

	public void excluir(DevolucaoBean devolucaoBean) {
		entityManager.remove(entityManager.merge(devolucaoBean));
		entityManager.close();
	}

	public List<DevolucaoBean> devolucao(Date dataDevolucao) {

		List<DevolucaoBean> list = new ArrayList<>();

		list = entityManager.createQuery(
				"from DevolucaoBean where status = 'Devolvido' and dataDevolucao =:dataDevolucao order by id desc",
				DevolucaoBean.class).setParameter("dataDevolucao", dataDevolucao).getResultList();

		return list;
	}

	public List<DevolucaoBean> porNome(String nome) {
		return entityManager.createQuery(
				" from DevolucaoBean where nome like :nome and status = 'Devolvido' order by id desc",
				DevolucaoBean.class).setParameter("nome", nome + "%").getResultList();
	}

	public List<DevolucaoBean> porNomeEstatistica(String nome) {
		return entityManager.createQuery(
				" from DevolucaoBean where nome =:nome order by id desc",
				DevolucaoBean.class).setParameter("nome", nome).getResultList();
	}
}
