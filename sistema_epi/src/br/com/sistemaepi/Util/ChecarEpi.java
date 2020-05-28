package br.com.sistemaepi.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.Bean.PontoDiario;
import br.com.sistemaepi.service.EmailService;
import br.com.sistemaepi.service.EntregaService;
import br.com.sistemaepi.service.PontoDiarioServices;

@Component
@EnableScheduling
public class ChecarEpi {

	
	@Autowired
	private EntregaService entregaService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PontoDiarioServices diarioServices;
	
	private EntregaBean entregaBean = new EntregaBean();
	private List<EntregaBean> listaEntrega;
	private List<FuncionarioBean> listaTemp;
	private List<PontoDiario> listaDiaria;
	private PontoDiario pontoDiario = new PontoDiario();
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	private Integer dia = 0;
	private Long id;
	private Date hoje = new Date();
	
	private ArrayList<String> listaVencida = new ArrayList<>();
	
	
	@Scheduled(cron = "0 0 07 * * *", zone = TIME_ZONE)
	//@Scheduled(fixedDelay = 10000)
	public void subtrairDias() {
		
		listaEntrega = new ArrayList<EntregaBean>();
		listaEntrega = entregaService.porSituacao();
		if (listaEntrega != null) {
			for (EntregaBean e : listaEntrega) {
				
				dia = e.getValidade() - 1;			
				id = e.getId();
				e.setId(id);
				e.setSituacao(e.getSituacao());
				e.setDevolucao(e.getDevolucao());	
				e.setStatus(e.getStatus());
				e.setQtdEntregas(e.getQtdEntregas());
				e.setData(e.getData());
				e.setEpi(e.getEpi());
				e.setFuncionario(e.getFuncionario());
				e.setEstado(e.getEstado());
				e.setHora(e.getHora());
				e.setProtocolo(e.getProtocolo());
				e.setQuantidade(e.getQuantidade());
				e.setValidade(dia);
				entregaService.salvar(e);
				entregaBean = new EntregaBean();
			}
		}
	}

	@Scheduled(cron = "0 38 07 * * *",zone = TIME_ZONE)
	//@Scheduled(fixedDelay = 45000)
	public void checarValidade() {
		listaEntrega = new ArrayList<EntregaBean>();
		listaEntrega = entregaService.pesquisarVencidos();		
		if (listaEntrega.isEmpty()) {
			System.out.println("Sem lista");

		} else {
			for (EntregaBean ent : listaEntrega) {
				
				Long id = ent.getEpi().getId();
				ent.setId(id);
				ent.setSituacao("Vencido");
				ent.setDevolucao(ent.getDevolucao());	
				ent.setStatus(ent.getStatus());
				ent.setQtdEntregas(ent.getQtdEntregas());
				ent.setData(ent.getData());
				ent.setEpi(ent.getEpi());
				ent.setFuncionario(ent.getFuncionario());
				ent.setEstado(ent.getEstado());
				ent.setHora(ent.getHora());
				ent.setProtocolo(ent.getProtocolo());
				ent.setQuantidade(ent.getQuantidade());
				ent.setValidade(ent.getValidade());
				entregaService.salvar(ent);
				
				
			}
			
			
			emailService.enviarEmail( listaEntrega);
			
		}
			
	}
	
	public List<EntregaBean> getListaEntrega() {
		return listaEntrega;
	}

	public ArrayList<String> getListaVencida() {
		return listaVencida;
	}

	public void setListaVencida(ArrayList<String> listaVencida) {
		this.listaVencida = listaVencida;
	}

	public void setListaEntrega(List<EntregaBean> listaEntrega) {
		this.listaEntrega = listaEntrega;
	}
	

}
