package br.com.netsuprema.repository;

import java.util.List;

import org.hibernate.Session;

import br.com.netsuprema.dominio.parametros.ConfiguracoesGeraisProjeto;

public class ConfiguracoesGeraisProjetoRepository extends AbstractRepository<ConfiguracoesGeraisProjeto>{

	public ConfiguracoesGeraisProjetoRepository(Session session) {
		super(session);
	}

	@Override
	public List<ConfiguracoesGeraisProjeto> listarPorDescricao(String descricao) {
		return null;
	}
}
