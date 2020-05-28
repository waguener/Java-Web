package br.com.sistemaepi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.SaldoMovBean;

@Service
@Transactional
public class SaldoMovService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(SaldoMovBean saldoBean) {
		
		if (!entityManager.isOpen()) {
			System.out.println("Conexao Fechada");
			}else {
		
		entityManager.merge(saldoBean);
			}
	}
	
	public List<SaldoMovBean> listar(){
		return entityManager.createQuery("from Saldobean",SaldoMovBean.class).getResultList();
	}
	
	
	
}
