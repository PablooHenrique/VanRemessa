package br.com.netsuprema.application.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogEnvioRemessaDto {

	private StringProperty codigoLoteAgendamento;
	private StringProperty situacao ;
	private StringProperty detalheErro;
	private StringProperty dataHoraEnvio;
	
	private StringProperty statusProcessamento;
	private StringProperty detalheSituacao;
	private StringProperty logProcessamento;
	private StringProperty dhProcessamento;
	
	public LogEnvioRemessaDto() {
		codigoLoteAgendamento 	= new SimpleStringProperty();
		statusProcessamento 	= new SimpleStringProperty();
		logProcessamento 		= new SimpleStringProperty();
		detalheSituacao 		= new SimpleStringProperty();
		dhProcessamento 		= new SimpleStringProperty();
		dataHoraEnvio 			= new SimpleStringProperty();
		detalheErro 			= new SimpleStringProperty();
		situacao 				= new SimpleStringProperty();
	}
	
	public StringProperty getCodigoLoteAgendamento() {
		return codigoLoteAgendamento;
	}
	public void setCodigoLoteAgendamentoProperty(StringProperty codigoLoteAgendamento) {
		this.codigoLoteAgendamento = codigoLoteAgendamento;
	}
	public void setCodigoLoteAgendamento(String codigoLoteAgendamento) {
		this.codigoLoteAgendamento.set(codigoLoteAgendamento);
	}
	public StringProperty getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao.set(situacao);
	}
	public void setSituacaoProperty(String situacao) {
		this.situacao.set(situacao);
	}
	public StringProperty getDetalheErro() {
		return detalheErro;
	}
	public void setDetalheErroProperty(StringProperty detalheErro) {
		this.detalheErro = detalheErro;
	}
	public void setDetalheErro(String detalheErro) {
		this.detalheErro.set(detalheErro);
	}
	public StringProperty getDataHoraEnvio() {
		return dataHoraEnvio;
	}
	public void setDataHoraEnvioProperty(StringProperty dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}
	public void setDataHoraEnvio(String dataHoraEnvio) {
		this.dataHoraEnvio.set(dataHoraEnvio);
	}
	public StringProperty getStatusProcessamento() {
		return statusProcessamento;
	}
	public void setStatusProcessamentoProperty(StringProperty statusProcessamento) {
		this.statusProcessamento = statusProcessamento;
	}
	public void setStatusProcessamento(String statusProcessamento) {
		this.statusProcessamento.set(statusProcessamento);
	}
	public StringProperty getDetalheSituacao() {
		return detalheSituacao;
	}
	public void setDetalheSituacaoProperty(StringProperty detalheSituacao) {
		this.detalheSituacao = detalheSituacao;
	}
	public void setDetalheSituacao(String detalheSituacao) {
		this.detalheSituacao.set(detalheSituacao);
	}
	public StringProperty getLogProcessamento() {
		return logProcessamento;
	}
	public void setLogProcessamentoProperty(StringProperty logProcessamento) {
		this.logProcessamento = logProcessamento;
	}
	public void setLogProcessamento(String logProcessamento) {
		this.logProcessamento.set(logProcessamento);
	}
	public StringProperty getDhProcessamento() {
		return dhProcessamento;
	}
	public void setDhProcessamentoProperty(StringProperty dhProcessamento) {
		this.dhProcessamento = dhProcessamento;
	}
	public void setDhProcessamento(String dhProcessamento) {
		this.dhProcessamento.set(dhProcessamento);
	}
}
