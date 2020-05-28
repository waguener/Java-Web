package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.AgenciaBean;

@Service
@Transactional
public class AgenciaService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(AgenciaBean agenciaBean) {

		entityManager.merge(agenciaBean);
		entityManager.close();
	}

	public List<AgenciaBean> listar() {
		return entityManager.createQuery("from AgenciaBean order by id desc", AgenciaBean.class).getResultList();
		
	}
	
	public List<AgenciaBean> listarAgencias() {
		return entityManager.createQuery("from AgenciaBean order by id desc", AgenciaBean.class).getResultList();
		
	}

	public void excluir(AgenciaBean agenciaBean) {
		entityManager.remove(entityManager.merge(agenciaBean));
		entityManager.close();
	}

	public List<AgenciaBean> porNome(String nome) {
		return entityManager.createQuery("from AgenciaBean where agencia like :agencia order by id desc", AgenciaBean.class)
				.setParameter("agencia", nome + "%").getResultList();
		
	}

}
