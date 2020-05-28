package br.com.integra_olgber.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.integra_olgber.autenticacao.AuthenticationContext;
import br.com.integra_olgber.model.Agencia;
import br.com.integra_olgber.model.Funcionario;
import br.com.integra_olgber.model.Projeto;
import br.com.integra_olgber.model.Turno;
import br.com.integra_olgber.model.Usuario;
import br.com.integra_olgber.services.AgenciaServices;
import br.com.integra_olgber.services.FuncionarioServices;
import br.com.integra_olgber.services.ProjetoServices;
import br.com.integra_olgber.services.TurnoServices;

@ManagedBean
@Controller
@Scope("session")
public class FuncionarioController implements Serializable {

	private static final long serialVersionUID = 5756807176481728746L;

	@Autowired
	private FuncionarioServices funcionarioServices;
	@Autowired
	private TurnoServices turnoServices;
	@Autowired
	private ProjetoServices projetoServices;
	@Autowired
	private AgenciaServices agenciaServices;
	@Autowired
	private AuthenticationContext authenticationContext;

	private Funcionario funcionario = new Funcionario();
	private Funcionario imprimir;
	private Agencia agencia = new Agencia();
	private Usuario usuario = new Usuario();
	private Funcionario funcionarioExclusao;
	private List<Funcionario> listarFuncionarios;
	private List<Funcionario> peloNome;
	private List<Funcionario> listar;
	private List<Funcionario> todos;
	private StreamedContent imagemEnviada;
	private CroppedImage imagemCortada;
	private String nomeImagem;
	private List<Turno> turnos;
	private Object buscaAgencia;
	private List<Projeto> projetos;
	private List<Agencia> agencias;
	private String nome;
	private String data;
	private Date hoje = new Date();
	private String fotoCapturada;
	private String foto;
	private String fotoRecortada;
	private String arquivoFoto;
	private String arquivoFotoRecortada;
	private File fotoSalva;
	private boolean exibeBotao;
	private boolean exibeBotaoSalvar;
	private boolean exibirImagemCortada;
	private boolean ligarWebCam;
	private ServletContext servletContext;
	private String imagemTemporaria;
	
	private boolean exibirImagem;
	
