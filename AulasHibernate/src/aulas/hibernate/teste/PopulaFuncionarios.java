package aulas.hibernate.teste;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Departamento;
import aulas.hibernate.modelo.Funcionario;

public class PopulaFuncionarios {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session sessao = factory.openSession();
		
		sessao.beginTransaction();
		
		Departamento ti = new Departamento("TI");
		Departamento rh = new Departamento("RH");
		Departamento financeiro = new Departamento("Financeiro");
		
		sessao.save(ti);
		sessao.save(rh);
		sessao.save(financeiro);
		
		Funcionario js = new Funcionario("Joco da Silva", 2000.0, criarData(28, 11, 1984), ti);
		Funcionario mt = new Funcionario("Maria Thereza", 2200.0, criarData(28, 2, 1992), ti);
		Funcionario lf = new Funcionario("Lucas Freitas", 1900.0, criarData(2, 11, 1994), ti);
		Funcionario ca = new Funcionario("Caroline Almeida", 4000.0, criarData(22, 11, 1984), ti);
		Funcionario pe = new Funcionario("Pedro Estagiario", 1000.0, criarData(28, 11, 2000), rh);
		Funcionario tf = new Funcionario("Tiago Farias", 2000.0, criarData(28, 1, 1985), financeiro);
		Funcionario jo = new Funcionario("Joco Oliveira", 3200.0, criarData(28, 11, 1987), financeiro);
		
		sessao.save(js);
		sessao.save(mt);
		sessao.save(lf);
		sessao.save(ca);
		sessao.save(pe);
		sessao.save(tf);
		sessao.save(jo);
		
		sessao.getTransaction().commit();
		
		sessao.close();
		
	}

	private static Date criarData(int dia, int mes, int ano) {
		return new GregorianCalendar(ano, mes - 1, dia).getTime();
	}
}
