package br.com.integra_olgber.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Funcionario;

@Service
@Transactional
public class FuncionarioServices {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Funcionario funcionario) {

		entityManager.merge(funcionario);
		entityManager.close();
	}

	public List<Funcionario> listar() {
		return entityManager.createQuery("from Funcionario order by id desc", Funcionario.class).getResultList();

	}

	public void excluir(Funcionario funcionario) {
		entityManager.remove(entityManager.merge(funcionario));
		entityManager.close();
	}

	public List<Funcionario> porNome1(String nome) {

		return entityManager.createQuery("from Funcionario where nome like :nome order by id desc", Funcionario.class)
				.setParameter("nome", nome + "%").getResultList();

	}

	public List<Funcionario> porAgencia(String nome, Long cod_agencia) {

		return entityManager.createQuery(
				"from Funcionario where nome like :nome and cod_agencia = :cod_agencia order by nome",
				Funcionario.class).setParameter("nome", nome + "%").setParameter("cod_agencia", cod_agencia).getResultList();

	}
	
	public List<Funcionario> cadastros(Long cod_agencia) {

		return entityManager.createQuery(
				"from Funcionario where cod_agencia = :cod_agencia order by id desc",
				Funcionario.class).setParameter("cod_agencia", cod_agencia).getResultList();

	}
	
	public List<Funcionario> todos() {

		return entityManager.createQuery(
				"from Funcionario order by id desc",
				Funcionario.class).getResultList();

	}
	
	public Boolean porCpf(String cpf) {
		boolean teste;
				
		Query tipo = entityManager
				.createQuery("from Funcionario where cpf =:cpf ",
						Funcionario.class)
				.setParameter("cpf", cpf);
				
		 if(tipo.getResultList().isEmpty()) {
			 teste = true;
		 }else {
			 teste = false;
		 }
		 return teste;
	}
	
	
	public Funcionario obterPorId(Long id) {

		return entityManager.find(Funcionario.class, id);
	}

}
