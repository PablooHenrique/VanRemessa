package br.com.netsuprema.service.retornos;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.DateUtils;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.retornoliquidacao.RetornoLiquidacao;
import br.com.netsuprema.dominio.retornoliquidacao.SituacaoRetornoLiquidacao;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.RetornosLiquidacaoRepository;
import br.com.netsuprema.service.cedente.CedenteService;
import br.com.netsuprema.service.parametros.ParametrosService;

public class RetornosLiquidacaoService {
	public void processar(){
		if (parametrosSaoValidosParaProcessamento()) {
			CedenteService service = new CedenteService();
			List<Cedente> cedentes = service.listar();
			
			ParametrosService parametrosService = new ParametrosService();
			List<Parametros> parametros = parametrosService.listarParametros();
			Parametros parametro = parametros.get(0);
			
			for (Cedente cedente : cedentes) {
				processarCedentes(cedente, parametro);
			}
		}
	}

	private boolean parametrosSaoValidosParaProcessamento() {
		ParametrosService parametrosService = new ParametrosService();
		List<Parametros> parametros = parametrosService.listarParametros();
		if (parametros.isEmpty()) {
			return false;
		}
		
		Parametros parametro = parametros.get(0);
		if (parametro.getDiretorioRetornos() == null || parametro.getDiretorioRetornos().isEmpty() || parametro.getFormatoRetornoLiquidacao() == null) {
			return false;
		}
		
		CedenteService service = new CedenteService();
		List<Cedente> cedentes = service.listar();
		if (cedentes.isEmpty()) {
			return false;
		}
		
		return true;
	}

	private void processarCedentes(Cedente cedente, Parametros parametro) {
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		for (Conta conta : cedente.getContas()) {
			try {
				try {
					
					session = factory.openSession();
					transaction = session.beginTransaction();
					processarConta(cedente, conta, parametro, session);
					transaction.commit();
					
				} catch (Exception e) {
					if ((session != null) && (session.isOpen())) {
						if (transaction.isActive()) {
							transaction.rollback();
						}
					}
					
					StringBuilder exception = new StringBuilder();
					exception.append("Erro ao realizar o processamento do cedente: " + cedente.getCodigo() +" - "+ cedente.getNome())
							 .append("Causa: " + e.getCause().getMessage())
							 .append("Mensagem: " + e.getMessage());
				}
			} finally {
				if ((session != null) && (session.isOpen())) {
					session.close();
				}
			}
		}
	}

	private void processarConta(Cedente cedente, Conta conta, Parametros parametro, Session session) throws Exception {
		try {
			
			RetornoLiquidacao retornoLiquidacao = new RetornoLiquidacao(cedente, conta.getNumeroConta(), parametro);
			
			RetornoLiquidacao ret = consultarRetornoLiquidacaoCedenteConta(cedente.getCodigo(), conta.getNumeroConta(), DateUtils.getUltimoDiaUtil(), session);
			if (ret!=null) {
				retornoLiquidacao = ret;
				retornoLiquidacao.setParametros(parametro);
			}
			
			retornoLiquidacao.processar(session);
			salvar(session, retornoLiquidacao);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private RetornoLiquidacao consultarRetornoLiquidacaoCedenteConta(int codigoCedente, int numeroConta, LocalDate data, Session session) {
		RetornosLiquidacaoRepository repository = new RetornosLiquidacaoRepository(session);
		RetornoLiquidacao retornoLiquidacao = repository.consultarRetornoLiquidacaoCedenteConta(codigoCedente, numeroConta, data);
		return retornoLiquidacao;
	}

	private void salvar(Session session, RetornoLiquidacao retornoLiquidacao) {
		new RetornosLiquidacaoRepository(session).salvar(retornoLiquidacao);
	}

	public Integer consultarQuantidadeArquivosRetornosProcessadosNoDia(){
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = factory.openSession();
		
		RetornosLiquidacaoRepository repository = new RetornosLiquidacaoRepository(session);
		List<RetornoLiquidacao> retornos = repository.consultarQuantidadeArquivosProcessadosNoDia(DateUtils.getUltimoDiaUtil());
		return retornos.size();
	}

	public List<RetornoLiquidacao> consultarRetornosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = null;
		try {
			try {
				session = factory.openSession();
				RetornosLiquidacaoRepository repository = new RetornosLiquidacaoRepository(session);
				List<RetornoLiquidacao> retornos = repository.consultarRetornosPorPeriodo(dataInicial, dataFinal);
				return retornos;
			} catch (Exception e) {
				throw e;
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

	public boolean processamentoDoDiaJaRealizado() {
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = null;
		try {
			try {
				session = factory.openSession();
				RetornosLiquidacaoRepository repository = new RetornosLiquidacaoRepository(session);
				List<RetornoLiquidacao> retornosPorPeriodo = repository.consultarRetornosPorPeriodo(DateUtils.getUltimoDiaUtil(), DateUtils.getUltimoDiaUtil());
				if (retornosPorPeriodo.isEmpty()) {
					return false;
				}else{
					if((retornosPorPeriodo.get(0).getLogEnvioRetornoLiquidacao().getSituacaoEnum() == SituacaoRetornoLiquidacao.ARQUIVO_PROCESSADO_SUCESSO)
						|| (retornosPorPeriodo.get(0).getLogEnvioRetornoLiquidacao().getSituacaoEnum() == SituacaoRetornoLiquidacao.SEM_RETORNO_PARA_CEDENTE)){
						return true;
					}
				}
				
				return false;
				
			} catch (Exception e) {
				throw e;
			}
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
}
