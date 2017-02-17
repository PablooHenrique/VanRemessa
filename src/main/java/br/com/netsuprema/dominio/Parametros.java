package br.com.netsuprema.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.netsuprema.dominio.enuns.FormatoRemessa;


@Entity(name = "parametros")
public class Parametros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String login;
	private String senha;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private FormatoRemessa formatoRemessa;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cooperativa")
	private Cooperativa cooperativa;
	
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
