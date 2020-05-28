package br.com.escolamusica.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;



@Service
public class AgendamentoService {
	
	//@Scheduled(fixedDelay=60*1000000000*5)
	//@Scheduled(cron="45 29 11 * * *")
	public void enviarEmailAniversariantes() {
		System.out.println("Enviado e-mails de aniversário");
		try {
			
		/*	Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("heitor.instrutor.season@gmail.com", "academiaweb20142015"));
			email.setSSLOnConnect(true);
			email.setFrom("heitor.instrutor.season@gmail.com");
			email.setSubject("Teste de envio de email Aw. 2018");
			email.setMsg("Email enviado com sucesso");
			email.addTo("suporte@olgber.com.br");
			email.send();*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
