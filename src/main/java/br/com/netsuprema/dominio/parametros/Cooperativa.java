package br.com.netsuprema.dominio.parametros;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.annotations.SerializedName;


@Entity(name = "cooperativa")
public class Cooperativa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/**
	 * Esse nome key deu um conflito por ser paralavra reservada do
	 * mysql o hibernat nao esta tratando isso
	 */
	
	@SerializedName("key")
	private long keyCop;
	
	@SerializedName("nome")
	private String nome;
	
	@SerializedName("webservice")
	private String webservice;
	
	@SerializedName("webservice_crm")
	private String webserviceCrm;
	
	@SerializedName("imagem")
	private String imagem;
	
	private int codigoCooperativa;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getKeyCop() {
		return keyCop;
	}
	public void setKeyCop(long keyCop) {
		this.keyCop = keyCop;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getWebservice() {
		return webservice;
	}
	public void setWebservice(String webservice) {
		this.webservice = webservice;
	}
	public String getWebserviceCrm() {
		return webserviceCrm;
	}
	public void setWebserviceCrm(String webserviceCrm) {
		this.webserviceCrm = webserviceCrm;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public int getCodigoCooperativa() {
		return codigoCooperativa;
	}
	public void setCodigoCooperativa(int codigoCooperativa) {
		this.codigoCooperativa = codigoCooperativa;
	}
}
