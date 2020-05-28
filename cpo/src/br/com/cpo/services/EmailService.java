package br.com.cpo.services;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.cpo.model.Arquivo;
import br.com.cpo.model.Carga;

@Service
public class EmailService {

	public void enviarEmail(String assunto, String texto, List<File> anexos, String... destinatarios) {
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.email-ssl.com.br");
			mailSender.setPort(587);
			mailSender.setProtocol("smtp");
			mailSender.setUsername("armazem@olgber.com.br");
			mailSender.setPassword("olgber2019@@");
			mailSender.setDefaultEncoding("utf-8");

			Properties properties = new Properties();
			properties.setProperty("username", "armazem@olgber.com.br");
			properties.setProperty("password", "olgber2019@@");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			mailSender.setJavaMailProperties(properties);

			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom("armazem@olgber.com.br");
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
			FacesMessage msm = new FacesMessage("E-mail enviado com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
	@Autowired
	private ArquivoServices arquivoServices;
	
	@Async
	public void enviarEmailCarga(Carga carga ) {

		String data = new SimpleDateFormat("dd/MM/yyyy ").format(new Date());
		
		String assunto = carga.getAssunto();

		String texto = pegarHtmlEmail("resources/email_cpo.html");

		texto = texto.replace("{data}", data);
		
		texto = texto.replace("{hora}", new SimpleDateFormat("HH:mm").format(new Date()));
		
		texto = texto.replace("{saudacao}", carga.getSaudacao());
		
		texto = texto.replace("{corpo}", carga.getCorpo());
					
		String[] destinatarios = carga.getRemetentes().split("\\,");
				
		List<File> anexos = new ArrayList<File>();
		
		String numCarga = carga.getId().toString();
		
		List<Arquivo> arquivos = arquivoServices.buscarArquivo(numCarga);
		
		for(Arquivo pegar : arquivos) {
			File file = arquivoServices.obterArquivo(pegar.getNome(), "carga/");
			
			anexos.add(file);
			
		}
			

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
