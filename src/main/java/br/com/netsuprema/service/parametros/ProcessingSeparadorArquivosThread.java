package br.com.netsuprema.service.parametros;

import java.util.Timer;
import java.util.TimerTask;

public class ProcessingSeparadorArquivosThread implements Runnable{

	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					new SeparadorArquivosService().processar();
				} catch (Exception e) {
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}
}
