package br.com.sistemaepi.converter;

import javax.faces.convert.FacesConverter;

import br.com.sistemaepi.Bean.FuncionarioBean;

@FacesConverter(value="funcionarioConverter")
public class FuncionarioConverter extends EntityConverter<FuncionarioBean>{

	public FuncionarioConverter() {
		super(FuncionarioBean.class);
		
	}
	
	

}
