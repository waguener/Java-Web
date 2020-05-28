package br.com.escolamusica.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escolamusica.model.Arquivo;
import br.com.escolamusica.model.Avaliacao;
import br.com.escolamusica.model.Bimestre;
import br.com.escolamusica.model.Matricula;
import br.com.escolamusica.service.AvaliacaoService;
import br.com.escolamusica.service.MatriculaService;
import br.com.escolamusica.util.Mensagem;

// TODO - Remover depois (somente para auto complete)
@ManagedBean

@Controller
@Scope("session")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;
	@Autowired
	private MatriculaService matriculaService;

	private Avaliacao avaliacao = new Avaliacao();
	private List<Avaliacao> listaAvaliacoes;
	private List<Matricula> listaMatriculas;
	private String arquivoSelecionado;

	@PostConstruct
	public void init() {
		listaAvaliacoes = avaliacaoService.listar();
		listaMatriculas = matriculaService.listar();
	}

	public String salvar() {
		if(avaliacao.getArquivo() == null) {
			Mensagem.mensagemErro("Selecione um arquivo");
			return null;
		}
		avaliacaoService.salvar(avaliacao);
		avaliacao = new Avaliacao();
		listaAvaliacoes = avaliacaoService.listar();
		arquivoSelecionado = null;
		return "ListaAvaliacoes?faces-redirect=true";
	}

	public String editar(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
		arquivoSelecionado = avaliacao.getArquivo().getNome();
		return "CadastroAvaliacao?faces-redirect=true";
	}

	public void prepararExclusao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public void excluir() {
		avaliacaoService.excluir(avaliacao);
		
		listaAvaliacoes = avaliacaoService.listar();
		Mensagem.mensagemInfo("Avaliação excluida com sucesso");
		avaliacao = new Avaliacao();
	}

	public void cancelarExclusao() {
		this.avaliacao = new Avaliacao();
		
	}
	
	public void fileUpLoaderHandler(FileUploadEvent event) throws IOException {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome(event.getFile().getFileName());
		arquivo.setStream(event.getFile().getInputstream());
		avaliacao.setArquivo(arquivo);
		arquivoSelecionado = arquivo.getNome();
	}

	public Bimestre[] getBimestres() {
		return Bimestre.values();
	}

	public StreamedContent baixarArquivo(Avaliacao avaliacao) throws FileNotFoundException {
		File file = avaliacaoService.obterArquivoDaAvaliacao(avaliacao);
		FileInputStream is = new FileInputStream(file);

		return new DefaultStreamedContent(is, "application/pdf", avaliacao.getArquivo().getNome());
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getListaAvaliacoes() {
		return listaAvaliacoes;
	}

	public void setListaAvaliacoes(List<Avaliacao> listaAvaliacoes) {
		this.listaAvaliacoes = listaAvaliacoes;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public String getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(String arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

}
