package br.com.netsuprema.controller;

import br.com.netsuprema.application.ConfiguracoesGeraisProjetoApplication;

public class MainAppController {
	
	private ConfiguracoesGeraisProjetoApplication configApplication;
	private boolean bloquearAplicacao;
	
	public MainAppController() {
		super();
		try {
			setConfigApplication(new ConfiguracoesGeraisProjetoApplication());
			this.bloquearAplicacao = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inicializarServicos(){
		if (getConfigApplication().versaoEstaAtualizada()) {
			configApplication.inicializarThreads();
		}else{
			this.bloquearAplicacao = true;
		}
	}
	
	public String getMensagemAlerta() {
		String msg = configApplication.carregarMensagemBloqueio();
		return msg;
	}

	private ConfiguracoesGeraisProjetoApplication getConfigApplication() {
		return configApplication;
	}

	private void setConfigApplication(ConfiguracoesGeraisProjetoApplication configApplication) {
		this.configApplication = configApplication;
	}

	public boolean isBloquearAplicacao() {
		return bloquearAplicacao;
	}

}
