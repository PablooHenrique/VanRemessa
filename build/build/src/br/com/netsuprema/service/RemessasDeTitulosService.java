package br.com.netsuprema.service;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ParametrosRepository;
import br.com.netsuprema.service.cedente.CedenteService;


@Component
public class RemessasDeTitulosService {
	
	private SessionFactory factory;
	
	public RemessasDeTitulosService() {
		factory = Application.getInstance().getSessionFactory();
	}
	
	public void processar() throws Exception{
		List<Cedente> cedentes = carregarCedentes();
		processarCedentes(cedentes);
	}
	
	private void processarCedente(Cedente cedente) throws Exception{
		List<Conta> contas = cedente.getContas();
		processarContas(cedente, contas);
	}
	
	private List<Cedente> carregarCedentes(){
		List<Cedente> cedentes = new CedenteService().listar();
		return cedentes;
	}
	
	private void processarConta(Cedente cedente, Conta conta) throws Exception{
		List<File> files = obterArquivosProcessamento(conta);
		if (files.size() > 0) {
			processarArquivos(cedente, conta, files);
		}
	}

	private void processarArquivos(Cedente cedente, Conta conta, List<File> files) throws Exception{
		for (File file : files) {
			processarArquivo(cedente, conta, file);
		}
	}
	
	private long processarArquivo(Cedente cedente, Conta conta, File file) throws Exception{
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
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())){
					if (transaction.isActive()) {
						transaction.rollback();
					}
				}
				e.printStackTrace();
				throw new Exception("falha ao realizar o processamento do arquivo: " + file.getName() + " " + e.getMessage());
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		
		return idProcessamento;
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
		FileService fileService = new FileService();
		List<File> files = fileService.obterArquivosParaEnvioDiretorio(conta.getDiretorio());
		return files;
	}
	
	private void processarContas(Cedente cedente, List<Conta> contas) throws Exception{
		for (Conta conta : contas) {
			processarConta(cedente, conta);
		}
	}
	
	private long processarRemessa(Cedente cedente, int numeroConta, Parametros parametro, File file, Session session) throws RemoteException{
		Remessa remessa = new Remessa(cedente, numeroConta, parametro, file);
		long idProcessamento = remessa.processar(session);
		return idProcessamento;
	}
	
	private void processarCedentes(List<Cedente> cedentes) throws Exception{
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
