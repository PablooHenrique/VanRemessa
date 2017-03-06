package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiretoriosEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnExpandirFiltros;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ImageView imgDiretorioEnvio;
	@FXML
	private JFXButton btnAdicionarDiretorio;
	
	@FXML
	public void initialize() {
		initializeComponents();
		initializeComponenteBtnAdicionarDiretorio();
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
		imgDiretorioEnvio.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO +"icone_resumo.png"));
	}
	
	private void initializeComponenteBtnAdicionarDiretorio() {
		btnAdicionarDiretorio.setText("");
		Label label = new Label("+");
		btnAdicionarDiretorio.getStyleClass().add("floatingFiltros");
		btnAdicionarDiretorio.setGraphic(label);
		
		btnAdicionarDiretorio.setPrefHeight(60);
		btnAdicionarDiretorio.setPrefWidth(60);
		label.getStyleClass().add("floatingfontsize");
		btnAdicionarDiretorio.setButtonType(ButtonType.RAISED);
		btnAdicionarDiretorio.getStyleClass().add("animated-option-button");
	} 
	
	

}
