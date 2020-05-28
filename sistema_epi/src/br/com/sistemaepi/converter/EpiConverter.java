package br.com.sistemaepi.converter;

import javax.faces.convert.FacesConverter;

import br.com.sistemaepi.Bean.EpiBean;
@FacesConverter(value="epiConverter")
public class EpiConverter extends EntityConverter<EpiBean>{

	public EpiConverter() {
		super(EpiBean.class);
		
	}

}
