package br.com.belcanto.converter;

import javax.faces.convert.FacesConverter;

import br.com.belcanto.model.Modulo;

@FacesConverter(value="moduloConverter")
public class ModuloConverter extends EntityConverter<Modulo>{

	public ModuloConverter(Class<Modulo> targetClass) {
		super(targetClass);
		
	}

	

}
