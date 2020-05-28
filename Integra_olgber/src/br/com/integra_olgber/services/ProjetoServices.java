package br.com.integra_olgber.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.integra_olgber.model.Projeto;

@Service
@Transactional
public class ProjetoServices {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Projeto projeto) {

		entityManager.merge(projeto);
		entityManager.close();
	}
	
	public void excluir(Projeto projeto) {
		entityManager.remove(entityManager.merge(projeto));
		entityManager.close();
	}

	public List<Projeto> listar() {
		return entityManager.createQuery("from Projeto order by id desc", Projeto.class).getResultList();
		
	}
	public List<Projeto> porNome(String projeto) {
		return entityManager.createQuery("from Projeto where projeto like :projeto order by id desc", Projeto.class)
				.setParameter("projeto", projeto + "%").getResultList();
		
	}
	
	public List<Projeto> projeto(Date hoje) {
		return entityManager.createQuery("from Projeto where data_termino >=:hoje order by id desc", Projeto.class)
				.setParameter("hoje", hoje ).getResultList();
		
	}

}
