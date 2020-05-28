package br.com.sistemaepi.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
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

import br.com.sistemaepi.Bean.AgenciaBean;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.service.AgenciaService;
import br.com.sistemaepi.service.FuncionarioService;

@ManagedBean
@Controller
@Scope("session")

public class SistemaControllerFuncionario {

	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private AgenciaService agenciaService;

	private FuncionarioBean funcionario = new FuncionarioBean();

	private List<FuncionarioBean> listaFuncionario;

	private FuncionarioBean funcionarioExclusao;
	
	private Date dataMinima = new Date();
	private Date data = new Date();
	private String hoje = new SimpleDateFormat("HH:mm").format(new Date());
	private List<FuncionarioBean> funcSalvos;
	private List<FuncionarioBean> listaTemp;
	private String nome;
	private List<FuncionarioBean> listaEstatistica;
	private String name;
	private FuncionarioBean estatisticaFunc = new FuncionarioBean();
	private List<AgenciaBean> agencias;
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

	// Metodos
	@PostConstruct
	public void init() {
		agencias = agenciaService.listar();
		funcSalvos = funcionarioService.listar();
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

			public Integer getContentLength() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		imagemCortada = new CroppedImage();
	}

	public void initTemp() {
		temp();
	}

	public String cadastraFunc() {
		boolean teste;
		teste = funcionarioService.porCpf(funcionario.getCpf());
		
		if(teste == true) {
			
		funcionario.setDataCriacao(data);
		funcionarioService.salvarFunc(funcionario);
		FacesMessage msm = new FacesMessage("Cadastro efetuado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		funcionario = new FuncionarioBean();
		
		return "cadastro?faces-redirect=true";
		
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este Funcionário já está cadastrado!!", ""));
			return null;
		}
		
	}

	public String editaFunc() {

		funcionarioService.salvarFunc(funcionario);
		FacesMessage msm = new FacesMessage("Funcionário editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		funcionario = new FuncionarioBean();
		funcSalvos = funcionarioService.listar();
		return "ListaFuncionario?faces-redirect";

	}

	public void novoFuncionario() {
		agencias = agenciaService.listar();
		funcionario = new FuncionarioBean();
	}

	public void pesquisar() {

		funcSalvos = funcionarioService.porNomeTodos(nome);
	}

	public void listaAgencia() {
		agencias = agenciaService.listar();
	}

	public void hoje() {
		funcSalvos = funcionarioService.porData(data);
		nome = null;
	}

	public void novaLista() {
		funcSalvos = funcionarioService.listar();
	}

	public void PrepararExcluirFunc(FuncionarioBean funcionario) {
		funcionarioExclusao = funcionario;
	}

	public void ExcluirFun() {
		funcionarioService.excluirFuncionario(funcionarioExclusao);
		listaFuncionario = funcionarioService.listar();
	}

	public String pesquisarFuncionario() {
		this.funcionario = new FuncionarioBean();
		return "ListaFuncionario?faces-redirect=true";
	}

	public String buscarFuncionario() {
		this.funcionario = new FuncionarioBean();
		return "BuscarFuncionario?faces-redirect=true";
	}

	public String editarFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
		return "cadastroEdit?faces-redirect=true";
	}

	public String estatiscaFunc(FuncionarioBean estatisticaFunc) {
		this.estatisticaFunc = estatisticaFunc;
		return "Estatistica?faces-redirect=true";
	}

	public String selecionarFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
		return "Entrega?faces-redirect=true";
	}

	public void estatsticaFunc() {
		listaEstatistica = funcionarioService.porNome(name);
	}

	public String Login() {
		this.funcionario = new FuncionarioBean();
		return "Login?faces-redirect=true";
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
		funcionario.setFoto(foto);

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

	public StreamedContent getImagemFunc() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idFuncStr = mapaParametros.get("idFunc");
		if (idFuncStr != null) {
			Long idFunc = new Long(idFuncStr);
			FuncionarioBean funcBd = funcionarioService.obterPorId(idFunc);
			return funcBd.getImagem();
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
			Logger.getLogger(SistemaControllerFuncionario.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caminho não encontrado!" + ex, "Erro"));
		} catch (IOException ex) {
			Logger.getLogger(SistemaControllerFuncionario.class.getName()).log(Level.SEVERE, null, ex);
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

		funcionario.setFoto(null);
		FacesMessage msm = new FacesMessage("Foto excluída com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Salvar Imagem Capturada

	@SuppressWarnings("deprecation")
	public void salvarImagem() {

		funcionario.setFoto(imagemCortada.getBytes());
		funcionario.getImagem();

		exibirImagem = false;
		exibirImagemCortada = false;
		FacesMessage msm = new FacesMessage("Imagem Adicionada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').hide();");
	}

	// Abrir buscar Funcionario
		public void buscarFunc() {
		

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('funcionarios').show();");
		}
	
	// consultas
	public void temp() {
		listaTemp = new ArrayList<FuncionarioBean>();
		listaTemp = funcionarioService.temp();
	}

	// Getters e Setters

	public FuncionarioBean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}

	public List<FuncionarioBean> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<FuncionarioBean> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public FuncionarioBean getFuncionarioExclusao() {
		return funcionarioExclusao;
	}

	public void setFuncionarioExclusao(FuncionarioBean funcionarioExclusao) {
		this.funcionarioExclusao = funcionarioExclusao;
	}

	public Date getDataMinima() {
		return dataMinima;
	}

	public void setDataMinima(Date dataMinima) {
		this.dataMinima = dataMinima;
	}

	public String getHoje() {
		return hoje;
	}

	public void setHoje(String hoje) {
		this.hoje = hoje;
	}

	public List<FuncionarioBean> getFuncSalvos() {
		return funcSalvos;
	}

	public void setFuncSalvos(List<FuncionarioBean> funcSalvos) {
		this.funcSalvos = funcSalvos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FuncionarioBean> getListaEstatistica() {
		return listaEstatistica;
	}

	public void setListaEstatistica(List<FuncionarioBean> listaEstatistica) {
		this.listaEstatistica = listaEstatistica;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FuncionarioBean getEstatisticaFunc() {
		return estatisticaFunc;
	}

	public void setEstatisticaFunc(FuncionarioBean estatisticaFunc) {
		this.estatisticaFunc = estatisticaFunc;
	}

	public List<AgenciaBean> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<AgenciaBean> agencias) {
		this.agencias = agencias;
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

	public List<FuncionarioBean> getListaTemp() {
		return listaTemp;
	}

	public void setListaTemp(List<FuncionarioBean> listaTemp) {
		this.listaTemp = listaTemp;
	}

}
