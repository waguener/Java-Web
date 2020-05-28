package br.com.sistemaepi.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.sistemaepi.Bean.EntregaBean;

@Service
public class EmailService {

	public void enviarEmail(String assunto, String texto, String destinatario) {
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
			helper.addTo(destinatario);

			mailSender.send(msg);
			FacesMessage msm = new FacesMessage("E-mail enviado com sucesso!!");
			FacesContext.getCurrentInstance().addMessage(null, msm);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Async
	public void enviarEmail(List<EntregaBean> entregas) {

		String assunto = "Epis vencidos!!";

		String texto = pegarHtmlEmail("resources/email_vencidos.html");

		texto = texto.replace("{saudacao}", "Bom dia!" + "\n<br/>" + "Segue a Lista de Epis Vencidos");

		String conteudo = "";

		for (Object e : entregas) {

			conteudo += "Funcionário: " + ((EntregaBean) e).getFuncionario().getNome() + "\n<br/>" + "Epi: "
					+ ((EntregaBean) e).getEpi().getDescricao() + "\n<br/>" + "Data Entrega: "
					+ ((EntregaBean) e).getData().toString() + "\n<br/>" + "\n<br/>";
				
			
		}
		
		
		
		
		texto = texto.replace("{corpo}", conteudo);

		String destinatario = "rh@olgber.com.br";

		enviarEmail(assunto, texto, destinatario);

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
