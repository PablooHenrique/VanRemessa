package br.com.netsuprema.repository;

import java.util.List;

import org.hibernate.Session;

import br.com.netsuprema.dominio.remessa.LogEnvioRemessa;

public class LogRepository extends AbstractRepository<LogEnvioRemessa>{

	public LogRepository(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<LogEnvioRemessa> listarPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}


}
