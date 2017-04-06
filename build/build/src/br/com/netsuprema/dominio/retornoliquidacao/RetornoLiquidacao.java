package br.com.netsuprema.dominio.retornoliquidacao;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.Session;

import NetSupremaSicoobRetorno.server.AutenticacaoRetorno;
import NetSupremaSicoobRetorno.server.RegistrosDoWSDLPortTypeProxy;
import NetSupremaSicoobRetorno.server.RespostaRetorno;
import NetSupremaSicoobRetorno.server.ServicoRetorno;
import br.com.netsuprema.dominio.DateUtils;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.service.FileService;

@Entity(name="retornoliquidacao")
public class RetornoLiquidacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cedente_id", nullable=false)
	private Cedente cedente;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = false)
	@JoinColumn(name="log_id", nullable=false)
	private LogEnvioRetornoLiquidacao logEnvioRetornoLiquidacao;
	
	private Integer numeroConta;
	
	@Transient
	private Parametros parametros;
	
	public RetornoLiquidacao() {
		super();
	}

	public RetornoLiquidacao(Cedente cedente, Integer numeroConta, Parametros parametros) {
		super();
		this.cedente = cedente;
		this.numeroConta = numeroConta;
		this.parametros = parametros;
		
		if (logEnvioRetornoLiquidacao == null) {
			logEnvioRetornoLiquidacao = new LogEnvioRetornoLiquidacao();
		}
	}

	public void processar(Session session) throws Exception{
		try {
			ServicoRetorno servico = new ServicoRetorno();
			
			servico.setAutenticacao(criarAutenticacao());
			servico.setSolicitacao(criarRespostaRetorno());
			
			RegistrosDoWSDLPortTypeProxy ws = new RegistrosDoWSDLPortTypeProxy();
			RespostaRetorno retorno = ws.RETORNO_FORMATO(servico);
			criarLog(retorno, session);
			if (retorno.getSITUACAO().equals("ok")) {
				criarArquivo(cedente, numeroConta, retorno.getRETORNO_ARQUIVO());
			}
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao realizar o processamento do webservice de consultas de retorno.")
					 .append("Causa: " + e.getCause().getMessage())
					 .append("Mensagem: " +e.getMessage());
			throw e;
		}
	}

	private void criarArquivo(Cedente cedente, int numeroConta, String conteudo) throws IOException{
		try {
			String nomeArquivo = cedente.getCodigo()+"-"+numeroConta;
			new FileService().salvarArquivoRetorno(parametros.getDiretorioRetornos(), nomeArquivo, conteudo);
		} catch (IOException e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao criar o arquivo de retorno")
					 .append("Causa: " + e.getCause().getMessage())
					 .append("Mensagem: "+ e.getMessage());
			throw e;
		}
	}

	private void criarLog(RespostaRetorno retorno, Session session) throws Exception {
		logEnvioRetornoLiquidacao.setDataHoraProcessamento(DateUtils.converterStringDateTimeParaLocalDateTime(retorno.getDATETIME()));
		logEnvioRetornoLiquidacao.setDetalheSituacao(retorno.getDETALHE_SITUACAO());
		logEnvioRetornoLiquidacao.setDataRetorno(DateUtils.converterStrinDateparaLocalDate(retorno.getDTRETORNO()));
		logEnvioRetornoLiquidacao.setSituacao(criarSituacaoRetorno(retorno.getSITUACAO()));
	}

	private SituacaoRetornoLiquidacao criarSituacaoRetorno(String codigoSituacao) {
		if (codigoSituacao.equals("ok")) {
			return SituacaoRetornoLiquidacao.ARQUIVO_PROCESSADO_SUCESSO;
		}
		
		Integer codigo = Integer.valueOf(codigoSituacao);
		
		List<SituacaoRetornoLiquidacao> situacoes = Arrays.asList(SituacaoRetornoLiquidacao.values());
		situacoes = situacoes.stream().filter(x->x.getCodigo() == codigo).collect(Collectors.toList());
		if (!situacoes.isEmpty()) {
			return situacoes.get(0);
		}
		return null;
	}

	private AutenticacaoRetorno criarAutenticacao() {
		AutenticacaoRetorno autenticacaoRetorno = new AutenticacaoRetorno();
		autenticacaoRetorno.setCODIGO_CEDENTE(String.valueOf(cedente.getCodigo()));
		autenticacaoRetorno.setCONTA_CORRENTE(String.valueOf(numeroConta));
		autenticacaoRetorno.setDTRETORNO(DateUtils.converterLocalDateParaString(DateUtils.getUltimoDiaUtil()));
		autenticacaoRetorno.setFORMATO(String.valueOf(parametros.getFormatoRetornoLiquidacao().getCodigo()));
		autenticacaoRetorno.setUSUARIO(parametros.getLogin());
		autenticacaoRetorno.setSENHA_MD5(parametros.getSenha());
		
		return autenticacaoRetorno;
	}

	private RespostaRetorno criarRespostaRetorno() {
		RespostaRetorno respostaRetorno = new RespostaRetorno("","","","","");
		return respostaRetorno;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cedente getCedente() {
		return cedente;
	}

	public void setCedente(Cedente cedente) {
		this.cedente = cedente;
	}

	public LogEnvioRetornoLiquidacao getLogEnvioRetornoLiquidacao() {
		return logEnvioRetornoLiquidacao;
	}

	public void setLogEnvioRetornoLiquidacao(LogEnvioRetornoLiquidacao logEnvioRetornoLiquidacao) {
		this.logEnvioRetornoLiquidacao = logEnvioRetornoLiquidacao;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Parametros getParametros() {
		return parametros;
	}

	public void setParametros(Parametros parametros) {
		this.parametros = parametros;
	}
}
