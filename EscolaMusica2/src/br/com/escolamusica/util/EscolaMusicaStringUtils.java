package br.com.escolamusica.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class EscolaMusicaStringUtils {

	public static String removerCaracteresEspeciais(String texto) {
		texto = Normalizer.normalize(texto, Form.NFD);
		texto = texto.replaceAll("[^\\p{ASCII}]", "");
		return texto;
	}
	
}
