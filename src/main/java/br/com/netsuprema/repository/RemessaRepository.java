package br.com.netsuprema.repository;

import java.util.List;

import org.hibernate.Session;

import br.com.netsuprema.dominio.remessa.Remessa;

public class RemessaRepository extends AbstractRepository<Remessa>{

	public RemessaRepository(Session session) {
		super(session);
	}

	@Override
	public List<Remessa> listarPorDescricao(String descricao) {
		return null;
	}
}
