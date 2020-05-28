package br.com.cpo.services;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpo.model.Arquivo;
import br.com.cpo.model.Carga;

@Service
@Transactional
public class CargaServices {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ArquivoServices arquivoServices;

	// Salvar
	public void salvar(Carga carga) {
		em.merge(carga);
	}

	public void salvarSaida(Carga carga) {

		Arquivo arquivosalvo = arquivoServices.inserirArquivoNoSistema(carga.getArquivo(),
				obterDiretorioEventos(carga));
		carga.setArquivo(arquivosalvo);

		em.merge(carga);
		em.close();
	}

	// Excluir
	public void excluir(Carga carga) {
		em.remove(em.merge(carga));
	}

	// Pesquisas
	public List<Carga> listar() {
		try {
		return em.createQuery("from Carga order by id desc", Carga.class).getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}

	public List<Carga> porData(Date data_chegada) {
		try {
		return em.createQuery("from Carga where data_chegada =:data_chegada order by id desc", Carga.class)
				.setParameter("data_chegada", data_chegada).getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}

	public Carga ultimaCarga(String id) {
		try {
		return em.createQuery("from Carga where id = :id ", Carga.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	public Carga ultimaCargaLong(Long id) {
		try {
		return em.createQuery("from Carga where id = :id ", Carga.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	public Carga obterPorId(Long id) {
		try {
		return em.find(Carga.class, id);
		}catch (NoResultException e) {
			return null;
		}
	}

	public Carga cargaAtual( Date data, String id) {
		try {
			return em.createQuery("from Carga where data_chegada =:data and id =:id", Carga.class)
					.setParameter("data", data).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public List<Carga> entreData(Date data1, Date data2) {
		try {
			return em
					.createQuery("from Carga where data_chegada BETWEEN :data_chegada and :data_saida order by id desc ",
							Carga.class)
					.setParameter("data_chegada", data1).setParameter("data_saida", data2).getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public Carga ultimoId() {
		try {
		String carga = em.createQuery("select max(id) from Carga ").getSingleResult().toString();
		Carga id = new Carga();

		id.setId(Long.parseLong(carga));

		return id;
		}catch (NoResultException e) {
			return null;
		}
	}

	public String retorno(String placa_carreta) {

		try {
			return em.createQuery(
					"select max(id) from Carga where placa_carreta =:placa_carreta and horaTotalSaida is not null")
					.setParameter("placa_carreta", placa_carreta).getSingleResult().toString();
		} catch (Exception e) {
			return null;
		}
	}

	private String obterDiretorioEventos(Carga carga) {
		return "carga/";
	}

	public File obterArquivoDeCarga(Carga carga) {
		return arquivoServices.obterArquivo(carga.getArquivo().getNome(), obterDiretorioEventos(carga));
	}

}
