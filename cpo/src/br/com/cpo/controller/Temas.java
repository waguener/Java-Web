package br.com.cpo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cpo.model.Tema;
import br.com.cpo.services.TemaServices;

@ManagedBean
@Controller
@Scope("session")
public class Temas implements Serializable{

	private static final long serialVersionUID = -3509722744465268169L;
	
	private Tema tema2;
	private String tema;
	private List<String> temasDisponiveis;
	private String temaSelecionado;
	@Autowired
	private TemaServices temaServices;
	
	public void init() {
		temasDisponiveis = getTemas();
	}
	
	public String getTema() {
			if(tema == null){
				tema = "redmond";
			}
			return tema;
		}
	
		public List<String> getTemas() {
			if(temasDisponiveis == null){
				temasDisponiveis = new ArrayList<String>();
				temasDisponiveis.add("aristo");
		        temasDisponiveis.add("bootstrap");
		        temasDisponiveis.add("flick");
		        temasDisponiveis.add("hot-sneaks");
		        temasDisponiveis.add("sam");
		        temasDisponiveis.add("rocket");
		        temasDisponiveis.add("black-tie");
		        temasDisponiveis.add("dot-luv");
		        temasDisponiveis.add("delta");
		        temasDisponiveis.add("blitzer");
		        temasDisponiveis.add("bluesky");
		        temasDisponiveis.add("casablanca");
		        temasDisponiveis.add("cupertino");
		        temasDisponiveis.add("dark-hive");
		        temasDisponiveis.add("eggplant");
		        temasDisponiveis.add("excite-bike");
		        temasDisponiveis.add("glass-x");
		        temasDisponiveis.add("humanity");
		        temasDisponiveis.add("le-frog");
		        temasDisponiveis.add("midnight");
		        temasDisponiveis.add("mint-choc");
		        temasDisponiveis.add("overcast");
		        temasDisponiveis.add("pepper-grinder");
		        temasDisponiveis.add("redmond");
		        temasDisponiveis.add("smoothness");
		        temasDisponiveis.add("south-street");
		        temasDisponiveis.add("start");
		        temasDisponiveis.add("sunny");
		        temasDisponiveis.add("swanky-purse");
		        temasDisponiveis.add("trontastic");
		        temasDisponiveis.add("ui-darkness");
		        temasDisponiveis.add("ui-lightness");
		        temasDisponiveis.add("vader");
		      
				}
			
			return temasDisponiveis;
			}

		public void salvar() {
			tema2.setTema(tema);
			temaServices.salvar(tema2);
			
			FacesMessage msm = new FacesMessage("Tema Salvo com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);
		}
		
		public List<String> getTemasDisponiveis() {
			return temasDisponiveis;
		}

		public void setTemasDisponiveis(List<String> temasDisponiveis) {
			this.temasDisponiveis = temasDisponiveis;
		}

		public void setTema(String tema) {
			this.tema = tema;
		}

		public String getTemaSelecionado() {
			return temaSelecionado;
		}

		public void setTemaSelecionado(String temaSelecionado) {
			this.temaSelecionado = temaSelecionado;
		}
		
		
		
	 
}
