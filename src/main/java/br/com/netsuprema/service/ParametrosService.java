package br.com.netsuprema.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.Parametros;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ParametrosRepository;

public class ParametrosService {

	private SessionFactory sessionFactory;
	
	public ParametrosService() {
		this.sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public void salvar(Parametros parametros){
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		ParametrosRepository repository = new ParametrosRepository(session);
		repository.salvar(parametros);
		
		transaction.commit();
		System.out.println("Parametros gravados com sucesso");
	}
	
	public List<Parametros> buscarParametros(){
		ParametrosRepository repository = new ParametrosRepository(this.sessionFactory.openSession());
		List<Parametros> parametros = repository.listar();
		return parametros;
	}
}
