package br.com.netsuprema.service;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.netsuprema.dominio.Cedente;
import br.com.netsuprema.dominio.Conta;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;

public class RemessasDeTitulosService {
	
	private SessionFactory factory;
	
	public RemessasDeTitulosService() {
		factory = Application.getInstance().getSessionFactory();
	}
	
	public List<Cedente> carregarCedentes(){
		Session session = factory.openSession();
		List<Cedente> cedentes = new CedenteRepository(session).listar();
		return cedentes;
	}
	
	public void processarCedente(Cedente cedente) throws IOException{
		List<Conta> contas = cedente.getContas();
		for (Conta conta : contas) {
			String path = conta.getDiretorio();
			FileService fileService = new FileService();
			File[] files = fileService.getArquivosDiretorio(path);
			
			if (files.length > 0) {
				for (File file : files) {
					String mimiType = URLConnection.guessContentTypeFromName(file.getName());
					System.out.println("Nome: " + cedente.getNome() +" Conta: "+conta.getNumeroConta()+ " File: "+ file.getName() +" MimiType: "+ mimiType);
				}
			}else{
				System.out.println("Nome: " + cedente.getNome() +" Conta: "+conta.getNumeroConta()+ " File: "+ "Sem arquivo");
			}
			
		}
	}
	
	public void processarCedentes(List<Cedente> cedentes) throws IOException{
		for (Cedente cedente : cedentes) {
			processarCedente(cedente);
		}
	}
	
	public void processar() throws IOException{
		List<Cedente> cedentes = carregarCedentes();
		processarCedentes(cedentes);
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public static void main(String[] args) throws IOException {
		RemessasDeTitulosService service = new RemessasDeTitulosService();
		service.processar();
		
	}
}
