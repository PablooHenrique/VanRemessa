package br.com.netsuprema.dominio.remessa;

import java.io.File;
import java.time.LocalDateTime;
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

import NetSupremaRemessa.server.Autenticacao;
import NetSupremaRemessa.server.Dados;
import NetSupremaRemessa.server.RegistrosDoWSDLPortTypeProxy;
import NetSupremaRemessa.server.Servico;
import NetSupremaRemessa.server.Solicitacao;
import br.com.netsuprema.dominio.DateUtils;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.repository.RemessaRepository;
import br.com.netsuprema.service.FileService;
import br.com.netsuprema.service.ScannerFilesThread;

@Entity(name="remessa")
public class Remessa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cedente_id", nullable=false)
	private Cedente cedente;
	
	@Transient
	private Parametros parametros;
	@Transient
	private File file;
	
	private String nomeDoArquivo;
	private int numeroConta;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = false)
	@JoinColumn(name="log_id", nullable=false)
	private LogEnvioRemessa log;
	
	public Remessa() {
		
	}

	public Remessa(Cedente cedente, int numeroConta, Parametros parametros, File file) {
		super();
		this.cedente = cedente;
		this.parametros = parametros;
		this.file = file;
		this.numeroConta = numeroConta;
	}

	public long processar(Session session) throws Exception{
		try {
			
			Solicitacao solicitacao = enviarRemessa();
			long id = salvarDadosLogEnvio(solicitacao, session);
			
			return id;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--Processar--");
			builder.append("Falha ao realizar o processamento. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao realizar o processamento. Motivo: " + e.getMessage());
			
		}
	}

	private long salvarDadosLogEnvio(Solicitacao solicitacao, Session session) throws Exception {
		try {
			
			nomeDoArquivo = file.getName();
			log = criarLogEnvioRemessa(solicitacao);
			new RemessaRepository(session).salvar(this);
			return this.id;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--salvarDadosLogEnvio--");
			builder.append("Falha ao salvar os dados do logEnvio. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Falha ao salvar os dados do logEnvio. Motivo: " + e.getMessage());
			
		}
	}

	private LogEnvioRemessa criarLogEnvioRemessa(Solicitacao solicitacao){
		LogEnvioRemessa log = new LogEnvioRemessa();
		log.setCodigoLoteAgendamento(solicitacao.getCODIGO_LOTE_AGENDAMENTO());
		
		try {
			log.setDataHoraEnvio(DateUtils.converterStringDateTimeParaLocalDateTime(solicitacao.getDATETIME()));
		} catch (Exception e) {
			log.setDataHoraEnvio(LocalDateTime.now());
		}
		
		log.setDetalheErro(solicitacao.getDETALHE_ERRO());
		log.setSituacao(solicitacao.getSITUACAO());
		return log;
	}

	private Solicitacao enviarRemessa() throws Exception{
		try {
			
			Servico servico = criarDadosEnvioRemessa();
			Solicitacao solicitacao = criarSolicitacaoWebService();
			
			RegistrosDoWSDLPortTypeProxy registro = new RegistrosDoWSDLPortTypeProxy();
			solicitacao = registro.REMESSA_TITULO(servico);
			
			return solicitacao;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--enviarRemessa--");
			builder.append("Erro ao a remessa. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Erro ao a remessa. Motivo: " + e.getMessage());
		}
	}

	private Servico criarDadosEnvioRemessa() throws Exception{
		try {
			
			NetSupremaRemessa.server.Cedente cedente = criarCedenteWebService();
			Autenticacao autenticacao = criarAutenticacaoWebService();
			Dados dados = criarDadosWebService();
			Solicitacao solicitacao = criarSolicitacaoWebService();
			Servico servico = criarServicoWebService(cedente, autenticacao, dados, solicitacao);
			
			return servico;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--criarDadosEnvioRemessa--");
			builder.append("Erro ao criar os dados de envio de remessa. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Erro ao criar os dados de envio de remessa. Motivo: " + e.getMessage());
		}
	}

	private Servico criarServicoWebService(NetSupremaRemessa.server.Cedente cedente,
										   Autenticacao autenticacao,
										   Dados dados, Solicitacao solicitacao) {
		Servico servico = new Servico();
		
		servico.setCedente(cedente);
		servico.setAutenticacao(autenticacao);
		servico.setDados(dados);
		servico.setSolicitacao(solicitacao);
		return servico;
	}

	
	/**
	 * O serviço me obriga a criar a solicitação e passar como parametro,
	 * mas esse objeto nao e utilizado na requisição servindo apenas como objeto de retorno.
	 */
	
	private Solicitacao criarSolicitacaoWebService() {
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setCODIGO_LOTE_AGENDAMENTO("");
		solicitacao.setDATETIME("");
		solicitacao.setDETALHE_ERRO("");
		solicitacao.setSITUACAO("");
		return solicitacao;
	}

	private Dados criarDadosWebService() throws Exception{
		try {
			
			Dados dados = new Dados();
			
			dados.setEMAIL_NOTIFICACAO(this.parametros.getEmail());
			dados.setFORMATO(String.valueOf(this.parametros.getFormatoRemessa().getCodigo()));
			dados.setNOME_ARQUIVO(this.file.getName());
			dados.setREMESSA(obterConteudoArquivo());
			
			return dados;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--criarDadosWebService--");
			builder.append("Erro ao criar os dados do webService. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Erro ao criar os dados do webService. Motivo: " + e.getMessage());
		}
	}

	private String obterConteudoArquivo() throws Exception{
		try {
			
			FileService service = new FileService();
			String conteudo;
			conteudo = service.obterConteudoDoArquivo(this.file);
			
			return conteudo;
			
		} catch (Exception e) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("--obterConteudoArquivo--");
			builder.append("Erro ao tentar obter o conteudo do arquivo. Motivo: " + e.getMessage());
			builder.append("Causa: " + e.getCause().getMessage());
			
			ScannerFilesThread.logErros.add(builder.toString());
			
			throw new Exception("Erro ao tentar obter o conteudo do arquivo. Motivo: " + e.getMessage());
		}
	}

	private Autenticacao criarAutenticacaoWebService(){
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setUSUARIO(this.parametros.getLogin());
		autenticacao.setSENHA_MD5(this.parametros.getSenha());
		return autenticacao;
	}

	private NetSupremaRemessa.server.Cedente criarCedenteWebService() {
		NetSupremaRemessa.server.Cedente cedenteWs = new NetSupremaRemessa.server.Cedente();
		
		cedenteWs.setCODIGO_CEDENTE(String.valueOf(this.cedente.getCodigo()));
		cedenteWs.setCODIGO_CEDENTE_DV(String.valueOf(this.cedente.getDigitoVerificador()));
		cedenteWs.setCONTA_CORRENTE(String.valueOf(obterConta().getNumeroConta()));
		cedenteWs.setCONTA_CORRENTE_DV(String.valueOf(obterConta().getDigitoVerificador()));
		return cedenteWs;
	}
	
	private Conta obterConta() {
		List<Conta> contas = cedente.getContas().stream().filter(x->x.getNumeroConta() == this.numeroConta).collect(Collectors.toList());
		if (!contas.isEmpty()) {
			return contas.get(0);
		}
		return null;
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

	public Parametros getParametros() {
		return parametros;
	}

	public void setParametros(Parametros parametros) {
		this.parametros = parametros;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public LogEnvioRemessa getLog() {
		return log;
	}

	public void setLog(LogEnvioRemessa log) {
		this.log = log;
	}
}

