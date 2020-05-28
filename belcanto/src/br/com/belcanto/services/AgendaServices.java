package br.com.belcanto.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Agenda;
import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Matricula;

@Service
@Transactional
public class AgendaServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Agenda agenda) {
		em.merge(agenda);
		em.close();
	}

	public void excluir(Agenda agenda) {
		em.remove(agenda);
		em.close();
	}

	public List<Agenda> listar() {
		try {
			return em.createQuery("from Agenda", Agenda.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

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
	
	public Boolean check(String cpf, String nome) {
		Boolean teste = false;

		List<Matricula> list = new ArrayList<Matricula>();
		list = em.createQuery(
				"select mat from Matricula mat inner join mat.aluno alu inner join mat.curso cur where alu.cpf =:cpf and cur.nome =:nome",
				Matricula.class).setParameter("cpf", cpf).setParameter("nome", nome).getResultList();
		if (list.isEmpty()) {
			teste = false;
		} else {
			teste = true;
		}
		return teste;
	}
}
