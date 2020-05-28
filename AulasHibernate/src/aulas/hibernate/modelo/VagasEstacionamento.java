package aulas.hibernate.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cli_vagas")
public class VagasEstacionamento {
	
	private static final String SEQ_NAME = "vagas_seq";

	private Long id;
	private String localizacao;
	
	private Funcionario Funcionario;
	
	@Id
	@SequenceGenerator(name=SEQ_NAME,sequenceName=SEQ_NAME, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=SEQ_NAME)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@OneToOne
	public Funcionario getFuncionario() {
		return Funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		Funcionario = funcionario;
	}
	
	
	
}
