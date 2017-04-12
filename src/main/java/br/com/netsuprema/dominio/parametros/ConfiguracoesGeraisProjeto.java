package br.com.netsuprema.dominio.parametros;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.netsuprema.dominio.Versao;
import br.com.netsuprema.dominio.VersoesLiberadasParaAtualizacao;

@Entity(name = "configuracoesgeraisprojeto")
public class ConfiguracoesGeraisProjeto {
	@Transient
	private final String url = "http://192.168.7.205/geovany/aplication/sigvan_consultar_atualizacao_rotina.php?&versao=1.0.13";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private LocalDateTime ultimaDataHoraEnvioRemessa;
	
	private LocalDateTime ultimaHoraValidacaoVersao;
	private boolean ultimoResultadoValidacaoVersao;
	
	private LocalDateTime ultimaDataHoraProcessamentoRemessa;
	private LocalDateTime ultimaDataHoraEnvioRetorno;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "versao")
	private Versao versao;
	
	public boolean rotinaEstaAtualizada(){
		return false;
	}
	
	public Versao consultarVersao() throws JSONException, URISyntaxException{
		String atualizacoes = getAtualizacoes();
		this.versao = converterJsonParaVersao(atualizacoes);
		return versao;
	}
	
	public List<VersoesLiberadasParaAtualizacao> consultarVersoesLiberadasDoServidor() throws URISyntaxException, JSONException{
		Versao versao = consultarVersao();
		return versao.getVersoesLiberadas();
	}

	private List<VersoesLiberadasParaAtualizacao> converterJsonParaListaVersoesLiberadas(String atualizacoes) throws JSONException {
		List<VersoesLiberadasParaAtualizacao> versoesLiberadasParaAtualizacaos = new ArrayList<VersoesLiberadasParaAtualizacao>();
		
		JSONObject jsonObject = new JSONObject(atualizacoes);
		JSONArray jsonArray = jsonObject.getJSONArray("versoesLiberadasParaAtualizacao");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			versoesLiberadasParaAtualizacaos.add(converterJsonParaVersoesLiberadas(jsonArray.getString(i)));
		}
		
		return versoesLiberadasParaAtualizacaos;
	}

	private Versao converterJsonParaVersao(String json) throws JSONException {
		Versao versao = new Gson().fromJson(json, Versao.class);
		List<VersoesLiberadasParaAtualizacao> versoesLiberadasParaAtualizacaos = converterJsonParaListaVersoesLiberadas(json);
		versao.setVersoesLiberadas(versoesLiberadasParaAtualizacaos);
		return versao;
	}

	private VersoesLiberadasParaAtualizacao converterJsonParaVersoesLiberadas(String json) {
		VersoesLiberadasParaAtualizacao versoesLiberadasParaAtualizacao = new Gson().fromJson(json, VersoesLiberadasParaAtualizacao.class);
		return versoesLiberadasParaAtualizacao;
	}

	private String getAtualizacoes() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(getUrl());
		ResponseEntity<String> atualizacao = restTemplate.getForEntity(uri, String.class);
		String atualizacoes = atualizacao.getBody();
		return atualizacoes;
	}
	
	public boolean acessoParaConsultaEstaLiberado(){
		if (ultimaHoraValidacaoVersao != null) {
			Duration duration;
			
			try {
				duration = Duration.between(this.ultimaHoraValidacaoVersao, LocalDateTime.now());
			} catch (Exception e) {
				return true;
			}
			
			long hours = duration.toHours();
			if (hours >= 1) {
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getUltimaHoraValidacaoVersao() {
		return ultimaHoraValidacaoVersao;
	}

	public void setUltimaHoraValidacaoVersao(LocalDateTime ultimaHoraValidacaoVersao) {
		this.ultimaHoraValidacaoVersao = ultimaHoraValidacaoVersao;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public void inicializarVersaoLiberada() {
		Versao versao = new Versao();
		versao.setVersaoEstaAtualizada(true);
		this.versao = versao;
	}

	public String carregarMensagem() {
		String numeroUltimaVersaoLiberada = this.versao.getVersoesLiberadas().get(0).getNumero();
		String linkPadraoDownloadVersao = this.versao.getLinkPadrao();
		String msg = "Sua versão do sistema esta desatualizada .\nAcesse o link: " + linkPadraoDownloadVersao + " \npara baixar a nova versão: " + numeroUltimaVersaoLiberada;
		
		return msg;
	}

	public LocalDateTime getUltimaDataHoraEnvioRemessa() {
		return ultimaDataHoraEnvioRemessa;
	}

	public void setUltimaDataHoraEnvioRemessa(LocalDateTime ultimaDataHoraEnvioRmessa) {
		this.ultimaDataHoraEnvioRemessa = ultimaDataHoraEnvioRmessa;
	}

	public LocalDateTime getUltimaDataHoraProcessamentoRemessa() {
		return ultimaDataHoraProcessamentoRemessa;
	}

	public void setUltimaDataHoraProcessamentoRemessa(LocalDateTime ultimaDataHoraProcessamentoRemessa) {
		this.ultimaDataHoraProcessamentoRemessa = ultimaDataHoraProcessamentoRemessa;
	}

	public LocalDateTime getUltimaDataHoraEnvioRetorno() {
		return ultimaDataHoraEnvioRetorno;
	}

	public void setUltimaDataHoraEnvioRetorno(LocalDateTime ultimaDataHoraEnvioRetorno) {
		this.ultimaDataHoraEnvioRetorno = ultimaDataHoraEnvioRetorno;
	}

	public boolean isUltimoResultadoValidacaoVersao() {
		return ultimoResultadoValidacaoVersao;
	}

	public void setUltimoResultadoValidacaoVersao(boolean ultimoResultadoValidacaoVersao) {
		this.ultimoResultadoValidacaoVersao = ultimoResultadoValidacaoVersao;
	}

	public void inicializarVersaoNaoLiberada() {
		Versao versao = new Versao();
		versao.setVersaoEstaAtualizada(false);
		this.versao = versao;
	}
}
