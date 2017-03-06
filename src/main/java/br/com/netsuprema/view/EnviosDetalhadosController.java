package br.com.netsuprema.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.utils.ConfigUtils;

import com.jfoenix.controls.JFXDrawer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EnviosDetalhadosController extends AbstractController{

	@FXML
	private JFXButton btnExpandirFiltrosPesquisa;
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
		initializeComponenteBtnExpandirFiltros();
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
	
	private void initializeComponenteBtnExpandirFiltros() {
		btnExpandirFiltrosPesquisa.setText("");
		Label label = new Label("+");
		btnExpandirFiltrosPesquisa.getStyleClass().add("floatingFiltros");
		btnExpandirFiltrosPesquisa.setGraphic(label);
		
		btnExpandirFiltrosPesquisa.setPrefHeight(60);
		btnExpandirFiltrosPesquisa.setPrefWidth(60);
		label.getStyleClass().add("floatingfontsize");
		btnExpandirFiltrosPesquisa.setButtonType(ButtonType.RAISED);
		btnExpandirFiltrosPesquisa.getStyleClass().add("animated-option-button");
	} 
	
	@FXML
	public void handleBtnExpandirFiltros(){
		try {
			AnchorPane panel = FXMLLoader.load(getClass().getResource("PanelFiltrosPesquisa.fxml"));
			drawer.setSidePane(panel);
		
			if (drawer.isShown()) {
				drawer.close();
				Label label = new Label("+");
				label.getStyleClass().add("floatingfontsize");
				btnExpandirFiltrosPesquisa.setGraphic(label);
			}else{
				drawer.open();
				Label label = new Label("-");
				label.getStyleClass().add("floatingfontsize");
				btnExpandirFiltrosPesquisa.setGraphic(label);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
