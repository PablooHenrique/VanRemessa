package br.com.netsuprema.service.parametros;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

public class ConfiguracoesGeraisProjetoProcessingWatcherThread implements Runnable{

	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					if(!new ConfiguracoesGeraisProjetoService().rotinaEstaAtualizada()){
						
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}
	
}
