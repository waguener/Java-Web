package aulas.hibernate.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue(value="reg_moto")
public class Moto extends Veiculo{

	
	private Integer cilindradas;

	public Integer getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(Integer cilindradas) {
		this.cilindradas = cilindradas;
	}
	
	
	
}
