package br.com.netsuprema.application.dto;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class CedenteDto{
	
	private StringProperty nome;
	private StringProperty codigo;
	private StringProperty digitoVerificador;
	private StringProperty diretorioPadrao;
	
	private ListProperty<ContaDto> contas;
	
	public CedenteDto() {
		this.digitoVerificador = new SimpleStringProperty();
		this.diretorioPadrao = new SimpleStringProperty();
		this.codigo = new SimpleStringProperty();
		this.contas = new SimpleListProperty<ContaDto>();
		this.nome = new SimpleStringProperty();
	}

	public void setCodigo(String codigo) {
		this.codigo.set(codigo);
	}
	
	public String getCodigo() {
		return codigo.get();
	}
	
	public StringProperty getCodigoProperty() {
		return codigo;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador.set(digitoVerificador);
	}

	public String getDigitoVerificador() {
		return digitoVerificador.get();
	}
	
	public StringProperty getDigitoProperty() {
		return digitoVerificador;
	}

	public StringProperty getNomeProperty() {
		return nome;
	}
	
	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public List<ContaDto> getContas() {
		return contas.get();
	}

	public void setContas(List<ContaDto> contas) {
		this.contas.set(FXCollections.observableArrayList(contas));
	}

	public String getDiretorioPadrao() {
		return diretorioPadrao.get();
	}

	public void setDiretorioPadrao(String diretorioPadrao) {
		this.diretorioPadrao.set(diretorioPadrao);
	}
	
	public StringProperty getDiretorioPadraoProperty() {
		return diretorioPadrao;
	}

	public void setDiretorioPadraoProperty(StringProperty diretorioPadrao) {
		this.diretorioPadrao = diretorioPadrao;
	}
}
