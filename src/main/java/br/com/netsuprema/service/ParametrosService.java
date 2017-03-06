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
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			ParametrosRepository repository = new ParametrosRepository(session);
			
			List<Parametros> parametrosBanco = repository.listar();
			Transaction transaction = session.beginTransaction();
			
			if (!parametrosBanco.isEmpty()) {
				atualizarParametros(parametrosBanco.get(0), parametros);
				repository.atualizar(parametrosBanco.get(0));
			}else{
				repository.salvar(parametros);
			}
			transaction.commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
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
	
	public List<Parametros> consultarParametros(){
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			ParametrosRepository repository = new ParametrosRepository(session);
			List<Parametros> parametros = repository.listar();
			return parametros;
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		
	}
}
