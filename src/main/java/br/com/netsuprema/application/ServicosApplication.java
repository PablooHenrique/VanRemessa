package br.com.netsuprema.application;

import java.time.Duration;
import java.time.LocalDateTime;

import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class ServicosApplication {

	public boolean verificarServicoRemessa() {
		ConfiguracoesGeraisProjeto config = new ConfiguracoesGeraisProjetoService().listarConfig();
		
		Duration duration = Duration.between(config.getUltimaDataHoraEnvio(), LocalDateTime.now());
		
		long minutes = duration.toMinutes();
		
		if (minutes >= 5) {
			return false;
		}
		return true;
	}

	public boolean verificarServicoAcompanhamentoLog() {
		return false;
	}
}