package br.com.netsuprema.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.netsuprema.service.cedente.DiretoriosEnvioService;

public class ScannerFilesThread implements Runnable{
	
	public static List<String> logErros = new ArrayList<String>();
	
	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					ScannerFilesThread.logErros.add("Vai Iniciar o processamento");
					new RemessasDeTitulosService().processar();
					
					File file = new File(DiretoriosEnvioService.DIRETORIO_PADRAO + "log.txt");
					FileWriter fileW = new FileWriter (file, true);
		            BufferedWriter buffW = new BufferedWriter (fileW);
		            buffW.newLine();
		            
		            logErros.stream().forEach(x-> {
						try {
							buffW.newLine();
							buffW.write(x);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
		            
		            buffW.close();
		            fileW.close();
		            
				} catch (Exception e) {
					ScannerFilesThread.logErros.add("Erro na thread msg: " + e.getMessage());
					ScannerFilesThread.logErros.add("Erro na thread causa: " + e.getCause().getMessage());
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}
}
