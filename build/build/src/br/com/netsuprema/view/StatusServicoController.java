package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StatusServicoController extends AbstractController {

	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnInicializarServico;
	@FXML
	private JFXButton btnParaServico;
	@FXML
	private Label labelOnOff;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Pane paneOnOff;
	
	@FXML
	public void initialize() {
		initializeComponents();
		inicializarPanelOnOff();
	}
	
	public void initializeComponents(){
		inicializarBtnVoltar();
	}
	
	private void inicializarPanelOnOff(){
		paneOnOff.getStyleClass().add("floatingOff");
	}
	
	private void inicializarBtnVoltar() {
		String url = ConfigUtils.PATH_RESOURCE_PADRAO + "imagens/voltar.png";
		ImageView image = new ImageView(url);
		btnVoltar.setPrefHeight(49);
		btnVoltar.setPrefWidth(49);
		btnVoltar.setText("");
		btnVoltar.setGraphic(image);			
		btnVoltar.setButtonType(ButtonType.RAISED);
		btnVoltar.getStyleClass().add("floating");
		
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
	}
	
	public void handleInicializarServico(){
		paneOnOff.getStyleClass().remove("floatingOff");
		paneOnOff.getStyleClass().add("floatingOn");
		btnInicializarServico.setDisable(true);
		btnParaServico.setDisable(false);
		labelOnOff.setText("ON");
	}
	
	public void hancdlePararServico(){
		paneOnOff.getStyleClass().remove("floatingOn");
		paneOnOff.getStyleClass().add("floatingOff");
		btnParaServico.setDisable(true);
		btnInicializarServico.setDisable(false);
		labelOnOff.setText("OFF");
	}
}
