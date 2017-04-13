package br.com.netsuprema.service.parametros;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONException;

import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ConfiguracoesGeraisProjetoRepository;
import br.com.netsuprema.service.ProcessingWatcherThread;
import br.com.netsuprema.service.ScannerFilesThread;
import br.com.netsuprema.service.retornos.ReturnProcessWatcherThread;

public class ConfiguracoesGeraisProjetoService {
	
	private SessionFactory factory;
	private ConfiguracoesGeraisProjeto config;
	
	public ConfiguracoesGeraisProjetoService() {
		factory = Application.getInstance().getSessionFactory();
	}

	private void salvar(Session session, ConfiguracoesGeraisProjeto config){
		new ConfiguracoesGeraisProjetoRepository(session).atualizar(config);
	}
	
	public void atualizarDataHoraEnvioRetorno(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			try {
			
				ConfiguracoesGeraisProjeto config;
				
				session = factory.openSession();
				List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
				if(!configs.isEmpty()){
					config = configs.get(0);
				}else{
					config = new ConfiguracoesGeraisProjeto();
				}
				
				config.setUltimaDataHoraEnvioRetorno(LocalDateTime.now());
				
				transaction = session.beginTransaction();
				salvar(session, config);
				transaction.commit();
			
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())) {
					if (transaction.isActive()) {
						transaction.rollback();
					}
					session.close();
				}
				session.close();
				e.printStackTrace();
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}
	
	public void atualizarDataHoraProcessamentoRemessa(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			try {
			
				ConfiguracoesGeraisProjeto config;
				
				session = factory.openSession();
				List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
				if(!configs.isEmpty()){
					config = configs.get(0);
				}else{
					config = new ConfiguracoesGeraisProjeto();
				}
				
				config.setUltimaDataHoraProcessamentoRemessa(LocalDateTime.now());
				
				transaction = session.beginTransaction();
				salvar(session, config);
				transaction.commit();
			
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())) {
					if (transaction.isActive()) {
						transaction.rollback();
					}
					session.close();
				}
				session.close();
				e.printStackTrace();
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}

	public void atualizarDataHoraEnvioRemessa() {
		Session session = null;
		Transaction transaction = null;
		try {
			
			try {
			
				ConfiguracoesGeraisProjeto config;
				
				session = factory.openSession();
				List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
				if(!configs.isEmpty()){
					config = configs.get(0);
				}else{
					config = new ConfiguracoesGeraisProjeto();
				}
				
				config.setUltimaDataHoraEnvioRemessa(LocalDateTime.now());
				
				transaction = session.beginTransaction();
				salvar(session, config);
				transaction.commit();
			
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())) {
					if (transaction.isActive()) {
						transaction.rollback();
					}
					session.close();
				}
				session.close();
				e.printStackTrace();
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}
	
	public ConfiguracoesGeraisProjeto listarConfig() {
		Session session = null;
		try {
			session = factory.openSession();
			List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
			if (!configs.isEmpty()) {
				return configs.get(0);
			}
			return new ConfiguracoesGeraisProjeto();
			
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public String carregarLinkCooperativaEscolhida(){
		List<Parametros> parametros = new ParametrosService().listarParametros();
		if (!parametros.isEmpty()) {
			Parametros parametro = parametros.get(0);
			long keyCop = parametro.getCooperativa().getKeyCop();
			int key = Integer.valueOf((String.valueOf(keyCop)));
			
			Cooperativa cooperativa = new ParametrosService().listarCooperativaPorKey(key);
			return cooperativa.getWebservice();
		}
		return "";
	}
	
	public ConfiguracoesGeraisProjeto carregarConfiguracoesGeraisProjeto() {
		Session session = null;
		try {
			session = factory.openSession();
			ConfiguracoesGeraisProjetoRepository repository = new ConfiguracoesGeraisProjetoRepository(session);
			List<ConfiguracoesGeraisProjeto> configs = repository.listar();
			if (!configs.isEmpty()) {
				return configs.get(0);
			}
			return new ConfiguracoesGeraisProjeto();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	private void atualizarUltimoResultadoVersao(ConfiguracoesGeraisProjeto config) {
		Session session = null;
		Transaction transaction = null;
		try {
			try {
				session = factory.openSession();
				
				config.setUltimaHoraValidacaoVersao(LocalDateTime.now());
				
				transaction = session.beginTransaction();
				session.merge(config);
				transaction.commit();
			
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())) {
					if (transaction.isActive()) {
						transaction.rollback();
					}
					session.close();
				}
				session.close();
				e.printStackTrace();
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}

	public void inicializarThreads() {
		inicializarThreadProcessamentoRemessa();	
		inicializarThreadConsultaProcessamentoRemessa();
		inicializarThreadRetorno();
	}

	private void inicializarThreadRetorno() {
		ReturnProcessWatcherThread returnProcessWatcherThread = new ReturnProcessWatcherThread();
		Thread tdDois = new Thread(returnProcessWatcherThread);
		tdDois.start();
	}

	private void inicializarThreadConsultaProcessamentoRemessa() {
		ProcessingWatcherThread processingWatcherThread = new ProcessingWatcherThread(); 
		Thread td = new Thread(processingWatcherThread);
		td.start();
	}

	private void inicializarThreadProcessamentoRemessa() {
		ScannerFilesThread instance = ScannerFilesThread.getInstance();
		instance.startProcessamento();
	}
	

	public boolean processarThreadsRotina() throws JSONException{
		if (rotinaEstaAtualizada()) {
			inicializarThreads();
			return true;
		}
		return false;
	}
	
	public boolean rotinaEstaAtualizada() throws JSONException{
		this.config = carregarConfiguracoesGeraisProjeto();
		if(acessoEstaLiberadoParaConsultaVersao(config)){
			config.getAtualizacoesSistema();
			atualizarUltimoResultadoVersao(config);
			return config.isVersaoEstaAtualizada();
		}else{
			if(config.isVersaoEstaAtualizada()){
				return true;
			}else{
				config.getAtualizacoesSistema();
				atualizarUltimoResultadoVersao(config);
				return config.isVersaoEstaAtualizada();
			}
		}
	}

	private boolean acessoEstaLiberadoParaConsultaVersao(ConfiguracoesGeraisProjeto config) {
		if (config.getUltimaHoraValidacaoVersao() != null) {
			Duration duration;
			
			try {
				duration = Duration.between(config.getUltimaHoraValidacaoVersao(), LocalDateTime.now());
			} catch (Exception e) {
				return true;
			}
			
			long hours = duration.toHours();
			if (hours >= 1) {
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	public String carregarMensagemBloqueioRotina() {
		if (config != null) {
			String numeroUltimaVersaoLiberada = config.getAutalizacao().getVersaoAtualSistemaRemoto().getNumero();
			String linkPadraoDownloadVersao = config.getAutalizacao().getLinkAtualizacao();
			String msg = "Sua versão do sistema esta desatualizada .\nAcesse o link: " + linkPadraoDownloadVersao + " \npara baixar a nova versão: " + numeroUltimaVersaoLiberada;
			
			return msg;
		}
		
		return "";
	}
	
	public SessionFactory getFactory() {
		return factory;
	}
	
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
}











