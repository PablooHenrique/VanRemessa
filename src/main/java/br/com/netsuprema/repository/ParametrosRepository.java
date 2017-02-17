package br.com.netsuprema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.com.netsuprema.dominio.Parametros;

public class ParametrosRepository extends AbstractRepository<Parametros> {

	public ParametrosRepository(Session session) {
		super(session);
	}

	@Override
	public List<Parametros> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		
		Map<String, Object> paramtros = new HashMap<String, Object>();
		paramtros.put("descricao", descricao);
		
		jpql.append("Select a from Parametros a where a.nome = :descricao");
		List<Parametros> parametros = select(jpql.toString(), paramtros);
		
		return parametros;
	}
}
