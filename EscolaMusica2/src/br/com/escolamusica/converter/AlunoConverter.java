package br.com.escolamusica.converter;

import javax.faces.convert.FacesConverter;

import br.com.escolamusica.model.Aluno;

@FacesConverter (value="alunoConverter")
public class AlunoConverter extends EntityConverter<Aluno>{

	public AlunoConverter() {
		super(Aluno.class);
		
	}
	
}
