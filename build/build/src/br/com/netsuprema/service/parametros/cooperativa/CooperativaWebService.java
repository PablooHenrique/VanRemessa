package br.com.netsuprema.service.parametros.cooperativa;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.netsuprema.dominio.parametros.Cooperativa;

public class CooperativaWebService {
	
//	private final String Url = "http://sig5.netsuprema.com.br/col_cobrancateste/url_webservice.php";
	private final String Url = "http://sig5.netsuprema.com.br/col_cobrancateste/cooperativa_webservice_remessa.php";
	
	public List<Cooperativa> obterCooperativas() throws URISyntaxException, JSONException{
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>(); 
		String json = getCoopetativas();
		if (!json.isEmpty()) {
			cooperativas = converterJsonParaListaCooperativa(json);
		}
		
		return cooperativas;
	}
	
	public String getCoopetativas() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(getUrl());
		ResponseEntity<String> cooperativas = restTemplate.getForEntity(uri, String.class);
		return cooperativas.getBody();
	}
	
	public List<Cooperativa> converterJsonParaListaCooperativa(String json) throws JSONException{
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>();
		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			Cooperativa cooperativa = converterJsonParaCooperativa(jsonArray.getString(i));
			cooperativas.add(cooperativa);
		}
		return cooperativas;
	}
	
	public Cooperativa converterJsonParaCooperativa(String json){
		return new Gson().fromJson(json, Cooperativa.class);
	}

	public String getUrl() {
		return Url;
	}
}
