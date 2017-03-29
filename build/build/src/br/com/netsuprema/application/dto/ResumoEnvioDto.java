package br.com.netsuprema.application.dto;

import java.time.LocalDateTime;

public class ResumoEnvioDto {
	
	private LocalDateTime dataUltimaVerificacaoEnvio;

	public LocalDateTime getDataUltimaVerificacaoEnvio() {
		return dataUltimaVerificacaoEnvio;
	}

	public void setDataUltimaVerificacaoEnvio(LocalDateTime dataUltimaVerificacaoEnvio) {
		this.dataUltimaVerificacaoEnvio = dataUltimaVerificacaoEnvio;
	}

}
