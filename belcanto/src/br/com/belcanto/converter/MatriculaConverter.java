package br.com.belcanto.converter;

import javax.faces.convert.FacesConverter;


import br.com.belcanto.model.Matricula;

@FacesConverter(value="matriculaConverter")
public class MatriculaConverter extends EntityConverter<Matricula>{

	public MatriculaConverter(Class<Matricula> targetClass) {
		super(targetClass);
		
	}

	

}
