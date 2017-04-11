package br.com.netsuprema.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ConfiguracoesGeraisProjetoDto {
	
	private LocalDateTime ultimaDataHoraEnvioRemessa;
	private LocalDateTime ultimaHoraValidacaoVersao;
	private String numeroVersaoSistema;
	private boolean versaoEstaAtualizada;
	private String linkPadrao;
	private List<VersoesLiberadasParaAtualizacaoDto> versoes;
	
	
	public LocalDateTime getUltimaDataHoraEnvioRemessa() {
		return ultimaDataHoraEnvioRemessa;
	}
	public void setUltimaDataHoraEnvioRemessa(LocalDateTime ultimaDataHoraEnvioRemessa) {
		this.ultimaDataHoraEnvioRemessa = ultimaDataHoraEnvioRemessa;
	}
	public LocalDateTime getUltimaHoraValidacaoVersao() {
		return ultimaHoraValidacaoVersao;
	}
	public void setUltimaHoraValidacaoVersao(LocalDateTime ultimaHoraValidacaoVersao) {
		this.ultimaHoraValidacaoVersao = ultimaHoraValidacaoVersao;
	}
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
	public List<VersoesLiberadasParaAtualizacaoDto> getVersoes() {
		return versoes;
	}
	public void setVersoes(List<VersoesLiberadasParaAtualizacaoDto> versoes) {
		this.versoes = versoes;
	}
}
