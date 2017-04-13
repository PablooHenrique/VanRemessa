package br.com.netsuprema.dominio;

import java.util.List;

public class Atualizacao {
	
	private Versao versaoAtualSistemaRemoto;
	private List<Versao> ultimasVersoesLiberadas;
	private String linkAtualizacao;
	
	public Versao getVersaoAtualSistemaRemoto() {
		return versaoAtualSistemaRemoto;
	}
	public void setVersaoAtualSistemaRemoto(Versao versaoAtualSistemaRemoto) {
		this.versaoAtualSistemaRemoto = versaoAtualSistemaRemoto;
	}
	public List<Versao> getUltimasVersoesLiberadas() {
		return ultimasVersoesLiberadas;
	}
	public void setUltimasVersoesLiberadas(List<Versao> ultimasVersoesLiberadas) {
		this.ultimasVersoesLiberadas = ultimasVersoesLiberadas;
	}
	public String getLinkAtualizacao() {
		return linkAtualizacao;
	}
	public void setLinkAtualizacao(String linkAtualizacao) {
		this.linkAtualizacao = linkAtualizacao;
	}
}
