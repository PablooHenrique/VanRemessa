package br.com.netsuprema.dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static LocalDateTime converterStringDateTimeParaLocalDateTime(String dateStr) throws Exception {
		try {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime date = LocalDateTime.parse(dateStr, pattern);
			return date;
		} catch (Exception e) {
			throw new Exception("Falha ao realizar a conversão de data Verifique o formato utilizado");
		}
	}	
}
