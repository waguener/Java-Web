package br.com.belcanto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.belcanto.model.AulaComp;
import br.com.belcanto.model.Curso;
import br.com.belcanto.model.Modulo;
import br.com.belcanto.services.AulaCompServices;
import br.com.belcanto.services.CursoServices;
import br.com.belcanto.services.ModuloServices;

@ManagedBean
@Controller
@Scope("session")
public class CursoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private ModuloServices moduloServices;
	@Autowired
	private AulaCompServices aulaCompServices;
	
	private Curso cursoMod;
	private Curso curso = new Curso();
	private List<Curso> listar;
	private Modulo modulo = new Modulo();
	private Modulo excluirModulo;
	private List<Modulo> listarMod;
	private AulaComp aulaComp = new AulaComp();
	private AulaComp excluirAulaComp;
	private List<AulaComp> listarAulasComp = new ArrayList<AulaComp>();
	private Date hj = new Date();
	private Long id;
	
	
	// Metodos de Inicio
	public void init() {
		curso = new Curso();
		listar = cursoServices.listarCursos();
		curso.setAtivo(true);
	}

	// Salvar
	public String salvar() {

		cursoServices.salvar(curso);
		curso = new Curso();
		FacesMessage msm = new FacesMessage("Curso salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		return "CadCurso?faces-redirect=true";
	}

	// Editar
	public void editar(Curso curso) {
		this.curso = curso;

	}

	// Abrir Modulo
	@SuppressWarnings("deprecation")
	public void abrirModulo(Curso curso) {
		this.curso = curso;
		cursoMod = new Curso();
		cursoMod = curso;
		modulo = new Modulo();
		listarMod = new ArrayList<Modulo>();
		Long id = curso.getId();
		listarMod = moduloServices.listar(id);
		
		if (listarMod.isEmpty()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cadMod').show();");
		}else {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('cadMod').show();");
		}
		
	}
	//Salvar Modulo
	public void salvarMod() {
		if(modulo.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe o nome do Módulo!!", ""));
		}else {
		modulo.setCurso(cursoMod);
		moduloServices.salvar(modulo);
		FacesMessage msm = new FacesMessage("Módulo salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		listarMod = moduloServices.listar(cursoMod.getId());
		
		modulo = new Modulo();
		}
		
	}
	
	//Editar Modulo
	public void editarMod(Modulo modulo) {
		this.modulo = modulo;
	}
	
	//Delete Modulo
	public void prepararExclusao(Modulo modulo) {
		excluirModulo = modulo;
		id = excluirModulo.getCurso().getId();
	}

	public void excluirMod() {
		moduloServices.excluir(excluirModulo);
		listarMod = moduloServices.listar(id);
		FacesMessage mensagem = new FacesMessage("Módulo excluído com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	//Fechar Modulo
	public void fecharMod() {
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('cadMod').hide();");
	}
	
	// Abrir AulaComplementar
		@SuppressWarnings("deprecation")
		public void abrirAula(Curso curso) {
			this.curso = curso;
			cursoMod = new Curso();
			cursoMod = curso;
			aulaComp = new AulaComp();
			listarAulasComp = new ArrayList<AulaComp>();
			Long id = curso.getId();
			listarAulasComp = aulaCompServices.listar(id);
			
			if (listarAulasComp.isEmpty()) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('cadAula').show();");
			}else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cadAula').show();");
			}
			
		}
		//Salvar Aula
		public void salvarAula() {
			if(aulaComp.getNome().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe o nome da Aula Complementar!!", ""));
			}else {
			aulaComp.setCurso(cursoMod);
			aulaCompServices.salvar(aulaComp);
			FacesMessage msm = new FacesMessage("Aula Complementar Salva com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
			listarAulasComp = aulaCompServices.listar(cursoMod.getId());
			
			aulaComp = new AulaComp();
			}
			
		}
		
		//Editar Modulo
		public void editarAula(AulaComp aulaComp) {
			this.aulaComp = aulaComp;
		}
		
		//Delete Modulo
		public void prepararExclusaoAula(AulaComp aulaComp) {
			excluirAulaComp = aulaComp;
			id = excluirAulaComp.getCurso().getId();
		}

		public void excluirAula() {
			aulaCompServices.excluir(excluirAulaComp);
			listarAulasComp = aulaCompServices.listar(id);
			FacesMessage mensagem = new FacesMessage("Aula Complementar Excluída com Sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}
		
		//Fechar Modulo
		public void fecharAula() {
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cadAula').hide();");
		}


	// Getters e Setters
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getListar() {
		return listar;
	}

	public void setListar(List<Curso> listar) {
		this.listar = listar;
	}

	public List<Modulo> getListarMod() {
		return listarMod;
	}

	public void setListarMod(List<Modulo> listarMod) {
		this.listarMod = listarMod;
	}

	public Curso getCursoMod() {
		return cursoMod;
	}

	public void setCursoMod(Curso cursoMod) {
		this.cursoMod = cursoMod;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public AulaComp getAulaComp() {
		return aulaComp;
	}

	public void setAulaComp(AulaComp aulaComp) {
		this.aulaComp = aulaComp;
	}

	public AulaComp getExcluirAulaComp() {
		return excluirAulaComp;
	}

	public void setExcluirAulaComp(AulaComp excluirAulaComp) {
		this.excluirAulaComp = excluirAulaComp;
	}

	public List<AulaComp> getListarAulasComp() {
		return listarAulasComp;
	}

	public void setListarAulasComp(List<AulaComp> listarAulasComp) {
		this.listarAulasComp = listarAulasComp;
	}
	
}
