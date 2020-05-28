package aulas.hibernate.teste;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Cliente;
import aulas.hibernate.modelo.TipoCliente;

public class ClienteTeste {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session sessao = factory.openSession();

		Cliente cliente = new Cliente();
		
		cliente.setNome("Pedro da Silva");
		
		cliente.setDataNascimento(new Date());
		
		cliente.setTipo(TipoCliente.PESSOA_FISICA);
		
		cliente.setFoto(new byte[] {0,0,1,0});
		
		cliente.getTelefone().add("(12)12358963");
		cliente.getTelefone().add("(12)12758963");
		
		sessao.beginTransaction();

		//sessao.saveOrUpdate(cliente);
		
		Cliente clienteBanco = sessao.get(Cliente.class, 1L);
		/*Cliente clienteBanco = (Cliente) sessao.createQuery("from Cliente where id = :id").setParameter("id", 1L).uniqueResult();*/
		
		sessao.getTransaction().commit();
		//JOptionPane.showMessageDialog(null, "Foto" + clienteBanco.getFoto());
		sessao.close();
		
		System.out.println("Nome: "+clienteBanco.getNome());
		System.out.println("Foto: "+clienteBanco.getFoto());
		System.out.println("Qtd de telefones: "+clienteBanco.getTelefone().size());
	}
}
