package aulas.hibernate.teste;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import aulas.hibernate.modelo.Departamento;
import aulas.hibernate.modelo.Funcionario;
import aulas.hibernate.vo.MediaSalarialPorDepartementoVO;

public class TesteFuncionarioCRUD {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session sessao = factory.openSession();

		/*
		 * Departamento ti = sessao.createQuery("from Departamento " + "where nome = ?",
		 * Departamento.class) .setParameter(0, "TI").getSingleResult();
		 */

		/*
		 * List<Funcionario> funcionarios = sessao
		 * .createQuery("from Funcionario where departamento = :departamento order by departamento.id, nome desc"
		 * , Funcionario.class) .setParameter("departamento", ti).getResultList();
		 */

		/*
		 * List<Funcionario> funcionarios = sessao
		 * .createQuery("from Funcionario where year (dataAdmissao) < :ano order by nome"
		 * , Funcionario.class) .setParameter("ano", 1990).getResultList();
		 */

		/*
		 * Long quantidadeCadastrados = (Long)
		 * sessao.createQuery("Select count(id) from Funcionario").getSingleResult();
		 * 
		 * 
		 * System.out.println("Qtd: " + quantidadeCadastrados);
		 */

		/*
		 * List<Object[]> resultado =
		 * sessao.createQuery("select nome, dataAdmissao from Funcionario order by nome"
		 * ).getResultList();
		 * 
		 * for (Object[] objs : resultado) { System.out.println("Nome: " + objs[0]);
		 * System.out.println("Data de Admissão: " + new
		 * SimpleDateFormat("dd/MM/yyyy").format(objs[1])); }
		 */

		/*
		 * List<Funcionario> funcionarios = sessao.createQuery(
		 * "select new Funcionario(func.nome, func.dataAdmissao, dep.nome) from Funcionario func inner join func.departamento dep order by func.nome"
		  ) .getResultList();*/
		  
		List<Funcionario> funcionarios = sessao.getNamedQuery("Funcionario.listarTodos").getResultList();		
		  for (Funcionario funcionario : funcionarios) {
		  
		  System.out.println("funcionario: " + funcionario.getNome());
		  System.out.println("Departamento: " +
		  funcionario.getDepartamento().getNome()); System.out.println(
		  "Data de Admissão: " + new
		  SimpleDateFormat("dd/MM/yyyy").format(funcionario.getDataAdmissao()));
		  System.out.println("--------------------------------------------"); }
		 

		/*List<MediaSalarialPorDepartementoVO> medias = sessao.createQuery(
				"select new aulas.hibernate.vo.MediaSalarialPorDepartementoVO(dep.nome, avg(func.salario)from Funcionario func inner join func.departamento dep group by dep.nome")
				.getResultList();

		for (MediaSalarialPorDepartementoVO vo : medias) {
			System.out.println("Departamento: " + vo.getNomeDepartamento());
			System.out.println("Média: " + vo.getMediaSalarial());
		}*/
		
		
		
		sessao.close();
	}

}
