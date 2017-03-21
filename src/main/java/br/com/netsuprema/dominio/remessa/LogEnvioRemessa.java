	package br.com.netsuprema.dominio.remessa;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="logenvioremessa")
public class LogEnvioRemessa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String codigoLoteAgendamento;
	private String situacao ;
	private String detalheErro;
	private LocalDateTime dataHoraEnvio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigoLoteAgendamento() {
		return codigoLoteAgendamento;
	}
	public void setCodigoLoteAgendamento(String codigoLoteAgendamento) {
		this.codigoLoteAgendamento = codigoLoteAgendamento;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDetalheErro() {
		return detalheErro;
	}
	public void setDetalheErro(String detalheErro) {
		this.detalheErro = detalheErro;
	}
	public LocalDateTime getDataHoraEnvio() {
		return dataHoraEnvio;
	}
	public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}
}
