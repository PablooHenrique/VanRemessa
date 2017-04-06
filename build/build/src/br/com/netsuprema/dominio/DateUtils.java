package br.com.netsuprema.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	public static LocalDate converterStrinDateparaLocalDate(String dateStr) throws Exception {
		try {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(dateStr, pattern);
			return date;
		} catch (Exception e) {
			throw new Exception("Falha ao realizar a conversão de data Verifique o formato utilizado");
		}
	}
	
	public static String converterLocalDateParaString(LocalDate data){
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = data.format(pattern);
		return date;
	}
	
	public static String converterLocalDateTimeParaString(LocalDate data){
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String date = data.format(pattern);
		return date;
	}
	
	public static LocalDate getUltimoDiaUtil(){
		LocalDate dataAtual = LocalDate.now();
		 
		List<LocalDate> listaDosDiasUteisDoMes = obterListaDiasUtilsMes(dataAtual);
		if ( (dataAtual.isBefore(listaDosDiasUteisDoMes.get(0))) || (dataAtual.isEqual(listaDosDiasUteisDoMes.get(0)))) {
			
			int month = 0;
			int year = dataAtual.getYear();
			int day = dataAtual.getDayOfMonth();
			
			if (dataAtual.getMonthValue() == 1) {
				month = 12;
				year = year - 1;
			}else{
				month = dataAtual.getMonthValue() - 1;
			}
				
			dataAtual = LocalDate.of(year, month, day);
			listaDosDiasUteisDoMes = obterListaDiasUtilsMes(dataAtual);
			return listaDosDiasUteisDoMes.get(listaDosDiasUteisDoMes.size()-1);
		}
				
		for (int i = 0; i < listaDosDiasUteisDoMes.size(); i++) {
			if (listaDosDiasUteisDoMes.get(i).isEqual(dataAtual)) {
				return listaDosDiasUteisDoMes.get(i-1);
			}
		}
		
		return listaDosDiasUteisDoMes.get(listaDosDiasUteisDoMes.size()-1);
	}
	
	private static List<LocalDate> obterListaDiasUtilsMes(LocalDate dataRef){
		int ano = dataRef.getYear();
		int mes = dataRef.getMonthValue();
		
		YearMonth anoMes = YearMonth.of(ano, mes);
		 
		List<LocalDate> listaDosDiasUteisDoMes = new ArrayList<>();
		 
		for(int dia=1; dia <= anoMes.lengthOfMonth(); dia++){ 
			LocalDate data = anoMes.atDay(dia); 
			if(!data.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				listaDosDiasUteisDoMes.add(data);
			}
		}
		
		return listaDosDiasUteisDoMes;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.getUltimoDiaUtil());
	}
}
