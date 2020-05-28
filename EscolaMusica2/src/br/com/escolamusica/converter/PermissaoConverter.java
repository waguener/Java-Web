package br.com.escolamusica.converter;

import javax.faces.convert.FacesConverter;

import br.com.escolamusica.model.Permissao;

@FacesConverter (value="permissaoConverter")
public class PermissaoConverter extends EntityConverter<Permissao>{

	public PermissaoConverter() {
		super(Permissao.class);
		
	}
	
}
