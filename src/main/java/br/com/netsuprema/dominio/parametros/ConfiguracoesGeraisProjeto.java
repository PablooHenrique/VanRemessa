package br.com.netsuprema.dominio.parametros;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "configuracoesgeraisprojeto")
public class ConfiguracoesGeraisProjeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private LocalDateTime ultimaDataHoraEnvio;

	public LocalDateTime getUltimaDataHoraEnvio() {
		return ultimaDataHoraEnvio;
	}

	public void setUltimaDataHoraEnvio(LocalDateTime ultimaDataHoraEnvio) {
		this.ultimaDataHoraEnvio = ultimaDataHoraEnvio;
	}
}
