package br.com.netsuprema;

import java.io.IOException;

import br.com.netsuprema.controller.MainAppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MainAppController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.setController(new MainAppController());
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Van SIG Cobrança - Envio de Remessa");
		this.primaryStage.setResizable(false);
		this.primaryStage.setWidth(1024);
		this.primaryStage.setHeight(670);
		
		initRootLayout();
		getController().showMenuPrincipal(this, getRootLayout());
	}
	
	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			this.setRootLayout((BorderPane) loader.load());
			
			Scene scene = new Scene(getRootLayout());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public MainAppController getController() {
		return controller;
	}

	public void setController(MainAppController controller) {
		this.controller = controller;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
}
