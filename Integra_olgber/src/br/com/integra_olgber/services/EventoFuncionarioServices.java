package br.com.integra_olgber.services;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Arquivo;
import br.com.integra_olgber.model.EventoFuncionario;
import br.com.integra_olgber.model.Funcionario;

@Service
@Transactional
public class EventoFuncionarioServices {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ArquivoServices arquivoServices;

	public void salvar(EventoFuncionario eventoFuncionario) {

		entityManager.merge(eventoFuncionario);
		entityManager.close();
	}

	public void salvarAtestado(EventoFuncionario eventoFuncionario) {

		Arquivo arquivosalvo = arquivoServices.inserirArquivoNoSistema(eventoFuncionario.getArquivo(),
				obterDiretorioEventos(eventoFuncionario));
		eventoFuncionario.setArquivo(arquivosalvo);

		entityManager.merge(eventoFuncionario);
		entityManager.close();
	}

	public List<EventoFuncionario> listar() {
		return entityManager.createQuery("from EventoFuncionario order by id desc", EventoFuncionario.class)
				.getResultList();

	}

	public void excluir(EventoFuncionario eventoFuncionario) {
		entityManager.remove(entityManager.merge(eventoFuncionario));
		entityManager.close();
	}

	private String obterDiretorioEventos(EventoFuncionario eventoFuncionario) {
		return "avalicoes/";
	}

	public File obterArquivoDaEventos(EventoFuncionario eventoFuncionario) {
		return arquivoServices.obterArquivo(eventoFuncionario.getArquivo().getNome(),
				obterDiretorioEventos(eventoFuncionario));
	}

	public Funcionario porNomeFuncionario(String nome) {

		return entityManager.createQuery("from Funcionario where nome = :nome", Funcionario.class).setParameter("nome", nome)
				
				.getSingleResult();

	}

	public List<EventoFuncionario> porNome(String nome) {
		return entityManager.createQuery(
				"select evt from EventoFuncionario evt inner join evt.funcionario as fun where fun.nome like :nome order by evt.id desc",
				EventoFuncionario.class).setParameter("nome", nome + "%").getResultList();
	}

}
