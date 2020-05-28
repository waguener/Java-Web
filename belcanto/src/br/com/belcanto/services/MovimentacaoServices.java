package br.com.belcanto.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.AulaCompReceber;
import br.com.belcanto.model.MatriculaReceber;
import br.com.belcanto.model.MensalReceber;
import br.com.belcanto.model.Movimentacao;

@Service
@Transactional
public class MovimentacaoServices {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Movimentacao movimentacao) {
		em.merge(movimentacao);
		em.close();
	}

	public void excluir(Movimentacao movimentacao) {
		em.remove(em.merge(movimentacao));
		em.close();
	}

	public List<MensalReceber> porNome(String nome) {
		try {
			return em.createQuery(
					"select mens from MensalReceber mens inner join mens.matricula mat where mat.agenda.aluno.nome like :nome and mens.receber.parcela = '1' order by mens.matricula.numMatricula",
					MensalReceber.class).setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<MensalReceber> mensalidades(String nome, String curso) {
		try {
			return em.createQuery(
					"select mens from MensalReceber mens inner join mens.matricula mat where mat.agenda.aluno.nome =:nome and mat.agenda.curso.nome =:curso and mens.receber.status != 'PAGO' order by dtVencimento",
					MensalReceber.class).setParameter("nome", nome).setParameter("curso", curso).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<AulaCompReceber> aulaCompReceber(String nome, String curso) {
		try {
			return em.createQuery(
					"select aul from AulaCompReceber aul inner join aul.matricula mat where mat.agenda.aluno.nome =:nome and mat.agenda.curso.nome =:curso and aul.receber.status != 'PAGO' order by dtVencimento",
					AulaCompReceber.class).setParameter("nome", nome).setParameter("curso", curso).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<MatriculaReceber> matReceber(String nome, String curso) {
		try {
			return em.createQuery(
					"select matRec from MatriculaReceber matRec inner join matRec.matricula mat where mat.agenda.aluno.nome =:nome and mat.agenda.curso.nome =:curso and matRec.receber.status != 'PAGO' order by dtVencimento",
					MatriculaReceber.class).setParameter("nome", nome).setParameter("curso", curso).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<MatriculaReceber> peloNome(String nome) {
		try {
			return em.createQuery(
					"select matRec from MatriculaReceber matRec inner join matRec.matricula mat where mat.agenda.aluno.nome like :nome ",
					MatriculaReceber.class).setParameter("nome", nome + "%").getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

	public MensalReceber mesPago(Long id) {
		try {
			return em.createQuery("from MensalReceber where id =:id", MensalReceber.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public AulaCompReceber aulaPago(Long id) {
		try {
			return em.createQuery("from AulaCompReceber where id =:id", AulaCompReceber.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public MatriculaReceber matpago(Long id) {
		try {
			return em.createQuery("from MatriculaReceber where id =:id", MatriculaReceber.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Movimentacao> entreData(Date dtInicial, Date dtFinal) {
		try {
			return em.createQuery(
					"from Movimentacao where dtMovimentacao BETWEEN :dtInicial and :dtFinal order by dtMovimentacao",
					Movimentacao.class).setParameter("dtInicial", dtInicial).setParameter("dtFinal", dtFinal)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
