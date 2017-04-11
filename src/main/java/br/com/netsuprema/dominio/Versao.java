package br.com.netsuprema.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "versao")
public class Versao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String numeroVersaoSistema;
	
	@Transient
	private boolean versaoEstaAtualizada;
	@Transient
	private String linkPadrao;
	@Transient
	private List<VersoesLiberadasParaAtualizacao> versoesLiberadas;
	
	public boolean isVersaoEstaAtualizada() {
		return versaoEstaAtualizada;
	}
	public void setVersaoEstaAtualizada(boolean versaoEstaAtualizada) {
		this.versaoEstaAtualizada = versaoEstaAtualizada;
	}
	public String getLinkPadrao() {
		return linkPadrao;
	}
	public void setLinkPadrao(String linkPadrao) {
		this.linkPadrao = linkPadrao;
	}
	public List<VersoesLiberadasParaAtualizacao> getVersoesLiberadas() {
		return versoesLiberadas;
	}
	public void setVersoesLiberadas(List<VersoesLiberadasParaAtualizacao> versoesLiberadas) {
		this.versoesLiberadas = versoesLiberadas;
	}
	public String getNumeroVersaoSistema() {
		return numeroVersaoSistema;
	}
	public void setNumeroVersaoSistema(String numeroVersaoSistema) {
		this.numeroVersaoSistema = numeroVersaoSistema;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
