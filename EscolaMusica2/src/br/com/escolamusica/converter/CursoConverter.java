package br.com.escolamusica.converter;

import javax.faces.convert.FacesConverter;

import br.com.escolamusica.model.Curso;
@FacesConverter(value="cursoConverter")
public class CursoConverter extends EntityConverter<Curso>{

	public CursoConverter() {
		super(Curso.class);
		
	}

	
	
}
