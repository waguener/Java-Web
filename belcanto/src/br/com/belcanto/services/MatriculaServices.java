package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Matricula;

@Service
@Transactional
public class MatriculaServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Matricula matricula) {
		em.merge(matricula);
		em.close();
	}

	public void excluir(Matricula matricula) {
		em.remove(em.merge(matricula));
		em.close();
	}

	public List<Matricula> listar() {
		try {
			return em.createQuery("from Matricula", Matricula.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Matricula> porNome(String nome) {
		try {
			return em.createQuery(
					"select mat from Matricula mat inner join mat.agenda age inner join age.aluno alu where alu.nome like :nome ",
					Matricula.class).setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String ultimoId() {
		try {
			String matricul = em.createQuery("select max(id) from Matricula").getSingleResult().toString();
			return matricul;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Matricula porId(Long id) {
		try {
			return em.createQuery("from Matricula where id =:id", Matricula.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * public List<Matricula> porNome(String nome) { try { return em.createQuery(
	 * "select mat from Matricula mat inner join mat.aluno alu where alu.nome like :nome and alu.ativo = 'true'"
	 * , Matricula.class).setParameter("nome", nome + "%").getResultList(); } catch
	 * (NoResultException e) { return null; } }
	 */

	public List<Matricula> porCurso(String curso) {
		try {
			return em.createQuery("select mat from Matricula mat inner join mat.curso cur where cur.nome =:curso ",
					Matricula.class).setParameter("curso", curso).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Boolean check(String cpf, String nome) {
		Boolean teste = false;

		Matricula matricula = new Matricula();
		matricula = em.createQuery(
				"select mat from Matricula mat inner join mat.agenda age where age.aluno.cpf =:cpf and age.curso.nome =:nome",
				Matricula.class).setParameter("cpf", cpf).setParameter("nome", nome).getSingleResult();
		if (matricula == null) {
			teste = false;
		} else {
			teste = true;
		}
		return teste;
	}

	public Boolean excluirCont(String nome) {
		Boolean teste = false;

		Matricula matricula = new Matricula();
		try {
			matricula = em
					.createQuery("select mat from Matricula mat inner join mat.contrato cont where cont.nome =:nome",
							Matricula.class)
					.setParameter("nome", nome).getSingleResult();

			teste = true;

			return teste;
		} catch (NoResultException e) {

			teste = false;

			return teste;
		}

	}

	/*
	 * public List<TotalCursos> totalCursos() { try { return em.createQuery(
	 * "select new br.com.belcanto.query.TotalCursos(cur.nome,cast (count(cur.nome) as integer), cast(sum(cur.valor) as integer)) from Matricula mat inner join mat.curso as cur where cur.ativo = 'true' group by cur.nome"
	 * , TotalCursos.class).getResultList(); } catch (NoResultException e) { return
	 * null; } }
	 */

	public Aluno ultimo() {
		try {
			String aluno = em.createQuery("select max(id) from Aluno ").getSingleResult().toString();
			Aluno id = new Aluno();

			id.setId(Long.parseLong(aluno));

			return id;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Aluno pegarAluno(Long id) {
		try {
			return em.createQuery("from Aluno where id =:id", Aluno.class).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
}
