package br.com.sistemaepi.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "data")
@ViewScoped
public class Data {

	public static String gerarData() {
		String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		
		return data;
	}
	
}
