package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.ServicosApplication;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StatusServicoController extends AbstractController {

	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnInicializarServico;
	@FXML
	private JFXButton btnParaServico;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Pane paneOnOffStatusServicoRemessa;
	@FXML
	private Pane paneOnOffStatusServicoAcopanhamentoLog;
	
	@FXML
	public void initialize() {
		initializeComponents();
		inicializarVerificacaoServicos();
	}
	
	private void inicializarVerificacaoServicos() {
		inicializarVerificacaoServicoRemessa();
		inicializarVerificacaoServicoAcompanhamentoLog();
	}

	private void inicializarVerificacaoServicoAcompanhamentoLog() {
		boolean status = new ServicosApplication().verificarServicoAcompanhamentoLog();
		if (status) {
			paneOnOffStatusServicoAcopanhamentoLog.getStyleClass().remove("floatingOff");
			paneOnOffStatusServicoAcopanhamentoLog.getStyleClass().add("floatingOn");
		}else{
			paneOnOffStatusServicoAcopanhamentoLog.getStyleClass().remove("floatingOn");
			paneOnOffStatusServicoAcopanhamentoLog.getStyleClass().add("floatingOff");
		}
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

	public void initializeComponents(){
		inicializarBtnVoltar();
	}
	
	private void inicializarBtnVoltar() {
		String url = ConfigUtils.PATH_RESOURCE_PADRAO + "imagens/voltar.png";
		ImageView image = new ImageView(url);
		btnVoltar.setPrefHeight(49);
		btnVoltar.setPrefWidth(49);
		btnVoltar.setText("");
		btnVoltar.setGraphic(image);			
		btnVoltar.setButtonType(ButtonType.RAISED);
		btnVoltar.getStyleClass().add("floating");
		
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
	}
	
	public void handleInicializarServico(){
//		paneOnOff.getStyleClass().remove("floatingOff");
//		paneOnOff.getStyleClass().add("floatingOn");
//		btnInicializarServico.setDisable(true);
//		btnParaServico.setDisable(false);
//		labelOnOff.setText("ON");
	}
	
	public void hancdlePararServico(){
//		paneOnOff.getStyleClass().remove("floatingOn");
//		paneOnOff.getStyleClass().add("floatingOff");
//		btnParaServico.setDisable(true);
//		btnInicializarServico.setDisable(false);
//		labelOnOff.setText("OFF");
	}
}
