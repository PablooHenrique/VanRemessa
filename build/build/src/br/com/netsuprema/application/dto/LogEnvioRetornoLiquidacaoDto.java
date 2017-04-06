package br.com.netsuprema.application.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogEnvioRetornoLiquidacaoDto {
	
	private StringProperty situacao;
	private StringProperty detalheSituacao;
	private StringProperty logProcessamento;
	private StringProperty dataHoraProcessamento;
	private StringProperty dataRetorno;
	
	public LogEnvioRetornoLiquidacaoDto() {
		this.situacao = new SimpleStringProperty();
		this.detalheSituacao = new SimpleStringProperty();
		this.logProcessamento = new SimpleStringProperty();
		this.dataHoraProcessamento = new SimpleStringProperty();
		this.dataRetorno = new SimpleStringProperty();
	}
	
	public String getSituacao() {
		return situacao.get();
	}
	public StringProperty getSituacaoProperty() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao.set(situacao);
	}
	public void setSituacaoProperty(StringProperty situacao) {
		this.situacao = situacao;
	}
	
	
	public String getDetalheSituacao() {
		return detalheSituacao.get();
	}
	public void setDetalheSituacao(String detalheSituacao) {
		this.detalheSituacao.set(detalheSituacao);
	}
	public StringProperty getDetalheSituacaoProperty() {
		return detalheSituacao;
	}
	public void setDetalheSituacaoProperty(StringProperty detalheSituacao) {
		this.detalheSituacao = detalheSituacao;
	}
	
	public String getLogProcessamento() {
		return logProcessamento.get();
	}
	public void setLogProcessamento(String logProcessamento) {
		this.logProcessamento.set(logProcessamento);
	}
	public StringProperty getLogProcessamentoProperty() {
		return logProcessamento;
	}
	public void setLogProcessamentoProperty(StringProperty logProcessamento) {
		this.logProcessamento = logProcessamento;
	}
	
	public String getDataHoraProcessamento() {
		return dataHoraProcessamento.get();
	}
	public void setDataHoraProcessamento(String dataHoraProcessamento) {
		this.dataHoraProcessamento.set(dataHoraProcessamento);
	}
	public StringProperty getDataHoraProcessamentoProperty() {
		return dataHoraProcessamento;
	}
	public void setDataHoraProcessamentoProperty(StringProperty dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}
	
	public String getDataRetorno() {
		return dataRetorno.get();
	}
	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno.set(dataRetorno);
	}
	public StringProperty getDataRetornoProperty() {
		return dataRetorno;
	}
	public void setDataRetornoProperty(StringProperty dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
}
