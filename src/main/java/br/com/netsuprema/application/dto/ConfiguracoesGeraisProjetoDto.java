package br.com.netsuprema.application.dto;

public class ConfiguracoesGeraisProjetoDto {
	
	private String ultimaDataHoraEnvioRemessa;
	private String ultimaHoraValidacaoVersao;
	private VersaoDto versao;
	
	public String getUltimaDataHoraEnvioRemessa() {
		return ultimaDataHoraEnvioRemessa;
	}
	public void setUltimaDataHoraEnvioRemessa(String ultimaDataHoraEnvioRemessa) {
		this.ultimaDataHoraEnvioRemessa = ultimaDataHoraEnvioRemessa;
	}
	public String getUltimaHoraValidacaoVersao() {
		return ultimaHoraValidacaoVersao;
	}
	public void setUltimaHoraValidacaoVersao(String ultimaHoraValidacaoVersao) {
		this.ultimaHoraValidacaoVersao = ultimaHoraValidacaoVersao;
	}
	public VersaoDto getVersao() {
		return versao;
	}
	public void setVersao(VersaoDto versao) {
		this.versao = versao;
	}
}
