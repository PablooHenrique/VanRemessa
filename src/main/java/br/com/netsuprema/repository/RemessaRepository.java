package br.com.netsuprema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = Application.getInstance().getSessionFactory();
		Session session2 = sessionFactory.openSession();
		List<Remessa> listarRemessasProcessadasComSucesso = new RemessaRepository(session2).listarRemessasEnviadas();
		System.out.println("");
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
	
	
}
