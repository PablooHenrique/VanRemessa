package br.com.netsuprema.view;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.application.DiretorioEnvioApplication;
import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.application.dto.ContaDto;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroDiretorioEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnCancelar;
	@FXML
	private JFXButton btnSalvar;
	@FXML
	private JFXButton btnAdicionar;
	@FXML
	private JFXButton btnRemover;
	@FXML
	private TextField edtCodigoCedente;
	@FXML
	private TextField edtNumeroConta;
	@FXML
	private TextField edtDigitoVerificador;
	@FXML
	private TextField edtDigitoVerificadorCedente;
	@FXML
	private TextField edtNome;
	
	@FXML
	private TableView<ContaDto> contaTable;
	@FXML
	private TableColumn<ContaDto, String> numeroContaColumn;
	@FXML
	private TableColumn<ContaDto, String> digitoVerificadorColumn;
	
	private ObservableList<ContaDto> contaData = FXCollections.observableArrayList();
	
	private boolean salvarCedente;
	
	private CedenteDto cedente;
	private Stage dialogStage;
	
	public CadastroDiretorioEnvioController() {
		setSalvarCedente(false);
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
	}
	
	public void initializeComponents(){
		inicializarTable();
		inicializarEdts();
	}

	private void inicializarTable() {
		numeroContaColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroContaProperty());
		digitoVerificadorColumn.setCellValueFactory(cellData -> cellData.getValue().getDigitoVerificadorProperty());
		contaTable.setItems(this.contaData);
	}

	private void inicializarEdts() {
		addTextLimiter(edtNome, 30);
		addTextLimiter(edtNumeroConta, 15);
		addTextLimiter(edtCodigoCedente, 15);
		addTextLimiter(edtDigitoVerificador, 1);
		addTextLimiter(edtDigitoVerificadorCedente, 1);
		addUpperCaseText(edtNome);
	}
	
	public void handleCancelar(){
		getDialogStage().close();
	}
	
	public void handleAdicionar(){
		if (dadosDaContaEValido()) {
			ContaDto conta = lerDadosConta();
			contaData.add(conta);
			limparCamposConta();
		}
	}
	
	public void handleRemover(){
		int selectedIndex = contaTable.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        contaTable.getItems().remove(selectedIndex);
	    } else {
	    	ViewUtils.exibirMensagemAlerta("Nenhuma seleção", "Nenhuma conta selecionada", "Por favor, selecione uma conta na tabela.");
	    }
	}
	
	private void limparCamposConta() {
		edtDigitoVerificador.clear();
		edtNumeroConta.clear();
	}

	public void handleSalvar(){
		if (dadosCedenteEValido()) {
			CedenteDto cedente = lerDadosCedente();
			salvarCedente(cedente);
			setSalvarCedente(true);
			ViewUtils.exibirMensagemSucesso("Sucesso", "Cedente : " + cedente.getNome(), "Cadastrado com sucesso");
			getDialogStage().close();
		}
	}

	private void salvarCedente(CedenteDto cedente) {
		DiretorioEnvioApplication application = new DiretorioEnvioApplication();
		application.criarCedente(cedente);
	}

	private CedenteDto lerDadosCedente(){
		CedenteDto cedente = new CedenteDto();
		cedente.setCodigo(edtCodigoCedente.getText());
		cedente.setDigitoVerificador(edtDigitoVerificadorCedente.getText());
		cedente.setNome(edtNome.getText());
		cedente.setContas(contaData);
		return cedente;
	}
	
	private boolean dadosCedenteEValido(){
		if(!codigoCedenteEValido(edtCodigoCedente.getText())){
			return false;
		}
		
		if (!digitoVerificadorCedenteEValido(edtDigitoVerificadorCedente.getText())) {
			return false;
		}
		
		return true;
	}

	private ContaDto lerDadosConta() {
		ContaDto conta  = new ContaDto();
		conta.setNumeroConta(edtNumeroConta.getText());
		conta.setDigitoVerificador(edtDigitoVerificador.getText());
		return conta;
	}
	
	private boolean dadosDaContaEValido(){
		if (!numeroContaEValido(edtNumeroConta.getText())) {
			return false;
		}
		
		if (!digitoVerificadorEValido(edtDigitoVerificador.getText())) {
			return false;
		}
		
		if(!numeroContaRepetido()){
			return false;
		}
		
		return true;
	}

	private boolean numeroContaRepetido() {
		boolean anyMatch = contaData.stream().anyMatch(x -> x.getNumeroConta().equals(edtNumeroConta.getText()));
		if (anyMatch) {
			ViewUtils.exibirMensagemErro("Inválido", "Número da conta invalido.", "Uma conta com esse número ja foi inserido para esse cedente");
			return false;
		}
		return true;
	}

	private boolean digitoVerificadorEValido(String digitoVerificador) {
		try {
			ViewUtils.converterEntradaStringParaInteger(digitoVerificador);
		} catch (NumberFormatException e) {
			ViewUtils.exibirMensagemErro("Inválido", "Dígito verificador inválido.", "O dígito verificador deve ser composto de apenas números.");
			return false;
		}
		
		if (!tamanhoDigitoVerificadorEValido(digitoVerificador)) {
			return false;
		}
		
		return true;
	}

	private boolean digitoVerificadorCedenteEValido(String digitoVerificador) {
		try {
			ViewUtils.converterEntradaStringParaInteger(digitoVerificador);
		} catch (NumberFormatException e) {
			ViewUtils.exibirMensagemErro("Inválido", "Dígito verificador cedente inválido.", "O dígito verificador deve ser composto de apenas números.");
			return false;
		}
		
		if (!tamanhoDigitoVerificadorEValido(digitoVerificador)) {
			return false;
		}
		
		return true;
	}

	private boolean tamanhoDigitoVerificadorEValido(String digitoVerificador) {
		try {
			ViewUtils.validarTamanhoString(digitoVerificador, 1);
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Inválido", "Dígito verificador inválido.", "O dígito verificador deve conter somente um caractere");
			return false;
		}
		
		return true;
	}

	private boolean codigoCedenteEValido(String codigoCedente) {
		try {
			ViewUtils.converterEntradaStringParaInteger(codigoCedente);
		} catch (NumberFormatException e) {
			ViewUtils.exibirMensagemErro("Inválido", "Código cedente inválido.","O código cedente deve ser composto de apenas números.");
			return false;
		}
		return true;
	}

	private boolean numeroContaEValido(String numeroConta) {
		try {
			ViewUtils.converterEntradaStringParaInteger(numeroConta);
		} catch (NumberFormatException e) {
			ViewUtils.exibirMensagemErro("Inválido", "Valor do numero da conta inválido.","O número da conta deve conter apenas numeros.");
			return false;
		}
		
		return true;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isSalvarCedente() {
		return salvarCedente;
	}

	public void setSalvarCedente(boolean salvarCedente) {
		this.salvarCedente = salvarCedente;
	}

	public CedenteDto getCedente() {
		return cedente;
	}

	public void setCedente(CedenteDto cedente) {
		this.cedente = cedente;
	}
}
