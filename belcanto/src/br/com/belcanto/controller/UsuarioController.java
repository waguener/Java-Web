package br.com.belcanto.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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

import br.com.belcanto.autenticacao.AuthenticationContext;
import br.com.belcanto.model.Usuario;
import br.com.belcanto.services.UsuarioServices;


@ManagedBean
@Controller
@Scope("session")
public class UsuarioController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioServices usuarioServices;
	
	private Usuario usuario = new Usuario();
	private Usuario usuarioExclusao;
	private Usuario user = new Usuario();
	private List<Usuario> listar;
	private String nome;
	private String confirmaSenha;
	boolean menu = false;
	private AuthenticationContext authenticationContext;
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
	private Boolean check;
	private Date hoje = new Date();
	//Metodos
	public void init() {
		usuario = new Usuario();
		exibeBotao = false;
		exibeBotaoSalvar = false;
		exibirImagem = false;
		exibirImagemCortada = false;
		check = false;
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
	
	//Salvar
	public String salvar() {
		
		if(!confirmaSenha.equals(usuario.getSenha())) {
			FacesMessage msm = new FacesMessage("Senhas não conferem!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			return null;
		}else {
		usuario.setDtcadastro(hoje);
		usuarioServices.salvar(usuario);
		FacesMessage msm = new FacesMessage("Usuario salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		

		return "CadUsuario?faces-redirect=true";
		}
	}
	
	//Excluir
		public void prepararExcluirUsuario(Usuario usuario) {
		usuarioExclusao = usuario;
		}

		public void excluirUsuario() {
			usuarioServices.excluir(usuarioExclusao);
			listar = usuarioServices.listar();
			FacesMessage msm = new FacesMessage("Excluido com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			
		}
		//Valida Senha
		public void validaSenha() {
			String senha = usuario.getSenha().toString();
			System.out.println("Senha -" + senha);
			if(!confirmaSenha.equals(senha)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não Conferem!!", ""));
				confirmaSenha = null;
			}else {
				check = true;
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
			usuario.setFoto(foto);

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
			System.out.println(img);
		}

		public StreamedContent getImagemUsuario() {
			Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String idUsuarioStr = mapaParametros.get("idUsuario");
			if (idUsuarioStr != null) {
				Long idUsuario = new Long(idUsuarioStr);
				Usuario usuarioBd = usuarioServices.obterPorId(idUsuario);
				return usuarioBd.getImagem();
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

			usuario.setFoto(null);
			FacesMessage msm = new FacesMessage("Foto excluída com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);

		}

		// Salvar Imagem Capturada

		@SuppressWarnings("deprecation")
		public void salvarImagem() {

			usuario.setFoto(imagemCortada.getBytes());
			usuario.getImagem();

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
		
		//Usuario Logado
		public void user() {
			user = new Usuario();
			String nome = authenticationContext.getUsuarioLogado().getNome();
			user = usuarioServices.obterPorUser(nome);
		}

		//Getters e Setters
		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Usuario getUsuarioExclusao() {
			return usuarioExclusao;
		}

		public void setUsuarioExclusao(Usuario usuarioExclusao) {
			this.usuarioExclusao = usuarioExclusao;
		}

		public List<Usuario> getListar() {
			return listar;
		}

		public void setListar(List<Usuario> listar) {
			this.listar = listar;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getConfirmaSenha() {
			return confirmaSenha;
		}

		public void setConfirmaSenha(String confirmaSenha) {
			this.confirmaSenha = confirmaSenha;
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

		public Boolean getCheck() {
			return check;
		}

		public void setCheck(Boolean check) {
			this.check = check;
		}

		public Usuario getUser() {
			return user;
		}

		public void setUser(Usuario user) {
			this.user = user;
		}
		
		
}
