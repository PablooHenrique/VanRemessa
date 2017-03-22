package br.com.netsuprema.service;

import java.util.Timer;
import java.util.TimerTask;

public class ScannerFilesThread implements Runnable{
	
	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println("Vai Iniciar o processamento");
					new RemessasDeTitulosService().processar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}
}
