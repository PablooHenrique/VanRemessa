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
	
	public List<Cooperativa> consultarCooperativas(){
		List<Cooperativa> cooperativas = new CooperativaService().consultarCooperativas();
		return cooperativas;
	}

	public void salvar(Parametros parametros) {
		new ParametrosService().salvar(parametros);
	}
	
	public Parametros consultarParametros(){
		List<Parametros> parametros = new ParametrosService().consultarParametros();
		if (!parametros.isEmpty()) {
			return parametros.get(0);
		}
		return null;
	}

	public void salvarCooperativa(Cooperativa cooperativa) {
		CooperativaService service = new CooperativaService();
		service.salvar(cooperativa);
	}

	public void salvarCooperativas(List<Cooperativa> cooperativas) {
		CooperativaService service = new CooperativaService();
		service.salvarListaCooperativas(cooperativas);
	}
}
