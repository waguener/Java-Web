package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import WebService.CepWebService;
import br.com.sistemaepi.Bean.AtuCadastral;
import br.com.sistemaepi.Bean.Cursos;
import br.com.sistemaepi.Bean.Dependentes;
import br.com.sistemaepi.Bean.Endereco;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.service.AtualCadServices;
import br.com.sistemaepi.service.FuncionarioService;

@ManagedBean
@Controller
@Scope("session")
public class AtualCadController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private AtualCadServices cadServices;

	private AtuCadastral cadastral = new AtuCadastral();
	private List<AtuCadastral> listaCad = new ArrayList<AtuCadastral>();
	private List<FuncionarioBean> listaFunc = new ArrayList<FuncionarioBean>();
	private FuncionarioBean funcionarioBean = new FuncionarioBean();
	private Dependentes dependentes;
	private Cursos cursos;
	private Endereco endereco;
	private Date hj = new Date();
	private String nome;
	private Boolean cnh;
	private String confirmaCnh;
	private String cep;
	private String tipoLogradouro;
	private String logradouro;
	private String estado;
	private String cidade;
	private String bairro;
	private String dtAdmissao;
	private String dataHoje;
	private String salario;
	private int indiceCurso;
	private int indiceDep;

	// Metodos
	public void init() {
		listaFunc = new ArrayList<FuncionarioBean>();
		funcionarioBean = new FuncionarioBean();
		cadastral = new AtuCadastral();
		dependentes = new Dependentes();
		cursos = new Cursos();
		cnh = false;
		confirmaCnh = null;
		cep = null;
		logradouro = null;
		cidade = null;
		tipoLogradouro = null;
		estado = null;
		bairro = null;
	}

	public void initBusca() {
		
		
		nome = null;
		listaCad = new ArrayList<AtuCadastral>();
	}
	
	public void initEdit() {
		
		confirmaCnh = null;
		cep = cadastral.getEndereco().getCep();
		logradouro = cadastral.getEndereco().getLogradouro();
		cidade = cadastral.getEndereco().getCidade();
		tipoLogradouro = cadastral.getEndereco().getTipoLogradouro();
		estado = cadastral.getEndereco().getEstado();
		bairro = cadastral.getEndereco().getBairro();
		
		confirmaCnh = cadastral.getCnh();		
		if(confirmaCnh.equals("SIM")) {
			cnh = true;
		}else {
			cnh = false;
		}
	}
	public String salvar() {
		cadastral.setCnh(confirmaCnh);
		addFilho();
		
		cadServices.salvar(cadastral);
		
		FacesMessage msm = new FacesMessage("Funcionário salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "AtualCadastral?faces-redirect=true";
	}
	
	public String salvarEdit() {
		cadastral.setCnh(confirmaCnh);
		addFilho();
		
		cadServices.salvar(cadastral);
		
		cadastral = new AtuCadastral();
		FacesMessage msm = new FacesMessage("Funcionário Editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "ListarCadastros?faces-redirect=true";
	}

	
	// Abrir buscar Funcionario
	public void abrirFunc() {

		nome = null;
		listaFunc = new ArrayList<FuncionarioBean>();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('funcionarios').show();");
	}

	// carregar Imagens
	public StreamedContent getImagemCad() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idCadStr = mapaParametros.get("idCad");
		if (idCadStr != null) {
			Long idCad = new Long(idCadStr);
			AtuCadastral cadBd = cadServices.obterPorId(idCad);
			return cadBd.getFuncionarioBean().getImagem();
		}
		return new DefaultStreamedContent();
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

	// Selecionar Funcionario
	public void selecionar(FuncionarioBean funcionarioBean) {
		cadastral.setFuncionarioBean(funcionarioBean);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('funcionarios').hide();");
	}

	// Calcular idade
	public void idade() {
		cadastral.setIdade("" + calculaIdade(cadastral.getDtNascimento()));
	}

	private static int calculaIdade(Date dataNasc) {

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(dataNasc);
		Calendar hoje = Calendar.getInstance();

		int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
			idade--;
		} else {
			if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
					&& hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
				idade--;
			}
		}

		return idade;
	}

	// Mostrar cnh
	public void mostrarCnh() {
		if (confirmaCnh.equals("SIM")) {
			cnh = true;
		} else {
			cnh = false;
		}
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
			cadastral.setEndereco(endereco);

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

	// Incluir Curso
	public void addCurso() {
		cadastral.getCursos().add(new Cursos());
	}

	// Excluir Curso
	public void prepararExclusaoCurso(int indiceCurso) {
		this.indiceCurso = indiceCurso;
	}

	public void excluirCurso() {

		cadastral.getCursos().remove(indiceCurso);
		FacesMessage msm = new FacesMessage("Curso Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	// Incluir Dependentes
	public void addDependentes() {
		cadastral.getDependentes().add(new Dependentes());
	}

	// Excluir Dependente
	public void prepararExclusaoDependente(int indiceDep) {
		this.indiceDep = indiceDep;
	}

	public void excluirDep() {

		cadastral.getDependentes().remove(indiceDep);
		FacesMessage msm = new FacesMessage("Dependente Excluido com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}
	
	//Add Filho
	public void addFilho() {
		
		for(Dependentes d : cadastral.getDependentes()) {
			String type = null;
			type = d.getTipo();
			if(type.equals("Filho(a)")) {
				cadastral.setFilhos(true);
				
			}else if(cadastral.getFilhos() == null){
				cadastral.setFilhos(false);
			}
			
		}
		
	}
	//Idade Dependente
	public void idadeDep(Dependentes dependentes) {
		dependentes.setIdade("" + calculaIdade(dependentes.getDtNascimento()));
	}

	// Consultas
	public void buscarFunc() {

		listaFunc = cadServices.buscarFunc(nome);

		if (listaFunc.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado!!", ""));
		}
	}
	
	public void buscar() {
		listaCad = cadServices.porNome(nome);
		
		if(listaCad.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Funcionário Encontrado!!", ""));
		}
	}
	
	//Editar
	public String editar(AtuCadastral atuCadastral) {
		this.cadastral = atuCadastral;
		return "AtualCadastralEdit?faces-redirect=true";
	}

	
	//Imprimir conta salario
	
	public void imprimirCS(AtuCadastral atuCadastral) {
		this.cadastral = atuCadastral;
		dtAdmissao = null;
		salario = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		dtAdmissao = formato.format(cadastral.getFuncionarioBean().getDataAdmissao());
		Locale local = new Locale("pt", "BR");
		DateFormat formato2 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		dataHoje = formato2.format(hj);
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('imprimir').show();");
	}
	
	
	// Getters e Setters
	public AtuCadastral getCadastral() {
		return cadastral;
	}

	public void setCadastral(AtuCadastral cadastral) {
		this.cadastral = cadastral;
	}

	public List<AtuCadastral> getListaCad() {
		return listaCad;
	}

	public void setListaCad(List<AtuCadastral> listaCad) {
		this.listaCad = listaCad;
	}

	public List<FuncionarioBean> getListaFunc() {
		return listaFunc;
	}

	public void setListaFunc(List<FuncionarioBean> listaFunc) {
		this.listaFunc = listaFunc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FuncionarioBean getFuncionarioBean() {
		return funcionarioBean;
	}

	public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
		this.funcionarioBean = funcionarioBean;
	}

	public Boolean getCnh() {
		return cnh;
	}

	public void setCnh(Boolean cnh) {
		this.cnh = cnh;
	}

	public String getConfirmaCnh() {
		return confirmaCnh;
	}

	public void setConfirmaCnh(String confirmaCnh) {
		this.confirmaCnh = confirmaCnh;
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

	public Dependentes getDependentes() {
		return dependentes;
	}

	public void setDependentes(Dependentes dependentes) {
		this.dependentes = dependentes;
	}

	public Cursos getCursos() {
		return cursos;
	}

	public void setCursos(Cursos cursos) {
		this.cursos = cursos;
	}

	public String getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(String dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getDataHoje() {
		return dataHoje;
	}

	public void setDataHoje(String dataHoje) {
		this.dataHoje = dataHoje;
	}
	
}
