package br.com.escolamusica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.escolamusica.model.Aluno;

public class AlunoDAO {

	public static void salvar(Aluno aluno){
		EntityManager em = JPAUtil.openConnection();
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Aluno> listar(){
		EntityManager em = JPAUtil.openConnection();
		List<Aluno> alunos = em.createQuery(
				"from Aluno order by nome", Aluno.class).getResultList();
		em.close();
		return alunos;
	}
	
	public static void excluir(Aluno aluno){
		EntityManager em = JPAUtil.openConnection();
		
		em.getTransaction().begin();
		
		em.remove(em.merge(aluno));
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static void main(String[] args) {
		/*Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua da Alegria");
		endereco.setNumero(100);
		
		Aluno aluno = new Aluno();
		aluno.setNome("Aluno Alegre");
		aluno.setCpf("123.123.123-43");
		
		aluno.setEndereco(endereco);
		
		salvar(aluno);*/
		
		for(Aluno aluno : listar()){
			System.out.println("Nome: " + aluno.getNome());
			System.out.println("Logradouro: " + aluno.getEndereco().getLogradouro());
		}
	}

	public static Aluno obterPorId(Long idAluno) {
		
		EntityManager em = JPAUtil.openConnection();
		
		Aluno aluno = em.find(Aluno.class, idAluno);
		em.close();
		return aluno;
	}
}







