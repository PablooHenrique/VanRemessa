package br.com.netsuprema.dominio;

import java.io.File;

import br.com.netsuprema.dominio.enuns.FormatoRemessa;

public class Remessa {
	private String usuario;
	private String senha;
	private int codigoCedente;
	private int digitoVerificadorCedente;
	private int conta;
	private int digitoVerificadorConta;
	private String email;
	private FormatoRemessa formato;
	private String nomeArquivo;
	private String conteudoArquivo;
	
	public Remessa(Cedente cedente, Conta conta, int digitoVerificadorConta, File file) {
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCodigoCedente() {
		return codigoCedente;
	}
	public void setCodigoCedente(int codigoCedente) {
		this.codigoCedente = codigoCedente;
	}
	public int getDigitoVerificadorCedente() {
		return digitoVerificadorCedente;
	}
	public void setDigitoVerificadorCedente(int digitoVerificadorCedente) {
		this.digitoVerificadorCedente = digitoVerificadorCedente;
	}
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	public int getDigitoVerificadorConta() {
		return digitoVerificadorConta;
	}
	public void setDigitoVerificadorConta(int digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FormatoRemessa getFormato() {
		return formato;
	}
	public void setFormato(FormatoRemessa formato) {
		this.formato = formato;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getConteudoArquivo() {
		return conteudoArquivo;
	}
	public void setConteudoArquivo(String conteudoArquivo) {
		this.conteudoArquivo = conteudoArquivo;
	}
}
