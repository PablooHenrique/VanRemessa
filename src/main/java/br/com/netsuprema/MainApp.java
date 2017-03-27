package br.com.netsuprema;

import java.io.IOException;

import br.com.netsuprema.controller.MainAppController;
import br.com.netsuprema.service.ProcessingWatcherThread;
import br.com.netsuprema.service.ScannerFilesThread;
import br.com.netsuprema.utils.ConfigUtils;
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
		startBanco();
		this.setController(new MainAppController());
		
		this.setPrimaryStage(primaryStage);
		this.getPrimaryStage().setTitle("Sistema SIG Cobrança - Envio de Remessa");
		this.getPrimaryStage().setResizable(false);
		this.getPrimaryStage().setWidth(1024);
		this.getPrimaryStage().setHeight(670);
		
		initRootLayout();
		getController().showMenuPrincipal(this, getRootLayout());
		
		ScannerFilesThread instance = ScannerFilesThread.getInstance();
		instance.startProcessamento();	
		
		ProcessingWatcherThread processingWatcherThread = new ProcessingWatcherThread(); 
		Thread td = new Thread(processingWatcherThread);
		td.start();
	}
	
	private void startBanco() {
		br.com.netsuprema.repository.Application.getInstance().getSessionFactory();
	}

	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			this.setRootLayout((BorderPane) loader.load());
			
			Scene scene = new Scene(getRootLayout());
			scene.getStylesheets().add(ConfigUtils.PATH_RESOURCE_PADRAO+"css/style.css");
			getPrimaryStage().setScene(scene);
			getPrimaryStage().show();
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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
