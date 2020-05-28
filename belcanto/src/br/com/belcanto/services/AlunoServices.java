package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Aluno;

@Service
@Transactional
public class AlunoServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Aluno aluno) {
		em.merge(aluno);
		em.close();
	}

	public void excluir(Aluno aluno) {
		em.remove(em.merge(aluno));
		em.close();
	}

	public List<Aluno> listar() {
		try {
			return em.createQuery("from Aluno", Aluno.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Aluno> listarAlunos() {
		try {
			return em.createQuery("from Aluno where ativo = 'true' order by id desc", Aluno.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Aluno> porNome(String nome) {
		try {
			return em.createQuery("from Aluno where nome like :nome order by dtCadastro desc", Aluno.class)
					.setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String totalAluno() {

		String t = em.createQuery("select count(*) from Aluno where ativo = 'true'").getSingleResult().toString();
		return t;
	}

	public Aluno obterPorId(Long id) {
		try {
			return em.find(Aluno.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Aluno> mostrarAlunos(String nome) {
		try {
			return em.createQuery("select alu from Aluno alu inner join alu.responsavel resp where resp.nome =:nome",
					Aluno.class).setParameter("nome", nome).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	public Boolean checarAluno(String cpf) {
		boolean teste;
		
		Query tipo = em.createQuery("from Aluno where cpf =:cpf").setParameter("cpf", cpf);
		
		if (tipo.getResultList().isEmpty()) {
			teste = true;
		} else {
			teste = false;
		}
		return teste;
	}

}
