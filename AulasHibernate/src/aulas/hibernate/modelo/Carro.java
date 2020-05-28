package aulas.hibernate.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue(value="reg_carro")
public class Carro extends Veiculo{

	private Integer quantidadedePortas;

	public Integer getQuantidadedePortas() {
		return quantidadedePortas;
	}

	public void setQuantidadedePortas(Integer quantidadedePortas) {
		this.quantidadedePortas = quantidadedePortas;
	}
	
}
