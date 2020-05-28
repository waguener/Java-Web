package aulas.hibernate.modelo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="tipo_veiculo", discriminatorType=DiscriminatorType.STRING)
public class Veiculo {

	private static final String SEQ_NAME = "veiculo_seq";
	
	private Long id;
	private String marca;
	private String modelo;

	// construtores
	public Veiculo() {
		super();
	}

	public Veiculo(String marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}

	// getters e stters
	
	@Id
	@SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
