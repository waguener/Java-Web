package aulas.hibernate.vo;

public class MediaSalarialPorDepartementoVO {

	private String nomeDepartamento;
	private Double mediaSalarial;

	public MediaSalarialPorDepartementoVO(String nomeDepartamento, Double mediaSalarial) {
		
		this.nomeDepartamento = nomeDepartamento;
		this.mediaSalarial = mediaSalarial;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	public Double getMediaSalarial() {
		return mediaSalarial;
	}

	public void setMediaSalarial(Double mediaSalarial) {
		this.mediaSalarial = mediaSalarial;
	}

}
