package br.com.sistemaepi.Bean;

import javax.persistence.Embeddable;

@Embeddable
public class Contato {

	private String telFixo;
	private String cel1;
	private String cel2;
	private String cel3;
	private String email;
	
	//Getters e Setters
	public String getTelFixo() {
		return telFixo;
	}
	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}
	public String getCel1() {
		return cel1;
	}
	public void setCel1(String cel1) {
		this.cel1 = cel1;
	}
	public String getCel2() {
		return cel2;
	}
	public void setCel2(String cel2) {
		this.cel2 = cel2;
	}
	public String getCel3() {
		return cel3;
	}
	public void setCel3(String cel3) {
		this.cel3 = cel3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
