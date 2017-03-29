package br.com.netsuprema;

import java.io.IOException;

import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.application.dto.RemessaDto;
import br.com.netsuprema.service.ProcessingWatcherThread;
import br.com.netsuprema.service.ScannerFilesThread;
import br.com.netsuprema.view.CadastroDiretorioEnvioController;
import br.com.netsuprema.view.ConfiguracoesServicoController;
import br.com.netsuprema.view.DetalhesRemessaController;
import br.com.netsuprema.view.DiretoriosEnvioController;
import br.com.netsuprema.view.EnviosDetalhadosController;
import br.com.netsuprema.view.MenuPrincipalController;
import br.com.netsuprema.view.ResumoEnvioController;
import br.com.netsuprema.view.StatusServicoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		startBanco();
		
		this.setPrimaryStage(primaryStage);
		this.getPrimaryStage().setTitle("Sistema SIG Cobrança - Envio de Remessa");
		this.getPrimaryStage().setResizable(false);
		this.getPrimaryStage().setWidth(1024);
		this.getPrimaryStage().setHeight(670);
		
		initRootLayout();
		
		showMenuPrincipal(this, getRootLayout());
		
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
			
			scene.getStylesheets().add(MainApp.class.getResource("")+"view/style.css");
			
			getPrimaryStage().setScene(scene);
			getPrimaryStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMenuPrincipal(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuPrincipal.fxml"));
			AnchorPane menuPrincipal = loader.load();
			
			rootLayout.setCenter(menuPrincipal);
			MenuPrincipalController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			System.out.println("Finalizou o menuPrincipal");
		} catch (IOException e) {
			System.out.println(e.getCause().getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void showConfiguracoesServico(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConfiguracoesServico.fxml"));
			AnchorPane configuracoesServico = loader.load();
			
			rootLayout.setCenter(configuracoesServico);
			ConfiguracoesServicoController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showResumoEnvio(MainApp mainApp, BorderPane rootLayout){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ResumoEnvio.fxml"));
			AnchorPane resumoEnvio = loader.load();
			
			rootLayout.setCenter(resumoEnvio);
			ResumoEnvioController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEnviosDetalhados(MainApp mainApp, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EnviosDetalhados.fxml"));
			AnchorPane enviosDetalhados = loader.load();
			
			rootLayout.setCenter(enviosDetalhados);
			EnviosDetalhadosController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDiretoriosEnvio(MainApp mainApp, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DiretoriosEnvio.fxml"));
			AnchorPane statusServico = loader.load();
			
			rootLayout.setCenter(statusServico);
			DiretoriosEnvioController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showStatusServico(MainApp mainApp, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StatusServico.fxml"));
			AnchorPane diretoriosEnvio = loader.load();
			
			rootLayout.setCenter(diretoriosEnvio);
			StatusServicoController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showCadastroDiretoriosEnvioDialog(MainApp mainApp, BorderPane rootLayout, CedenteDto cedente){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastroDiretorioEnvio.fxml"));
			AnchorPane cadastroDiretoriosEnvio = loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Cadastro Diretório");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(mainApp.getPrimaryStage());
	        dialogStage.setResizable(false);
	        Scene scene = new Scene(cadastroDiretoriosEnvio);
	        dialogStage.setScene(scene);
	        
	        CadastroDiretorioEnvioController controller = loader.getController();
	        controller.setMainApp(mainApp);
	        controller.setDialogStage(dialogStage);
	        controller.setCedente(cedente);
	        dialogStage.showAndWait();
	        
	        return controller.isSalvarCedente();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public void showCadastroDetalhesRemessaDialog(MainApp mainApp, BorderPane rootLayout, RemessaDto remessa) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DetalhesRemessa.fxml"));
			AnchorPane detalhesRemessa = loader.load();
			
			Stage dialogStage = new Stage();
	        dialogStage.setTitle("Detalhes da Remessa");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(mainApp.getPrimaryStage());
	        dialogStage.setResizable(false);
	        Scene scene = new Scene(detalhesRemessa);
	        dialogStage.setScene(scene);
	        
	        DetalhesRemessaController controller = loader.getController();
	        controller.setMainApp(mainApp);
	        controller.setRemessa(remessa);
	        controller.inicializarTables();
	        dialogStage.showAndWait();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
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
