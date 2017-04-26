package br.com.netsuprema.view;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.application.ConfiguracoesGeraisProjetoApplication;
import br.com.netsuprema.application.ParametrosApplication;
import br.com.netsuprema.application.RetornosApplication;
import br.com.netsuprema.application.ServicosApplication;
import br.com.netsuprema.dominio.DateUtils;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuPrincipalController extends AbstractController{
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private StackPane stackPane;
	@FXML
	private JFXButton btnConfiguracoes;
	@FXML
	private Label labelBtnConfiguracoes;
	@FXML
	private JFXButton btnResumoEnvio;
	@FXML
	private Label labelBtnResumo;
	@FXML
	private JFXButton btnEnviosDetalhados;
	@FXML
	private Label labelBtnEnvioDetalhados;
	@FXML
	private JFXButton btnDiretoriosEnvio;
	@FXML
	private Label labelBtnDiretoriosEnvio;
	@FXML
	private JFXButton btnRetorno;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Pane paneOnOffStatusServicoRemessa;
	@FXML
	private Label labelPaneOnOffStatusServicoRemessa;
	@FXML
	private Pane paneOnOffStatusServicoProcessamentoRetorno;
	@FXML
	private Label labelPaneOnOffStatusServicoProcessamentoRetorno;
	@FXML
	private Pane paneOnOffStatusServicoProcessamentoRemessa;
	@FXML
	private Label labelPaneOnOffStatusServicoProcessamentoRemessa;
	
	@FXML
	private Hyperlink hyperLinkSigVan;
	@FXML
	private Label labelMsg;
	@FXML
	private Button btnTesteSpinner;
	
	private boolean applicacaoBloqueada;
	
	@FXML
	private void initialize(){
		imgLogo.setImage(new Image("file:resources/imagens/logo.png"));
		inicializarImagensBtns();
		inicializarHyperLinkDownloadVersao();
	}

	private void inicializarHyperLinkDownloadVersao() {
		hyperLinkSigVan.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getMainApp().getHostServices().showDocument("www.netsuprema.com.br/media/SigVan.exe");
			}
		});
	}
	
	private void inicializarImagensBtns() {
		inicializarBtnConfiguracoes();
		inicializarBtnDiretorioEnvio();
		inicializarBtnEnviosDetalhados();
		inicializarBtnResumoEnvio();
		inicializarBtnRetorno();
	}

	private void inicializarBtnResumoEnvio() {
		String url = "file:resources/imagens/lista_envio.png";
		ImageView image = new ImageView(url);
		btnResumoEnvio.setGraphic(image);
	}

	private void inicializarBtnEnviosDetalhados() {
		String url = "file:resources/imagens/envio_detalahado.png";
		ImageView image = new ImageView(url);
		btnEnviosDetalhados.setGraphic(image);
	}

	private void inicializarBtnDiretorioEnvio() {
		String url = "file:resources/imagens/diretorio.png";
		ImageView image = new ImageView(url);
		btnDiretoriosEnvio.setGraphic(image);
	}

	private void inicializarBtnConfiguracoes() {
		String url = "file:resources/imagens/configuracao.png";
		ImageView image = new ImageView(url);
		btnConfiguracoes.setGraphic(image);
	}
	
	private void inicializarBtnRetorno() {
		Label lblRetornos = new Label("RETORNOS: \n"
				+ "STATUS: Ag. Processamento\n"
				+ "PROCESSADOS : 0 / 0");
		
		btnRetorno.setGraphic(lblRetornos);
	}

	public void inicializarConfigurações() {
		if (parametrosArquivoRetornoEValido()) {
			processingWhatcherReturnsThread();
		}
		
		if (parametrosSaoValidos()) {
			processingWhatcherThread();
			processingWhatcherThreadProcessamentoEnvioRemessa();
			processingWhatcherThreadEnvioRetorno();
		}else{
			labelMsg.setText("Cadastre os parâmetros para inicializar o processamento dos dados");
		}
	}

	private boolean parametrosArquivoRetornoEValido() {
		try {
			RetornosApplication application = new RetornosApplication();
			boolean retorno = application.parametrosRetornoLiquidacaoSaoValidos();
			return retorno;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao consultar os parâmetros.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			ViewUtils.exibirMensagemErro("Erro", "", exception.toString());
			return false;
		}
	}

	private boolean parametrosSaoValidos() {
		try {
			ParametrosApplication parametrosApplication = new ParametrosApplication();
			Parametros parametros = parametrosApplication.consultarParametros();
			
			if (parametros == null) {
				desabilitarBtns(true, false);
				
				return false;
			}
			
			desabilitarBtns(false, false);
			return true;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao validar os parametros.")
			 		 .append("Motivo: " + e.getMessage())
			 		 .append("Causa: " + e.getCause().getMessage());
			ViewUtils.exibirMensagemErro("", "Falha ao validar os parametros", exception.toString());
		}
		
		return false;
	}

	private void desabilitarBtns(boolean habiitar, boolean todos) {
		btnDiretoriosEnvio.setDisable(habiitar);
		labelBtnDiretoriosEnvio.setDisable(habiitar);
		
		btnEnviosDetalhados.setDisable(habiitar);
		labelBtnEnvioDetalhados.setDisable(habiitar);
		
		btnResumoEnvio.setDisable(habiitar);
		labelBtnResumo.setDisable(habiitar);
		
		btnRetorno.setDisable(habiitar);
		if (todos) {
			btnConfiguracoes.setDisable(habiitar);
			labelBtnConfiguracoes.setDisable(habiitar);
			
			hyperLinkSigVan.setVisible(true);
		}
	}
	
	private void desabilitarStatusServicos(boolean desabilitar){
		paneOnOffStatusServicoProcessamentoRemessa.setDisable(desabilitar);
		labelPaneOnOffStatusServicoProcessamentoRemessa.setDisable(desabilitar);
		
		paneOnOffStatusServicoProcessamentoRetorno.setDisable(desabilitar);
		labelPaneOnOffStatusServicoProcessamentoRetorno.setDisable(desabilitar);
		
		paneOnOffStatusServicoRemessa.setDisable(desabilitar);
		labelPaneOnOffStatusServicoRemessa.setDisable(desabilitar);
	}
	
	public void inicializarVerificacaoServicoEnvioRemessa() {
		boolean status = new ServicosApplication().verificarServicoRemessa();
		if (status) {
			paneOnOffStatusServicoRemessa.getStyleClass().remove("floatingOff");
			paneOnOffStatusServicoRemessa.getStyleClass().add("floatingOn");
		}else{
			paneOnOffStatusServicoRemessa.getStyleClass().remove("floatingOn");
			paneOnOffStatusServicoRemessa.getStyleClass().add("floatingOff");
		}
	}
	
	public void inicializarVerificacaoServicoEnvioRetorno() {
		boolean status = new ServicosApplication().verificarServicoEnvioRetorno();
		if (status) {
			paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().remove("floatingOff");
			paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().add("floatingOn");
		}else{
			paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().remove("floatingOn");
			paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().add("floatingOff");
		}
	}
	
	public void inicializarVerificacaoServicoProcessamentoRemessa() {
		boolean status = new ServicosApplication().verificarServicoProcessamentoRemessa();
		if (status) {
			paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().remove("floatingOff");
			paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().add("floatingOn");
		}else{
			paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().remove("floatingOn");
			paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().add("floatingOff");
		}
	}
	
	private String verificarErrosProcessamento(){
		ServicosApplication servicosApplication = new ServicosApplication();
		String errosProcessamento = servicosApplication.obterErrosProcessamento();
		servicosApplication.limparErroProcessamento();
		
		return errosProcessamento;
		
	}
	
	public void handleShowConfiguracoesServico(){
		try {
			getMainApp().showConfiguracoesServico(getMainApp(), getMainApp().getRootLayout());
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao abrir a pagina de configurações.")
			 		 .append("Motivo: " + e.getMessage())
			 		 .append("Causa: " + e.getCause().getMessage());
			ViewUtils.exibirMensagemErro("", "Flha ao abrir a pagina de configurações", exception.toString());
		}
	}
	
	public void handleShowRetornos(){
		try {
			getMainApp().showRetornos(getMainApp(), getMainApp().getRootLayout());
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao abrir a pagina de retornos.")
			 		 .append("Motivo: " + e.getMessage())
			 		 .append("Causa: " + e.getCause().getMessage());
			ViewUtils.exibirMensagemErro("", "Flha ao abrir a pagina de retornos", exception.toString());
		}
	}
	
	public void handleShowResumoEnvio(){
		getMainApp().showResumoEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowEnviosDetalhados(){
		getMainApp().showEnviosDetalhados(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowDiretoriosEnvio(){
		getMainApp().showDiretoriosEnvio(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleShowStatusServico(){
		getMainApp().showStatusServico(getMainApp(), getMainApp().getRootLayout());
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
			    	   inicializarVerificacaoServicoEnvioRemessa();
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
	
	public void processingWhatcherThreadEnvioRetorno(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			    public void run() {
			    Platform.runLater(new Runnable() {
			       public void run() {
			    	   inicializarVerificacaoServicoEnvioRetorno();
			      }
			    });
			}
		},2000, 60000);
	}
	
	public void processingWhatcherThreadProcessamentoEnvioRemessa(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			    public void run() {
			    Platform.runLater(new Runnable() {
			       public void run() {
			    	   inicializarVerificacaoServicoProcessamentoRemessa();
			      }
			    });
			}
		},2000, 60000);
	}
	
	public void processingWhatcherReturnsThread(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			    public void run() {
			    Platform.runLater(new Runnable() {
			       public void run() {
			    	   String proscessados = "";
			    	   RetornosApplication application = new RetornosApplication();
			    	   Integer quantidade = application.consultarQuantidadeRetornosDoDia();
			    	   Integer quantidadeTotalCedentes = application.consultarQuantidadeTotalCedentes();
			    	   
			    	   
			    	   if(application.processamentoRetornoDoDiaJaRealizado()){
			    		   proscessados = "Finalizado. ";
			    	   }else{
			    		   proscessados = "Ag. Processamento. ";
			    	   }
			    	   
			    	   
			    	   if (quantidadeTotalCedentes == 0) {
			    		   Label lblRetornos = new Label("Sem cedentes \n para processamento");
				   		   btnRetorno.setGraphic(lblRetornos);
			    	   }else{
				    	   Label lblRetornos = new Label("RETORNOS: "+DateUtils.converterLocalDateParaString(DateUtils.getUltimoDiaUtil())+" \n"
				   				+ "STATUS: "+proscessados+" \n"
				   				+ "PROCESSADOS : "+quantidade+" / "+quantidadeTotalCedentes);
				   		
				   		   btnRetorno.setGraphic(lblRetornos);
			   		   }
			       }
			    });
			}
		},2000, 60000);
	}
	
	public void configuracoesGeraisProjetoProcessingWatcherThread(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			    public void run() {
			    Platform.runLater(new Runnable() {
			       public void run() {
			    	   try {
			    		   ConfiguracoesGeraisProjetoApplication application = new ConfiguracoesGeraisProjetoApplication();
			    		   if (!application.rotinaEstaAtualizada()){
			    			   bloquearAplicacao(application.carregarMensagemBloqueio());
						   }
						} catch (JSONException e) {
						}
			       }
			    });
			}
		},2000, 60000);
	}
	
	public void handleShowSippner(){
		VBox bx = new VBox();
        bx.setAlignment(Pos.CENTER);
        
        btnTesteSpinner.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ProgressIndicator pi = new ProgressIndicator();
				VBox box = new VBox(pi);
				box.setAlignment(Pos.CENTER);
				stackPane.getChildren().add(box);
			}
		});
	}

	public void bloquearAplicacao(String msgBloqueio) {
		labelMsg.setText(msgBloqueio);
		desabilitarBtns(true, true);
		desabilitarStatusServicos(true);
		
		desabilitarProcessamento();
	}

	private void desabilitarProcessamento() {
		paneOnOffStatusServicoRemessa.getStyleClass().add("floatingOff");
		paneOnOffStatusServicoRemessa.getStyleClass().remove("floatingOn");
		
		paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().add("floatingOff");
		paneOnOffStatusServicoProcessamentoRetorno.getStyleClass().remove("floatingOn");
		
		paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().add("floatingOff");
		paneOnOffStatusServicoProcessamentoRemessa.getStyleClass().remove("floatingOn");
	}

	public boolean isApplicacaoBloqueada() {
		return applicacaoBloqueada;
	}

	public void setApplicacaoBloqueada(boolean applicacaoBloqueada) {
		this.applicacaoBloqueada = applicacaoBloqueada;
	}
}
