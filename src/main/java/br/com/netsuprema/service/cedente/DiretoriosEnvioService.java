package br.com.netsuprema.service.cedente;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.exceptions.DiretoriosInvalidosExceptions;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.service.FileService;

public class DiretoriosEnvioService {
	
	public static final String DIRETORIO_PADRAO = "C:/Van Remessas/dir/arq/";
	
	private SessionFactory sessionFactory;
	
	public DiretoriosEnvioService() {
		this.setSessionFactory(Application.getInstance().getSessionFactory());
	}
	
	public void criarDiretorio(Cedente cedente) throws Exception, DiretoriosInvalidosExceptions{
		List<String> diretorios = criarPathDiretorios(cedente);
		FileService service = new FileService();

		for (String diretorio : diretorios) {
			if (!service.diretorioExists(diretorio)) {
				try {
					service.criarDiretorio(diretorio);
				} catch (AccessControlException e) {
					throw new DiretoriosInvalidosExceptions(e.getMessage());
				}
			}
		}
	}

	private List<String> criarPathDiretorios(Cedente cedente) {
		List<String> diretorios = new ArrayList<String>();
		String path = cedente.getDiretorioPadrao();
		
		diretorios.add(path);
		diretorios.add(path + "/enviados");
		
		return diretorios;
	}

	public void abrirDiretorio(Integer codigo) {
		Cedente cedente = new CedenteService().listarPorCodigo(codigo);
		abrirDiretorio(cedente.getDiretorioPadrao());
	}

	private void abrirDiretorio(String path) {
		try {
			path = tratarCaminhoPadrao(path);
			Runtime.getRuntime().exec("explorer.exe " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String tratarCaminhoPadrao(String path) {
		path = path.replace("/", "\\");
		return path;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
