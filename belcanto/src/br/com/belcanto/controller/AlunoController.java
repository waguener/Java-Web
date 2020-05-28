
package br.com.belcanto.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import WebService.CepWebService;
import br.com.belcanto.model.Agenda;
import br.com.belcanto.model.Aluno;
import br.com.belcanto.model.Aula;
import br.com.belcanto.model.Contato;
import br.com.belcanto.model.Endereco;
import br.com.belcanto.model.Matricula;
import br.com.belcanto.model.Responsavel;
import br.com.belcanto.services.AlunoServices;
import br.com.belcanto.services.MatriculaServices;
import br.com.belcanto.services.ResponsavelServices;

@ManagedBean
@Controller
@Scope("session")
public class AlunoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AlunoServices alunoServices;
	@Autowired
	private ResponsavelServices responsavelServices;
	@Autowired
	private MatriculaServices matriculaServices;

	
	private Aluno aluno = new Aluno();
	private Aluno alunoHist;
	private Agenda agenda = new Agenda();
	private Aula aula = new Aula();
	private Endereco endereco;
	private Contato contato;
	private Responsavel responsavel;
	private List<Aluno> listar;
	private List<Aluno> listarPeloNome;
	private List<Responsavel> listaResp;
	private List<Matricula> listarMatricula;
	private Matricula matricula;
	private String nome;
	private String tipoLogradouro;
	private String logradouro;
	private String estado;
	private String cidade;
	private String bairro;
	private Date hoje = new Date();
	private String cep = null;
	private boolean exibeBotao;
	private boolean exibeBotaoSalvar;
	private boolean exibirImagemCortada;
	private StreamedContent imagemEnviada;
	private boolean exibirImagem;
	private CroppedImage imagemCortada;
	private String fotoCapturada;
	private String arquivoFotoRecortada;
	private String fotoRecortada;
	private ServletContext servletContext;
	private String total;
	private String nomeHist;
	private boolean carregar;
	private boolean dados;
	private boolean botaoResp;
	// Metodos de Inicio
	public void init() {
		aluno = new Aluno();
		agenda = new Agenda();
		responsavel = new Responsavel();
		aluno.setAtivo(true);
		cep = null;
		logradouro = null;
		cidade = null;
		tipoLogradouro = null;
		estado = null;
		bairro = null;
		dados = false;
		botaoResp = false;
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
		carregar = false;
		imagemEnviada = new StreamedContent() {

			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer getContentLength() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		imagemCortada = new CroppedImage();

	}
	
	

	public void inicioConsulta() {
		nome = null;
		total = "0";
		
		listarPeloNome = new ArrayList<Aluno>();
	}

	public void inicioEdit() {
		
		cep = aluno.getEndereco().getCep();
		logradouro = aluno.getEndereco().getLogradouro();
		cidade = aluno.getEndereco().getCidade();
		tipoLogradouro = aluno.getEndereco().getTipoLogradouro();
		estado = aluno.getEndereco().getEstado();
		bairro = aluno.getEndereco().getBairro();
		
		
	}
	
	

	// Salvar
	public String salvarAluno() {
		
		boolean teste;
		teste = alunoServices.checarAluno(aluno.getCpf());
		
		if(teste == true) {
			
		aluno.setDtCadastro(hoje);
		aluno.setEndereco(endereco);
		aluno.setResponsavel(responsavel);
		
		alunoServices.salvar(aluno);
		
		FacesMessage msm = new FacesMessage("Aluno salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "Agenda?faces-redirect=true";
		
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este Aluno já foi Cadastrado!!", "Aluno já Cadastrado"));
			RequestContext.getCurrentInstance().execute("growlColour(\".ui-growl-image-error\");");
			return null;
		}
	}
	
	

	public String salvarEdit() {
		
		
		alunoServices.salvar(aluno);
		
		FacesMessage msm = new FacesMessage("Aluno Editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "ConMatriculas?faces-redirect=true";
	}

	// Setar Responsavel para Aluno
	
	public void setarResp(Responsavel responsavel) {
		this.responsavel = responsavel;
		aluno.setResponsavel(responsavel);
		botaoResp = true;
		FacesMessage msm = new FacesMessage("Responsável Selecionado com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('cadResp').hide();");
	}
	
	public void proprio() {
		aluno.setContato(responsavel.getContato());
		aluno.setCpf(responsavel.getCpf());
		aluno.setFoto(responsavel.getFoto());
		aluno.setProfissao(responsavel.getProfissao());
		
		cep = responsavel.getEndereco().getCep();
		buscarCep();
		
		aluno.setNome(responsavel.getNome());
		aluno.setRg(responsavel.getRg());
		
	}
	
	

	// Buscar Cep
	
	public void buscarCep() {
		CepWebService cepWebService = new CepWebService(getCep());
		endereco = new Endereco();
		if (cepWebService.getResultado() == 1) {
			setTipoLogradouro(cepWebService.getTipoLogradouro());
			setLogradouro(cepWebService.getLogradouro());
			setEstado(cepWebService.getEstado());
			setCidade(cepWebService.getCidade());
			setBairro(cepWebService.getBairro());
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setLogradouro(logradouro);
			endereco.setTipoLogradouro(tipoLogradouro);
			aluno.setEndereco(endereco);

		} else {
			logradouro = null;
			cidade = null;
			tipoLogradouro = null;
			estado = null;
			bairro = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cep inválido ou inexistente!!", "Cep não existe"));
			RequestContext.getCurrentInstance().execute("growlColour(\".ui-growl-image-error\");");
		}

	}

	// Carregar Imagem
	
	public void abrirTela() {
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
		imagemCortada = null;
		imagemEnviada = new StreamedContent() {

			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer getContentLength() {
				// TODO Auto-generated method stub
				return null;
			}
		};

		imagemCortada = new CroppedImage();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').show();");
	}
	// colocar foto

	public void enviarFoto(FileUploadEvent event) throws IOException {

		byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
		aluno.setFoto(foto);
		
	}

	public void abrirPasta(FileUploadEvent uploadEvent) throws InterruptedException {
		byte[] img = uploadEvent.getFile().getContents();
		fotoCapturada = uploadEvent.getFile().getFileName();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivo = scontext.getRealPath("/resources/imagens/" + fotoCapturada);
		criarArquivo(arquivo, img);
		carregar = true;
		new Thread().sleep(5000);
		exibeBotao = true;
		exibirImagem = true;
		carregar = false;
	}

	public StreamedContent getImagemAluno() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idAlunoStr = mapaParametros.get("idAluno");
		if (idAlunoStr != null) {
			Long idAluno = new Long(idAlunoStr);
			Aluno alunoBd = alunoServices.obterPorId(idAluno);
			return alunoBd.getImagem();
		}
		return new DefaultStreamedContent();
	}

	public void criarArquivo(String arquivo, byte[] dados) {

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(arquivo));
			imageOutput.write(dados, 0, dados.length);
			imageOutput.close();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caminho não encontrado!" + ex, "Erro"));
		} catch (IOException ex) {
			Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar arquivo!", "Erro"));

		}

	}

	@SuppressWarnings("deprecation")
	public void fecharTela() {
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').hide();");

	}

	// Cortar Foto
	private String getNumeroRandomico() {
		int i = (int) (Math.random() * 10000);
		return String.valueOf(i);
	}

	public void botao() {
		exibeBotao = true;
	}

	public void recortar() {

		verificaExistenciaArquivo(arquivoFotoRecortada);
		fotoRecortada = "fotoRecortada" + getNumeroRandomico() + ".png";
		arquivoFotoRecortada = servletContext.getRealPath("") + File.separator + "resources" + File.separator
				+ "imagens" + File.separator + fotoRecortada;
		criarArquivo(arquivoFotoRecortada, imagemCortada.getBytes());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto RECORTADA com sucesso!", "Informação"));

	}

	public void cortarImagem() {
		exibirImagemCortada = true;
		exibeBotaoSalvar = true;

		setImagemEnviada(new DefaultStreamedContent(new ByteArrayInputStream(imagemCortada.getBytes())));

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Recortada com sucesso!", "Informação"));
	}

	private void verificaExistenciaArquivo(String arquivo) {
		if (arquivo != null) {
			File file = new File(arquivo);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	// Apagar Foto

	public void apagarFoto() {

		aluno.setFoto(null);
		FacesMessage msm = new FacesMessage("Foto excluída com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Salvar Imagem Capturada

	@SuppressWarnings("deprecation")
	public void salvarImagem() {

		aluno.setFoto(imagemCortada.getBytes());
		aluno.getImagem();

		exibirImagem = false;
		exibirImagemCortada = false;
		FacesMessage msm = new FacesMessage("Imagem Adicionada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').hide();");
	}

	public void suscess() {
		FacesMessage msm = new FacesMessage("Imagem Construída com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Abrir Responsavel
	public String resp() {

		return "CadResponsavel?faces-redirect=true";
	}

	// Consultas
	public void listarREsp() {
		listaResp = responsavelServices.listar();
	}

	public void peloNome() {
		total = alunoServices.totalAluno();
		listarPeloNome = alunoServices.porNome(nome);
		if (listarPeloNome == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Aluno Encontrado!", "Erro"));
		}
	}

	
	/*public void histAluno(Aluno aluno) {
		alunoHist = aluno;
		listarMatricula = new ArrayList<Matricula>();
		nomeHist = aluno.getNome();
		listarMatricula = matriculaServices.porNome(alunoHist.getNome());
		if (listarMatricula.isEmpty()) {
			aluno = new Aluno();
			nomeHist = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este Aluno não está Matriculado!", "Erro"));
		}else {
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('histAluno').show();");
		}
	}*/

	
	public void fecharHist() {
		alunoHist = new Aluno();
		nomeHist = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('histAluno').hide();");
	}

	//Relatórios
/*	public void imprimeAlunos() {
		Relatorio relatorio = new Relatorio();
		listar = alunoServices.listar();
		relatorio.getRelatorio(listar);
	}*/
	
	// Editar
	public String editar(Matricula matricula) {
		this.matricula = matricula;
		aluno = new Aluno();
		aluno = matricula.getAgenda().getAluno();
		
		return "AlunoEdit?faces-rediredt=true";
	}

	
	// Getters e Setters
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListar() {
		return listar;
	}

	public void setListar(List<Aluno> listar) {
		this.listar = listar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public boolean isExibeBotao() {
		return exibeBotao;
	}

	public void setExibeBotao(boolean exibeBotao) {
		this.exibeBotao = exibeBotao;
	}

	public boolean isExibeBotaoSalvar() {
		return exibeBotaoSalvar;
	}

	public void setExibeBotaoSalvar(boolean exibeBotaoSalvar) {
		this.exibeBotaoSalvar = exibeBotaoSalvar;
	}

	public boolean isExibirImagemCortada() {
		return exibirImagemCortada;
	}

	public void setExibirImagemCortada(boolean exibirImagemCortada) {
		this.exibirImagemCortada = exibirImagemCortada;
	}

	public StreamedContent getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImagemEnviada(StreamedContent imagemEnviada) {
		this.imagemEnviada = imagemEnviada;
	}

	public boolean isExibirImagem() {
		return exibirImagem;
	}

	public void setExibirImagem(boolean exibirImagem) {
		this.exibirImagem = exibirImagem;
	}

	public CroppedImage getImagemCortada() {
		return imagemCortada;
	}

	public void setImagemCortada(CroppedImage imagemCortada) {
		this.imagemCortada = imagemCortada;
	}

	public String getFotoCapturada() {
		return fotoCapturada;
	}

	public void setFotoCapturada(String fotoCapturada) {
		this.fotoCapturada = fotoCapturada;
	}

	public String getArquivoFotoRecortada() {
		return arquivoFotoRecortada;
	}

	public void setArquivoFotoRecortada(String arquivoFotoRecortada) {
		this.arquivoFotoRecortada = arquivoFotoRecortada;
	}

	public String getFotoRecortada() {
		return fotoRecortada;
	}

	public void setFotoRecortada(String fotoRecortada) {
		this.fotoRecortada = fotoRecortada;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getListaResp() {
		return listaResp;
	}

	public void setListaResp(List<Responsavel> listaResp) {
		this.listaResp = listaResp;
	}

	public List<Aluno> getListarPeloNome() {
		return listarPeloNome;
	}

	public void setListarPeloNome(List<Aluno> listarPeloNome) {
		this.listarPeloNome = listarPeloNome;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Matricula> getListarMatricula() {
		return listarMatricula;
	}

	public void setListarMatricula(List<Matricula> listarMatricula) {
		this.listarMatricula = listarMatricula;
	}

	public String getNomeHist() {
		return nomeHist;
	}

	public void setNomeHist(String nomeHist) {
		this.nomeHist = nomeHist;
	}

	public boolean isCarregar() {
		return carregar;
	}

	public void setCarregar(boolean carregar) {
		this.carregar = carregar;
	}

	public boolean isDados() {
		return dados;
	}

	public void setDados(boolean dados) {
		this.dados = dados;
	}

	public boolean isBotaoResp() {
		return botaoResp;
	}

	public void setBotaoResp(boolean botaoResp) {
		this.botaoResp = botaoResp;
	}
	
}
