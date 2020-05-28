package br.com.escolamusica.service;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escolamusica.model.Arquivo;
import br.com.escolamusica.model.Avaliacao;

@Service
@Transactional
public class AvaliacaoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ArquivoService arquivoService;
	@Autowired
	private EmailService emailService;
	

	public void salvar(Avaliacao avaliacao) {

		Arquivo arquivosalvo = arquivoService.inserirArquivoNoSistema(avaliacao.getArquivo(),
				obterDiretorioAvaliacao(avaliacao));

		avaliacao.setArquivo(arquivosalvo);
		entityManager.merge(avaliacao);
		emailService.enviarEmailCorrecaoAvaliacao(avaliacao);
	}
	public void excluir(Avaliacao avaliacao) {
		entityManager.remove(entityManager.merge(avaliacao));
	}
	
	private String obterDiretorioAvaliacao(Avaliacao avaliacao) {
		return "avalicoes/" + avaliacao.getAno() + "/" + avaliacao.getBimestre();
	}

	public List<Avaliacao> listar(){
		 
		return entityManager.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}
	
	public File obterArquivoDaAvaliacao (Avaliacao avaliacao) {
		return arquivoService.obterArquivo(avaliacao.getArquivo().getNome(),obterDiretorioAvaliacao(avaliacao));
	}
}
