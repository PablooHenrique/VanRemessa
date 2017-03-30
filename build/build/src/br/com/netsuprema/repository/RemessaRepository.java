package br.com.netsuprema.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.dominio.remessa.StatusProcessamento;

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

	public List<Remessa> listarRemessasComAguardoProcessamentoLocal() {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook and l.statusProcessamento is null ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}
	
	public List<Remessa> listarRemessasAguardandoProcessamentoServidor(){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where l.statusProcessamento = :statusprocessamentopendente ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("statusprocessamentopendente", StatusProcessamento.AGURDANDO_PROCESSAMENTO_PROCESSAMENTO);
		List<Remessa> remessas = select(jpql.toString(), parametros);
		return remessas;
	}
	
	public List<Remessa> listarRemessasAguardandoProcessamentoServidorPorData(LocalDate dataInicial, LocalDate dataFinal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where l.statusProcessamento = :statusprocessamentopendente ")
			.append("and l.dataHoraEnvio between :dataInicial and :dataFinal ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("statusprocessamentopendente", StatusProcessamento.AGURDANDO_PROCESSAMENTO_PROCESSAMENTO);
		parametros.put("dataInicial", dataInicial.atStartOfDay());
		parametros.put("dataFinal", dataFinal.atStartOfDay());
		
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}
	
	public List<Remessa> listarRemessasProcessadasComSucesso(){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook and l.statusProcessamento = :statusprocessamento ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		parametros.put("statusprocessamento", StatusProcessamento.IMPORTACAO_REALIZADA_COM_SUCESSO);
		List<Remessa> remessas = select(jpql.toString(), parametros);
		return remessas;
	}
	
	public List<Remessa> listarRemessasProcessadasComSucessoPorData(LocalDate dataInicial, LocalDate dataFinal){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook and l.statusProcessamento = :statusprocessamento ")
			.append("and l.dataHoraEnvio between :dataInicial and :dataFinal ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		parametros.put("statusprocessamento", StatusProcessamento.IMPORTACAO_REALIZADA_COM_SUCESSO);
		parametros.put("dataInicial", dataInicial.atStartOfDay());
		parametros.put("dataFinal", dataFinal.atStartOfDay());
		List<Remessa> remessas = select(jpql.toString(), parametros);
		return remessas;
	}
	
	public List<Remessa> listarRemessasProcessadasComErro() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where l.statusProcessamento is not null and l.statusProcessamento <> :statusprocessamento ")
			.append("and l.statusProcessamento <> :statusprocessamentopendente ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("statusprocessamento", StatusProcessamento.IMPORTACAO_REALIZADA_COM_SUCESSO);
		parametros.put("statusprocessamentopendente", StatusProcessamento.AGURDANDO_PROCESSAMENTO_PROCESSAMENTO);
		List<Remessa> remessas = select(jpql.toString(), parametros);
		return remessas;
	}
	
	
	public List<Remessa> listarRemessasProcessadasComErroPorData(LocalDate dataInicial, LocalDate dataFinal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where l.statusProcessamento is not null and l.statusProcessamento <> :statusprocessamento ")
			.append("and l.statusProcessamento <> :statusprocessamentopendente ")
			.append("and l.dataHoraEnvio between :dataInicial and :dataFinal ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("statusprocessamento", StatusProcessamento.IMPORTACAO_REALIZADA_COM_SUCESSO);
		parametros.put("statusprocessamentopendente", StatusProcessamento.AGURDANDO_PROCESSAMENTO_PROCESSAMENTO);
		parametros.put("dataInicial", dataInicial.atStartOfDay());
		parametros.put("dataFinal", dataFinal.atStartOfDay());
		List<Remessa> remessas = select(jpql.toString(), parametros);
		return remessas;
	}
	
	public List<Remessa> listarRemessasEnviadas() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}

	public List<Remessa> listarRemessasEnviadasPorData(LocalDate dataInicial, LocalDate dataFinal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("where UPPER(l.situacao) like :situacaook ")
			.append(" and l.dataHoraEnvio between :dataInicial and :dataFinal ")
			.append("order by l.dataHoraEnvio desc");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("situacaook", "%"+"OK"+"%");
		parametros.put("dataInicial", dataInicial.atStartOfDay());
		parametros.put("dataFinal", dataFinal.atStartOfDay());
		
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}

	public List<Remessa> listarTodaRemessasPorParametros(LocalDate dataInicial, LocalDate dataFinal, Integer codigoCedente, Integer codigoEnvio, Integer codigoConta, StatusProcessamento status){
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("select a from remessa a ")
			.append("inner join a.log l on a.log.id = l.id ")
			.append("inner join a.cedente b on a.cedente.id = b.id ")
			.append("where 1=1 ");
			
		if ((dataFinal != null) && (dataFinal != null)) {
			jpql.append(" and l.dataHoraEnvio between :dataInicial and :dataFinal ");
			parametros.put("dataInicial", dataInicial.atStartOfDay());
			parametros.put("dataFinal", dataFinal.atStartOfDay());
		}
		
		if (codigoCedente > 0) {
			jpql.append("and b.codigo = :codigo");
			parametros.put("codigo", codigoCedente);
		}
		
		
		if (codigoEnvio > 0 ) {
			jpql.append(" and l.codigoLoteAgendamento = :codigoLoteAgendamento ");
			parametros.put("codigoLoteAgendamento", String.valueOf(codigoEnvio));
		}
		
		if (codigoConta > 0 ) {
			jpql.append(" and a.numeroConta = :numeroConta ");
			parametros.put("numeroConta", codigoConta);
		}
		
		if (status != null) {
			jpql.append(" and l.statusProcessamento = :statusProcessamento ");
			parametros.put("statusProcessamento", status);
		}
		
		List<Remessa> remessas = select(jpql.toString(), parametros);
		
		return remessas;
	}
}
