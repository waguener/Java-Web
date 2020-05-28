package br.com.sistemaepi.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.Bean.SaldoMovBean;
import br.com.sistemaepi.service.SaldoMovService;

@ManagedBean
@Controller
@Scope
public class SistemaControllerSaldoMov implements Serializable{

	
	private static final long serialVersionUID = 8927598987718863353L;

	@Autowired
	private SaldoMovService saldoService;
	
	private SaldoMovBean saldo;
	private EntregaBean entrega;
	public void salvar() {
		
	}
	
	
	
}
