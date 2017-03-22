package br.com.netsuprema.application;

import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;

import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.service.parametros.ParametrosService;
import br.com.netsuprema.service.parametros.cooperativa.CooperativaService;

public class ParametrosApplication {
	
	public List<Cooperativa> consultarCooperativasWebService() throws URISyntaxException, JSONException{
		return new ParametrosService().listarCooperativasWebService();
	}
	
	public Cooperativa consultarCooperativaPorKey(int key){
		return new ParametrosService().listarCooperativaPorKey(key);
	}
	
	public List<Cooperativa> consultarCooperativas(){
		return new CooperativaService().listarCooperativas();
	}

	public void salvar(Parametros parametros) {
		new ParametrosService().salvar(parametros);
	}
	
	public Parametros consultarParametros(){
		List<Parametros> parametros = new ParametrosService().listarParametros();
		if (!parametros.isEmpty()) {
			return parametros.get(0);
		}
		return null;
	}

	public void salvarCooperativa(Cooperativa cooperativa) {
		ParametrosService service = new ParametrosService();
		service.salvarCooperativa(cooperativa);
	}

	public void salvarCooperativas(List<Cooperativa> cooperativas) {
		ParametrosService service = new ParametrosService();
		service.salvarListaCooperativas(cooperativas);
	}
}
