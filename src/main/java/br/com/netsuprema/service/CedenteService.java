package br.com.netsuprema.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.Cedente;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;

public class CedenteService {
	private SessionFactory sessionFactory;
	
	public CedenteService() {
		this.setSessionFactory(Application.getInstance().getSessionFactory());
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
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void excluirCedentePorCodigo(Integer codigo) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			try {
				transaction = session.beginTransaction();
				
				Cedente cedente = listarPorCodigo(codigo);
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
}
