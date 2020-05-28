package Testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.sistemaepi.Bean.FuncionarioBean;
import br.com.sistemaepi.controller.SistemaControllerFuncionario;




public class Funcionario {

	@Test
	public void testBuscarFuncionario() {
		FuncionarioBean funcionarioBean = new FuncionarioBean();
		funcionarioBean.setAgencia("teste");
		funcionarioBean.setCpf("305.845.759-27");		
		funcionarioBean.setId(new Long(15));
		
		SistemaControllerFuncionario func = new SistemaControllerFuncionario();
		
		assertEquals(funcionarioBean, func.cadastraFunc());
		
		
	}

}
