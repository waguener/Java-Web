package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.Atestado;
import br.com.sistemaepi.Bean.AtuCadastral;
import br.com.sistemaepi.Bean.FuncionarioBean;

@Service
@Transactional
public class AtualCadServices {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ArquivoServices arquivoServices;

	public void salvar(AtuCadastral cadastral) {

		entityManager.merge(cadastral);
		entityManager.close();
	}

	

	public AtuCadastral obterPorId(Long id) {
		try {
			return entityManager.find(AtuCadastral.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<FuncionarioBean> buscarFunc(String nome) {
		try {
			return entityManager
					.createQuery("from FuncionarioBean where nome like :nome order by nome", FuncionarioBean.class)
					.setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<AtuCadastral> porNome(String nome) {
		try {
			return entityManager.createQuery(
					"select at from AtuCadastral at inner join at.funcionarioBean func where func.nome like :nome order by func.nome",
					AtuCadastral.class).setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
