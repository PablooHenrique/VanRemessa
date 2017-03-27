package br.com.netsuprema.application.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RemessaDto {
	
	private StringProperty id;
	private CedenteDto cedente;
	private StringProperty nomeDoArquivo;
	private StringProperty numeroConta;
	private LogEnvioRemessaDto log;
	
	public RemessaDto() {
		nomeDoArquivo =  new SimpleStringProperty();
		numeroConta = new SimpleStringProperty();
		id = new SimpleStringProperty();
	}
	
	public CedenteDto getCedente() {
		return cedente;
	}
	public void setCedente(CedenteDto cedente) {
		this.cedente = cedente;
	}
	public StringProperty getNomeDoArquivo() {
		return nomeDoArquivo;
	}
	
	public void setNomeDoArquivoProperty(StringProperty nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo.set(nomeDoArquivo);
	}
	
	public StringProperty getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroContaProperty(StringProperty numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta.set(numeroConta);
	}
	
	public LogEnvioRemessaDto getLog() {
		return log;
	}
	
	public void setLog(LogEnvioRemessaDto log) {
		this.log = log;
	}

	public StringProperty getId() {
		return id;
	}

	public void setIdProperty(StringProperty id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id.set(id);
	}
}
