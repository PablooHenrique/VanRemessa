package br.com.netsuprema.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ParametrosRepository;
import br.com.netsuprema.repository.RemessaRepository;
import br.com.netsuprema.service.cedente.CedenteService;

public class RemessasDeTitulosService {
	
	
	private Logger logger = Logger.getLogger(RemessasDeTitulosService.class);
	private SessionFactory factory;
	
	public RemessasDeTitulosService() {
		factory = Application.getInstance().getSessionFactory();
	}
	
	public void processar() throws Exception{
		
		try {
		
			logger.info("Inicializando processamento: Processando");
			logger.info("========================================");
			
			ScannerFilesThread.logErros.add("Inicializando processamento: Processando");
			ScannerFilesThread.logErros.add("========================================");
			
			if (!processamentoEValido()) {
				ScannerFilesThread.logErros.add("Dados Invalidos para realizar o processamento");
			}else{ 
				List<Cedente> cedentes = carregarCedentes();
				processarCedentes(cedentes);
			}
		
			ScannerFilesThread.logErros.add("Finalizado processamento: Em espera");
			ScannerFilesThread.logErros.add("========================================");
			
			logger.info("Finalizado processamento: Em espera");
			logger.info("========================================");
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--processarCedente--");
			builder.append("Falha no processamento. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha no processamento");
			
		}
	}
	
