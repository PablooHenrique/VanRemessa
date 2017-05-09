package br.com.netsuprema.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.application.DiretorioEnvioApplication;
import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.application.dto.ContaDto;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CadastroDiretorioEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnCancelar;
	@FXML
	private JFXButton btnSalvar;
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
	private TextField edtDiretorio;
	
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
		inicializarEdts();
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
	
	public void limparCamposConta() {
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
		cedente.setDiretorioPadrao(edtDiretorio.getText());
		cedente.setContas(criarConta());
		return cedente;
	}
	
	private List<ContaDto> criarConta() {
		ContaDto conta = lerDadosConta();
		
		List<ContaDto> contas = new ArrayList<ContaDto>();
		contas.add(conta);
		return contas;
	}

	private boolean dadosCedenteEValido(){
		if(!codigoCedenteEValido(edtCodigoCedente.getText())){
			return false;
		}
		
		if (!digitoVerificadorCedenteEValido(edtDigitoVerificadorCedente.getText())) {
			return false;
		}
		
		if (!diretorioEValido(edtDiretorio.getText().trim())) {
			return false;
		} 
		
		if(!dadosDaContaEValido()){
			return false;
		}
		
		return true;
	}

	private boolean diretorioEValido(String diretorio) {
		if (!new File(diretorio).isDirectory()) {
			ViewUtils.exibirMensagemErro("Inválido", "Diretório inválido.", "O diretório do arquivo e inválido. Selecione um diretório valido para prosseguir");
			return false;
		}
		
		return true;
	}

	private ContaDto lerDadosConta() {
		ContaDto conta  = new ContaDto();
		conta.setNumeroConta(edtNumeroConta.getText());
		conta.setDigitoVerificador(edtDigitoVerificador.getText());
		conta.setDiretorio(edtDiretorio.getText());
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
		Integer numeroConta = Integer.valueOf(edtNumeroConta.getText());
		boolean numeroContaExiste = new DiretorioEnvioApplication().numeroContaExiste(numeroConta);
		
		if (numeroContaExiste) {
			ViewUtils.exibirMensagemErro("Inválido", "Número da conta invalido.", "Uma conta com esse número ja foi inserida");
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
	
	@FXML
	public void handleAbrirDiretorio(){
		DirectoryChooser dirChoser = new DirectoryChooser();
		dirChoser.setTitle("Selecione o diretorio");
		File file = dirChoser.showDialog(null);
		
		if (file != null) {
			this.edtDiretorio.setText(file.getAbsolutePath());
		}
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
