package br.com.netsuprema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.fabric.xmlrpc.base.Array;

import NetSupremaConsultaProcessamento.server.Autenticacao;
import NetSupremaConsultaProcessamento.server.DadosConsulta;
import NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortTypeProxy;
import NetSupremaConsultaProcessamento.server.RetornoProcessamento;
import NetSupremaConsultaProcessamento.server.ServicoConsultaProcessamentoRemessa;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.remessa.LogEnvioRemessa;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.dominio.remessa.StatusProcessamento;
import br.com.netsuprema.service.parametros.ParametrosService;

public class RelatoriosWebService {
	
	public void reprocessarRemessaEnviadasAoServidor(Remessa remessa) throws Exception{
		RetornoProcessamento retornoProcessamento = criarRetornoProcessamento();
		DadosConsulta dadosConsulta = criarDadosConsulta(remessa.getCedente(), remessa.getNumeroConta(), Integer.valueOf(remessa.getLog().getCodigoLoteAgendamento()));
		Autenticacao autenticacao 	= criarAutenticacao();
		ServicoConsultaProcessamentoRemessa servico = criarServico(retornoProcessamento, dadosConsulta, autenticacao);
		retornoProcessamento = new RegistrosDoWSDLPortTypeProxy().CONSULTA_PROCESSAMENTO_REMESSA_TITULO(servico);
		
		atualizarLog(remessa, retornoProcessamento);
	}
	
	private void atualizarLog(Remessa remessa, RetornoProcessamento retornoProcessamento) {
		LogEnvioRemessa log = remessa.getLog();
		
		List<StatusProcessamento> status = Arrays.asList(StatusProcessamento.values());
		List<StatusProcessamento> filtro = status.stream().filter(x -> x.getCodigo() == Integer.valueOf(retornoProcessamento.getSITUACAO())).collect(Collectors.toList());
		
		if (!filtro.isEmpty()) {
			log.setStatusProcessamento(filtro.get(0));
		} 
		
		log.setDetalheSituacao(retornoProcessamento.getDETALHE_SITUACAO());
		log.setDetalheErro(retornoProcessamento.getLOG_PROCESSAMENTO());
		log.setDhProcessamento(retornoProcessamento.getDHPROCESSAMENTO());
	}

	private ServicoConsultaProcessamentoRemessa criarServico(RetornoProcessamento retornoProcessamento, DadosConsulta dadosConsulta,
			Autenticacao autenticacao) {
		ServicoConsultaProcessamentoRemessa servico = new ServicoConsultaProcessamentoRemessa();
		servico.setAutenticacao(autenticacao);
		servico.setDadosConsulta(dadosConsulta);
		servico.setRetornoProcessamento(retornoProcessamento);
		
		return servico;
	}
	
	public Autenticacao criarAutenticacao() throws Exception{
		Parametros parametro;
		List<Parametros> parametros = new ParametrosService().listarParametros();
		
		if (!parametros.isEmpty()) {
			parametro = parametros.get(0);
		}else{
			throw new Exception("Parametros não cadastrados. Realize o cadastro das configurações do serviço.");
		}
		
		return new Autenticacao(parametro.getLogin(), parametro.getSenha());
	}
	
	public DadosConsulta criarDadosConsulta(Cedente cedente, int numeroConta, int loteAgendamento) throws Exception {
		try {
			
			List<Conta> contas = cedente.getContas().stream().filter(x->x.getNumeroConta() == numeroConta).collect(Collectors.toList());
			Conta conta = new Conta();
			
			if (!contas.isEmpty()) {
				conta = contas.get(0);
			}else{
				throw new Exception("Conta : " + numeroConta +" invalido."+"Falha ao atualizar o status do processamento");
			}
			
			return new DadosConsulta(String.valueOf(cedente.getCodigo()), String.valueOf(cedente.getDigitoVerificador()), String.valueOf( conta.getNumeroConta()), String.valueOf(conta.getDigitoVerificador()), String.valueOf(loteAgendamento));
		} catch (Exception e) {
			throw new Exception("Erro ao criar os dados da consulta. Erro: " + e.getMessage() +" Motivo: "+e.getCause().getMessage());
		}
	}
	
	public RetornoProcessamento criarRetornoProcessamento(){
		RetornoProcessamento retornoProcessamento = new RetornoProcessamento("", "", "", "");
		return retornoProcessamento;
	}
}
