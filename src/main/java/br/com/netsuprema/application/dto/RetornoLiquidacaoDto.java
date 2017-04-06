package br.com.netsuprema.application.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RetornoLiquidacaoDto {
	
	private CedenteDto cedente;
	private LogEnvioRetornoLiquidacaoDto logEnvioRetornoLiquidacao;
	private StringProperty numeroConta;
	
	public RetornoLiquidacaoDto() {
		this.cedente = new CedenteDto();
		this.logEnvioRetornoLiquidacao = new LogEnvioRetornoLiquidacaoDto();
		this.numeroConta = new SimpleStringProperty();
	}
	public CedenteDto getCedente() {
		return cedente;
	}
	public void setCedente(CedenteDto cedente) {
		this.cedente = cedente;
	}
	public LogEnvioRetornoLiquidacaoDto getLogEnvioRetornoLiquidacao() {
		return logEnvioRetornoLiquidacao;
	}
	public void setLogEnvioRetornoLiquidacao(LogEnvioRetornoLiquidacaoDto logEnvioRetornoLiquidacao) {
		this.logEnvioRetornoLiquidacao = logEnvioRetornoLiquidacao;
	}
	public String getNumeroConta() {
		return numeroConta.get();
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta.set(numeroConta);
	}
	
	public void setNumeroContaProperty(StringProperty numeroConta) {
		this.numeroConta = numeroConta;
	}
	public StringProperty getNumeroContaProperty() {
		return numeroConta;
	}
}
