package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.controller.MainAppController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipalController extends AbstractController{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private JFXButton btnConfiguracoes;
	
	@FXML
	private void initialize(){
		
	}
	
	public void handleShowConfiguracoesServico(){
		MainAppController controller = getMainApp().getController();
		controller.showConfiguracoesServico(getMainApp(), getMainApp().getRootLayout());
	}
}
