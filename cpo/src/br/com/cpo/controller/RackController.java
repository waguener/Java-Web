package br.com.cpo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.Movimentacao;
import br.com.cpo.model.Rack;
import br.com.cpo.services.MovimentacaoServices;
import br.com.cpo.services.RackServices;

@SuppressWarnings("deprecation")
@ManagedBean
@Controller
@Scope("session")
public class RackController implements Serializable{

	private static final long serialVersionUID = 58429903804090276L;
	
	@Autowired
	private RackServices rackServices;
	@Autowired
	private MovimentacaoServices movimentacaoServices;
	
	private Movimentacao movimentacao;
	private List<Movimentacao> listaRack;
	private Rack rack = new Rack();
	private Object teste;
	private Rack excluirMaterial;
	private List<Rack> listaMaterial;
	
	public void init() {
		rack = new Rack();
		listaMaterial = rackServices.listar();
		
	}
	
	//Salvar
	public void salvar() {
				
		rackServices.salvar(rack);
				
		FacesMessage msm = new FacesMessage("Material Cadastrado com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
			
	}
	
	public String salvarEdit() {
		
		FacesMessage msm = new FacesMessage("Material Editado com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		return "CadastroMaterial?faces-redorect=true";
	}
	
	//Editar
	public String editar(Rack rack) {
		this.rack = rack;
		return "EditarCadastroMaterial?faces-redirect=true";
	}
	
	//Excluir
	
	public void PrepararExcluirMaterial(Rack rack) {
		excluirMaterial = rack;
	}

	public void excluirMaterial() {
		rackServices.excluir(excluirMaterial);
		listaMaterial = rackServices.listar();
		FacesMessage msm = new FacesMessage("Produto Excluido com Sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
	}

	//Pesquisar
	
	
	public void abrirBusca1() {
		rack.setDrive("1");
		rack.setLinha("1");
		rack.setColuna("1");
		buscarRack();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('retirarProduto').show();");
	}
	public void abrirBusca2() {
		rack.setDrive("1");
		rack.setLinha("1");
		rack.setColuna("2");
		buscarRack();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('retirarProduto').show();");
	}
	
	public void buscarRack() {
		listaRack = movimentacaoServices.buscarRack();
	}
	
	public void testeBd() {
		teste = rackServices.teste();
		if(teste == null) {
			System.out.println("Erro");
		}else {
			System.out.println("Deu Certo!!");
		}
	}
	//Fechar Buscarack
	public void fecharRack() {
		rack = new Rack();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('retirarProduto').hide();");
	}
	
	//Selecionar Rack
	
	
	public void selecionar(Movimentacao movimentacao) {
		rack.setCod(movimentacao.getRecebimento().getCodigo());
		rack.setLote(movimentacao.getRecebimento().getLote());
		rack.setProduto(movimentacao.getRecebimento().getCodigo());
		rack.setQtd(movimentacao.getRecebimento().getPesoFicha());
		rack.setUni(movimentacao.getRecebimento().getUniMedida());
		rack.setValidade(movimentacao.getRecebimento().getDataValidade());
		rackServices.salvar(rack);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('retirarProduto').hide();");
	}
	
	//Getters e Setters

	public Rack getRack() {
		return rack;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

	public Rack getExcluirMaterial() {
		return excluirMaterial;
	}

	public void setExcluirMaterial(Rack excluirMaterial) {
		this.excluirMaterial = excluirMaterial;
	}

	public List<Rack> getListaMaterial() {
		return listaMaterial;
	}

	public void setListaMaterial(List<Rack> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public List<Movimentacao> getListaRack() {
		return listaRack;
	}

	public void setListaRack(List<Movimentacao> listaRack) {
		this.listaRack = listaRack;
	}
	
	
	
	
}
