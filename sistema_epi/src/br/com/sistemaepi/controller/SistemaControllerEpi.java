package br.com.sistemaepi.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.Bean.EpiBean;
import br.com.sistemaepi.Bean.RecebimentoEpiBean;
import br.com.sistemaepi.service.EntregaService;
import br.com.sistemaepi.service.EpiService;
import br.com.sistemaepi.service.RecebimentoEpiService;

@ManagedBean
@Controller
@Scope("session")
public class SistemaControllerEpi implements Serializable{

	private static final long serialVersionUID = -360244156049378926L;
	
	@Autowired
	private EpiService epiService;
	@Autowired
	private RecebimentoEpiService recebimentoEpiService;
	@Autowired
	private EntregaService entregaService;
	
	private EpiBean epi = new EpiBean();
	private RecebimentoEpiBean recebimentoEpiBean = new RecebimentoEpiBean();
	private List<EpiBean> listaEpi;
	private List<EpiBean> novaLista;
	private EpiBean epiExclusao;
	private Double valorTotal;
	private Integer qtd;
	private Double total;
	private List<EpiBean> buscaEpi;
	private String desc;
	private Date data = new Date();
	private List<RecebimentoEpiBean> listaRecebida;
	private String receber;
	private EpiBean entregaEpi;
	private Long id;
	private String result;
	private boolean ca;
	
	
	// Metodos

	public void init() {
		listaEpi = epiService.listarEpi();
	}
		
	public String salvaEpi() {
		if(epi.getValorTotal() <= 0) {
			FacesMessage msm = new FacesMessage("O valor total deve ser calculado");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}else {
		
		epiService.salvarEpi(epi);
		salvarRecebimento();
		FacesMessage msm = new FacesMessage("Epi salvo com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);		
		epi = new EpiBean();
		
		}
		return "epi?faces-redirect = true";

	}
	
	
	public String salvaEpiEditado() {

		epiService.salvarEpi(epi);
		
		FacesMessage msm = new FacesMessage("Epi editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		epi = new EpiBean();
		listaEpi = epiService.listarEpi();
		return "ListaEpi?faces-redirect = true";

	}
	public String novoItem() {
		salvarRecebimento();
		epiService.salvarEpi(epi);
		
		FacesMessage msm = new FacesMessage("Epi editado com sucesso!!");
		FacesContext.getCurrentInstance().addMessage(null, msm);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		epi = new EpiBean();
		listaEpi = epiService.listarEpi();
		return "ListaEpi?faces-redirect = true";

	}
	public void novoEpi() {
		epi = new EpiBean();
		epi.setStatus("Ativo");
	}
	
	public void salvarRecebimento() {
		recebimentoEpiBean.setCa(epi.getCa());
		recebimentoEpiBean.setData(epi.getData());
		recebimentoEpiBean.setDescricao(epi.getDescricao());
		recebimentoEpiBean.setLote(epi.getLote());
		recebimentoEpiBean.setQuantidadeTotal(epi.getQuantidadeTotal());
		recebimentoEpiBean.setValorTotal(epi.getValorTotal());
		recebimentoEpiBean.setValorUnitario(epi.getValorUnitario());
		recebimentoEpiService.salvar(recebimentoEpiBean);
		recebimentoEpiBean = new RecebimentoEpiBean();
	}
	
    public Double calcular() {
		
		total = epi.getValorUnitario() * epi.getQuantidadeTotal();
		epi.setValorTotal(total);
		System.out.println(""+total);
		return total;
	}
    
    public void pesquisarEpi() {
		buscaEpi = epiService.porDesc(desc);
	}
    
    
    public void porDesc () {
    	buscaEpi = epiService.porNome(desc);
    	
    }
    
    public void recebimento() {
    	listaRecebida = recebimentoEpiService.porNome(receber);
    }
    public void porData() {
    	buscaEpi = epiService.porData(data);
    	desc = null;
    }
    public String busca(EpiBean epi) {
    	Long teste = epi.getId();
    	ca = entregaService.porCa(teste);
    	if(ca == true) {
    		editarEpi(epi);
        	return "epiEdit?faces-redirect=true";
    		
    	}else {
    		FacesMessage msm = new FacesMessage("Este epi já foi entregue, para editar apague a entrega deste epi!!");
    		FacesContext.getCurrentInstance().addMessage(null, msm);
    		System.out.println("Passei aqui");
    	}
    	return null;
    }
    
	public String editarEpi(EpiBean epi) {
		this.epi = epi;	
		return "epiEdit?faces-redirect=true";
	}

	public String selecionarEpi(EpiBean epi) {
		this.epi = epi;
		return "Entrega?faces-redirect=true";
	}
	public String selecionarEpi2(EpiBean epi) {
		this.epi = epi;
		return "Devolucao?faces-redirect=true";
	}

	public String voltar(EpiBean epi) {
		this.epi = new EpiBean();
		return "Entrega?faces-redirect=true";
	}

	public void PrepararExcluirEpi(EpiBean epi) {
		epiExclusao = epi;
	}

	public void ExcluirEpi() {
		epiService.ExcluirEpi(epiExclusao);
		listaEpi = epiService.listarEpi();
	}
	
	public String pesquisaEpi() {
		this.epi = new EpiBean();
		return "BuscarEpi?faces-redirect=true";
	}

	// Getters e Setters

	public EpiBean getEpi() {
		return epi;
	}

	public void setEpi(EpiBean epi) {
		this.epi = epi;
	}

	public List<EpiBean> getListaEpi() {
		return listaEpi;
	}

	public void setListaEpi(List<EpiBean> listaEpi) {
		this.listaEpi = listaEpi;
	}

	public EpiBean getEpiexclusao() {
		return epiExclusao;
	}

	public void setEpiexclusao(EpiBean epiExclusao) {
		this.epiExclusao = epiExclusao;
	}

	public EpiBean getEpiExclusao() {
		return epiExclusao;
	}

	public void setEpiExclusao(EpiBean epiExclusao) {
		this.epiExclusao = epiExclusao;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<EpiBean> getBuscaEpi() {
		return buscaEpi;
	}

	public void setBuscaEpi(List<EpiBean> buscaEpi) {
		this.buscaEpi = buscaEpi;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<RecebimentoEpiBean> getListaRecebida() {
		return listaRecebida;
	}

	public void setListaRecebida(List<RecebimentoEpiBean> listaRecebida) {
		this.listaRecebida = listaRecebida;
	}

	public String getReceber() {
		return receber;
	}

	public void setReceber(String receber) {
		this.receber = receber;
	}

	public EpiBean getEntregaEpi() {
		return entregaEpi;
	}

	public void setEntregaEpi(EpiBean entregaEpi) {
		this.entregaEpi = entregaEpi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
