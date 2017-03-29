package br.com.netsuprema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.com.netsuprema.dominio.parametros.Cooperativa;

public class CooperativaRepository extends AbstractRepository<Cooperativa>{

	public CooperativaRepository(Session session) {
		super(session);
	}

	@Override
	public List<Cooperativa> listarPorDescricao(String descricao) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", descricao);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select a from Cooperativa a where a.nome = :nome");
		
		List<Cooperativa> cooperativas = select(jpql.toString(), parametros);
		return cooperativas;
	}
	
	public Cooperativa selectByKey(long key){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("keyCop", key);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select a from cooperativa a where a.keyCop = :keyCop");
		
		List<Cooperativa> cooperativas = select(jpql.toString(), parametros);
		if (!cooperativas.isEmpty()) {
			return cooperativas.get(0);
		}
		
		return null;
	}
}
