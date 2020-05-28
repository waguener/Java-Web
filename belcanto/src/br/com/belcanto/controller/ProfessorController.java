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
import br.com.belcanto.model.Banco;
import br.com.belcanto.model.Endereco;
import br.com.belcanto.model.Professor;
import br.com.belcanto.services.BancoServices;
import br.com.belcanto.services.ProfessorServices;

@ManagedBean
@Controller
@Scope("session")
public class ProfessorController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProfessorServices professorServices;
	@Autowired
	private BancoServices bancoServices;

	private Endereco endereco;
	private Professor professor;
	private Banco banco = new Banco();
	private List<Professor> listar;
	private List<Banco> listarBancos;
	private String nome;
	private String tipoLogradouro;
	private String logradouro;
	private String estado;
	private String cidade;
	private String bairro;
	private String cep = null;
	private Date hoje = new Date();
	private String bd;
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
	private String disciplina;

	// Metodos de Inicio
	public void init() {
		professor = new Professor();
		bd = null;
		listarBancos = bancoServices.listar();
		professor.setAtivo(true);
		cep = null;
		logradouro = null;
		cidade = null;
		tipoLogradouro = null;
		estado = null;
		bairro = null;
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
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
	
	public void inicioEdit() {
		listarBancos = bancoServices.listar();
		cep = professor.getEndereco().getCep();
		logradouro = professor.getEndereco().getLogradouro();
		cidade = professor.getEndereco().getCidade();
		tipoLogradouro = professor.getEndereco().getTipoLogradouro();
		estado = professor.getEndereco().getEstado();
		bairro = professor.getEndereco().getBairro();
	}

	public void inicioConsulta() {
		
		listar = new ArrayList<Professor>();
		nome = null;
		total = "0";
	}

	// Salvar
	public String salvar() {

		professor.setDtCadastro(hoje);
		professor.setEndereco(endereco);
		professorServices.salvar(professor);

		FacesMessage msm = new FacesMessage("Professor salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "CadProfessor?faces-redirect=true";

	}
	
	public String salvarEdit() {
		professorServices.salvar(professor);

		FacesMessage msm = new FacesMessage("Professor Editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "ConProfessor?faces-redirect=true";
	}

	
	

	// Buscar Cep
	@SuppressWarnings("deprecation")
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
			professor.setEndereco(endereco);
			System.out.println("Teste - " + professor);
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

	public void cod() {

		banco = bancoServices.buscarCod(bd);

		if (banco != null) {
			professor.setBanco(banco);
		}
	}

	// Carregar Imagem
	@SuppressWarnings("deprecation")
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
		professor.setFoto(foto);

	}

	public void abrirPasta(FileUploadEvent uploadEvent) throws InterruptedException {
		byte[] img = uploadEvent.getFile().getContents();
		fotoCapturada = uploadEvent.getFile().getFileName();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivo = scontext.getRealPath("/resources/imagens/" + fotoCapturada);
		criarArquivo(arquivo, img);
		new Thread().sleep(5000);
		exibeBotao = true;
		exibirImagem = true;

	}

	public StreamedContent getImagemProfessor() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idProfessorStr = mapaParametros.get("idProfessor");
		if (idProfessorStr != null) {
			Long idProfessor = new Long(idProfessorStr);
			Professor professorBanco = professorServices.obterPorId(idProfessor);
			return professorBanco.getImagem();
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

		professor.setFoto(null);
		FacesMessage msm = new FacesMessage("Foto excluída com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Salvar Imagem Capturada

	@SuppressWarnings("deprecation")
	public void salvarImagem() {

		professor.setFoto(imagemCortada.getBytes());
		professor.getImagem();
		System.out.println("Teste - " + professor.getImagem());
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
	
	//EdtidarProfessor
	public String editarProfessor(Professor professor) {
		this.professor = professor;
		
		return "EditProfessor?faces-redirect=true";
	}

	// Buscas e Consultas

	public void buscarProf() {
		total = professorServices.totalProf();
		listar = professorServices.porNome(nome);
		if (listar.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum Professor encontrado!!", "Informação"));
		}
	}
	
	

	// Getters e Setters

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getListar() {
		return listar;
	}

	public void setListar(List<Professor> listar) {
		this.listar = listar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public List<Banco> getListarBancos() {
		return listarBancos;
	}

	public void setListarBancos(List<Banco> listarBancos) {
		this.listarBancos = listarBancos;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	
	
	
}
