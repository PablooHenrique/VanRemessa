package br.com.netsuprema.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "cooperativa")
public class Cooperativa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int key;
	private String nome;
	private String webservice;
	private String webservice_crm;
	private String imagem;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
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
	public String getWebservice_crm() {
		return webservice_crm;
	}
	public void setWebservice_crm(String webservice_crm) {
		this.webservice_crm = webservice_crm;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
}
