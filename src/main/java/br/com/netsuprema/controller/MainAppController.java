package br.com.netsuprema.controller;

import org.json.JSONException;

import br.com.netsuprema.application.ConfiguracoesGeraisProjetoApplication;

public class MainAppController {
	
	private String msgErroProcessamento;
	
	public boolean processarThreadsRotina() throws JSONException{
		ConfiguracoesGeraisProjetoApplication application = new ConfiguracoesGeraisProjetoApplication();
		boolean threadsProcessadas = application.processarThreadsRotina();
		this.msgErroProcessamento = application.carregarMensagemBloqueio();
		
		return threadsProcessadas;
	}
	
	public boolean rotinaEstaAtualizada() throws JSONException{
		ConfiguracoesGeraisProjetoApplication application = new ConfiguracoesGeraisProjetoApplication();
		boolean rotinaEstaAtualizada = application.rotinaEstaAtualizada();
		this.msgErroProcessamento = application.carregarMensagemBloqueio();
		return rotinaEstaAtualizada;
	}

	public String getMsgErroProcessamento() {
		return msgErroProcessamento;
	}
}
