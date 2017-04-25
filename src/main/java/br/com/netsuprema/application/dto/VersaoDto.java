package br.com.netsuprema.application.dto;

import java.util.List;

public class VersaoDto {
	
	private String numeroVersaoSistema;
	private boolean versaoEstaAtualizada;
	private String linkPadrao;
	private List<VersoesLiberadasParaAtualizacaoDto> versoesLiberadas;
	
	public String getNumeroVersaoSistema() {
		return numeroVersaoSistema;
	}
	public void setNumeroVersaoSistema(String numeroVersaoSistema) {
		this.numeroVersaoSistema = numeroVersaoSistema;
	}
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
	public List<VersoesLiberadasParaAtualizacaoDto> getVersoesLiberadas() {
		return versoesLiberadas;
	}
	public void setVersoesLiberadas(List<VersoesLiberadasParaAtualizacaoDto> versoesLiberadas) {
		this.versoesLiberadas = versoesLiberadas;
	}
}
