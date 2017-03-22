package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResumoEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnExpandirFiltros;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ImageView imgResumo;

	public ResumoEnvioController(){

	}
	
	@FXML
	public void initialize() {
		initializeComponents();
	}
	
	public void initializeComponents(){
		String url = ConfigUtils.PATH_RESOURCE_PADRAO + "imagens/voltar.png";
		ImageView image = new ImageView(url);
		btnVoltar.setPrefHeight(49);
		btnVoltar.setPrefWidth(49);
		btnVoltar.setText("");
		btnVoltar.setGraphic(image);			
		btnVoltar.setButtonType(ButtonType.RAISED);
		btnVoltar.getStyleClass().add("floating");
		
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
		imgResumo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO +"icone_resumo.png"));
	}
}
