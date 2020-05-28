package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Banco;

@Service
@Transactional
public class BancoServices {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Banco banco) {
		em.merge(banco);
		em.close();
	}
	
	public void excluir(Banco banco) {
		em.remove(em.merge(banco));
		em.close();
	}
	
	public List<Banco> listar(){
		try {
			return em.createQuery("from Banco order by banco desc",Banco.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public  Banco buscarCod(String banco) {
		try {
			return em.createQuery("from Banco where banco =:banco",Banco.class).setParameter("banco", banco).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
