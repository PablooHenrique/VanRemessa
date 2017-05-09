package br.com.netsuprema.repository;

import java.util.List;

import org.hibernate.Session;

import br.com.netsuprema.dominio.parametros.SeparadorArquivos;

public class SeparadorArquivosRepository extends AbstractRepository<SeparadorArquivos>{
	

	public SeparadorArquivosRepository(Session session) {
		super(session);
	}

	@Override
	public List<SeparadorArquivos> listarPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}

}