	// Metodos
	@PostConstruct
	public void init() {
		funcionario = new Funcionario();
		agencia = new Agencia();
		funcionario.setStatus("ATIVO");
		funcionario.setHomologado("NÃO");
		peloNome = new ArrayList<Funcionario>();
		listar = new ArrayList<Funcionario>();
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
		ligarWebCam = false;
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
		};
		imagemCortada = new CroppedImage();
	}

	public void iniciarEdicao() {
		
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
		ligarWebCam = false;
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
		};
		imagemCortada = new CroppedImage();
	}
	public void iniciarPesquisa() {
		funcionario = new Funcionario();
		peloNome = new ArrayList<Funcionario>();

		nome = null;

	}

	public String salvarFuncionario() {
		String cpf = funcionario.getCpf();
		boolean result;

		result = funcionarioServices.porCpf(cpf);

		if (result != true) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Atenção: Este funcionário já está cadastrado no sistema!!",
							"Este funcionário já está cadastrado no sistema!!"));
			return null;

		} else {

			Long id_agencia;
			String agencia;
			id_agencia = authenticationContext.getUsuarioLogado().getAgencia().getId();
			agencia = authenticationContext.getUsuarioLogado().getAgencia().getNome();
			funcionario.setCod_agencia(id_agencia);
			funcionario.setDataCadastro(new Date());
			funcionario.setAgencia(agencia);
			funcionarioServices.salvar(funcionario);
			FacesMessage msm = new FacesMessage("Funcionário salvo com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			funcionario = new Funcionario();
			listarFuncionarios = funcionarioServices.listar();

			return "CadastroFuncionario?faces-redirect=true";
		}

	}

	public String salvarFuncionarioEdit() {

		funcionarioServices.salvar(funcionario);
		FacesMessage msm = new FacesMessage("Funcionário editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		funcionario = new Funcionario();
		listarFuncionarios = funcionarioServices.listar();

		return "PesquisaFuncionario?faces-redirect=true";

	}

	// Editar

	public String editarFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		return "FuncionarioEdit?faces-redirect=true";
	}

	// Excluir

	public void prepararExcluirFuncionario(Funcionario funcionario) {
		funcionarioExclusao = funcionario;
	}

	public void excluirFuncionario() {
		funcionarioServices.excluir(funcionarioExclusao);
		peloNome = funcionarioServices.listar();
		FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Botão Status Ativo
	public void ativo() {
		funcionario.setDataDesligamento(null);
		funcionario.setObsDesligamento(null);
		funcionario.setMotivo(null);
		funcionario.setStatus("ATIVO");
	}

	// Botão Status Desligado
	public void desligado() {

		System.out.println("Passei aqui");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verDesligamento').show();");
	}

	public void fecharDesligado() {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verDesligamento').hide();");
	}

	public void salvarDesligado() {
		if (funcionario.getDataDesligamento() == null || funcionario.getMotivo().equals(null)) {
			FacesMessage msm = new FacesMessage("Os Campos devem ser Preenchidos Corretamente!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		} else {
			funcionario.setStatus("DESLIGADO");

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('verDesligamento').hide();");
		}
	}
	// Pesquisas

	public void porNome() {

		peloNome = funcionarioServices.porNome1(nome);
		if (peloNome.isEmpty()) {
			FacesMessage msm = new FacesMessage("Nenhum Funcionário Encontrado!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
	}

	public void porAgencia() {

		Long cod;
		cod = authenticationContext.getUsuarioLogado().getAgencia().getId();

		peloNome = funcionarioServices.porAgencia(nome, cod);

		if (peloNome.isEmpty()) {
			FacesMessage msm = new FacesMessage("Nenhum Funcionário Encontrado!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
	}

	public void comboBox() {
		turnos = turnoServices.listar();
		projetos = projetoServices.listar();

	}

	public void tabela() {
		
		Long cod;
		cod = authenticationContext.getUsuarioLogado().getAgencia().getId();
		listar = funcionarioServices.cadastros(cod);
	}

	public void tabelaTodos() {
		
		todos = funcionarioServices.todos();
	}

	// Imprimir Ficha do Funcionário

	public List<Funcionario> getListar() {
		return listar;
	}

	public void setListar(List<Funcionario> listar) {
		this.listar = listar;
	}

	public String hoje() {
		Date data3 = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		data = formato.format(data3);
		return data;
	}

	public void imprimir(Funcionario funcionario) {
		this.funcionario = funcionario;
		Long id;
		id = funcionario.getCod_agencia();
		agencia = (Agencia) agenciaServices.porCodAgencia(id);
		data = hoje();
		imprimir = funcionario;
		System.out.println("teste -" + imprimir);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verDialog').show();");
	}

	// colocar foto funcionario

	public void enviarFoto(FileUploadEvent event) throws IOException {

		byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
		funcionario.setFoto(foto);

	}

	public void abrirPasta(FileUploadEvent uploadEvent) {
		byte[] img = uploadEvent.getFile().getContents();
		fotoCapturada = uploadEvent.getFile().getFileName();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivo = scontext.getRealPath("/resources/imagens/" + fotoCapturada);
		criarArquivo(arquivo, img);
		exibeBotao = true;
		exibirImagem = true;
		
	}

	public StreamedContent getImagemFuncionario() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idFuncionarioStr = mapaParametros.get("idFuncionario");
		if (idFuncionarioStr != null) {
			Long idFuncionario = new Long(idFuncionarioStr);
			Funcionario funcionarioBanco = funcionarioServices.obterPorId(idFuncionario);
			return funcionarioBanco.getImagem();
		}
		return new DefaultStreamedContent();
	}

	// Apagar Foto
	
	public void apagarFoto() {

		funcionario.setFoto(null);
		FacesMessage msm = new FacesMessage("Foto excluída com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);

	}

	// Usar webcam

	public void abrirTela() {
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
		};
		imagemCortada = new CroppedImage();
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').show();");
	}
	
	public void abrirWebCam() {
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogCamera').show();");
	}

	public void capturar(CaptureEvent captureEvent) {
		exibirImagem = true;
		System.out.println("exibirImagem - " + exibirImagem);
		exibeBotao = true;
		fotoCapturada = "capturada" + getNumeroRandomico() + ".png";
		servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String newFoto = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + fotoCapturada;

		criarArquivo(newFoto, captureEvent.getData());
		
		
	}

	public void criarArquivo(String arquivo, byte[] dados) {

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(arquivo));
			imageOutput.write(dados, 0, dados.length);
			imageOutput.close();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caminho não encontrado!" + ex, "Erro"));
		} catch (IOException ex) {
			Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar arquivo!", "Erro"));
		}

	}

	private String getNumeroRandomico() {
		int i = (int) (Math.random() * 10000);
		return String.valueOf(i);
	}

	// Cortar Foto
	
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
		System.out.println("exibirImagemCortada - " + exibirImagemCortada);
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


	public void fecharTela() {
		exibeBotao = false;
		exibeBotaoSalvar = false;	
		exibirImagem = false;
		exibirImagemCortada = false;
		ligarWebCam = false;
		System.out.println("exibirImagem - " + exibirImagem);
		System.out.println("exibirImagemCortada - " + exibirImagemCortada);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').hide();");

	}

	// Salvar Imagem Capturada

	public void salvarImagem() {

		funcionario.setFoto(imagemCortada.getBytes());
		exibirImagem = false;
		exibirImagemCortada = false;
		FacesMessage msm = new FacesMessage("Imagem Adicionada com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('painelCamera').hide();");
	}

	
	// Getters e Setters
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioExclusao() {
		return funcionarioExclusao;
	}

	public void setFuncionarioExclusao(Funcionario funcionarioExclusao) {
		this.funcionarioExclusao = funcionarioExclusao;
	}

	public List<Funcionario> getListarFuncionarios() {
		return listarFuncionarios;
	}

	public void setListarFuncionarios(List<Funcionario> listarFuncionarios) {
		this.listarFuncionarios = listarFuncionarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getPeloNome() {
		return peloNome;
	}

	public void setPeloNome(List<Funcionario> peloNome) {
		this.peloNome = peloNome;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}

	public Funcionario getImprimir() {
		return imprimir;
	}

	public void setImprimir(Funcionario imprimir) {
		this.imprimir = imprimir;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Object getBuscaAgencia() {
		return buscaAgencia;
	}

	public void setBuscaAgencia(Object buscaAgencia) {
		this.buscaAgencia = buscaAgencia;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	

	public boolean isExibirImagemCortada() {
		return exibirImagemCortada;
	}

	public void setExibirImagemCortada(boolean exibirImagemCortada) {
		this.exibirImagemCortada = exibirImagemCortada;
	}

	public CroppedImage getImagemCortada() {
		return imagemCortada;
	}

	public void setImagemCortada(CroppedImage imagemCortada) {
		this.imagemCortada = imagemCortada;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	private String getRandomNomeImagem() {
		int i = (int) (Math.random() * 100000);

		return String.valueOf(i);
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public List<Funcionario> getTodos() {
		return todos;
	}

	public void setTodos(List<Funcionario> todos) {
		this.todos = todos;
	}

	public String getFotoCapturada() {
		return fotoCapturada;
	}

	public void setFotoCapturada(String fotoCapturada) {
		this.fotoCapturada = fotoCapturada;
	}

	public String getFotoRecortada() {
		return fotoRecortada;
	}

	public void setFotoRecortada(String fotoRecortada) {
		this.fotoRecortada = fotoRecortada;
	}

	public String getArquivoFotoRecortada() {
		return arquivoFotoRecortada;
	}

	public void setArquivoFotoRecortada(String arquivoFotoRecortada) {
		this.arquivoFotoRecortada = arquivoFotoRecortada;
	}

	public StreamedContent getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImagemEnviada(StreamedContent imagemEnviada) {
		this.imagemEnviada = imagemEnviada;
	}

	public boolean isExibeBotao() {
		return exibeBotao;
	}

	public void setExibeBotao(boolean exibeBotao) {
		this.exibeBotao = exibeBotao;
	}

	public boolean isExibirImagem() {
		return exibirImagem;
	}

	public void setExibirImagem(boolean exibirImagem) {
		this.exibirImagem = exibirImagem;
	}

	public boolean isExibeBotaoSalvar() {
		return exibeBotaoSalvar;
	}

	public void setExibeBotaoSalvar(boolean exibeBotaoSalvar) {
		this.exibeBotaoSalvar = exibeBotaoSalvar;
	}

	public boolean isLigarWebCam() {
		return ligarWebCam;
	}

	public void setLigarWebCam(boolean ligarWebCam) {
		this.ligarWebCam = ligarWebCam;
	}
	
	
}
