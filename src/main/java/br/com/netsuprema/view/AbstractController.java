package br.com.netsuprema.view;

import br.com.netsuprema.MainApp;

public class AbstractController {
	protected MainApp mainApp;

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
