package br.com.netsuprema.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class ScannerFilesThread implements Runnable{
	
	public static List<String> logErros = new ArrayList<String>();
	public static List<String> errosUsuario = new ArrayList<String>();
	private static ScannerFilesThread ScannerFilesThread;
	private Thread thread;
	
	public static synchronized ScannerFilesThread getInstance(){
		if (ScannerFilesThread == null) {
			ScannerFilesThread = new ScannerFilesThread();
		}
		
		return ScannerFilesThread;
	}
	
	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				logErros.clear();
				
				try {
					new ConfiguracoesGeraisProjetoService().atualizarDataHoraEnvioRemessa();
					logErros.add("Vai Iniciar o processamento");
					
					try {
						
						ConfiguracoesGeraisProjetoService config = new ConfiguracoesGeraisProjetoService();
						boolean rotinaEstaAtualizada = config.rotinaEstaAtualizada(true);
						
						if(rotinaEstaAtualizada){
							new RemessasDeTitulosService().processar();
						}else{
							logErros.add("Rotina n�o est� atualizada.");
						}
						
					} catch (Exception e) {
						logErros.add("Processamento Falhou");
					}
					
					try {
						FileService service = new FileService();
						service.salvarLog(logErros);
					} catch (Exception e) {
						logErros.add("Falha ao gravar o arquivo de log");
					}
		            
				} catch (Exception e) {
					logErros.add("Erro na thread msg: " + e.getMessage());
					logErros.add("Erro na thread causa: " + e.getCause().getMessage());
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}

	public void startProcessamento() {
		this.thread = new Thread(getInstance());
		this.thread.start();
	}
	
	public boolean isAlive(){
		if (this.thread != null) {
			return this.thread.isAlive();
		}else{
			return false;
		}
	}
}
