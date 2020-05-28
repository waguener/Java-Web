package br.com.sistemaepi.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.EpiBean;

@Service
@Transactional
public class EpiService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvarEpi(EpiBean epiBean) {
		entityManager.merge(epiBean);
		entityManager.close();
	}

	public List<EpiBean> listarEpi() {
		return entityManager.createQuery("from EpiBean order by id desc", EpiBean.class).getResultList();
	}

	public List<EpiBean> listarEpiEstoque() {
		return entityManager
				.createQuery("from EpiBean where quantidadeTotal > '0' and status = 'Ativo' order by id desc",
						EpiBean.class)
				.getResultList();
	}

	public List<EpiBean> porDesc(String descricao) {
		return entityManager.createQuery(
				"from EpiBean where descricao like :descricao and status = 'Ativo' and quantidadeTotal > '0' order by data desc",
				EpiBean.class).setParameter("descricao", descricao + "%").getResultList();
	}

	public List<EpiBean> porNome(String descricao) {
		return entityManager
				.createQuery("from EpiBean where descricao like :descricao and quantidadeTotal > '0' order by id desc",
						EpiBean.class)
				.setParameter("descricao", descricao + "%").getResultList();
	}

	public List<EpiBean> porSituacao() {
		try {
			return entityManager
					.createQuery("from EpiBean where situacao = 'Válido' and status = 'Ativo' order by id desc",
							EpiBean.class)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public void ExcluirEpi(EpiBean epiBean) {
		entityManager.remove(entityManager.merge(epiBean));
		entityManager.close();
	}

	public List<EpiBean> porData(Date data) {
		return entityManager.createQuery("from EpiBean where data =:data and quantidadeTotal > '0' ", EpiBean.class)
				.setParameter("data", data).getResultList();
	}

	public EpiBean obterPorId(Long id) {

		return entityManager.find(EpiBean.class, id);
	}

}
