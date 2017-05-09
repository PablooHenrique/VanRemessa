package br.com.netsuprema.service.parametros;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.parametros.SeparadorArquivos;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ParametrosRepository;
import br.com.netsuprema.repository.SeparadorArquivosRepository;
import br.com.netsuprema.service.FileService;

public class SeparadorArquivosService {
	
	private SessionFactory factory;
	
	public SeparadorArquivosService() {
		super();
		this.setFactory(Application.getInstance().getSessionFactory());
	}

	public void salvarSeparadorArquivo(String diretorio, boolean ativarSeparador) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			try {
				SeparadorArquivos separadorArquivos;
				
				Parametros parametros = carregarParametros();
				session = factory.openSession();
				
				SeparadorArquivosRepository repository = new SeparadorArquivosRepository(session);
				List<SeparadorArquivos> separadoresDeArquivos = repository.listar();
				
				ParametrosRepository parametrosRepository = new ParametrosRepository(session);
				
				transaction = session.beginTransaction();
				
				parametros.setUtilizaSeparadorArquivoPorPastasCedentes(ativarSeparador);
				parametrosRepository.atualizar(parametros);
				
				if (separadoresDeArquivos.isEmpty()) {
					separadorArquivos = new SeparadorArquivos();
				}else{
					separadorArquivos = separadoresDeArquivos.get(0);
				}
				
				separadorArquivos.setDiretorio(diretorio);
				repository.atualizar(separadorArquivos);
				
				transaction.commit();
			} finally {
				if ((session != null) && (session.isOpen())) {
					session.close();
				}
			}
		} catch (Exception e) {
			if ((transaction != null) && ( transaction.isActive())) {
				transaction.rollback();
			}
			throw new Exception(e);
		}
	}
	
	public SeparadorArquivos carregarSeparadorDeArquivos() throws Exception{
		Session session = null;
		try {
			try {
				SeparadorArquivos separadorArquivos;
				
				session = factory.openSession();
				
				SeparadorArquivosRepository repository = new SeparadorArquivosRepository(session);
				List<SeparadorArquivos> separadoresDeArquivos = repository.listar();
				
				if (separadoresDeArquivos.isEmpty()) {
					separadorArquivos = new SeparadorArquivos();
				}else{
					separadorArquivos = separadoresDeArquivos.get(0);
				}
				
				return separadorArquivos;
			} finally {
				if ((session != null) && (session.isOpen())) {
					session.close();
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Parametros carregarParametros(){
		ParametrosService service = new ParametrosService();
		List<Parametros> parametros = service.listarParametros();
		
		if (!parametros.isEmpty()) {
			return parametros.get(0);
		}
		
		return null;
	}
	
	public void processar() throws Exception{
		Parametros parametros = carregarParametros();
		if (parametros.isUtilizaSeparadorArquivoPorPastasCedentes()) {
			SeparadorArquivos separadorDeArquivos = carregarSeparadorDeArquivos();
			String diretorio = separadorDeArquivos.getDiretorio();
			
			FileService fileService = new FileService();
			List<File> obterArquivosParaEnvioDiretorio = fileService.obterArquivosParaEnvioDiretorio(diretorio);
			for (File arq : obterArquivosParaEnvioDiretorio) {
				if(arquivoEValido(arq)){
					String pathConta = criarDiretorioConta(arq, diretorio);
					moverArquivo(arq, pathConta);
				}
			}
		}
	}
		
	private boolean arquivoEValido(File arq) {
		
		String[] array = arq.getAbsolutePath().split("_");
		List<String> list = Arrays.asList(array);
		if (list.size() != 5) {
			return false;
		}
		
		if (!list.get(0).substring(list.get(0).length()-9).substring(6).equals("756")) {
			return false;
		}
		
		return true;
	}

	private void moverArquivo(File arq, String pathConta) throws IOException {
		FileService service = new FileService();
		service.copyFile(arq, new File(pathConta+"/"+arq.getName()));
		arq.delete();
	}

	private String criarDiretorioConta(File file, String caminhoPadrao){
		String[] caminhosQuebrados = file.getAbsolutePath().split("_");
		List<String> list = Arrays.asList(caminhosQuebrados);
		
		String pathConta = caminhoPadrao + "/" + list.get(0).substring(list.get(0).length()-9).substring(6);
		pathConta = pathConta + "/" + list.get(1);
		pathConta = pathConta + "/" + list.get(2);
		System.out.println(pathConta);
		
		FileService service = new FileService();
		service.criarDiretorio(pathConta);
		
		return pathConta;
	}

	public SessionFactory getFactory() {
		return factory;
	}



	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public static void main(String[] args) throws Exception {
		new SeparadorArquivosService().processar();
	}
}
