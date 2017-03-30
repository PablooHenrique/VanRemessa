package br.com.netsuprema.application;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.service.cedente.CedenteService;
import br.com.netsuprema.service.cedente.DiretoriosEnvioService;
import br.com.netsuprema.view.utils.ViewUtils;

public class DiretorioEnvioApplication {

	public void criarCedente(CedenteDto cedenteDto) {
		CedenteService service = new CedenteService();
		
		ModelMapper mapper = new ModelMapper();
		Cedente cedente = mapper.map(cedenteDto, Cedente.class);
		
		try {
			service.criarCedente(cedente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<CedenteDto> listarCedentes() {
		ModelMapper mapper = new ModelMapper();
		
		List<CedenteDto> cedentesDto = new ArrayList<CedenteDto>(); 
		List<Cedente> cedentes 		 = new CedenteService().listar();
		
		for (Cedente cedente : cedentes) {
			
			cedentesDto.add(mapper.map(cedente, CedenteDto.class));
		}
		
		return cedentesDto;
	}

	public List<CedenteDto> listarCedentes(String nome) {
		ModelMapper mapper = new ModelMapper();
		
		List<CedenteDto> cedentesDto = new ArrayList<CedenteDto>(); 
		List<Cedente> cedentes 		 = new CedenteService().listarPorNome(nome);
		
		for (Cedente cedente : cedentes) {
			
			cedentesDto.add(mapper.map(cedente, CedenteDto.class));
		}
		
		return cedentesDto;
	}

	public CedenteDto listarCedentes(int codigo) {
		ModelMapper mapper = new ModelMapper();
		
		Cedente cedente = new CedenteService().listarPorCodigo(codigo);
		CedenteDto cedenteDto = mapper.map(cedente, CedenteDto.class);
		
		return cedenteDto;
	}

	public CedenteDto listarCedentes(Integer codigo, String nome) {
		ModelMapper mapper = new ModelMapper();
		
		Cedente cedente = new CedenteService().listarPorNomeECodigo(codigo, nome);
		CedenteDto cedenteDto = mapper.map(cedente, CedenteDto.class);
		
		return cedenteDto;
	}
	
	public void excluirCedente(Integer codigo) throws Exception {
		new CedenteService().excluirCedentePorCodigo(codigo);
	}

	public void abrirDiretorio(Integer codigo) {
		DiretoriosEnvioService service = new DiretoriosEnvioService();
		service.abrirDiretorio(codigo);
	}

	public boolean numeroContaExiste(Integer numeroConta) {
		try {
			boolean numeroContaExiste = new CedenteService().numeroContaExiste(numeroConta);
			return numeroContaExiste;
		} catch (Exception e) {
			ViewUtils.exibirMensagemAlerta("Erro", "Falha ao verifica se o numero da conta existe", e.getMessage());
			return true;
		}
	}
}
