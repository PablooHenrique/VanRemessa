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
import br.com.netsuprema.service.cedente.CedenteService;

public class RemessasDeTitulosService {
	
	
	private Logger logger = Logger.getLogger(RemessasDeTitulosService.class);
	private SessionFactory factory;
	
	public RemessasDeTitulosService() {
		factory = Application.getInstance().getSessionFactory();
	}
	
	public void processar() throws Exception {
		
		
		logger.info("Inicializando processamento: Processando");
		logger.info("========================================");
		ScannerFilesThread.logErros.add("Inicializando processamento: Processando");
		ScannerFilesThread.logErros.add("========================================");
		
		List<Cedente> cedentes = carregarCedentes();
		processarCedentes(cedentes);
		
		ScannerFilesThread.logErros.add("Finalizado processamento: Em espera");
		ScannerFilesThread.logErros.add("========================================");
		logger.info("Finalizado processamento: Em espera");
		logger.info("========================================");
		
	}
	
	private void processarCedente(Cedente cedente) throws Exception{
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
	}
	
	private List<Cedente> carregarCedentes(){
		List<Cedente> cedentes = new CedenteService().listar();
		return cedentes;
	}
	
	private void processarConta(Cedente cedente, Conta conta) throws Exception{
		
		ScannerFilesThread.logErros.add("Processando Conta");
		ScannerFilesThread.logErros.add("Cedente: " + cedente.getCodigo() +" - "+ cedente.getNome());
		ScannerFilesThread.logErros.add("Conta: " + conta.getNumeroConta());
		
		List<File> files = obterArquivosProcessamento(conta);
		if (files.size() > 0) {
			processarArquivos(cedente, conta, files);
		}
	}

	private void processarArquivos(Cedente cedente, Conta conta, List<File> files) throws Exception{
		logger.info("Processando Arquivos");
		ScannerFilesThread.logErros.add("Processando Arquivos");
		
		for (File file : files) {
			processarArquivo(cedente, conta, file);
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
				idProcessamento = processarRemessa(cedente, conta.getNumeroConta(), carregarParametros(session), file, session);
				transaction.commit();
				new FileService().copyFile(file, new File(cedente.getDiretorioPadrao()+"/"+conta.getNumeroConta()+"/enviados/"+file.getName()+".txt"));
				file.delete();
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

	private Parametros carregarParametros(Session session) {
		ParametrosRepository repository = new ParametrosRepository(session);
		List<Parametros> parametros = repository.listar();
		if (!parametros.isEmpty()) {
			return parametros.get(0);
		}
		return null;
	}

	private List<File> obterArquivosProcessamento(Conta conta) throws IOException {
		logger.info("Carregando Arquivos");
		ScannerFilesThread.logErros.add("Carregando Arquivos");
		FileService fileService = new FileService();
		List<File> files = fileService.obterArquivosParaEnvioDiretorio(conta.getDiretorio());
		return files;
	}
	
	private void processarContas(Cedente cedente, List<Conta> contas) throws Exception{
		logger.info("Processar Contas");
		ScannerFilesThread.logErros.add("Processar Contas");
		for (Conta conta : contas) {
			processarConta(cedente, conta);
		}
	}
	
	private long processarRemessa(Cedente cedente, int numeroConta, Parametros parametro, File file, Session session) throws IOException{
		Remessa remessa = new Remessa(cedente, numeroConta, parametro, file);
		long idProcessamento = remessa.processar(session);
		return idProcessamento;
	}
	
	private void processarCedentes(List<Cedente> cedentes) throws Exception{
		logger.info("Processando Cedentes");
		ScannerFilesThread.logErros.add("Processando Cedentes");
		for (Cedente cedente : cedentes) {
			processarCedente(cedente);
		}
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
}
