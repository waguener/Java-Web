package aulas.hibernate.teste;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import aulas.hibernate.modelo.Funcionario;

public class TesteCriteria {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session sessao = factory.openSession();
		
		sessao.beginTransaction();
		
		
		Criteria criteria = sessao.createCriteria(Funcionario.class);
		//criteria.add(Restrictions.eq("nome", "Joco Oliveira"));
		//criteria.add(Restrictions.ge("salario", 2000.0));
		//criteria.add(Restrictions.between("salario", 1800.0, 2200.0));
		//criteria.add(Restrictions.or(Restrictions.eq("nome", "Joco Oliveira"),(Restrictions.ge("salario", 2000.0))));
		//criteria.setProjection(Projections.max("salario"));
		criteria.setProjection(Projections.property("nome"));
		criteria.addOrder(Order.asc("nome"));
		
		List<String> nomes = criteria.list();
		
		for (String nome : nomes) {
			System.out.println("Nomes: " + nomes);
		}
		
		Double maiorSalario = (Double) criteria.uniqueResult();
		/*List<Funcionario> funcionarios = criteria.list();
		 for (Funcionario funcionario : funcionarios) {
			  
			  System.out.println("funcionario: " + funcionario.getNome());
			  System.out.println("Departamento: " +
			  funcionario.getDepartamento().getNome()); System.out.println(
			  "Data de Admissão: " + new
			  
			  SimpleDateFormat("dd/MM/yyyy").format(funcionario.getDataAdmissao()));
			  System.out.println("Salario: " + funcionario.getSalario());
			  //System.out.println("Maior Salario: " + maiorSalario);
			  System.out.println("--------------------------------------------"); }*/
		
		sessao.getTransaction().commit();
		
		sessao.close();
	}

}
