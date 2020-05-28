package br.com.escolamusica.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.escolamusica.model.Avaliacao;


@Service
public class EmailService {

	
	
	
	
	public void enviarEmail(String assunto, String texto, 
			List<File> anexos, String... destinatarios) {
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setProtocol("smtp");
			mailSender.setUsername("heitor.instrutor.season@gmail.com");
			mailSender.setPassword("academiawebseason2019");
			mailSender.setDefaultEncoding("utf-8");
			
			Properties properties = new Properties();
			properties.setProperty("username", "heitor.instrutor.season@gmail.com");
			properties.setProperty("password", "academiawebseason2019");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			mailSender.setJavaMailProperties(properties);
			
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom("heitor.instrutor.season@gmail.com");
			helper.setSubject(assunto);
			helper.setText(texto, true);
			
			for (String destinatario : destinatarios) {
				helper.addTo(destinatario);
			}
			
			if(anexos != null && !anexos.isEmpty()){
				for(File anexo : anexos){
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
	public void enviarEmailCorrecaoAvaliacao(Avaliacao avaliacao) {
		String assunto = "Avaliação" + avaliacao.getBimestreAno() +  " corrigida";
		String texto = pegarHtmlEmail("resources/email_correcao_avaliacao.html");
		texto = texto.replace("{aluno}", avaliacao.getMatricula().getAluno().getNome());
		texto = texto.replace("{bimestre}", avaliacao.getBimestre().getLabel());
		texto = texto.replace("{ano}", avaliacao.getAno().toString());
		
		
		List<File> anexos = Arrays.asList(avaliacao.getArquivo().getFile());
		enviarEmail(assunto, texto, anexos, avaliacao.getMatricula().getAluno().getEmail());
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
