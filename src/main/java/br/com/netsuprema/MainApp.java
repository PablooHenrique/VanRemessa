package br.com.netsuprema;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.application.dto.RemessaDto;
import br.com.netsuprema.controller.MainAppController;
import br.com.netsuprema.view.CadastroDiretorioEnvioController;
import br.com.netsuprema.view.ConfiguracoesServicoController;
import br.com.netsuprema.view.DetalhesRemessaController;
import br.com.netsuprema.view.DiretoriosEnvioController;
import br.com.netsuprema.view.EnviosDetalhadosController;
import br.com.netsuprema.view.MenuPrincipalController;
import br.com.netsuprema.view.ResumoEnvioController;
import br.com.netsuprema.view.RetornosController;
import br.com.netsuprema.view.StatusServicoController;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application{
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private boolean firstTime;
    private TrayIcon trayIcon;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			startBanco();
			
			try {
				createTrayIcon(primaryStage);
				firstTime = true;
				Platform.setImplicitExit(false);
			} catch (Exception e) {
			}
		
			this.setPrimaryStage(primaryStage);
			this.getPrimaryStage().setTitle("Sistema SIG Cobrança - SigVan");
			this.getPrimaryStage().setResizable(false);
			this.getPrimaryStage().setWidth(1024);
			this.getPrimaryStage().setHeight(670);
			
			initRootLayout();
			
			MainAppController controller = new MainAppController();
			
			boolean threadsProcessadas = false;
			try {
				threadsProcessadas = controller.processarThreadsRotina();
			} catch (Exception e) {
				ViewUtils.exibirMensagemErro("Erro", "Falha ao acessar WebService", "Falha ao acessar o WebService de validação da versão, verifique sua conexão com a internet");
			}
			
			String msgBloqueio = "";
			if (!threadsProcessadas) {
				msgBloqueio = controller.getMsgErroProcessamento();
			}
			
			showMenuPrincipal(this, getRootLayout(), !threadsProcessadas, msgBloqueio);
			
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Falha no acesso ao banco", "Falha ao acessar o banco de dados, uma nova instancia do processo ja está iniciada");
		}
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
	
	public void showMenuPrincipal(MainApp mainApp, BorderPane rootLayout, boolean bloquearAplicacao, String msgBloqueio){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuPrincipal.fxml"));
			AnchorPane menuPrincipal = loader.load();
			
			rootLayout.setCenter(menuPrincipal);
			MenuPrincipalController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			if (bloquearAplicacao) {
				controller.bloquearAplicacao(msgBloqueio);
			}else{
				controller.inicializarConfigurações();
				
				controller.inicializarVerificacaoServicoEnvioRemessa();
				controller.inicializarVerificacaoServicoEnvioRetorno();
				controller.inicializarVerificacaoServicoProcessamentoRemessa();
			}
			
			controller.configuracoesGeraisProjetoProcessingWatcherThread();
			
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
	
	public void showRetornos(MainApp mainApp, BorderPane rootLayout2) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Retornos.fxml"));
			AnchorPane retornos = loader.load();
			
			rootLayout.setCenter(retornos);
			RetornosController controller = loader.getController();
			controller.setMainApp(mainApp);
		} catch (IOException e) {
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

	public void createTrayIcon(final Stage stage) {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            java.awt.Image image = null;
            try {
                URL url = new URL("file:resources/imagens/iconepng15px.png");
                image = ImageIO.read(url);
            } catch (IOException ex) {
            }


            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    hide(stage);
                }
            });
            // create a action listener to listen for default action executed on the tray icon
            final ActionListener closeListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            };

            ActionListener showListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.show();
                        }
                    });
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            /// ... add other items
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "SigVan", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(showListener);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        }
    }

    public void showProgramIsMinimizedMsg() {
        if (firstTime) {
            trayIcon.displayMessage("SigVan.",
                    "Processando Dados.",
                    TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }

    private void hide(final Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (SystemTray.isSupported()) {
                    stage.hide();
                    showProgramIsMinimizedMsg();
                } else {
                    System.exit(0);
                }
            }
        });
    }
}
