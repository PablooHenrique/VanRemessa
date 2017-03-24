package br.com.netsuprema.service.parametros;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.ConfiguracoesGeraisProjetoRepository;

public class ConfiguracoesGeraisProjetoService {
	
	private SessionFactory factory;
	
	public ConfiguracoesGeraisProjetoService() {
		factory = Application.getInstance().getSessionFactory();
	}

	private void salvar(Session session, ConfiguracoesGeraisProjeto config){
		new ConfiguracoesGeraisProjetoRepository(session).atualizar(config);
	}

	public void atualizarDataHoraEnvio() {
		Session session = null;
		Transaction transaction = null;
		try {
			
			try {
			
				ConfiguracoesGeraisProjeto config;
				
				session = factory.openSession();
				List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
				if(!configs.isEmpty()){
					config = configs.get(0);
				}else{
					config = new ConfiguracoesGeraisProjeto();
				}
				
				config.setUltimaDataHoraEnvio(LocalDateTime.now());
				
				transaction = session.beginTransaction();
				salvar(session, config);
				transaction.commit();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				session.close();
			}
		}
	}
	
	public ConfiguracoesGeraisProjeto listarConfig() {
		Session session = null;
		try {
			session = factory.openSession();
			List<ConfiguracoesGeraisProjeto> configs = new ConfiguracoesGeraisProjetoRepository(session).listar();
			if (!configs.isEmpty()) {
				return configs.get(0);
			}
			return new ConfiguracoesGeraisProjeto();
			
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

}
