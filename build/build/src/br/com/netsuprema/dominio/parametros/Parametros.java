package br.com.netsuprema.dominio.parametros;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	private FormatoRetornoLiquidacao formatoRetornoLiquidacao;
	
	private String diretorioRetornos;
	
	public String gerarMD5Senha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02x", 0xFF & b));
		}
		
		return hexString.toString();
	}
	
	
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
	public FormatoRetornoLiquidacao getFormatoRetornoLiquidacao() {
		return formatoRetornoLiquidacao;
	}
	public void setFormatoRetornoLiquidacao(FormatoRetornoLiquidacao formatoRetornoLiquidacao) {
		this.formatoRetornoLiquidacao = formatoRetornoLiquidacao;
	}


	public String getDiretorioRetornos() {
		return diretorioRetornos;
	}


	public void setDiretorioRetornos(String diretorioRetornos) {
		this.diretorioRetornos = diretorioRetornos;
	}
}
