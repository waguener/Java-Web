package aulas.hibernate.teste;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Carro;
import aulas.hibernate.modelo.Moto;

public class TesteHeranca {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session sessao = factory.openSession();
		
		sessao.beginTransaction();
		
		/*Carro carro = new Carro();
		carro.setMarca("Ford");
		carro.setModelo("Fiesta");
		carro.setQuantidadedePortas(4);
		
		sessao.save(carro);
		
		Moto moto = new Moto();
		moto.setMarca("Suzuki");
		moto.setModelo("V-Strom 650 XT");
		moto.setCilindradas(645);
		
		sessao.save(moto);*/
		
		//Moto moto = sessao.get(Moto.class, 2L);
		
		List<Moto> motos = sessao.createQuery("from Moto where marca = :marca").setParameter("marca","Suzuki").getResultList();
		
		System.out.println(motos.get(0).getModelo());
		
		
		sessao.getTransaction().commit();
		
		sessao.close();
	}
}
