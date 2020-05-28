package br.com.sistemaepi.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaepi.Bean.EntregaBean;
import br.com.sistemaepi.dto.EquipamentosEmUsoDTO;
import br.com.sistemaepi.dto.VencimentosDTO;

@Service
@Transactional
public class EntregaService {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(EntregaBean entregaBean) {

		if (!entityManager.isOpen()) {
			System.out.println("Conexao Fechada");
		} else {

			entityManager.merge(entregaBean);

		}
		entityManager.close();
	}

	public List<EntregaBean> listar() {
		return entityManager.createQuery("from EntregaBean where quantidade > '0' order by id desc", EntregaBean.class)
				.getResultList();
	}

	public List<EntregaBean> listarTodos() {
		return entityManager.createQuery("from EntregaBean ", EntregaBean.class).getResultList();
	}

	public void excluir(EntregaBean entregaBean) {
		entityManager.remove(entityManager.merge(entregaBean));
		entityManager.close();
	}

	public List<EntregaBean> peloNome() {
		return entityManager
				.createQuery("from EntregaBean where status = 'Entrega' and quantidade > '0' order by funcionario desc",
						EntregaBean.class)
				.getResultList();
	}

	public List<EntregaBean> entrega() {
		return entityManager.createQuery(
				"from EntregaBean where status = 'Entrega' and quantidade > '0' and devolucao = 'Em uso' order by id desc",
				EntregaBean.class).getResultList();
	}

	public List<EntregaBean> porData(Date data) {
		return entityManager.createQuery(
				"from EntregaBean where data =:data and quantidade > '0' and devolucao = 'Em uso' order by id desc",
				EntregaBean.class).setParameter("data", data).getResultList();
	}

	public List<EntregaBean> entregaPeloFunc(String nome) {
		return entityManager.createQuery(
				"select ent from EntregaBean ent inner join ent.funcionario as fun where fun.nome like :nome and ent.quantidade > '0' and ent.devolucao = 'Em uso'  order by ent.data desc",
				EntregaBean.class).setParameter("nome", nome + "%").getResultList();
	}

	public List<EntregaBean> entregaFeitaNome(String nome) {
		return entityManager.createQuery(
				"select ent from EntregaBean ent inner join ent.funcionario as fun where fun.nome like :nome and ent.quantidade > '0' and ent.devolucao = 'Em uso' order by ent.id desc",
				EntregaBean.class).setParameter("nome", nome + "%").getResultList();
	}

	public List<EntregaBean> ultimoEpi(String nome) {
		return entityManager.createQuery(
				"select ent from EntregaBean ent inner join ent.funcionario as fun ent where fun.nome like :nome and ent.devolucao = 'Em uso' order by ent.id desc",
				EntregaBean.class).setParameter("nome", nome + "%").getResultList();
	}

	public List<EntregaBean> pesquisar(String nome) {
		return entityManager.createQuery(
				"select ent from EntregaBean ent inner join ent.funcionario as fun where fun.nome like :nome and fun.status = 'Admitido' order by ent.id desc",
				EntregaBean.class).setParameter("nome", nome + "%").getResultList();
	}

	public List<EquipamentosEmUsoDTO> obterEqpEmUso(String funcionario) {
		return entityManager.createQuery(
				"select new br.com.sistemaepi.dto.EquipamentosEmUsoDTO(epi.descricao, ent.data, ent.hora, ent.quantidade, cast ((CURRENT_DATE - ent.data) as integer)) from EntregaBean ent inner join ent.funcionario as fun "
						+ "inner join ent.epi as epi where fun.nome =:nome and ent.devolucao = 'Em uso' and ent.quantidade > '0' order by ent.id desc",
				EquipamentosEmUsoDTO.class).setParameter("nome", funcionario).getResultList();

	}
	
	public List<VencimentosDTO> vencimentosEpi() {
		return entityManager.createQuery(
				"select new br.com.sistemaepi.dto.VencimentosDTO(ent.funcionario.nome,epi.descricao, ent.data, ent.hora, ent.quantidade, cast ((CURRENT_DATE - ent.data) as integer)) from EntregaBean ent inner join ent.funcionario as fun "
						+ "inner join ent.epi as epi where ent.devolucao = 'Em uso' and ent.quantidade > '0' and fun.status = 'Admitido' order by ent.id desc",
						VencimentosDTO.class).getResultList();

	}

	public Boolean porCa(Long ca) {
		boolean teste;
				
		Query tipo = entityManager
				.createQuery("from EntregaBean where id_epi =:id_epi ",
						EntregaBean.class)
				.setParameter("id_epi", ca);
				
		 if(tipo.getResultList().isEmpty()) {
			 teste = true;
		 }else {
			 teste = false;
		 }
		 return teste;
	}
	
	public List<EntregaBean> pesquisarVencidos() {
		try {
			return entityManager.createQuery(
					"select ent from EntregaBean ent inner join ent.funcionario as fun where ent.status = 'Entrega' and fun.status = 'Admitido' and ent.validade = '5' order by ent.id desc",
					EntregaBean.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public List<EntregaBean> porSituacao(){
		try {
			return entityManager.createQuery("from EntregaBean where validade >= 5", EntregaBean.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
