package br.com.netsuprema.dominio.remessa;

import java.io.File;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

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
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.repository.RemessaRepository;

@Entity(name="remessa")
public class Remessa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
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

	public long processar(Session session) throws RemoteException {
		Solicitacao solicitacao = enviarRemessa();
		long id = salvarDadosLogEnvio(solicitacao, session);
		return id;
	}

	private long salvarDadosLogEnvio(Solicitacao solicitacao, Session session){
		nomeDoArquivo = file.getName();
		log = criarLogEnvioRemessa(solicitacao);
		long id = 0;
		try {
			new RemessaRepository(session).salvar(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	private LogEnvioRemessa criarLogEnvioRemessa(Solicitacao solicitacao){
		LogEnvioRemessa log = new LogEnvioRemessa();
		log.setCodigoLoteAgendamento(solicitacao.getCODIGO_LOTE_AGENDAMENTO());
		
		try {
			log.setDataHoraEnvio(DateUtils.converterStringDateTimeParaLocalDateTime(solicitacao.getDATETIME()));
		} catch (Exception e) {
			log.setDataHoraEnvio(LocalDateTime.now());
			// TODO: Realizar o lancamento de log com o logger
		}
		
		log.setDetalheErro(solicitacao.getDETALHE_ERRO());
		log.setSituacao(solicitacao.getSITUACAO());
		return log;
	}

	private Solicitacao enviarRemessa() throws RemoteException {
		Servico servico = criarDadosEnvioRemessa();
		Solicitacao solicitacao = criarSolicitacaoWebService();
		
		RegistrosDoWSDLPortTypeProxy registro = new RegistrosDoWSDLPortTypeProxy();
		solicitacao = registro.REMESSA_TITULO(servico);
		return solicitacao;
	}

	private Servico criarDadosEnvioRemessa() {
		NetSupremaRemessa.server.Cedente cedente = criarCedenteWebService();
		Autenticacao autenticacao = criarAutenticacaoWebService();
		Dados dados = criarDadosWebService();
		Solicitacao solicitacao = criarSolicitacaoWebService();
		Servico servico = criarServicoWebService(cedente, autenticacao, dados, solicitacao);
		return servico;
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

	private Dados criarDadosWebService() {
		Dados dados = new Dados();
		dados.setEMAIL_NOTIFICACAO("pabloohenrique32@gmail.com");
		dados.setFORMATO("1");
		dados.setNOME_ARQUIVO("Arquivo");
		dados.setREMESSA("Rapaz como isso e somente um teste entao vou mandar esse texto aqui mesmo");
		return dados;
	}

	private Autenticacao criarAutenticacaoWebService() {
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setUSUARIO("BELOMONTE_WEBSERVICE");
		autenticacao.setSENHA_MD5("28a4fb4c09237e95f95312a0409f499e");
		return autenticacao;
	}

	private NetSupremaRemessa.server.Cedente criarCedenteWebService() {
		NetSupremaRemessa.server.Cedente cedenteWs = new NetSupremaRemessa.server.Cedente();
		cedenteWs.setCODIGO_CEDENTE("10971");
		cedenteWs.setCODIGO_CEDENTE_DV("1");
		cedenteWs.setCONTA_CORRENTE("51557");
		cedenteWs.setCONTA_CORRENTE_DV("4");
		return cedenteWs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

