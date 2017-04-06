package br.com.netsuprema.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import br.com.netsuprema.dominio.retornoliquidacao.RetornoLiquidacao;
import br.com.netsuprema.dominio.retornoliquidacao.SituacaoRetornoLiquidacao;

public class RetornosLiquidacaoRepository extends AbstractRepository<RetornoLiquidacao>{

	public RetornosLiquidacaoRepository(Session session) {
		super(session);
	}

	@Override
	public List<RetornoLiquidacao> listarPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetornoLiquidacao> consultarQuantidadeArquivosProcessadosNoDia(LocalDate data) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append("select a from retornoliquidacao a ")
			.append(" inner join a.logEnvioRetornoLiquidacao l on a.logEnvioRetornoLiquidacao.id = l.id ")
			.append(" where l.dataRetorno = :dataRetorno ")
			.append(" and l.situacao = :situacao");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dataRetorno", data);
		parametros.put("situacao", SituacaoRetornoLiquidacao.ARQUIVO_PROCESSADO_SUCESSO);
		List<RetornoLiquidacao> retornos = select(jpql.toString(), parametros);
		
		return retornos;
	}

	public RetornoLiquidacao consultarRetornoLiquidacaoCedenteConta(int codigoCedente, int numeroConta, LocalDate data) {
		try {
			StringBuilder jpql = new StringBuilder(); 
			jpql.append("select a from retornoliquidacao a ")
				.append(" inner join a.logEnvioRetornoLiquidacao l on a.logEnvioRetornoLiquidacao.id = l.id ")
				.append(" inner join a.cedente c on a.cedente.id = c.id ")
				.append(" where l.dataRetorno = :dataRetorno ")
				.append(" and a.numeroConta = :numeroConta ")
				.append(" and c.codigo = :codigoCedente ");
			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataRetorno", data);
			parametros.put("numeroConta", numeroConta);
			parametros.put("codigoCedente", codigoCedente);
			
			List<RetornoLiquidacao> retornos = select(jpql.toString(), parametros);
			if (!retornos.isEmpty()) {
				return retornos.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<RetornoLiquidacao> consultarRetornosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		try {
			StringBuilder jpql = new StringBuilder(); 
			jpql.append("select a from retornoliquidacao a ")
				.append(" inner join a.logEnvioRetornoLiquidacao l on a.logEnvioRetornoLiquidacao.id = l.id ")
				.append(" inner join a.cedente c on a.cedente.id = c.id ")
				.append(" where l.dataRetorno between :dataInicial and :dataFinal ");
			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataInicial", dataInicial);
			parametros.put("dataFinal", dataFinal);
			
			List<RetornoLiquidacao> retornos = select(jpql.toString(), parametros);
			return retornos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<RetornoLiquidacao> listarPorCodigoCedente(Integer codigoCedente) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append("select a from retornoliquidacao a ")
			.append(" inner join a.logEnvioRetornoLiquidacao l on a.logEnvioRetornoLiquidacao.id = l.id ")
			.append(" inner join a.cedente c on a.cedente.id = c.id ")
			.append(" where c.codigo = :codigoCedente ");
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigoCedente", codigoCedente);
		
		List<RetornoLiquidacao> retornos = select(jpql.toString(), parametros);
		return retornos;
	}
}
