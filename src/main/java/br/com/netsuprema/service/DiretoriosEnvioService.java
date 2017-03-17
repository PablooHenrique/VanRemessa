package br.com.netsuprema.service;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.Cedente;
import br.com.netsuprema.dominio.Conta;
import br.com.netsuprema.dominio.exceptions.DiretoriosInvalidosExceptions;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;

public class DiretoriosEnvioService {
	
	public static final String DIRETORIO_PADRAO = "C:/Van Remessas/dir/arq/";
	
	private SessionFactory sessionFactory;
	
	public DiretoriosEnvioService() {
		this.setSessionFactory(Application.getInstance().getSessionFactory());
	}

	public void criarCedente(Cedente cedente) throws Exception {
		Session session = getSessionFactory().openSession();
		CedenteRepository repository = new CedenteRepository(session);
		Transaction transaction = null;
		
		try {
			if(!cedente.cedenteExiste(session)){
				
				transaction = session.beginTransaction();
				
				criarDiretorio(cedente);
				salvarCedente(cedente, session);
				
				transaction.commit();
			}else{
				repository.atualizar(cedente);
			}
		} catch (DiretoriosInvalidosExceptions e) {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			}
			throw new DiretoriosInvalidosExceptions("Não foi possivel criar os diretórios para os cedentes. Motivo : " +e.getMessage());
		} catch (Exception e) {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			}
			throw new Exception("Erro ao realizar a criação dos diretorios para o cedente. Motivo : " +e.getMessage());
		}finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	private void criarDiretorio(Cedente cedente) throws Exception, DiretoriosInvalidosExceptions{
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
		String path = getDiretorioPadrao() + cedente.getCodigo();
		cedente.setDiretorioPadrao(path);
		
		for (Conta conta : cedente.getContas()) {
			String pathConta = path +"/"+ conta.getNumeroConta();
			conta.setDiretorio(pathConta);
			diretorios.add(pathConta); 
			pathConta = path +"/"+ conta.getNumeroConta() +"/enviados";
			diretorios.add(pathConta);
		}
		
		return diretorios;
	}

	private String getDiretorioPadrao() {
		return DIRETORIO_PADRAO;
	}

	public void salvarCedente(Cedente cedente,Session session){
		new CedenteService().salvar(session, cedente);
	}


	public void excluirCedente(Integer codigo) throws Exception {
		new CedenteService().excluirCedentePorCodigo(codigo);
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
