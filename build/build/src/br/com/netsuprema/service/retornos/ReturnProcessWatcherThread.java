package br.com.netsuprema.service.retornos;

import java.util.Timer;
import java.util.TimerTask;

public class ReturnProcessWatcherThread implements Runnable{

	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				try {
					
					RetornosLiquidacaoService service = new RetornosLiquidacaoService();
					if(!service.processamentoDoDiaJaRealizado()){
						service.processar();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}
}
