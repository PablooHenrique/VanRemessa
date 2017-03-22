package br.com.netsuprema.testes.parametrosservico;

import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import br.com.netsuprema.dominio.enuns.FormatoRemessa;
import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.service.parametros.ParametrosService;
import br.com.netsuprema.service.parametros.cooperativa.CooperativaWebService;

public class TesteParametrosServico {
	
	@Test
	public void testeConsultarCooperativasViaWebService(){
		try {
			List<Cooperativa> cooperativas = new ParametrosService().listarCooperativasWebService();
			Assert.assertEquals(true, !cooperativas.isEmpty());
		} catch (Exception e) {
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testeConverterJsonCooperativasParaListaCooperativas(){
		try {
			CooperativaWebService cooperativaWebService = new CooperativaWebService();
			String json = cooperativaWebService.getCoopetativas();			
			List<Cooperativa> cooperativas = cooperativaWebService.converterJsonParaListaCooperativa(json);
			
			boolean result = verificarCampoVazioListaCooperativa(cooperativas);
			Assert.assertEquals(true, result);
		} catch (URISyntaxException | JSONException e) {
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void salvarParametros(){
		try {
			criarParametro();
			ParametrosService service = new ParametrosService();
			Assert.assertEquals(true, !service.listarParametros().isEmpty()); 
		} catch (URISyntaxException | JSONException e) {
			Assert.assertEquals(true, false);
		}
	}

	private void criarParametro() throws URISyntaxException, JSONException {
		Parametros parametros = new Parametros();

		ParametrosService service = new ParametrosService();
		
		criarCooperativa();
		Cooperativa cooperativa = obterCooperativa();
		
		if (cooperativa == null) {
			Assert.assertEquals(true, false);
		}
		
		parametros.setCooperativa(cooperativa);
		parametros.setEmail("pabloohenrique32@gmail.com");
		parametros.setFormatoRemessa(FormatoRemessa.REMESSA_CNAB_400);
		parametros.setLogin("pablohenrique");
		parametros.setSenha("net@123");
		
		service.salvar(parametros);
	}

	private void criarCooperativa() throws URISyntaxException, JSONException {
		ParametrosService service = new ParametrosService();
		List<Cooperativa> cooperativas = service.listarCooperativasWebService();
		service.salvarListaCooperativas(cooperativas);
	}

	private Cooperativa obterCooperativa() {
		List<Cooperativa> cooperativas = listarCooperativas();
		if (!cooperativas.isEmpty()) {
			return cooperativas.get(0);
		}
		return null;
	}

	private List<Cooperativa> listarCooperativas() {
		return new ParametrosService().listarCooperativas();
	}

	private boolean verificarCampoVazioListaCooperativa(List<Cooperativa> cooperativas) {
		for (Cooperativa cooperativa : cooperativas) {
			if (!verificarCampoVazioCooperativa(cooperativa)) {
				return false;
			}
		}
		return true;
	}

	private boolean verificarCampoVazioCooperativa(Cooperativa cooperativa) {
		if (cooperativa.getNome().trim().equals("")) {
			return false;
		}
		
		if (!(cooperativa.getKeyCop() > 0)) {
			return false;
		}
		
		if (cooperativa.getWebservice().trim().equals("")) {
			return false;
		}
		
		return true;
	}
}

