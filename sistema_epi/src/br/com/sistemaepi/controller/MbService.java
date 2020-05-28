package br.com.sistemaepi.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import WebService.CepWebService;
@ManagedBean
@Controller
@Scope("session")
public class MbService implements Serializable{

	private static final long serialVersionUID = 1L;
	 
    private String cep = null;
 
    private String tipoLogradouro;
    private String logradouro;
    private String estado;
    private String cidade;
    private String bairro;
 
    public void encontraCEP() {
        CepWebService cepWebService = new CepWebService(getCep());
 
        if (cepWebService.getResultado() == 1) {
            setTipoLogradouro(cepWebService.getTipoLogradouro());
            setLogradouro(cepWebService.getLogradouro());
            setEstado(cepWebService.getEstado());
            setCidade(cepWebService.getCidade());
            setBairro(cepWebService.getBairro());
            
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Cep inválido ou inexistente!!",
                            "Cep não existe"));
        }}
        
    public void novo() {
    	CepWebService cepWebService = new CepWebService(getCep());
    	cep = "";
    	setTipoLogradouro(cepWebService.getTipoLogradouro());
        setLogradouro(cepWebService.getLogradouro());
        setEstado(cepWebService.getEstado());
        setCidade(cepWebService.getCidade());
        setBairro(cepWebService.getBairro());
    }

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
}