	public void reprocessarRemessasEnviadasAoServidor() throws Exception{
		List<Remessa> remessas = new RemessasDeTitulosService().listarRemessasComAguardoProcessamentoLocal();
		RelatoriosWebService serviceRelatorios = new RelatoriosWebService();
		for (Remessa remessa : remessas) {
			serviceRelatorios.reprocessarRemessaEnviadasAoServidor(remessa);
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			new RemessaRepository(session).atualizar(remessa);
			transaction.commit();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		new RemessasDeTitulosService().reprocessarRemessasEnviadasAoServidor();
		System.out.println("");
		
	}
	
	private boolean processamentoEValido() {
		return parametrosSaoValidos();
	}

	private boolean parametrosSaoValidos() {
		try {
			Session session = factory.openSession();
			carregarParametros(session);
			return true;
		} catch (Exception e) {
			ScannerFilesThread.errosUsuario.add("Parametros não cadastrados. Não e possivel conectar com o serviço.");
			return false;
		}
	}

	private void processarCedente(Cedente cedente) throws Exception{
		try {
			
			logger.info("Processar Cedente");
			logger.info("*****************");
			
			ScannerFilesThread.logErros.add("Processar Cedente");
			ScannerFilesThread.logErros.add("*****************");
			
			List<Conta> contas = cedente.getContas();
			processarContas(cedente, contas);
			
			ScannerFilesThread.logErros.add("Finalizado Processamento Cedente");
			ScannerFilesThread.logErros.add("*****************");
			
			logger.info("Finalizado Processamento Cedente");
			logger.info("*****************");
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--processarCedente--");
			builder.append("Falha ao processar conta. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao processar cedente. Motivo: " + e.getMessage());
		}
	}
	
	private List<Cedente> carregarCedentes(){
		List<Cedente> cedentes = new CedenteService().listar();
		return cedentes;
	}
	
	private void processarConta(Cedente cedente, Conta conta) throws Exception{
		try {
		
			ScannerFilesThread.logErros.add("Processando Conta");
			ScannerFilesThread.logErros.add("Cedente: " + cedente.getCodigo() +" - "+ cedente.getNome());
			ScannerFilesThread.logErros.add("Conta: " + conta.getNumeroConta());
			
			logger.info("Processando Conta");
			logger.info("Cedente: " + cedente.getCodigo() +" - "+ cedente.getNome());
			logger.info("Conta: " + conta.getNumeroConta());
			
			List<File> files = obterArquivosProcessamento(conta);
		
			if (files.size() > 0) {
				processarArquivos(cedente, conta, files);
			}
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--processarConta--");
			builder.append("Falha ao processar conta. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao processar conta. Motivo: " + e.getMessage());
			
		}
	}

	private void processarArquivos(Cedente cedente, Conta conta, List<File> files) throws Exception{
		logger.info("Processando Arquivos");
		
		ScannerFilesThread.logErros.add("Processando Arquivos");
		
		for (File file : files) {
			long idProcessamento;
				idProcessamento = processarArquivo(cedente, conta, file);
			
			Session session = factory.openSession();
			if(!processamentoEncerradoComSucesso(session, idProcessamento)){
				logger.warn("Processamento do arquivo não realizado de forma efetiva. Aguardar para tentar novamente");
				throw new Exception("Processamento do arquivo não realizado de forma efetiva. Aguardar para tentar novamente");
			}
		}
	}
	
	private long processarArquivo(Cedente cedente, Conta conta, File file) throws Exception{
		logger.info("Processando Arquivo: " + file.getName());
		
		ScannerFilesThread.logErros.add("Processando Arquivo: " + file.getName());
		
		Session session = null;
		Transaction transaction = null;
		
		long idProcessamento;
		
		try {
		
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				
				try {
					idProcessamento = processarRemessa(cedente, conta.getNumeroConta(), carregarParametros(session), file, session);
				} catch (Exception e) {
					idProcessamento = 0;
					ScannerFilesThread.logErros.add("Falha no processamento do arquivo");
					ScannerFilesThread.logErros.add("IDREMESSA: " + idProcessamento);
					ScannerFilesThread.logErros.add("Nome Arquivo: " + file.getName());
					ScannerFilesThread.logErros.add("Erro: " +e.getMessage());
					ScannerFilesThread.logErros.add("Causa: " +e.getCause().getMessage());
				}
				
				boolean processamentoEncerradoComSucesso = processamentoEncerradoComSucesso(session, idProcessamento);
				
				if (processamentoEncerradoComSucesso) {
					new FileService().copyFile(file, new File(cedente.getDiretorioPadrao()+"/"+conta.getNumeroConta()+"/enviados/"+file.getName()+".txt"));
					file.delete();
				}else{
					ScannerFilesThread.logErros.add("Processamento realizado com erro. Favor verificar o log do processamento para mais detalhes.");
					ScannerFilesThread.logErros.add("IDREMESSA: " + idProcessamento);
					ScannerFilesThread.logErros.add("Nome Arquivo: " + file.getName());
				}
				
				transaction.commit();
				return idProcessamento;
			} catch (Exception e) {
				
				if ((session != null) && (session.isOpen())){
					if (transaction.isActive()) {
						transaction.rollback();
					}
				}
				
				
				logger.info("falha ao realizar o processamento do arquivo: " + file.getName() + " " + e.getMessage());
				logger.info("Causa: " + e.getCause().getMessage());
				
				ScannerFilesThread.logErros.add("falha ao realizar o processamento do arquivo: " + file.getName() + " " + e.getMessage());
				ScannerFilesThread.logErros.add("Causa: " + e.getCause().getMessage());
				
				logger.error("falha ao realizar o processamento do arquivo: " + file.getName() + " " + e.getMessage());
				throw new Exception("falha ao realizar o processamento do arquivo: " + file.getName() + " " + e.getMessage());
			}
			
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	private boolean processamentoEncerradoComSucesso(Session session, long idProcessamento) {
		RemessaRepository repository = new RemessaRepository(session);
		Remessa remessa = repository.listarPorId(idProcessamento);
		
		if(remessa.getLog().getSituacao().equals("OK")){
			return true;
		}
		
		return false;
	}

	private Parametros carregarParametros(Session session) throws Exception {
		ParametrosRepository repository = new ParametrosRepository(session);
		List<Parametros> parametros = repository.listar();
		if (!parametros.isEmpty()) {
			return parametros.get(0);
		}
		
		ScannerFilesThread.logErros.add("Parametros para remessa dos arquivos invalidos. Verique o cadastro de parametros para realizar a importação");
		throw new Exception("Parametros para remessa dos arquivos invalidos. Verique o cadastro de parametros para realizar a importação");
	}

	private List<File> obterArquivosProcessamento(Conta conta) throws Exception{
		try {
			
			logger.info("Carregando Arquivos");
			
			ScannerFilesThread.logErros.add("Carregando Arquivos");
			
			FileService fileService = new FileService();
			List<File> files;
			files = fileService.obterArquivosParaEnvioDiretorio(conta.getDiretorio());
			
			return files;
			
		} catch (IOException e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--obterArquivosProcessamento--");
			builder.append("Falha ao obter os arquivos de processamento. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao obter os arquivos de processamento. Motivo: " + e.getMessage());
		}
	}
	
	private void processarContas(Cedente cedente, List<Conta> contas) throws Exception{
		try {
			
			logger.info("Processar Contas");
			ScannerFilesThread.logErros.add("Processar Contas");
			
			for (Conta conta : contas) {
					processarConta(cedente, conta);
			}
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--processarContas--");
			builder.append("Falha ao processar a remessa. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao processar contas. Motivo: " + e.getMessage());
		}
	}
	
	private long processarRemessa(Cedente cedente, int numeroConta, Parametros parametro, File file, Session session) throws Exception{
		try {
			
			Remessa remessa = new Remessa(cedente, numeroConta, parametro, file);
			long idProcessamento = remessa.processar(session);
			
			return idProcessamento;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--obterArquivosProcessamento--");
			builder.append("Falha ao processar a remessa. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao processar a remessa. Motivo: " + e.getMessage());
			
		}
	}
	
	private void processarCedentes(List<Cedente> cedentes) throws Exception{
		try {
			
			logger.info("Processando Cedentes");
			ScannerFilesThread.logErros.add("Processando Cedentes");
			
			for (Cedente cedente : cedentes) {
				processarCedente(cedente);
			}
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--processarCedentes--");
			builder.append("Falha ao processar a remessa. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao processar cedente. Motivo: " + e.getMessage());
			
		}
	}
	
	public List<Remessa> listarRemessasComAguardoProcessamentoLocal() {
		Session session = null;
		try {
			session = factory.openSession();
			List<Remessa> remessas = new RemessaRepository(session).listarRemessasComAguardoProcessamentoLocal();
			return remessas;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public List<Remessa> listarRemessasProcessadasComSucesso() {
		Session session = null;
		try {
			session = factory.openSession();
			List<Remessa> remessas = new RemessaRepository(session).listarRemessasProcessadasComSucesso();
			return remessas;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public List<Remessa> listarRemessasProcessadasComErro() {
		Session session = null;
		try {
			session = factory.openSession();
			List<Remessa> remessas = new RemessaRepository(session).listarRemessasProcessadasComErro();
			return remessas;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public List<Remessa> listarRemessasEnviadas() {
		Session session = null;
		try {
			session = factory.openSession();
			List<Remessa> remessas = new RemessaRepository(session).listarRemessasEnviadas();
			return remessas;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public List<Remessa> listarRemessasComAguardoProcessamentoServidor() {
		Session session = null;
		try {
			session = factory.openSession();
			List<Remessa> remessas = new RemessaRepository(session).listarRemessasAguardandoProcessamentoServidor();
			return remessas;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
