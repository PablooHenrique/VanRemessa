package br.com.netsuprema.application.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContaDto{
	
	private StringProperty numeroConta;
	private StringProperty digitoVerificador; 
	private StringProperty agencia;
	private StringProperty diretorio;
	
	public ContaDto() {
		this.numeroConta = new SimpleStringProperty();
		this.digitoVerificador = new SimpleStringProperty();
		this.agencia = new SimpleStringProperty();
		this.diretorio = new SimpleStringProperty();
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta.set(numeroConta);
	}
	
	public String getNumeroConta() {
		return numeroConta.get();
	}
	
	public StringProperty getNumeroContaProperty(){
		return numeroConta;
	}
	
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador.set(digitoVerificador);
	}
	
	public String getDigitoVerificador() {
		return digitoVerificador.get();
	}
	
	public StringProperty getDigitoVerificadorProperty() {
		return digitoVerificador;
	}
	
	public void setAgencia(String agencia) {
		this.agencia.set(agencia);
	}

	public String getAgencia() {
		return agencia.get();
	}
	
	public String getAgenciaProperty() {
		return agencia.get();
	}

	public String getDiretorio() {
		return diretorio.get();
	}

	public void setDiretorio(String diretorio) {
		this.diretorio.set(diretorio);
	}
	
	public StringProperty getDiretorioProperty() {
		return diretorio;
	}

	public void setDiretorioProperty(StringProperty diretorio) {
		this.diretorio = diretorio;
	}
}
