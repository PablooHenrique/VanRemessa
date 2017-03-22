package br.com.netsuprema.service.parametros;

import java.net.URISyntaxException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONException;

import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ParametrosRepository;
import br.com.netsuprema.service.parametros.cooperativa.CooperativaService;
import br.com.netsuprema.service.parametros.cooperativa.CooperativaWebService;

public class ParametrosService {

	private SessionFactory sessionFactory;
	
	public ParametrosService() {
		this.setSessionFactory(Application.getInstance().getSessionFactory());
	}
	
	public void salvar(Parametros parametros){
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.getSessionFactory().openSession();
			ParametrosRepository repository = new ParametrosRepository(session);
			
			List<Parametros> parametrosBanco = repository.listar();
			transaction = session.beginTransaction();
			
			if (!parametrosBanco.isEmpty()) {
				atualizarParametros(parametrosBanco.get(0), parametros);
				repository.atualizar(parametrosBanco.get(0));
			}else{
				repository.salvar(parametros);
			}
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}
	
	public void atualizarParametros(Parametros parametrosBanco, Parametros parametros){
		parametrosBanco.setCooperativa(parametros.getCooperativa());
		parametrosBanco.setFormatoRemessa(parametros.getFormatoRemessa());
		parametrosBanco.setEmail(parametros.getEmail());
		parametrosBanco.setLogin(parametros.getLogin());
		parametrosBanco.setSenha(parametros.getSenha());
	}
	
	public List<Parametros> listarParametros(){
		Session session = null;
		try {
			session = this.getSessionFactory().openSession();
			ParametrosRepository repository = new ParametrosRepository(session);
			List<Parametros> parametros = repository.listar();
			return parametros;
		} finally {
			if ((session != null) && (session.isOpen())){
				session.close();
			}
		}
	}
	
	public List<Cooperativa> listarCooperativasWebService() throws URISyntaxException, JSONException {
		return new CooperativaWebService().obterCooperativas();
	}
	
	public Cooperativa listarCooperativaPorKey(int key){
		return new CooperativaService().listarCooperativaPorKey(key);
	}
	
	public void salvarCooperativa(Cooperativa cooperativa){
		new CooperativaService().salvar(cooperativa);
	}
	
	public void salvarListaCooperativas(List<Cooperativa> cooperativas){
		new CooperativaService().salvarListaCooperativas(cooperativas);
	}
	
	public List<Cooperativa> listarCooperativas(){
		return new CooperativaService().listarCooperativas();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
