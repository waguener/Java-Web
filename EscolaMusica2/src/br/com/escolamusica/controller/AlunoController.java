package br.com.escolamusica.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.context.annotation.SessionScope;

import br.com.escolamusica.dao.AlunoDAO;
import br.com.escolamusica.model.Aluno;
import br.com.escolamusica.model.Estado;
import br.com.escolamusica.service.Alunoservice;
@ManagedBean
@SessionScope
@Secured({"ROLE_CADASTRAR_ALUNO"})
public class AlunoController {

	@Autowired
	private Alunoservice alunoservice;
	
	private Aluno aluno = new Aluno();
	private Aluno alunoExclusao;
	private List<Aluno> alunos;

	public void init(){
		alunos = alunoservice.listar();
	}
	
	
	public String salvar() {
		alunoservice.salvar(aluno);
		aluno = new Aluno();
		alunos = alunoservice.listar();
		return "ListaAlunos?faces-redirect=true";
	}

	public String editar(Aluno aluno) {
		this.aluno = aluno;
		return "CadastroAluno?faces-redirect=true";
	}

	public String cancelar() {
		aluno = new Aluno();
		return "ListaAlunos?faces-redirect=true";
	}

	public void prepararExclusao(Aluno aluno) {
		alunoExclusao = aluno;
	}

	public void excluir() {
		alunoservice.excluir(alunoExclusao);
		alunos = alunoservice.listar();
	}

	public Estado[] getEstados() {
		return Estado.values();
	}

	public void enviarFoto(FileUploadEvent event) throws IOException {

		byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
		aluno.setFoto(foto);
	}

	public StreamedContent getImagemAluno() {
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String idAlunoStr = mapaParametros.get("idAluno");
		if(idAlunoStr != null) {
			Long idAluno = new Long(idAlunoStr);
			Aluno alunoBanco = AlunoDAO.obterPorId(idAluno);
			return alunoBanco.getImagem();
		}
		return new DefaultStreamedContent();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
