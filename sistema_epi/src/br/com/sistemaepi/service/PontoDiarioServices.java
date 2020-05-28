package br.com.sistemaepi.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.Arquivo;
import br.com.sistemaepi.Bean.Atestado;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.Bean.PontoDiario;

@Service
@Transactional
public class PontoDiarioServices {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ArquivoServices arquivoServices;

	public void salvar(PontoDiario pontoDiario) {

		entityManager.merge(pontoDiario);
		entityManager.close();
	}

	public void remove(PontoDiario pontoDiario) {

		entityManager.remove(entityManager.merge(pontoDiario));
		entityManager.close();
	}

	public void salvarAtestado(Atestado atestado) {

		Arquivo arquivosalvo = arquivoServices.inserirArquivoNoSistema(atestado.getArquivo(),
				obterDiretorioEventos(atestado));
		atestado.setArquivo(arquivosalvo);

		entityManager.merge(atestado);
		entityManager.close();
	}

	public void removeArquivo(Arquivo arquivo) {

		entityManager.remove(entityManager.merge(arquivo));
		entityManager.close();
	}

	public void removeAtestado(Atestado atestado) {

		entityManager.remove(entityManager.merge(atestado));
		entityManager.close();
	}

	public List<PontoDiario> listar() {
		return entityManager.createQuery("from PontoDiario order by id desc", PontoDiario.class).getResultList();

	}

	public void excluir(PontoDiario pontoDiario) {
		entityManager.remove(entityManager.merge(pontoDiario));
		entityManager.close();
	}

	public PontoDiario obterPorId(Long id) {
		try {
			return entityManager.find(PontoDiario.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}

	public Atestado obterPorIdAtestado(Long id) {
		try {
			return entityManager.find(Atestado.class, id);
		} catch (NoResultException e) {
			return null;
		}

	}

	public PontoDiario buscarTemp(Long id) {
		try {
			return entityManager
					.createQuery("from PontoDiario where id_funcionario =:id_funcionario", PontoDiario.class)
					.setParameter("id_funcionario", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> delCompensar() {
		try {
			return entityManager.createQuery("from PontoDiario where batida = 'Feriado'", PontoDiario.class)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FuncionarioBean> temp(String agencia, String turno, Date data) {
		try {
			return entityManager.createQuery(
					"from FuncionarioBean where situacao = 'Temporario' and status = 'Admitido' and agencia =:agencia and turno =:turno and dataAdmissao <=:data order by nome",
					FuncionarioBean.class).setParameter("agencia", agencia).setParameter("turno", turno)
					.setParameter("data", data).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FuncionarioBean> feriados(Date data) {
		try {
			return entityManager.createQuery(
					"from FuncionarioBean where situacao = 'Temporario' and status = 'Admitido' and dataAdmissao <=:data order by nome",
					FuncionarioBean.class).setParameter("data", data).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String totalTemp(String agencia, String turno, Date data) {

		String t = entityManager.createQuery(
				"select count(*) from FuncionarioBean where situacao = 'Temporario' and status = 'Admitido' and agencia =:agencia and turno =:turno and dataAdmissao <=:data ")
				.setParameter("agencia", agencia).setParameter("turno", turno).setParameter("data", data)
				.getSingleResult().toString();
		return t;
	}

	public List<FuncionarioBean> tempTodos(Date data) {
		try {
			return entityManager.createQuery(
					"from FuncionarioBean where situacao = 'Temporario' and status = 'Admitido' and dataAdmissao <=:data  order by nome",
					FuncionarioBean.class).setParameter("data", data).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public PontoDiario todosTemp(Date data, Long id) {
		try {
			return entityManager
					.createQuery("from PontoDiario where dtEntrada =:data and id_funcionario =:id ", PontoDiario.class)
					.setParameter("data", data).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> hoje(String agencia, String turno, Date hoje) {
		try {
			return entityManager.createQuery(
					"select pon from PontoDiario pon inner join pon.funcionarioBean func where func.agencia =:agencia and func.turno =:turno and pon.dtEntrada =:hoje order by func.nome,func.turno",
					PontoDiario.class).setParameter("agencia", agencia).setParameter("turno", turno)
					.setParameter("hoje", hoje).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> hoje(Date hoje) {
		try {
			return entityManager.createQuery("from PontoDiario where dtEntrada =:hoje ", PontoDiario.class)
					.setParameter("hoje", hoje).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Boolean check(Long id, Date hoje) {
		boolean teste;
		Query tipo = (Query) entityManager
				.createQuery("from PontoDiario where id_funcionario =:id_funcionario and dtEntrada =:hoje",
						PontoDiario.class)
				.setParameter("id_funcionario", id).setParameter("hoje", hoje);
		if (tipo.getResultList().isEmpty()) {
			teste = true;
		} else {
			teste = false;
		}
		return teste;
	}

	public List<PontoDiario> porData(Date dataInicio, Date dataFinal, String nome) {
		try {
			return entityManager.createQuery(
					"select pon from PontoDiario pon inner join pon.funcionarioBean func where pon.dtEntrada between :dtEntrada and :data2 and func.nome =:nome order by pon.dtEntrada desc",
					PontoDiario.class).setParameter("dtEntrada", dataInicio).setParameter("data2", dataFinal)
					.setParameter("nome", nome).getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> faltas(Date dataInicio, Date dataFinal) {
		try {
			return entityManager.createQuery(
					"select pon from PontoDiario pon inner join pon.funcionarioBean func where pon.situacao = 'Falta' and pon.dtEntrada between :dtEntrada and :data2 order by pon.dtEntrada desc",
					PontoDiario.class).setParameter("dtEntrada", dataInicio).setParameter("data2", dataFinal)
					.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> porPeriodo(Date dataInicio, Date dataFinal) {
		try {
			return entityManager
					.createQuery("from PontoDiario where dtEntrada between :dtEntrada and :data2  ", PontoDiario.class)
					.setParameter("dtEntrada", dataInicio).setParameter("data2", dataFinal).getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<PontoDiario> periodo(Date dataInicio, Date dataFinal) {
		try {
			return entityManager.createQuery(
					"select pon from PontoDiario pon inner join pon.funcionarioBean fun where pon.dtEntrada between :dtEntrada and :data2 order by fun.nome  ",
					PontoDiario.class).setParameter("dtEntrada", dataInicio).setParameter("data2", dataFinal)
					.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Atestado> buscarAtestados() {
		try {
			return entityManager.createQuery("from Atestado order by atestadoDiaInicio desc", Atestado.class)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	private String obterDiretorioEventos(Atestado atestado) {
		return "atestados/";
	}

	public File obterArquivoDaEventos(Atestado atestado) {
		return arquivoServices.obterArquivo(atestado.getArquivo().getNome(), obterDiretorioEventos(atestado));
	}
}
