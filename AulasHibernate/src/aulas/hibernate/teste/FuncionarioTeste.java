package aulas.hibernate.teste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Funcionario;
import aulas.hibernate.modelo.Projeto;
import aulas.hibernate.modelo.Telefone;
import aulas.hibernate.modelo.VagasEstacionamento;

public class FuncionarioTeste {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session sessao = factory.openSession();
		
		VagasEstacionamento vaga = new VagasEstacionamento();
		
		vaga.setLocalizacao("A2");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("João");
		funcionario.setSalario(2000.0);
		funcionario.setVagas(vaga);

		Funcionario funcionario2 = new Funcionario();
		funcionario.setNome("Pedro");
		funcionario.setSalario(1500.0);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Novo Site Season");
		projeto.getFuncionarios().add(funcionario);
		projeto.getFuncionarios().add(funcionario2);
		
		Projeto projeto2 = new Projeto();
		projeto2.setNome("Novo Site GlobalCode");
		projeto2.getFuncionarios().add(funcionario);
		projeto2.getFuncionarios().add(funcionario2);
		
		
		funcionario.getTelefones().add(new Telefone("12", "1234-4321"));
		funcionario.getTelefones().add(new Telefone("12", "1256-4321"));
		
		sessao.beginTransaction();		
		
		/*sessao.save(vaga);
		sessao.save(funcionario);*/
		sessao.save(projeto);
		sessao.save(projeto2);
		
		sessao.getTransaction().commit();
		
		sessao.close();
		
	}
	
	

}
