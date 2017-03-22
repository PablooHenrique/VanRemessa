package br.com.netsuprema.service.parametros.cooperativa;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CooperativaRepository;

public class CooperativaService {
	
	private SessionFactory sessionFactory;
	
	public CooperativaService() {
		this.sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public Cooperativa listarCooperativaPorKey(int key){
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			Cooperativa cooperativa = new CooperativaRepository(session).selectByKey(key);
			return cooperativa;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public void salvar(Cooperativa cooperativa){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			new CooperativaRepository(session).salvar(cooperativa);
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public void salvarListaCooperativas(List<Cooperativa> cooperativas) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			CooperativaRepository repository = new CooperativaRepository(session);
			
			Transaction transaction = session.beginTransaction();
			cooperativas.stream().forEach(x-> repository.salvar(x));
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public List<Cooperativa> listarCooperativas() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			CooperativaRepository repository = new CooperativaRepository(session);
			return repository.listar();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
