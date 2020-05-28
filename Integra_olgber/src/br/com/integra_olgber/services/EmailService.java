package br.com.integra_olgber.services;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.integra_olgber.model.EventoFuncionario;

@Service
public class EmailService {

	public void enviarEmail(String assunto, String texto, List<File> anexos, String... destinatarios) {
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.email-ssl.com.br");
			mailSender.setPort(587);
			mailSender.setProtocol("smtp");
			mailSender.setUsername("rh@olgber.com.br");
			mailSender.setPassword("olgber2018@@");
			mailSender.setDefaultEncoding("utf-8");

			Properties properties = new Properties();
			properties.setProperty("username", "rh@olgber.com.br");
			properties.setProperty("password", "olgber2018@@");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			mailSender.setJavaMailProperties(properties);

			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom("rh@olgber.com.br");
			helper.setSubject(assunto);
			helper.setText(texto, true);

			for (String destinatario : destinatarios) {

				helper.addTo(destinatario);
				

			}

			if (anexos != null && !anexos.isEmpty()) {

				for (File anexo : anexos) {
					
					FileSystemResource attachment = new FileSystemResource(anexo);
					helper.addAttachment(anexo.getName(), attachment);
				}

			}

			mailSender.send(msg);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Async
	public void enviarEmailAtestado(EventoFuncionario eventoFuncionario)  {
						
		String data = new SimpleDateFormat("dd/MM/yyyy").format( eventoFuncionario.getDataAtestado());
		
		String assunto = "Inclusão de Evento - Atestado: " + eventoFuncionario.getFuncionario().getNome();
		
		String texto = pegarHtmlEmail("resources/email_evento_funcionario.html");
		texto = texto.replace("{funcionario}", eventoFuncionario.getFuncionario().getNome());
		texto = texto.replace("{data}",data);
		texto = texto.replace("{obs}", eventoFuncionario.getObsAtestado());
		
		String[] destinatarios = { "suporte@olgber.com.br", eventoFuncionario.getAgencia().getEmail()};
		
		File anexo = eventoFuncionario.getArquivo().getFile();
		
		List<File> anexos = new ArrayList<File>();
		anexos.add(anexo);
		enviarEmail(assunto, texto, anexos, destinatarios);

	}

	private String pegarHtmlEmail(String url) {
		InputStream is = getClass().getResourceAsStream(url);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		try {
			result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			return buf.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	

}
