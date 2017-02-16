package br.com.netsuprema.controller;

import java.io.IOException;

import br.com.netsuprema.MainApp;
import br.com.netsuprema.view.ConfiguracoesServicoController;
import br.com.netsuprema.view.MenuPrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainAppController {
	public void showMenuPrincipal(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppController.class.getResource("../view/MenuPrincipal.fxml"));
			AnchorPane menuPrincipal = loader.load();
			
			rootLayout.setCenter(menuPrincipal);
			MenuPrincipalController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showConfiguracoesServico(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppController.class.getResource("../view/ConfiguracoesServico.fxml"));
			AnchorPane configuracoesServico = loader.load();
			
			rootLayout.setCenter(configuracoesServico);
			ConfiguracoesServicoController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

