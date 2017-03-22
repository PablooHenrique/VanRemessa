package br.com.netsuprema.service.cedente;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.exceptions.DiretoriosInvalidosExceptions;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;
import br.com.netsuprema.repository.RemessaRepository;

public class CedenteService {
	private SessionFactory sessionFactory;
	
	public CedenteService() {
		this.setSessionFactory(Application.getInstance().getSessionFactory());
	}
	
	public void criarCedente(Cedente cedente) throws Exception {
		Session session = getSessionFactory().openSession();
		CedenteRepository repository = new CedenteRepository(session);
		Transaction transaction = null;
		
		try {
			if(!cedente.cedenteExiste(session)){
				
				transaction = session.beginTransaction();
				
				new DiretoriosEnvioService().criarDiretorio(cedente);
				salvar(session, cedente);
				
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

	public void salvar(Session session, Cedente cedente) {
		new CedenteRepository(session).salvar(cedente);
	}

	public List<Cedente> listar() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<Cedente> cedentes = new CedenteRepository(session).listar();
			return cedentes;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public List<Cedente> listarPorNome(String nome) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<Cedente> cedentes = new CedenteRepository(session).listarPorNome(nome);
			session.close();
			return cedentes;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public Cedente listarPorCodigo(int codigo) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Cedente cedente = new CedenteRepository(session).listarPorCodigo(codigo);
			session.close();
			return cedente;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public void excluirCedentePorCodigo(Integer codigo) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			try {
				transaction = session.beginTransaction();
				
				Cedente cedente = listarPorCodigo(codigo);
				removerReferenciasCedentePorCodigo(session, codigo);
				new CedenteRepository(session).remover(cedente.getId());
				transaction.commit();
			} catch (Exception e) {
				if ((session != null) && (session.isOpen())) {
					if (transaction.isActive()) {
						transaction.rollback();
					}
				}
				throw new Exception("Não foi possível realizar a exclusão do cedente. Motivo: " +e.getMessage());
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	private void removerReferenciasCedentePorCodigo(Session session, Integer codigo) {
		RemessaRepository repository = new RemessaRepository(session);
		List<Remessa> remessas = repository.listarPorCodigoCedente(codigo);
		remessas.stream().forEach(x->x.setCedente(null));
		remessas.stream().forEach(x->repository.remover(x.getId()));
	}

	public void abrirDiretorio(Integer codigo){
		new DiretoriosEnvioService().abrirDiretorio(codigo);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
