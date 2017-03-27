package br.com.netsuprema.application;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.netsuprema.application.dto.RemessaDto;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.service.RemessasDeTitulosService;

public class RemessasApplication {

	public List<RemessaDto> listar() {
		
		ModelMapper mapper = new ModelMapper();

		List<RemessaDto> remessasDto = new ArrayList<RemessaDto>();
		List<Remessa> remessas 		 = new RemessasDeTitulosService().listar();
		
		for (Remessa remessa : remessas) {
			remessasDto.add(mapper.map(remessa, RemessaDto.class));
		}
		
		return remessasDto;
	}

	public RemessaDto listar(int id) {
		ModelMapper mapper = new ModelMapper();
		
		Remessa remessa = new RemessasDeTitulosService().listar(id);
		RemessaDto remessaDto = mapper.map(remessa, RemessaDto.class);
		
		return remessaDto;
	}
}
