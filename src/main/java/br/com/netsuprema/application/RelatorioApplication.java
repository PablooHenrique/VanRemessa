package br.com.netsuprema.application;

import java.time.LocalDate;

import br.com.netsuprema.application.dto.ResumoEnvioDto;
import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class RelatorioApplication {

	public ResumoEnvioDto listarResumoEnvio() {
		ResumoEnvioDto resumo = new ResumoEnvioDto();
		
		ConfiguracoesGeraisProjeto config = new ConfiguracoesGeraisProjetoService().listarConfig();
		resumo.setDataUltimaVerificacaoEnvio(config.getUltimaDataHoraEnvioRemessa());
		
		return resumo;
	}

	public ResumoEnvioDto listarResumoEnvioPorData(LocalDate dataInicial, LocalDate dataFinal) {
		ResumoEnvioDto resumo = new ResumoEnvioDto();
		
		ConfiguracoesGeraisProjeto config = new ConfiguracoesGeraisProjetoService().listarConfig();
		resumo.setDataUltimaVerificacaoEnvio(config.getUltimaDataHoraEnvioRemessa());
		
		return resumo;
	}

}
