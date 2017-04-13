package br.com.netsuprema.dominio.parametros;

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

import br.com.netsuprema.dominio.Atualizacao;
import br.com.netsuprema.dominio.Versao;

@Entity(name = "configuracoesgeraisprojeto")
public class ConfiguracoesGeraisProjeto {
	
	@Transient
	private static final String VERSAO = "1.0.15";
	
	public static String getVersao(){
		return VERSAO;
	}
	
	@Transient
	private final String url = "http://192.168.7.205/geovany/aplication/sigvan_consultar_atualizacao_rotina.php?&versao=";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private LocalDateTime ultimaDataHoraEnvioRemessa;
	private LocalDateTime ultimaHoraValidacaoVersao;
	private LocalDateTime ultimaDataHoraProcessamentoRemessa;
	private LocalDateTime ultimaDataHoraEnvioRetorno;
	
	private boolean versaoEstaAtualizada;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "versaoSistema")
	private Versao versaoSistema;
	
	@Transient
	private Atualizacao autalizacao;
	
	public ConfiguracoesGeraisProjeto() {
		super();
		if (this.versaoSistema == null) {
			this.versaoSistema = new Versao(getVersao(), "");
		}
	}

	public void getAtualizacoesSistema() throws JSONException{
		System.out.println("");
		RestTemplate restTemplate = new RestTemplate();
		String url = getUrl() + getVersao();
		ResponseEntity<String> json = restTemplate.getForEntity(url, String.class);
		this.versaoEstaAtualizada = converterJsonPropertyVersaoDoSistemaEstaAtualizada(json.getBody());
		setAutalizacao(converterJsonParaAtualizacao(json.getBody()));
	}

	private Atualizacao converterJsonParaAtualizacao(String json) throws JSONException {
		Atualizacao atualizacao = new Gson().fromJson(json, Atualizacao.class);
		List<Versao> versoes = converterJsonParaVersoesLiberadas(json);
		Versao versao = converterJsonParaUltimaVersaoSistemaRemoto(json);
		atualizacao.setVersaoAtualSistemaRemoto(versao);
		atualizacao.setUltimasVersoesLiberadas(versoes);
		return atualizacao;
	}

	private Versao converterJsonParaUltimaVersaoSistemaRemoto(String json) throws JSONException {
		JSONObject jsonObject = new JSONObject(json);
		String ultimaVersaoSistema = jsonObject.getString("ultimaVersaoSistema");
		return new Versao(ultimaVersaoSistema, "");
	}
	
	private boolean converterJsonPropertyVersaoDoSistemaEstaAtualizada(String json) throws JSONException {
		JSONObject jsonObject = new JSONObject(json);
		String versaoEstaAtualizada = jsonObject.getString("versaoEstaAtualizada");
		if (versaoEstaAtualizada.toUpperCase().equals("FALSE")) {
			return false;
		}else{
			return true;
		}
	}

	private List<Versao> converterJsonParaVersoesLiberadas(String json) throws JSONException {
		
		List<Versao> versoes = new ArrayList<Versao>();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("ultimasVersoesLiberadas");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			versoes.add(converterJsonParaVersao(jsonArray.getString(i)));
		}
		
		return versoes;
	}

	private Versao converterJsonParaVersao(String json) {
		Versao versao = new Gson().fromJson(json, Versao.class);
		return versao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getUltimaDataHoraEnvioRemessa() {
		return ultimaDataHoraEnvioRemessa;
	}

	public void setUltimaDataHoraEnvioRemessa(LocalDateTime ultimaDataHoraEnvioRemessa) {
		this.ultimaDataHoraEnvioRemessa = ultimaDataHoraEnvioRemessa;
	}

	public LocalDateTime getUltimaHoraValidacaoVersao() {
		return ultimaHoraValidacaoVersao;
	}

	public void setUltimaHoraValidacaoVersao(LocalDateTime ultimaHoraValidacaoVersao) {
		this.ultimaHoraValidacaoVersao = ultimaHoraValidacaoVersao;
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

	public boolean isVersaoEstaAtualizada() {
		return versaoEstaAtualizada;
	}

	public void setVersaoEstaAtualizada(boolean versaoEstaAtualizada) {
		this.versaoEstaAtualizada = versaoEstaAtualizada;
	}

	public Versao getVersaoSistema() {
		return versaoSistema;
	}

	public void setVersaoSistema(Versao versaoSistema) {
		this.versaoSistema = versaoSistema;
	}

	public String getUrl() {
		return url;
	}

	public Atualizacao getAutalizacao() {
		return autalizacao;
	}

	public void setAutalizacao(Atualizacao autalizacao) {
		this.autalizacao = autalizacao;
	}
}
