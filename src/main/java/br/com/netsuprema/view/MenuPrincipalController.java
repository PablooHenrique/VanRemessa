package br.com.netsuprema.view;

import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.application.ServicosApplication;
import br.com.netsuprema.controller.MainAppController;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MenuPrincipalController extends AbstractController{
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private JFXButton btnConfiguracoes;
	@FXML
	private JFXButton btnResumoEnvio;
	@FXML
	private JFXButton btnEnviosDetalhados;
	@FXML
	private JFXButton btnDiretoriosEnvio;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Pane paneOnOffStatusServicoRemessa;
	@FXML
	private Label labelMsg;
	
	
	@FXML
	private void initialize(){
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
		inicializarVerificacaoServicoRemessa();
		processingWhatcherThread();
	}
	
	private void inicializarVerificacaoServicoRemessa() {
		boolean status = new ServicosApplication().verificarServicoRemessa();
		if (status) {
			paneOnOffStatusServicoRemessa.getStyleClass().remove("floatingOff");
			paneOnOffStatusServicoRemessa.getStyleClass().add("floatingOn");
		}else{
			paneOnOffStatusServicoRemessa.getStyleClass().remove("floatingOn");
			paneOnOffStatusServicoRemessa.getStyleClass().add("floatingOff");
		}
	}
	
	private String verificarErrosProcessamento(){
		ServicosApplication servicosApplication = new ServicosApplication();
		String errosProcessamento = servicosApplication.obterErrosProcessamento();
		servicosApplication.limparErroProcessamento();
		
		return errosProcessamento;
		
	}
	
	public void handleShowConfiguracoesServico(){
		MainAppController controller = getMainApp().getController();
		controller.showConfiguracoesServico(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowResumoEnvio(){
		MainAppController controller = getMainApp().getController();
		controller.showResumoEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowEnviosDetalhados(){
		MainAppController controller = getMainApp().getController();
		controller.showEnviosDetalhados(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowDiretoriosEnvio(){
		MainAppController controller = getMainApp().getController();
		controller.showDiretoriosEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowStatusServico(){
		MainAppController controller = getMainApp().getController();
		controller.showStatusServico(getMainApp(), getMainApp().getRootLayout());
	}

	public void setMsg(String msg) {
		labelMsg.setText(msg);
	}
	
	public void processingWhatcherThread(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			    public void run() {
			    Platform.runLater(new Runnable() {
			       public void run() {
			    	   inicializarVerificacaoServicoRemessa();
			    	   String msg = verificarErrosProcessamento();
			    	   if (msg.equals("")) {
			    		   setMsg("Processando");
			    	   }else{
			    		   setMsg(msg);
			    	   }
			      }
			    });
			}
		},2000, 60000);
	}
}
