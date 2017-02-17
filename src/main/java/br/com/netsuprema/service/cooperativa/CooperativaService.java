package br.com.netsuprema.service.cooperativa;

import java.net.URISyntaxException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;

import br.com.netsuprema.dominio.Cooperativa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CooperativaRepository;

public class CooperativaService {
	
	private SessionFactory sessionFactory;
	
	public CooperativaService() {
		this.sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public List<Cooperativa> consultarCooperativasWebService() throws URISyntaxException, JSONException{
		return new CooperativaWebService().obterCooperativas();
	}
	
	public Cooperativa consultarCooperativaPorKey(int key){
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
}
