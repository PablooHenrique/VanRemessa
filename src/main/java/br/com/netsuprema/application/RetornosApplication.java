package br.com.netsuprema.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.netsuprema.application.dto.RetornoLiquidacaoDto;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.retornoliquidacao.RetornoLiquidacao;
import br.com.netsuprema.service.cedente.CedenteService;
import br.com.netsuprema.service.retornos.RetornosLiquidacaoService;

public class RetornosApplication {

	public boolean parametrosRetornoLiquidacaoSaoValidos() throws Exception {
		try {
			ParametrosApplication application = new ParametrosApplication();
			Parametros parametros = application.consultarParametros();
			if ((parametros!=null) && (parametros.getFormatoRetornoLiquidacao()!=null) && (parametros.getDiretorioRetornos() != null) && (!parametros.getDiretorioRetornos().trim().equals(""))) {
				return true;
			}
			return false;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao consultar os parametros.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw e;
		}
	}

	public Integer consultarQuantidadeRetornosDoDia() {
		RetornosLiquidacaoService service = new RetornosLiquidacaoService();
		Integer quantidade = service.consultarQuantidadeArquivosRetornosProcessadosNoDia();
		return quantidade;
	}

	public Integer consultarQuantidadeTotalCedentes() {
		CedenteService service = new CedenteService();
		List<Cedente> cedentes = service.listar();
		return cedentes.size();
	}

	public List<RetornoLiquidacaoDto> consultarRetornosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		ModelMapper mapper = new ModelMapper();
		RetornosLiquidacaoService service = new RetornosLiquidacaoService();
		
		List<RetornoLiquidacao> retornos = service.consultarRetornosPorPeriodo(dataInicial, dataFinal);
		List<RetornoLiquidacaoDto> retornosDto = new ArrayList<RetornoLiquidacaoDto>();
		
		if (retornos!=null) {
			for (RetornoLiquidacao ret : retornos) {
				retornosDto.add(mapper.map(ret, RetornoLiquidacaoDto.class));
			}
		} 
		
		return retornosDto;
	}

	public boolean processamentoRetornoDoDiaJaRealizado() {
		RetornosLiquidacaoService service = new RetornosLiquidacaoService();
		return service.processamentoDoDiaJaRealizado();
	}
}
