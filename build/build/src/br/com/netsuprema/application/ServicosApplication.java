package br.com.netsuprema.application;

import java.time.Duration;
import java.time.LocalDateTime;

import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.service.ScannerFilesThread;
import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class ServicosApplication {

	public boolean verificarServicoRemessa() {
		ConfiguracoesGeraisProjeto config = new ConfiguracoesGeraisProjetoService().listarConfig();
		
		Duration duration;
		
		try {
			duration = Duration.between(config.getUltimaDataHoraEnvio(), LocalDateTime.now());
		} catch (Exception e) {
			return false;
		}
				
		long minutes = duration.toMinutes();
		
		if (minutes >= 5) {
			return false;
		}
		return true;
	}

	public boolean verificarServicoAcompanhamentoLog() {
		return false;
	}

	public String obterErrosProcessamento() {
		if (!ScannerFilesThread.errosUsuario.isEmpty()) {
			return ScannerFilesThread.errosUsuario.get(0);
		}
		return "";
	}

	public void limparErroProcessamento() {
		ScannerFilesThread.errosUsuario.clear();
	}
}
