package br.com.belcanto.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belcanto.model.Horario;

@Service
@Transactional
public class HorarioServices {

	@PersistenceContext
	private EntityManager em;

	public Horario salvar(Horario horario) {
		return em.merge(horario);

	}

	public void excluir(Horario horario) {
		em.remove(horario);
		em.close();
	}

	public List<Horario> listarPorTurma(Long idTurma) {
		try {
			return em.createQuery("select t.horarios from Turma t where t.id =:idTurma",Horario.class)
					.setParameter("id", idTurma).getResultList();
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Horario> listarHorarios(Long id) {
		try {
			return em.createQuery("select hor from Horario hor inner join hor.turma tur where tur.id =:id", Horario.class)
					.setParameter("id", id).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
