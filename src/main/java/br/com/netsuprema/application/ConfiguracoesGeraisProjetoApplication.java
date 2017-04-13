package br.com.netsuprema.application;

import org.json.JSONException;

import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class ConfiguracoesGeraisProjetoApplication {
	
	private ConfiguracoesGeraisProjetoService service;
	private String msgErrorProcessamento;
	
	public ConfiguracoesGeraisProjetoApplication() {
		super();
		setService(new ConfiguracoesGeraisProjetoService());
	}

	public boolean processarThreadsRotina() throws JSONException{
		try {
			boolean process = getService().processarThreadsRotina();
			if (!process) {
				this.msgErrorProcessamento = getService().carregarMensagemBloqueioRotina();
			}
			return process;
		} catch (JSONException e) {
			throw e;
		}
	}
	
	public boolean rotinaEstaAtualizada() throws JSONException{
		try {
			boolean rotinaEstaAtualizada = getService().rotinaEstaAtualizada();
			if (!rotinaEstaAtualizada) {
				this.msgErrorProcessamento = getService().carregarMensagemBloqueioRotina();
			}
			return rotinaEstaAtualizada;
		} catch (JSONException e) {
			throw e;
		}
	}
	
	public String carregarMensagemBloqueio(){
		return this.msgErrorProcessamento;
	}

	public ConfiguracoesGeraisProjetoService getService() {
		return service;
	}

	public void setService(ConfiguracoesGeraisProjetoService service) {
		this.service = service;
	}
}