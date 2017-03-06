package br.com.netsuprema.view;

import br.com.netsuprema.MainApp;
import br.com.netsuprema.controller.MainAppController;
import javafx.fxml.FXML;

public class AbstractController {
	protected MainApp mainApp;
	
	@FXML
	public void handleVoltar(){
		MainAppController controller = getMainApp().getController();
		controller.showMenuPrincipal(getMainApp(), getMainApp().getRootLayout());
	}
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
