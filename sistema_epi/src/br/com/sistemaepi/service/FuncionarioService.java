package br.com.sistemaepi.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.FuncionarioBean;

@Service
@Transactional
public class FuncionarioService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvarFunc(FuncionarioBean funcionarioBean) {
		entityManager.merge(funcionarioBean);
		entityManager.close();
	}

	public List<FuncionarioBean> listar() {
		return entityManager.createQuery("from FuncionarioBean order by nome", FuncionarioBean.class).getResultList();
	}

	public List<FuncionarioBean> porNome(String nome) {
		return entityManager
				.createQuery("from FuncionarioBean where nome like :nome and status = 'Admitido' order by nome", FuncionarioBean.class)
				.setParameter("nome", nome + "%").getResultList();
	}
	
	public List<FuncionarioBean> porNomeTemp(String nome) {
		return entityManager
				.createQuery("from FuncionarioBean where nome like :nome and situacao = 'Temporario' order by nome", FuncionarioBean.class)
				.setParameter("nome", nome + "%").getResultList();
	}

	public List<FuncionarioBean> porNomeTodos(String nome) {
		return entityManager
				.createQuery("from FuncionarioBean where nome like :nome order by nome", FuncionarioBean.class)
				.setParameter("nome", nome + "%").getResultList();
	}

	public FuncionarioBean jaCad(String cpf) {
		try {
			return entityManager.createQuery("from FuncionarioBean where cpf =:cpf", FuncionarioBean.class)
					.setParameter("cpf", cpf).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FuncionarioBean> porData(Date data) {
		return entityManager.createQuery("from FuncionarioBean where dataCriacao =:dataCriacao", FuncionarioBean.class)
				.setParameter("dataCriacao", data).getResultList();
	}

	public void excluirFuncionario(FuncionarioBean funcionarioBean) {
		entityManager.remove(entityManager.merge(funcionarioBean));
		entityManager.close();
	}

	public FuncionarioBean obterPorId(Long id) {
		try {
			return entityManager.find(FuncionarioBean.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public List<FuncionarioBean> temp() {
		try {
			return entityManager.createQuery(
					"from FuncionarioBean where situacao = 'Temporario' order by nome",
					FuncionarioBean.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<FuncionarioBean> tempReg() {
		try {
			return entityManager.createQuery(
					"from FuncionarioBean where situacao = 'Temporario' and status = 'Admitido' order by nome ",
					FuncionarioBean.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public Boolean porCpf(String cpf) {
		boolean teste;
		Query tipo = (Query) entityManager
				.createQuery("from FuncionarioBean where cpf =:cpf",
						FuncionarioBean.class)
				.setParameter("cpf", cpf);
		if (tipo.getResultList().isEmpty()) {
			teste = true;
		} else {
			teste = false;
		}
		return teste;
	}
}
