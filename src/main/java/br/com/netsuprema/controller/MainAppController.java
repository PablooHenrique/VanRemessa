package br.com.netsuprema.controller;

import java.io.IOException;

import br.com.netsuprema.MainApp;
import br.com.netsuprema.view.ConfiguracoesServicoController;
import br.com.netsuprema.view.DiretoriosEnvioController;
import br.com.netsuprema.view.MenuPrincipalController;
import br.com.netsuprema.view.ResumoEnvioController;
import br.com.netsuprema.view.EnviosDetalhadosController;
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
	
	public void showResumoEnvio(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppController.class.getResource("../view/ResumoEnvio.fxml"));
			AnchorPane resumoEnvio = loader.load();
			
			rootLayout.setCenter(resumoEnvio);
			ResumoEnvioController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEnviosDetalhados(MainApp mainApp, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppController.class.getResource("../view/EnviosDetalhados.fxml"));
			AnchorPane enviosDetalhados = loader.load();
			
			rootLayout.setCenter(enviosDetalhados);
			EnviosDetalhadosController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDiretoriosEnvio(MainApp mainApp, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppController.class.getResource("../view/DiretoriosEnvio.fxml"));
			AnchorPane diretoriosEnvio = loader.load();
			
			rootLayout.setCenter(diretoriosEnvio);
			DiretoriosEnvioController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}












