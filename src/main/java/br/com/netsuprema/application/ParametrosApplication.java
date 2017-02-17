package br.com.netsuprema.application;

import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;

import br.com.netsuprema.dominio.Cooperativa;
import br.com.netsuprema.dominio.Parametros;
import br.com.netsuprema.service.ParametrosService;
import br.com.netsuprema.service.cooperativa.CooperativaService;

public class ParametrosApplication {
	
	public List<Cooperativa> consultarCooperativasWebService() throws URISyntaxException, JSONException{
		return new CooperativaService().consultarCooperativasWebService();
	}
	
	public Cooperativa consultarCooperativaPorKey(int key){
		return new CooperativaService().consultarCooperativaPorKey(key);
	}

	public void salvar(Parametros parametros) {
		new ParametrosService().salvar(parametros);
	}
}
