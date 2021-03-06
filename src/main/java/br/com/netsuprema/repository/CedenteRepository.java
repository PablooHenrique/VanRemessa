package br.com.netsuprema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.com.netsuprema.dominio.cedente.Cedente;

public class CedenteRepository extends AbstractRepository<Cedente>{

	public CedenteRepository(Session session) {
		super(session);
	}

	@Override
	public List<Cedente> listarPorDescricao(String descricao) {
		return null;
	}

	public Cedente listarPorCodigo(int codigo) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select a from cedente a where a.codigo = :codigo");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		
		List<Cedente> cedentes = select(jpql.toString(), parametros);
		if (!cedentes.isEmpty()) {
			return cedentes.get(0);
		}
		return null;
	}

	public List<Cedente> listarPorNome(String nome) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select a from cedente a where a.nome like :nome");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", "%" + nome + "%");
		
		List<Cedente> cedentes = select(jpql.toString(), parametros);
		return cedentes;
	}

	public Cedente listarPorNomeECodigo(Integer codigo, String nome) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select a from cedente a where a.nome like :nome ")
			.append("and a.codigo = :codigo");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", "%" + nome + "%");
		parametros.put("codigo", codigo);
		
		List<Cedente> cedentes = select(jpql.toString(), parametros);
		
		if (!cedentes.isEmpty()) {
			return cedentes.get(0);
		}
		
		return null;
	}

	public Cedente listarPorNumeroConta(Integer numeroConta) {
		
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("select a from cedente a ")
				.append("join a.contas as c ")
				.append("where c.numeroConta = :numeroConta ");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numeroConta", numeroConta);
			
			List<Cedente> cedentes = select(jpql.toString(), parametros);
			if (!cedentes.isEmpty()) {
				return cedentes.get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}
}
