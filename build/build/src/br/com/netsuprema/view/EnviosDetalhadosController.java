package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDrawer;

import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnviosDetalhadosController extends AbstractController{

	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ImageView imgResumo;
	
	public EnviosDetalhadosController() {
		
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
	}
	
	public void initializeComponents(){
		initializeComponenteBtnVoltar();
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
		imgResumo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO +"icone_resumo.png"));
	}

	private void initializeComponenteBtnVoltar() {
		String url = ConfigUtils.PATH_RESOURCE_PADRAO + "imagens/voltar.png";
		ImageView image = new ImageView(url);
		btnVoltar.setPrefHeight(49);
		btnVoltar.setPrefWidth(49);
		btnVoltar.setText("");
		btnVoltar.setGraphic(image);			
		btnVoltar.setButtonType(ButtonType.RAISED);
		btnVoltar.getStyleClass().add("floating");
	}
}
