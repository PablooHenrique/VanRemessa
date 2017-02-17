package br.com.netsuprema.service.cooperativa;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.netsuprema.dominio.Cooperativa;

public class CooperativaWebService {
	
	private final String Url = "http://192.168.7.205/geovany/aplication/url_webservice.php";
	
	public List<Cooperativa> obterCooperativas() throws URISyntaxException, JSONException{
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>(); 
		String json = getCoopetativas();
		if (!json.isEmpty()) {
			cooperativas = converterJsonParaListaCooperativa(json);
		}
		
		return cooperativas;
	}
	
	private String getCoopetativas() throws URISyntaxException{
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
