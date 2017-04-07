package br.com.netsuprema.dominio;

import java.util.List;

public class Versao {
	
	private boolean versaoEstaAtualizada;
	private String linkPadrao;
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
}
