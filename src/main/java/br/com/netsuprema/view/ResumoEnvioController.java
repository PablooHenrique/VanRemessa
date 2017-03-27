package br.com.netsuprema.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.RelatorioApplication;
import br.com.netsuprema.application.dto.ResumoEnvioDto;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.service.RemessasDeTitulosService;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResumoEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnExpandirFiltros;
	@FXML
	private ImageView imgLogo;
	@FXML
	private TextField edtDataHoraUltimaVerificacaoEnvio;
	@FXML
	private TextField edtDataHoraUltimoEnvio;
	@FXML
	private TextField edtQuantidadeRemessasEnviadas;
	@FXML
	private TextField edtQuantidadeRemessasProcessadasComSucesso;
	@FXML
	private TextField edtQuantidadeRemessasAguardandoProcessamento;
	@FXML
	private TextField edtQuantidadeRemessasProcessadasComErro;
	
	
	public ResumoEnvioController(){

	}
	
	@FXML
	public void initialize() {
		initializeComponents();
	}
	
	public void initializeComponents(){
		inicializarBtnVoltar();
		inicializarLogo();
		inicializarInputs();
	}
	
	private void inicializarInputs() {
		edtDataHoraUltimaVerificacaoEnvio.setEditable(false);
		edtDataHoraUltimoEnvio.setEditable(false);
		edtQuantidadeRemessasAguardandoProcessamento.setEditable(false);
		edtQuantidadeRemessasEnviadas.setEditable(false);
		edtQuantidadeRemessasProcessadasComErro.setEditable(false);
		edtQuantidadeRemessasProcessadasComSucesso.setEditable(false);
	}

	private ResumoEnvioDto consultar(){
		RelatorioApplication application = new RelatorioApplication();
		ResumoEnvioDto resumo = application.listarResumoEnvio();
		return resumo;
	}
	
	public void handlePesquisar(){
		ResumoEnvioDto resumo = consultar();
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = resumo.getDataUltimaVerificacaoEnvio();
		String date = dateTime.format(pattern);
		
		edtDataHoraUltimaVerificacaoEnvio.setText(date);
		
		RemessasDeTitulosService service = new RemessasDeTitulosService();
		
		List<Remessa> remessas = service.listarRemessasEnviadas();
		
		if (!remessas.isEmpty()) {
			LocalDateTime dataHoraEnvio = remessas.get(0).getLog().getDataHoraEnvio();
			date = dataHoraEnvio.format(pattern);
			edtDataHoraUltimoEnvio.setText(date);
		}
		
		remessas = service.listarRemessasEnviadas();
		edtQuantidadeRemessasEnviadas.setText(String.valueOf(remessas.size()));
		
		remessas = service.listarRemessasComAguardoProcessamentoServidor();
		edtQuantidadeRemessasAguardandoProcessamento.setText(String.valueOf(remessas.size()));
		
		remessas = service.listarRemessasProcessadasComSucesso();
		edtQuantidadeRemessasProcessadasComSucesso.setText(String.valueOf(remessas.size()));
		
		remessas = service.listarRemessasProcessadasComErro();
		edtQuantidadeRemessasProcessadasComErro.setText(String.valueOf(remessas.size()));
	}

	private void inicializarLogo() {
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
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
	}
}
