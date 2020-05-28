package br.com.sistemaepi.converter;

import javax.faces.convert.FacesConverter;

import br.com.sistemaepi.Bean.EntregaBean;
@FacesConverter(value="entregaConverter")
public class EntregaConverter extends EntityConverter<EntregaBean>{

	public EntregaConverter() {
		super(EntregaBean.class);
		
	}
	
	

}
