package br.com.netsuprema.application;

import java.util.List;

import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.service.parametros.ParametrosService;
import br.com.netsuprema.service.parametros.cooperativa.CooperativaService;

public class ParametrosApplication {
	
	public List<Cooperativa> consultarCooperativasWebService() throws Exception{
		try {
			return new ParametrosService().listarCooperativasWebService();
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao consultar as cooperativas no webservice.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw e;
		}
	}
	
	public Cooperativa consultarCooperativaPorKey(int key) throws Exception{
		try {
			return new ParametrosService().listarCooperativaPorKey(key);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao realizar a consulta de cooperativa por key.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
	}
	
	public List<Cooperativa> consultarCooperativas() throws Exception{
		try {
			return new CooperativaService().listarCooperativas();
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao consultar as cooperativas.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
	}

	public void salvar(Parametros parametros) throws Exception {
		try {
			new ParametrosService().salvar(parametros);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao salvar os dados dos parametros.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
	}
	
	public Parametros consultarParametros() throws Exception{
		try {
			List<Parametros> parametros = new ParametrosService().listarParametros();
			if (!parametros.isEmpty()) {
				return parametros.get(0);
			}
			return null;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao consultar os parametros.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw e;
		}
	}

	public void salvarCooperativa(Cooperativa cooperativa) throws Exception {
		try {
			ParametrosService service = new ParametrosService();
			service.salvarCooperativa(cooperativa);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao salvar os dados da cooperativa.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
		
		
	}

	public void salvarCooperativas(List<Cooperativa> cooperativas) throws Exception {
		try {
			ParametrosService service = new ParametrosService();
			service.salvarListaCooperativas(cooperativas);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao salvar os dados da lista de cooperativas.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
	}

	public void salvarDiretorioRetorno(String diretorioRetorno) throws Exception {
		try {
			new ParametrosService().salvarDiretorio(diretorioRetorno);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Erro ao realizar tentar salvar o diretório dos arquivos de retorno")
					 .append("Cause: " + e.getCause().getMessage())
					 .append("Mensagem: " + e.getMessage());
			
			throw e;
		}
	}

	public String consultarDiretorioRetornos() throws Exception {
		try {
			Parametros parametros;
			parametros = consultarParametros();
			
			if (parametros != null) {
				return parametros.getDiretorioRetornos();
			}else{
				return "";
			}
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Erro ao realizar tentar consultar o diretório dos arquivos de retorno")
					 .append("Cause: " + e.getCause().getMessage())
					 .append("Mensagem: " + e.getMessage());
			
			throw e;
		}
	}

	public void removerCooperativas() {
		ParametrosService service = new ParametrosService();
		service.removerCooperativas();
		
	}

	public boolean codigoCooperativaEValido(long codigo) {
		boolean result = new CooperativaService().codigoCooperativaEValido(codigo);
		return result;
	}
}
