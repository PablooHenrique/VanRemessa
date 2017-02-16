package br.com.netsuprema.dominio;

public class Cedente {
	private int codigo;
	private int digitoVerificador;
	private Conta conta;
	private String diretorio;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getDigitoVerificador() {
		return digitoVerificador;
	}
	
	public void setDigitoVerificador(int digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public String getDiretorio() {
		return diretorio;
	}
	
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	
}
