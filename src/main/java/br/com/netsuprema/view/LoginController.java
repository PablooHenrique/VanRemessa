package br.com.netsuprema.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {
	
	@FXML
	private ImageView logo;
	
	public LoginController() {
		Image image = new Image("resources/logo_512.png");
		logo = new ImageView(image);
		logo.setImage(image);
	}
	
	@FXML
	private void initialize(){
		
	}
}
