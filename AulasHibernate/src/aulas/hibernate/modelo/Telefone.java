package aulas.hibernate.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Func_telefone")
public class Telefone {
	
	private static final String SEQ_NAME = "tel_seq";
	
	private Long id;
	private String codigo;
    private String numero;
    
    public Telefone(String codigo, String numero) {
		super();
		this.codigo = codigo;
		this.numero = numero;
	}
	@Id
    @SequenceGenerator(name=SEQ_NAME,sequenceName=SEQ_NAME, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=SEQ_NAME)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
    
    
}
