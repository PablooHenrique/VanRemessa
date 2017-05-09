package br.com.netsuprema.application;

import br.com.netsuprema.dominio.parametros.SeparadorArquivos;
import br.com.netsuprema.service.parametros.SeparadorArquivosService;

public class SeparadorArquivosApplication {

	public void salvarSeparadorArquivo(String diretorio, boolean ativarSeparador) throws Exception {
		SeparadorArquivosService service = new SeparadorArquivosService();
		service.salvarSeparadorArquivo(diretorio, ativarSeparador);
	}

	public SeparadorArquivos listarSeparadorArquivo() throws Exception {
		SeparadorArquivosService service = new SeparadorArquivosService();
		SeparadorArquivos separador = service.carregarSeparadorDeArquivos();
		return separador;
	}
}
