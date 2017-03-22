package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.controller.MainAppController;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipalController extends AbstractController{
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private JFXButton btnConfiguracoes;
	@FXML
	private JFXButton btnResumoEnvio;
	@FXML
	private JFXButton btnEnviosDetalhados;
	@FXML
	private ImageView imgLogo;
	
	@FXML
	private void initialize(){
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
	}
	
	public void handleShowConfiguracoesServico(){
		MainAppController controller = getMainApp().getController();
		controller.showConfiguracoesServico(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowResumoEnvio(){
		MainAppController controller = getMainApp().getController();
		controller.showResumoEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowEnviosDetalhados(){
		MainAppController controller = getMainApp().getController();
		controller.showEnviosDetalhados(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowDiretoriosEnvio(){
		MainAppController controller = getMainApp().getController();
		controller.showDiretoriosEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowStatusServico(){
		MainAppController controller = getMainApp().getController();
		controller.showStatusServico(getMainApp(), getMainApp().getRootLayout());
	}
}
