package br.com.belcanto.converter;

import javax.faces.convert.FacesConverter;

import br.com.belcanto.model.Curso;

@FacesConverter(value="cursoConverter")
public class CursoConverter extends EntityConverter<Curso>{

	public CursoConverter(Class<Curso> targetClass) {
		super(targetClass);
		
	}

}
