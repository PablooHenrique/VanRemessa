package br.com.netsuprema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Remessa> listarPorCodigoCedente(int codigo) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ");
		jpql.append("inner join a.cedente b on a.cedente.id = b.id ");
		jpql.append("where b.codigo = :codigo");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}

	public List<Remessa> listarRemessasComAguardoProcessamento() {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook ");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}
}
