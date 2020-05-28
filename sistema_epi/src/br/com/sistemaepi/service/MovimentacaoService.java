package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.MovimentacaoBean;

@Service
@Transactional
public class MovimentacaoService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(MovimentacaoBean movimentacaoBean) {
		entityManager.merge(movimentacaoBean);
	}
	
	public List<MovimentacaoBean> listarMovimentacao(){
		return entityManager.createQuery("from MovimentacaoBean order by id desc", MovimentacaoBean.class).getResultList();
	}
	
	public void excluirMovimentacao(MovimentacaoBean movimentacaoBean) {
		entityManager.remove(entityManager.merge(movimentacaoBean));
	}
	
}
