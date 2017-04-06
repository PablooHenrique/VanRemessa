package br.com.netsuprema.dominio.retornoliquidacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="logenvioretornoliquidacao")
public class LogEnvioRetornoLiquidacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private SituacaoRetornoLiquidacao situacao;
	private String detalheSituacao;
	private String logProcessamento;
	private LocalDateTime dataHoraProcessamento;
	private LocalDate dataRetorno;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSituacao() {
		return situacao.toString();
	}
	
	public SituacaoRetornoLiquidacao getSituacaoEnum() {
		return situacao;
	}
	
	public void setSituacao(SituacaoRetornoLiquidacao situacao) {
		this.situacao = situacao;
	}
	public String getDetalheSituacao() {
		return detalheSituacao;
	}
	public void setDetalheSituacao(String detalheSituacao) {
		this.detalheSituacao = detalheSituacao;
	}
	public String getLogProcessamento() {
		return logProcessamento;
	}
	public void setLogProcessamento(String logProcessamento) {
		this.logProcessamento = logProcessamento;
	}
	public LocalDateTime getDataHoraProcessamento() {
		return dataHoraProcessamento;
	}
	public void setDataHoraProcessamento(LocalDateTime dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}
	public LocalDate getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(LocalDate dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
}
