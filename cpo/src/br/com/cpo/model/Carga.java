package br.com.cpo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Carga implements Serializable{
	
	private static final long serialVersionUID = 1093255448704697989L;

	private Long id;
	private String transportadora;
	private Boolean situacao;
	private String placa_carreta;
	private String motorista;
	private Date data_chegada;
	private String hora_chegada;
	private String hora_total_parado;
	
	private String hora_inicio_descarregamento;
	private String hora_termino_descarregamento;
	private String hora_total_descarregamento;
	
	private String hora_inicio_carregamento;
	private String hora_termino_carregamento;
	private String hora_total_carregamento;
	
	private String hora_saida_carga;
	private String horaTotalsaida;
	private String hora_total_olgber;
	private Date data_saida;
	private String tempo_descarregamento;
	private String tempo_carregamento;
	private String tempo_retorno;
	private String tempo_parado;
	private String status;
	
	private String prodRecebido;
	private String checkRecebimento;
	private String nfRecebimento;
	private String temperatura;
	private String prodRecebido2;
	private String checkRecebimento2;
	
	private String nfRecebimento2;
	private String temperatura2;
	private String prodRecebido3;
	private String checkRecebimento3;
	
	private String nfRecebimento3;
	private String temperatura3;
	private String prodRecebido4;
	private String checkRecebimento4;

	private String nfRecebimento4;
	private String temperatura4;
	private String prodRecebido5;
	private String checkRecebimento5;
	private String nfRecebimento5;
	private String temperatura5;
	
	private String insumoRecebido;
	private String nfInsumoRecebido;

	private String insumoRecebido2;
	private String nfInsumoRecebido2;
	
	private String insumoRecebido3;
	private String nfInsumoRecebido3;
	
	private String insumoRecebido4;
	private String nfInsumoRecebido4;
	
	private String insumoRecebido5;
	private String nfInsumoRecebido5;
	
	private Date dataEnviado;
	private String prodEnviado;
	private String nfExpedicao;
	private String checkEnviado;
	private String tipo;
	private Integer totalEnviado;
	private Integer totalPallets;
	private String obs;
	private String temperaturaEnvio;
	
	private String prodEnviado2;
	private String nfExpedicao2;
	private String checkEnviado2;
	private String tipo2;
	private Integer totalEnviado2;
	private Integer totalPallets2;
	private String obs2;
	private String temperaturaEnvio2;
	
	private String prodEnviado3;
	private String nfExpedicao3;
	private String checkEnviado3;
	private String tipo3;
	private Integer totalEnviado3;
	private Integer totalPallets3;
	private String obs3;
	private String temperaturaEnvio3;
	
	private String prodEnviado4;
	private String nfExpedicao4;
	private String checkEnviado4;
	private String tipo4;
	private Integer totalEnviado4;
	private Integer totalPallets4;
	private String obs4;
	private String temperaturaEnvio4;
	
	private String prodEnviado5;
	private String nfExpedicao5;
	private String checkEnviado5;
	private String tipo5;
	private Integer totalEnviado5;
	private Integer totalPallets5;
	private String obs5;
	private String temperaturaEnvio5;
	
	private String prodInsumoEnviado;
	private String nfExpedicaoInsumo;
	private String tipoInsumo;
	private Integer totalInsumoEnviado;
	private String obsInsumo;
	
	private String prodInsumoEnviado2;
	private String nfExpedicaoInsumo2;
	private String tipoInsumo2;
	private Integer totalInsumoEnviado2;
	private String obsInsumo2;
	
	private String prodInsumoEnviado3;
	private String nfExpedicaoInsumo3;
	private String tipoInsumo3;
	private Integer totalInsumoEnviado3;
	private String obsInsumo3;
	
	private String prodInsumoEnviado4;
	private String nfExpedicaoInsumo4;
	private String tipoInsumo4;
	private Integer totalInsumoEnviado4;
	private String obsInsumo4;
	
	private String prodInsumoEnviado5;
	private String nfExpedicaoInsumo5;
	private String tipoInsumo5;
	private Integer totalInsumoEnviado5;
	private String obsInsumo5;
	
	private Arquivo arquivo;
	private String remetentes;
	private String Assunto;
	private String saudacao;
	private String corpo;
	private String tipoCarga;
	private Boolean botaoCalculoRetorno;
	private Boolean botaoInicioOperacao;
	private Boolean botaoTerminoOperacao;
	private Boolean botaoInicioCarregamento;
	private Boolean botaoTerminoCarregamento;
	private Boolean botaoCargaVazia;
	private Boolean botaoExcluirEntrada1;
	private Boolean botaoExcluirEntrada2;
	private Boolean botaoExcluirEntrada3;
	private Boolean botaoContinuarCarga;
	private String cargaParalela;
	//Getters e Setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public String getPlaca_carreta() {
		return placa_carreta;
	}
	public void setPlaca_carreta(String placa_carreta) {
		this.placa_carreta = placa_carreta;
	}
	public String getMotorista() {
		return motorista;
	}
	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}
	public Boolean getSituacao() {
		return situacao;
	}
	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_chegada")
	public Date getData_chegada() {
		return data_chegada;
	}
	public void setData_chegada(Date data_chegada) {
		this.data_chegada = data_chegada;
	}
	public String getHora_chegada() {
		return hora_chegada;
	}
	public void setHora_chegada(String hora_chegada) {
		this.hora_chegada = hora_chegada;
	}
	public String getHora_inicio_descarregamento() {
		return hora_inicio_descarregamento;
	}
	public void setHora_inicio_descarregamento(String hora_inicio_descarregamento) {
		this.hora_inicio_descarregamento = hora_inicio_descarregamento;
	}
	public String getHora_termino_descarregamento() {
		return hora_termino_descarregamento;
	}
	public void setHora_termino_descarregamento(String hora_termino_descarregamento) {
		this.hora_termino_descarregamento = hora_termino_descarregamento;
	}
	public String getHora_inicio_carregamento() {
		return hora_inicio_carregamento;
	}
	public void setHora_inicio_carregamento(String hora_inicio_carregamento) {
		this.hora_inicio_carregamento = hora_inicio_carregamento;
	}
	public String getHora_termino_carregamento() {
		return hora_termino_carregamento;
	}
	public void setHora_termino_carregamento(String hora_termino_carregamento) {
		this.hora_termino_carregamento = hora_termino_carregamento;
	}
	public String getHora_saida_carga() {
		return hora_saida_carga;
	}
	public void setHora_saida_carga(String hora_saida_carga) {
		this.hora_saida_carga = hora_saida_carga;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_saida")
	public Date getData_saida() {
		return data_saida;
	}
	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}
	public String getTempo_descarregamento() {
		return tempo_descarregamento;
	}
	public void setTempo_descarregamento(String tempo_descarregamento) {
		this.tempo_descarregamento = tempo_descarregamento;
	}
	public String getTempo_carregamento() {
		return tempo_carregamento;
	}
	public void setTempo_carregamento(String tempo_carregamento) {
		this.tempo_carregamento = tempo_carregamento;
	}
	public String getTempo_retorno() {
		return tempo_retorno;
	}
	public void setTempo_retorno(String tempo_retorno) {
		this.tempo_retorno = tempo_retorno;
	}
	public String getTempo_parado() {
		return tempo_parado;
	}
	public void setTempo_parado(String tempo_parado) {
		this.tempo_parado = tempo_parado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProdRecebido() {
		return prodRecebido;
	}
	public void setProdRecebido(String prodRecebido) {
		this.prodRecebido = prodRecebido;
	}
	public String getCheckRecebimento() {
		return checkRecebimento;
	}
	public void setCheckRecebimento(String checkRecebimento) {
		this.checkRecebimento = checkRecebimento;
	}
	
	public String getNfRecebimento() {
		return nfRecebimento;
	}
	public void setNfRecebimento(String nfRecebimento) {
		this.nfRecebimento = nfRecebimento;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_enviado")
	public Date getDataEnviado() {
		return dataEnviado;
	}
	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}
	public String getProdEnviado() {
		return prodEnviado;
	}
	public void setProdEnviado(String prodEnviado) {
		this.prodEnviado = prodEnviado;
	}
	public String getNfExpedicao() {
		return nfExpedicao;
	}
	public void setNfExpedicao(String nfExpedicao) {
		this.nfExpedicao = nfExpedicao;
	}
	public String getCheckEnviado() {
		return checkEnviado;
	}
	public void setCheckEnviado(String checkEnviado) {
		this.checkEnviado = checkEnviado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getTotalEnviado() {
		return totalEnviado;
	}
	public void setTotalEnviado(Integer totalEnviado) {
		this.totalEnviado = totalEnviado;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getProdRecebido2() {
		return prodRecebido2;
	}
	public void setProdRecebido2(String prodRecebido2) {
		this.prodRecebido2 = prodRecebido2;
	}
	public String getCheckRecebimento2() {
		return checkRecebimento2;
	}
	public void setCheckRecebimento2(String checkRecebimento2) {
		this.checkRecebimento2 = checkRecebimento2;
	}
	
	public String getNfRecebimento2() {
		return nfRecebimento2;
	}
	public void setNfRecebimento2(String nfRecebimento2) {
		this.nfRecebimento2 = nfRecebimento2;
	}
	public String getTemperatura2() {
		return temperatura2;
	}
	public void setTemperatura2(String temperatura2) {
		this.temperatura2 = temperatura2;
	}
	public String getProdRecebido3() {
		return prodRecebido3;
	}
	public void setProdRecebido3(String prodRecebido3) {
		this.prodRecebido3 = prodRecebido3;
	}
	public String getCheckRecebimento3() {
		return checkRecebimento3;
	}
	public void setCheckRecebimento3(String checkRecebimento3) {
		this.checkRecebimento3 = checkRecebimento3;
	}
	
	public String getNfRecebimento3() {
		return nfRecebimento3;
	}
	public void setNfRecebimento3(String nfRecebimento3) {
		this.nfRecebimento3 = nfRecebimento3;
	}
	public String getTemperatura3() {
		return temperatura3;
	}
	public void setTemperatura3(String temperatura3) {
		this.temperatura3 = temperatura3;
	}
	public String getProdRecebido4() {
		return prodRecebido4;
	}
	public void setProdRecebido4(String prodRecebido4) {
		this.prodRecebido4 = prodRecebido4;
	}
	public String getCheckRecebimento4() {
		return checkRecebimento4;
	}
	public void setCheckRecebimento4(String checkRecebimento4) {
		this.checkRecebimento4 = checkRecebimento4;
	}
	
	public String getNfRecebimento4() {
		return nfRecebimento4;
	}
	public void setNfRecebimento4(String nfRecebimento4) {
		this.nfRecebimento4 = nfRecebimento4;
	}
	public String getTemperatura4() {
		return temperatura4;
	}
	public void setTemperatura4(String temperatura4) {
		this.temperatura4 = temperatura4;
	}
	public String getProdRecebido5() {
		return prodRecebido5;
	}
	public void setProdRecebido5(String prodRecebido5) {
		this.prodRecebido5 = prodRecebido5;
	}
	public String getCheckRecebimento5() {
		return checkRecebimento5;
	}
	public void setCheckRecebimento5(String checkRecebimento5) {
		this.checkRecebimento5 = checkRecebimento5;
	}
	
	public String getNfRecebimento5() {
		return nfRecebimento5;
	}
	public void setNfRecebimento5(String nfRecebimento5) {
		this.nfRecebimento5 = nfRecebimento5;
	}
	public String getTemperatura5() {
		return temperatura5;
	}
	public void setTemperatura5(String temperatura5) {
		this.temperatura5 = temperatura5;
	}
	public String getInsumoRecebido() {
		return insumoRecebido;
	}
	public void setInsumoRecebido(String insumoRecebido) {
		this.insumoRecebido = insumoRecebido;
	}
	public String getNfInsumoRecebido() {
		return nfInsumoRecebido;
	}
	public void setNfInsumoRecebido(String nfInsumoRecebido) {
		this.nfInsumoRecebido = nfInsumoRecebido;
	}
	
	public String getInsumoRecebido2() {
		return insumoRecebido2;
	}
	public void setInsumoRecebido2(String insumoRecebido2) {
		this.insumoRecebido2 = insumoRecebido2;
	}
	public String getNfInsumoRecebido2() {
		return nfInsumoRecebido2;
	}
	public void setNfInsumoRecebido2(String nfInsumoRecebido2) {
		this.nfInsumoRecebido2 = nfInsumoRecebido2;
	}
	
	public String getInsumoRecebido3() {
		return insumoRecebido3;
	}
	public void setInsumoRecebido3(String insumoRecebido3) {
		this.insumoRecebido3 = insumoRecebido3;
	}
	public String getNfInsumoRecebido3() {
		return nfInsumoRecebido3;
	}
	public void setNfInsumoRecebido3(String nfInsumoRecebido3) {
		this.nfInsumoRecebido3 = nfInsumoRecebido3;
	}
	
	public String getInsumoRecebido4() {
		return insumoRecebido4;
	}
	public void setInsumoRecebido4(String insumoRecebido4) {
		this.insumoRecebido4 = insumoRecebido4;
	}
	public String getNfInsumoRecebido4() {
		return nfInsumoRecebido4;
	}
	public void setNfInsumoRecebido4(String nfInsumoRecebido4) {
		this.nfInsumoRecebido4 = nfInsumoRecebido4;
	}
	
	public String getInsumoRecebido5() {
		return insumoRecebido5;
	}
	public void setInsumoRecebido5(String insumoRecebido5) {
		this.insumoRecebido5 = insumoRecebido5;
	}
	public String getNfInsumoRecebido5() {
		return nfInsumoRecebido5;
	}
	public void setNfInsumoRecebido5(String nfInsumoRecebido5) {
		this.nfInsumoRecebido5 = nfInsumoRecebido5;
	}
		
	public String getHora_total_parado() {
		return hora_total_parado;
	}
	public void setHora_total_parado(String hora_total_parado) {
		this.hora_total_parado = hora_total_parado;
	}
	public String getHora_total_descarregamento() {
		return hora_total_descarregamento;
	}
	public void setHora_total_descarregamento(String hora_total_descarregamento) {
		this.hora_total_descarregamento = hora_total_descarregamento;
	}
	public String getHora_total_carregamento() {
		return hora_total_carregamento;
	}
	public void setHora_total_carregamento(String hora_total_carregamento) {
		this.hora_total_carregamento = hora_total_carregamento;
	}
	public String getHora_total_olgber() {
		return hora_total_olgber;
	}
	public void setHora_total_olgber(String hora_total_olgber) {
		this.hora_total_olgber = hora_total_olgber;
	}
	
	public String getTemperaturaEnvio() {
		return temperaturaEnvio;
	}
	public void setTemperaturaEnvio(String temperaturaEnvio) {
		this.temperaturaEnvio = temperaturaEnvio;
	}
	
	public String getProdEnviado2() {
		return prodEnviado2;
	}
	public void setProdEnviado2(String prodEnviado2) {
		this.prodEnviado2 = prodEnviado2;
	}
	public String getNfExpedicao2() {
		return nfExpedicao2;
	}
	public void setNfExpedicao2(String nfExpedicao2) {
		this.nfExpedicao2 = nfExpedicao2;
	}
	public String getCheckEnviado2() {
		return checkEnviado2;
	}
	public void setCheckEnviado2(String checkEnviado2) {
		this.checkEnviado2 = checkEnviado2;
	}
	public String getTipo2() {
		return tipo2;
	}
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}
	public Integer getTotalEnviado2() {
		return totalEnviado2;
	}
	public void setTotalEnviado2(Integer totalEnviado2) {
		this.totalEnviado2 = totalEnviado2;
	}
	public String getObs2() {
		return obs2;
	}
	public void setObs2(String obs2) {
		this.obs2 = obs2;
	}
	public String getTemperaturaEnvio2() {
		return temperaturaEnvio2;
	}
	public void setTemperaturaEnvio2(String temperaturaEnvio2) {
		this.temperaturaEnvio2 = temperaturaEnvio2;
	}
	public String getProdEnviado3() {
		return prodEnviado3;
	}
	public void setProdEnviado3(String prodEnviado3) {
		this.prodEnviado3 = prodEnviado3;
	}
	public String getNfExpedicao3() {
		return nfExpedicao3;
	}
	public void setNfExpedicao3(String nfExpedicao3) {
		this.nfExpedicao3 = nfExpedicao3;
	}
	public String getCheckEnviado3() {
		return checkEnviado3;
	}
	public void setCheckEnviado3(String checkEnviado3) {
		this.checkEnviado3 = checkEnviado3;
	}
	public String getTipo3() {
		return tipo3;
	}
	public void setTipo3(String tipo3) {
		this.tipo3 = tipo3;
	}
	public Integer getTotalEnviado3() {
		return totalEnviado3;
	}
	public void setTotalEnviado3(Integer totalEnviado3) {
		this.totalEnviado3 = totalEnviado3;
	}
	public String getObs3() {
		return obs3;
	}
	public void setObs3(String obs3) {
		this.obs3 = obs3;
	}
	public String getTemperaturaEnvio3() {
		return temperaturaEnvio3;
	}
	public void setTemperaturaEnvio3(String temperaturaEnvio3) {
		this.temperaturaEnvio3 = temperaturaEnvio3;
	}
	public String getProdEnviado4() {
		return prodEnviado4;
	}
	public void setProdEnviado4(String prodEnviado4) {
		this.prodEnviado4 = prodEnviado4;
	}
	public String getNfExpedicao4() {
		return nfExpedicao4;
	}
	public void setNfExpedicao4(String nfExpedicao4) {
		this.nfExpedicao4 = nfExpedicao4;
	}
	public String getCheckEnviado4() {
		return checkEnviado4;
	}
	public void setCheckEnviado4(String checkEnviado4) {
		this.checkEnviado4 = checkEnviado4;
	}
	public String getTipo4() {
		return tipo4;
	}
	public void setTipo4(String tipo4) {
		this.tipo4 = tipo4;
	}
	public Integer getTotalEnviado4() {
		return totalEnviado4;
	}
	public void setTotalEnviado4(Integer totalEnviado4) {
		this.totalEnviado4 = totalEnviado4;
	}
	public String getObs4() {
		return obs4;
	}
	public void setObs4(String obs4) {
		this.obs4 = obs4;
	}
	public String getTemperaturaEnvio4() {
		return temperaturaEnvio4;
	}
	public void setTemperaturaEnvio4(String temperaturaEnvio4) {
		this.temperaturaEnvio4 = temperaturaEnvio4;
	}
	public String getProdEnviado5() {
		return prodEnviado5;
	}
	public void setProdEnviado5(String prodEnviado5) {
		this.prodEnviado5 = prodEnviado5;
	}
	public String getNfExpedicao5() {
		return nfExpedicao5;
	}
	public void setNfExpedicao5(String nfExpedicao5) {
		this.nfExpedicao5 = nfExpedicao5;
	}
	public String getCheckEnviado5() {
		return checkEnviado5;
	}
	public void setCheckEnviado5(String checkEnviado5) {
		this.checkEnviado5 = checkEnviado5;
	}
	public String getTipo5() {
		return tipo5;
	}
	public void setTipo5(String tipo5) {
		this.tipo5 = tipo5;
	}
	public Integer getTotalEnviado5() {
		return totalEnviado5;
	}
	public void setTotalEnviado5(Integer totalEnviado5) {
		this.totalEnviado5 = totalEnviado5;
	}
	public String getObs5() {
		return obs5;
	}
	public void setObs5(String obs5) {
		this.obs5 = obs5;
	}
	public String getTemperaturaEnvio5() {
		return temperaturaEnvio5;
	}
	public void setTemperaturaEnvio5(String temperaturaEnvio5) {
		this.temperaturaEnvio5 = temperaturaEnvio5;
	}
	
	public Integer getTotalPallets() {
		return totalPallets;
	}
	public void setTotalPallets(Integer totalPallets) {
		this.totalPallets = totalPallets;
	}
	public Integer getTotalPallets2() {
		return totalPallets2;
	}
	public void setTotalPallets2(Integer totalPallets2) {
		this.totalPallets2 = totalPallets2;
	}
	public Integer getTotalPallets3() {
		return totalPallets3;
	}
	public void setTotalPallets3(Integer totalPallets3) {
		this.totalPallets3 = totalPallets3;
	}
	public Integer getTotalPallets4() {
		return totalPallets4;
	}
	public void setTotalPallets4(Integer totalPallets4) {
		this.totalPallets4 = totalPallets4;
	}
	public Integer getTotalPallets5() {
		return totalPallets5;
	}
	public void setTotalPallets5(Integer totalPallets5) {
		this.totalPallets5 = totalPallets5;
	}
	
	
	public String getProdInsumoEnviado() {
		return prodInsumoEnviado;
	}
	public void setProdInsumoEnviado(String prodInsumoEnviado) {
		this.prodInsumoEnviado = prodInsumoEnviado;
	}
	public String getNfExpedicaoInsumo() {
		return nfExpedicaoInsumo;
	}
	public void setNfExpedicaoInsumo(String nfExpedicaoInsumo) {
		this.nfExpedicaoInsumo = nfExpedicaoInsumo;
	}
	public String getTipoInsumo() {
		return tipoInsumo;
	}
	public void setTipoInsumo(String tipoInsumo) {
		this.tipoInsumo = tipoInsumo;
	}
	public Integer getTotalInsumoEnviado() {
		return totalInsumoEnviado;
	}
	public void setTotalInsumoEnviado(Integer totalInsumoEnviado) {
		this.totalInsumoEnviado = totalInsumoEnviado;
	}
	public String getObsInsumo() {
		return obsInsumo;
	}
	public void setObsInsumo(String obsInsumo) {
		this.obsInsumo = obsInsumo;
	}
	public String getProdInsumoEnviado2() {
		return prodInsumoEnviado2;
	}
	public void setProdInsumoEnviado2(String prodInsumoEnviado2) {
		this.prodInsumoEnviado2 = prodInsumoEnviado2;
	}
	public String getNfExpedicaoInsumo2() {
		return nfExpedicaoInsumo2;
	}
	public void setNfExpedicaoInsumo2(String nfExpedicaoInsumo2) {
		this.nfExpedicaoInsumo2 = nfExpedicaoInsumo2;
	}
	public String getTipoInsumo2() {
		return tipoInsumo2;
	}
	public void setTipoInsumo2(String tipoInsumo2) {
		this.tipoInsumo2 = tipoInsumo2;
	}
	public Integer getTotalInsumoEnviado2() {
		return totalInsumoEnviado2;
	}
	public void setTotalInsumoEnviado2(Integer totalInsumoEnviado2) {
		this.totalInsumoEnviado2 = totalInsumoEnviado2;
	}
	public String getObsInsumo2() {
		return obsInsumo2;
	}
	public void setObsInsumo2(String obsInsumo2) {
		this.obsInsumo2 = obsInsumo2;
	}
	public String getProdInsumoEnviado3() {
		return prodInsumoEnviado3;
	}
	public void setProdInsumoEnviado3(String prodInsumoEnviado3) {
		this.prodInsumoEnviado3 = prodInsumoEnviado3;
	}
	public String getNfExpedicaoInsumo3() {
		return nfExpedicaoInsumo3;
	}
	public void setNfExpedicaoInsumo3(String nfExpedicaoInsumo3) {
		this.nfExpedicaoInsumo3 = nfExpedicaoInsumo3;
	}
	public String getTipoInsumo3() {
		return tipoInsumo3;
	}
	public void setTipoInsumo3(String tipoInsumo3) {
		this.tipoInsumo3 = tipoInsumo3;
	}
	public Integer getTotalInsumoEnviado3() {
		return totalInsumoEnviado3;
	}
	public void setTotalInsumoEnviado3(Integer totalInsumoEnviado3) {
		this.totalInsumoEnviado3 = totalInsumoEnviado3;
	}
	public String getObsInsumo3() {
		return obsInsumo3;
	}
	public void setObsInsumo3(String obsInsumo3) {
		this.obsInsumo3 = obsInsumo3;
	}
	public String getProdInsumoEnviado4() {
		return prodInsumoEnviado4;
	}
	public void setProdInsumoEnviado4(String prodInsumoEnviado4) {
		this.prodInsumoEnviado4 = prodInsumoEnviado4;
	}
	public String getNfExpedicaoInsumo4() {
		return nfExpedicaoInsumo4;
	}
	public void setNfExpedicaoInsumo4(String nfExpedicaoInsumo4) {
		this.nfExpedicaoInsumo4 = nfExpedicaoInsumo4;
	}
	public String getTipoInsumo4() {
		return tipoInsumo4;
	}
	public void setTipoInsumo4(String tipoInsumo4) {
		this.tipoInsumo4 = tipoInsumo4;
	}
	public Integer getTotalInsumoEnviado4() {
		return totalInsumoEnviado4;
	}
	public void setTotalInsumoEnviado4(Integer totalInsumoEnviado4) {
		this.totalInsumoEnviado4 = totalInsumoEnviado4;
	}
	public String getObsInsumo4() {
		return obsInsumo4;
	}
	public void setObsInsumo4(String obsInsumo4) {
		this.obsInsumo4 = obsInsumo4;
	}
	public String getProdInsumoEnviado5() {
		return prodInsumoEnviado5;
	}
	public void setProdInsumoEnviado5(String prodInsumoEnviado5) {
		this.prodInsumoEnviado5 = prodInsumoEnviado5;
	}
	public String getNfExpedicaoInsumo5() {
		return nfExpedicaoInsumo5;
	}
	public void setNfExpedicaoInsumo5(String nfExpedicaoInsumo5) {
		this.nfExpedicaoInsumo5 = nfExpedicaoInsumo5;
	}
	public String getTipoInsumo5() {
		return tipoInsumo5;
	}
	public void setTipoInsumo5(String tipoInsumo5) {
		this.tipoInsumo5 = tipoInsumo5;
	}
	public Integer getTotalInsumoEnviado5() {
		return totalInsumoEnviado5;
	}
	public void setTotalInsumoEnviado5(Integer totalInsumoEnviado5) {
		this.totalInsumoEnviado5 = totalInsumoEnviado5;
	}
	public String getObsInsumo5() {
		return obsInsumo5;
	}
	public void setObsInsumo5(String obsInsumo5) {
		this.obsInsumo5 = obsInsumo5;
	}
	
	@ManyToOne
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	
	public String getRemetentes() {
		return remetentes;
	}
	public void setRemetentes(String remetentes) {
		this.remetentes = remetentes;
	}
	
	public String getAssunto() {
		return Assunto;
	}
	public void setAssunto(String assunto) {
		Assunto = assunto;
	}
	public String getSaudacao() {
		return saudacao;
	}
	public void setSaudacao(String saudacao) {
		this.saudacao = saudacao;
	}
	
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	
	public Boolean getBotaoCalculoRetorno() {
		return botaoCalculoRetorno;
	}
	public void setBotaoCalculoRetorno(Boolean botaoCalculoRetorno) {
		this.botaoCalculoRetorno = botaoCalculoRetorno;
	}
	public Boolean getBotaoInicioOperacao() {
		return botaoInicioOperacao;
	}
	public void setBotaoInicioOperacao(Boolean botaoInicioOperacao) {
		this.botaoInicioOperacao = botaoInicioOperacao;
	}
	public Boolean getBotaoTerminoOperacao() {
		return botaoTerminoOperacao;
	}
	public void setBotaoTerminoOperacao(Boolean botaoTerminoOperacao) {
		this.botaoTerminoOperacao = botaoTerminoOperacao;
	}
	public Boolean getBotaoInicioCarregamento() {
		return botaoInicioCarregamento;
	}
	public void setBotaoInicioCarregamento(Boolean botaoInicioCarregamento) {
		this.botaoInicioCarregamento = botaoInicioCarregamento;
	}
	public Boolean getBotaoTerminoCarregamento() {
		return botaoTerminoCarregamento;
	}
	public void setBotaoTerminoCarregamento(Boolean botaoTerminoCarregamento) {
		this.botaoTerminoCarregamento = botaoTerminoCarregamento;
	}
	
	
	public String getCargaParalela() {
		return cargaParalela;
	}
	public void setCargaParalela(String cargaParalela) {
		this.cargaParalela = cargaParalela;
	}
	
	
	public Boolean getBotaoCargaVazia() {
		return botaoCargaVazia;
	}
	public void setBotaoCargaVazia(Boolean botaoCargaVazia) {
		this.botaoCargaVazia = botaoCargaVazia;
	}
	
	public Boolean getBotaoExcluirEntrada1() {
		return botaoExcluirEntrada1;
	}
	public void setBotaoExcluirEntrada1(Boolean botaoExcluirEntrada1) {
		this.botaoExcluirEntrada1 = botaoExcluirEntrada1;
	}
	public Boolean getBotaoExcluirEntrada2() {
		return botaoExcluirEntrada2;
	}
	public void setBotaoExcluirEntrada2(Boolean botaoExcluirEntrada2) {
		this.botaoExcluirEntrada2 = botaoExcluirEntrada2;
	}
	public Boolean getBotaoExcluirEntrada3() {
		return botaoExcluirEntrada3;
	}
	public void setBotaoExcluirEntrada3(Boolean botaoExcluirEntrada3) {
		this.botaoExcluirEntrada3 = botaoExcluirEntrada3;
	}
	
	public Boolean getBotaoContinuarCarga() {
		return botaoContinuarCarga;
	}
	public void setBotaoContinuarCarga(Boolean botaoContinuarCarga) {
		this.botaoContinuarCarga = botaoContinuarCarga;
	}
	
	
	
	public String getHoraTotalsaida() {
		return horaTotalsaida;
	}
	public void setHoraTotalsaida(String horaTotalsaida) {
		this.horaTotalsaida = horaTotalsaida;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carga other = (Carga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

	
	
	
	
	

}
