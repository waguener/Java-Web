package br.com.belcanto.converter;

import javax.faces.convert.FacesConverter;

import br.com.belcanto.model.Horario;

@FacesConverter(value="horarioConverter")
public class HorarioConverter extends EntityConverter<Horario>{

	public HorarioConverter(Class<Horario> targetClass) {
		super(targetClass);
		
	}

}
