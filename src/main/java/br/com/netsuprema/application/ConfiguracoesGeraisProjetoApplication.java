package br.com.netsuprema.application;

import org.modelmapper.ModelMapper;

import br.com.netsuprema.application.dto.ConfiguracoesGeraisProjetoDto;
import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class ConfiguracoesGeraisProjetoApplication {
	
	private ConfiguracoesGeraisProjetoService service;
	private ConfiguracoesGeraisProjetoDto configuracoesGeraisProjetoDto;
	private ConfiguracoesGeraisProjeto config;
	
	public ConfiguracoesGeraisProjetoApplication() throws Exception {
		super();
		setService(new ConfiguracoesGeraisProjetoService());
		inicializarConfiguracoesGeraisProjeto();
	}

	private void inicializarConfiguracoesGeraisProjeto() throws Exception{
		try {
			config = getService().carregarConfiguracoesGeraisProjeto();
			getService().inicializarVersao(config);
			ModelMapper mapper = new ModelMapper();
			ConfiguracoesGeraisProjetoDto configDto = mapper.map(config, ConfiguracoesGeraisProjetoDto.class);
			this.configuracoesGeraisProjetoDto = configDto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void inicializarThreads() {
		service.inicializarThreads();
	}

	public String carregarMensagemBloqueio() {
		String msg = service.carregarMensagemBloqueio(config);
		return msg;
	}
	
	public boolean versaoEstaAtualizada(){
		return this.configuracoesGeraisProjetoDto.isVersaoEstaAtualizada();
	}

	public ConfiguracoesGeraisProjetoService getService() {
		return service;
	}

	public void setService(ConfiguracoesGeraisProjetoService service) {
		this.service = service;
	}
	
	public static void main(String[] args) throws Exception {
		ConfiguracoesGeraisProjetoApplication aplication = new ConfiguracoesGeraisProjetoApplication();
		aplication.inicializarConfiguracoesGeraisProjeto();
	}
}
