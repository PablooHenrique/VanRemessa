package br.com.netsuprema.dominio;

import br.com.netsuprema.dominio.enuns.FormatoRemessa;

public class Parametros {
	
	private Cooperativa cooperativa;
	private String login;
	private String senha;
	private String email;
	private FormatoRemessa formatoRemessa;
	
	public Cooperativa getCooperativa() {
		return cooperativa;
	}
	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FormatoRemessa getFormatoRemessa() {
		return formatoRemessa;
	}
	public void setFormatoRemessa(FormatoRemessa formatoRemessa) {
		this.formatoRemessa = formatoRemessa;
	}
}