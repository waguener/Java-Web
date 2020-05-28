package aulas.hibernate.teste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Funcionario;
import aulas.hibernate.modelo.Projeto;

public class FuncionarioProjetoTeste {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session sessao = factory.openSession();

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("João");
		funcionario.setSalario(2000.0);

		Funcionario funcionario2 = new Funcionario();
		funcionario.setNome("Pedro");
		funcionario.setSalario(1500.0);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Novo Site Season");
		projeto.getFuncionarios().add(funcionario);
		projeto.getFuncionarios().add(funcionario2);
		
		sessao.beginTransaction();

		sessao.save(projeto);
		//sessao.save(funcionario);
		//sessao.save(funcionario2);
		
		sessao.getTransaction().commit();

		sessao.close();

	}

}
