package br.com.netsuprema.service;

import java.util.Timer;
import java.util.TimerTask;

public class ProcessingWatcherThread implements Runnable{

	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 2000, 60000);
	}

}
