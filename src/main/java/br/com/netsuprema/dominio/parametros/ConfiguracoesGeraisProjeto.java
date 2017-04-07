package br.com.netsuprema.dominio.parametros;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import br.com.netsuprema.dominio.Versao;
import br.com.netsuprema.dominio.VersoesLiberadasParaAtualizacao;

@Entity(name = "configuracoesgeraisprojeto")
public class ConfiguracoesGeraisProjeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private LocalDateTime ultimaDataHoraEnvio;
	
	private final String url = "http://192.168.7.205/geovany/aplication/sigvan_consultar_atualizacao_rotina.php?&versao=1.0.13";
	
	@SerializedName("versoesLiberadasParaAtualizacao")
	private LocalDateTime ultimaHoraValidacaoVersao;
	private String versao;
	
	@Transient
	private String linkAtualizacao;
	
	public boolean rotinaEstaAtualizada(){
		return false;
	}
	
	public List<VersoesLiberadasParaAtualizacao> consultarVersoesServidor() throws URISyntaxException, JSONException{
		String atualizacoes = getAtualizacoes();
		Versao versao = converterJsonParaVersao(atualizacoes);
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
		return false;
	}

	public LocalDateTime getUltimaDataHoraEnvio() {
		return ultimaDataHoraEnvio;
	}

	public void setUltimaDataHoraEnvio(LocalDateTime ultimaDataHoraEnvio) {
		this.ultimaDataHoraEnvio = ultimaDataHoraEnvio;
	}

	public String getUrl() {
		return url;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getLinkAtualizacao() {
		return linkAtualizacao;
	}

	public void setLinkAtualizacao(String linkAtualizacao) {
		this.linkAtualizacao = linkAtualizacao;
	}

	public LocalDateTime getUltimaHoraValidacaoVersao() {
		return ultimaHoraValidacaoVersao;
	}

	public void setUltimaHoraValidacaoVersao(LocalDateTime ultimaHoraValidacaoVersao) {
		this.ultimaHoraValidacaoVersao = ultimaHoraValidacaoVersao;
	}
}
