package br.com.netsuprema.service.parametros.cooperativa;

import java.util.List;
import java.util.stream.Collectors;

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

	public void salvarListaCooperativas(List<Cooperativa> cooperativas) throws Exception {
		try {
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
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao salvar os dados da lista de cooperativa.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
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

	public boolean codigoCooperativaEValido(long codigo) {
		List<Cooperativa> cooperativas = listarCooperativas();
		List<Cooperativa> list = cooperativas.stream().filter(x->x.getKeyCop() == codigo).collect(Collectors.toList());
		
		return !list.isEmpty();
	}
}
