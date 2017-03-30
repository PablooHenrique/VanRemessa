package br.com.netsuprema.view;

import java.time.LocalDate;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTabPane;

import br.com.netsuprema.application.RemessasApplication;
import br.com.netsuprema.application.dto.RemessaDto;
import br.com.netsuprema.utils.ConfigUtils;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnviosDetalhadosController extends AbstractController{

	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private ImageView imgLogo;
	@FXML
	private JFXButton btnBuscar;
	
	@FXML
	private TableView<RemessaDto> remessasTable;
	@FXML
	private TableColumn<RemessaDto, String> codigoCedenteColumn;
	@FXML
	private TableColumn<RemessaDto, String> contaColumn;
	@FXML
	private TableColumn<RemessaDto, String> idColumn;
	@FXML
	private TableColumn<RemessaDto, String> nomeCedenteColumn;
	
	@FXML
	private TableColumn<RemessaDto, String> statusEnvioColumn;
	@FXML
	private TableColumn<RemessaDto, String> logEnvioColumn;
	@FXML
	private TableColumn<RemessaDto, String> StatusProcessamentoColumn;
	@FXML
	private TableColumn<RemessaDto, String> logProcessamentoColumn;
	
	@FXML
	private JFXTabPane tabPane;
	@FXML
	private DatePicker dataInicial;
	@FXML
	private DatePicker dataFinal;
	@FXML
	private TextField edtCodigoCedente;
	@FXML
	private TextField edtCodigoEnvio;
	@FXML
	private TextField edtCodigoConta;
	@FXML
	private ComboBox<String> comboBoxStatusProcessamento;
	
	private ObservableList<RemessaDto> remessasData = FXCollections.observableArrayList();
	
	public EnviosDetalhadosController() {
		
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
		inicializarTabela();
		handleBuscar();
	}

	private void initializeComponents(){
		initializeComponenteBtnVoltar();
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
	}

	private void inicializarTabela() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
		codigoCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCedente().getCodigoProperty());
		contaColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroConta());
		nomeCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCedente().getNomeProperty());
		
		statusEnvioColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getSituacao());
		logEnvioColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getDetalheErro());
		StatusProcessamentoColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getStatusProcessamento());
		logProcessamentoColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getLogProcessamento());
		
		remessasTable.setItems(remessasData);
		
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
	
	public void handleBuscar(){
		if (!edtCodigoCedente.getText().trim().isEmpty()
			|| !edtCodigoConta.getText().trim().isEmpty()
			|| !edtCodigoEnvio.getText().trim().isEmpty()
			|| (dataFinal.getValue() != null && dataInicial.getValue() != null)) {
			
			buscarPorParametros();
		}else{
			buscarSemParametros();
		}
	}

	private void buscarSemParametros() {
		remessasData.clear();
		List<RemessaDto> remessas = new RemessasApplication().listar();
		remessas.stream().forEach(x->remessasData.add(x));
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(1);
	}
	
	public void buscarPorParametros(){
		
		if (parametrosSaoValido()) {
			remessasData.clear();
			LocalDate dtIni = dataInicial.getValue();
			LocalDate dtFim = dataInicial.getValue();
			
			String codigoCedente;
			String codigoEnvio;
			String codigoConta;
			
			if (!edtCodigoCedente.getText().trim().isEmpty()) {
				codigoCedente = edtCodigoCedente.getText(); 	
			}else{
				codigoCedente = "0";
			}
			
			if (!edtCodigoEnvio.getText().trim().isEmpty()) {
				codigoEnvio = edtCodigoEnvio.getText(); 	
			}else{
				codigoEnvio = "0";
			}
			
			
			if (!edtCodigoConta.getText().trim().isEmpty()) {
				codigoConta = edtCodigoConta.getText(); 	
			}else{
				codigoConta = "0";
			}
			
			List<RemessaDto> remessas = new RemessasApplication().listarTodasRemessasPorParametro(dtIni, dtFim, codigoCedente, codigoEnvio, codigoConta, null);
			remessas.stream().forEach(x->remessasData.add(x));
			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(1);
		
		}
	}
	
	private boolean parametrosSaoValido(){
		if ((!edtCodigoConta.getText().trim().equals("")) && (!codigoContaEValido(edtCodigoConta.getText().trim()))) {
			return false;
		}
		
		if ((!edtCodigoCedente.getText().trim().equals("")) && (!codigoCedenteEValido(edtCodigoCedente.getText().trim()))) {
			return false;
		}
		
		if ((!edtCodigoEnvio.getText().trim().equals("")) && (!codigoEnvioEValido(edtCodigoEnvio.getText().trim()))) {
			return false;
		}
		
		return true;
	}

	private boolean codigoContaEValido(String codigoConta) {
		try {
			ViewUtils.converterEntradaStringParaInteger(codigoConta);
			return true;
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Código da conta inválido", "O código da conta deve ser composto de apenas numeros");
			return false;
		}
	}

	private boolean codigoEnvioEValido(String codigoEnvio) {
		try {
			ViewUtils.converterEntradaStringParaInteger(codigoEnvio);
			return true;
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Código envio inválido", "O código de envio deve ser composto de apenas numeros");
			return false;
		}
	}

	private boolean codigoCedenteEValido(String codigoCedente) {
		try {
			ViewUtils.converterEntradaStringParaInteger(codigoCedente);
			return true;
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Código cedente inválido", "O código do cedente deve ser composto de apenas numeros");
			return false;
		}
	}
	
	public void handleExibirDetalhes(){
		int selectedIndex = remessasTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			RemessaDto remessaDto = remessasData.get(selectedIndex);
			RemessaDto remessa = new RemessasApplication().listar(Integer.valueOf(remessaDto.getId().get()));
			mainApp.showCadastroDetalhesRemessaDialog(getMainApp(), mainApp.getRootLayout(), remessa);	
		}else {
	    	ViewUtils.exibirMensagemAlerta("Nenhuma seleção", "Nenhuma remessa selecionada", "Por favor, selecione uma remessa na tabela.");
	    }
	}
}
