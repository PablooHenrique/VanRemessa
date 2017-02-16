package br.com.netsuprema.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.netsuprema.dominio.Cooperativa;

public class CooperativaService {
	private final String cooperativas = "[{ "+  
											"'nome':'Credipar',"+
											"'webservice':'https://sig.netsuprema.com.br/col_credipar/?KMP=COL&KMF=webservice_rest',"+
											"'webservice_crm':'',"+
											"'imagem':'https://sig.netsuprema.com.br/col_credipar/logo_mobile.png',"+
											"'key':4"+
   											"},"+
										 "{ "+  
											"'nome':'Credipar',"+
											"'webservice':'https://sig.netsuprema.com.br/col_credipar/?KMP=COL&KMF=webservice_rest',"+
											"'webservice_crm':'',"+
											"'imagem':'https://sig.netsuprema.com.br/col_credipar/logo_mobile.png',"+
											"'key':4"+
											"}]";
	
	public String getCoopetativas(){
		return cooperativas;
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
	
	public static void main(String[] args) throws JSONException {
		CooperativaService service = new CooperativaService();
		List<Cooperativa> cooperativas = service.converterJsonParaListaCooperativa(service.cooperativas);
		cooperativas.stream().forEach(x-> System.out.println(x.getNome()));
	}
}
