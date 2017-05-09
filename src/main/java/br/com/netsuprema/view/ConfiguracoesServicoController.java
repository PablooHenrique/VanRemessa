package br.com.netsuprema.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.ParametrosApplication;
import br.com.netsuprema.dominio.enuns.FormatoRemessa;
import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.FormatoRetornoLiquidacao;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.utils.ConfigUtils;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfiguracoesServicoController extends AbstractController{
	
	private static final Logger logger = Logger.getLogger(ConfiguracoesServicoController.class);
	
	private ParametrosApplication application; 
	
	@FXML
	private ComboBox<String> comboBoxFormatoRemessa;
	@FXML
	private ComboBox<String> comboBoxFormatoRetorno;
	@FXML
	private TextField edtUsuario;
	@FXML
	private TextField edtEmail;
	@FXML
	private TextField edtSenha;
	@FXML
	private TextField edtCooperativa;
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnsalvar;
	@FXML
	private ImageView imgLogo;
	@FXML
	private JFXButton btnSeparadorDeArquivos;
	
	ObservableList<String> cooperativas 	= FXCollections.observableArrayList();
	ObservableList<String> formatoRemessa 	= FXCollections.observableArrayList();
	ObservableList<String> formatoRetorno 	= FXCollections.observableArrayList();
	
	public ConfiguracoesServicoController() {
		setApplication(new ParametrosApplication());
	}
	
	@FXML
	public void initialize() throws Exception{
		try {
			initializeComponents();
			carregarInformacoesIniciais();
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao realizar a consultar de cooperativas no servidor.")
			 		 .append("Motivo: " + e.getMessage())
			 		 .append("Causa: " + e.getCause().getMessage());
			ViewUtils.exibirMensagemErro("Erro","Falha ao realizar o carregamento da pagina de parametros", exception.toString());
			throw e;
		}
	}
	
	public void carregarInformacoesIniciais() throws Exception {
		Parametros parametros = application.consultarParametros();
		if (parametros != null) {
			preencherInformacoesParametros(parametros);
		}
	}

	public void preencherInformacoesParametros(Parametros parametros) {
		edtEmail.setText(parametros.getEmail());
		edtUsuario.setText(parametros.getLogin());
		edtSenha.setText(parametros.getSenha());
		edtCooperativa.setText(parametros.getCooperativa().getKeyCop() + "-" + parametros.getCooperativa().getNome());
		
//		preencherInformacoesComboCooperativa(parametros);
		preencherInformacoesComboFormatoRemessa(parametros);
		preencherInformacoesComboFormatoRetorno(parametros);
	}

	public void preencherInformacoesComboFormatoRemessa(Parametros parametros) {
		SingleSelectionModel<String> selectionModel = comboBoxFormatoRemessa.getSelectionModel();
		
		for (int i = 0; i < formatoRemessa.size(); i++) {
			if (!formatoRemessa.get(i).trim().isEmpty()) {
				String[] itens = formatoRemessa.get(i).split("-");
				
				if (Integer.valueOf(itens[0]) == parametros.getFormatoRemessa().getCodigo()) {
					selectionModel.select(formatoRemessa.get(i));
					break;
				}
			}
		}
	}

	
	public void preencherInformacoesComboFormatoRetorno(Parametros parametros) {
		SingleSelectionModel<String> selectionModel = comboBoxFormatoRetorno.getSelectionModel();
		
		for (int i = 0; i < formatoRetorno.size(); i++) {
			if (!formatoRetorno.get(i).trim().isEmpty()) {
				String[] itens = formatoRetorno.get(i).split("-");
				
				if (Integer.valueOf(itens[0]) == parametros.getFormatoRetornoLiquidacao().getCodigo()) {
					selectionModel.select(formatoRetorno.get(i));
					break;
				}
			}
		}
	}

	public void initializeComponents() throws Exception{
		inicializarCooperativas();
		carregarComboFormatoRemessa();
		carregarComboFormatoRetorno();
		
		addTextLimiter(edtCooperativa, 15);
		
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
	
	private void carregarComboFormatoRetorno() {
		List<FormatoRetornoLiquidacao> list = Arrays.asList(FormatoRetornoLiquidacao.values());
		list.stream().forEach(x-> this.formatoRetorno.add(x.getCodigo() +"-"+x.getDescricao().toUpperCase()));
		comboBoxFormatoRetorno.setItems(this.formatoRetorno);
	}

	public void inicializarCooperativas() throws Exception {
		List<Cooperativa> cooperativas;
		
		cooperativas = obterCooperativasBanco();
		boolean anyMatch = cooperativas.stream().anyMatch(x->String.valueOf(x.getKeyCop()).length() < 4);
		
		if (anyMatch || cooperativas.isEmpty()) {
			cooperativas = obterCooperativasViaWebService();
			application.salvarCooperativas(cooperativas);
		}
	}

	public List<Cooperativa> obterCooperativasBanco() throws Exception {
		List<Cooperativa> cooperativas = application.consultarCooperativas();
		return cooperativas;
	}

	public void carregarComboFormatoRemessa(){
		List<FormatoRemessa> list = Arrays.asList(FormatoRemessa.values());
		list.stream().forEach(x-> this.formatoRemessa.add(x.getCodigo() + "-" +x.getDescricao().toUpperCase()));
		comboBoxFormatoRemessa.setItems(this.formatoRemessa);
	}
	
	public List<Cooperativa> obterCooperativasViaWebService() throws Exception{
		try {
			List<Cooperativa> cooperativas = new ArrayList<Cooperativa>();
			cooperativas = getApplication().consultarCooperativasWebService();
			return cooperativas;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao realizar a consultar de cooperativas no servidor.")
			 		 .append("Motivo: " + e.getMessage())
			 		 .append("Causa: " + e.getCause().getMessage());
			
			logger.error(exception.toString());
			
			throw e;
		}
	}
	
	@FXML
	public void handleSalvar(){
		try {
			if (dadosSaoValidos()) {
				salvarParametros();
				ViewUtils.exibirMensagemSucesso("Sucesso", "", "Parametros Salvos Com sucesso");
			}
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Falha ao salvar os parametros", e.getMessage(), e.getCause().getMessage());
		}
	}
	
	@FXML
	public void handleShowSeparadosArquivos(){
		mainApp.showCadastroSeparadorArquivosDialog(getMainApp(), mainApp.getRootLayout());
	}

	public void salvarParametros() throws Exception {
		try {
			Parametros parametros = getParametros();
			getApplication().salvar(parametros);
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Falha ao salvar os parametros.")
					 .append("Motivo: " + e.getMessage())
					 .append("Causa: " + e.getMessage());
			
			throw new Exception(exception.toString());
		}
	}
	
	public Parametros getParametros() throws NumberFormatException, Exception{
		Parametros parametros = new Parametros();
		
		parametros.setEmail(edtEmail.getText());
		parametros.setLogin(edtUsuario.getText());
		parametros.setSenha(edtSenha.getText());
		
		parametros.setCooperativa(null);
		parametros.setCooperativa(criarCooperativa());
		parametros.setFormatoRemessa(criarFormatoRemessa());
		parametros.setFormatoRetornoLiquidacao(criarFormatoRetornoLiquidacao());
		
		return parametros;
	}

	private FormatoRetornoLiquidacao criarFormatoRetornoLiquidacao() {
		SingleSelectionModel<String> selectionModel = comboBoxFormatoRetorno.getSelectionModel();
		String item = selectionModel.getSelectedItem();
		
		if (!item.trim().isEmpty()) {
			String[] itens = item.split("-");
			
			FormatoRetornoLiquidacao[] values = FormatoRetornoLiquidacao.values();
			for (int i = 0; i < values.length; i++) {
				if (values[i].getCodigo() == Integer.valueOf(itens[0])) {
					return values[i];
				}
			}
		}
		
		return null;
	}

	public FormatoRemessa criarFormatoRemessa() {
		SingleSelectionModel<String> selectionModel = comboBoxFormatoRemessa.getSelectionModel();
		String item = selectionModel.getSelectedItem();
		
		if (!item.trim().isEmpty()) {
			String[] itens = item.split("-");
			
			FormatoRemessa[] values = FormatoRemessa.values();
			for (int i = 0; i < values.length; i++) {
				if (values[i].getCodigo() == Integer.valueOf(itens[0])) {
					return values[i];
				}
			}
		}
		
		return null;
	}

	public Cooperativa criarCooperativa() throws NumberFormatException, Exception {
		
		Integer codigoCooperativa;
		
		try {
			codigoCooperativa = Integer.valueOf(edtCooperativa.getText());
		} catch (Exception e) {
			String cooperativa  = edtCooperativa.getText();
			if(cooperativa.indexOf("-") != -1){
				String[] dadosCooperaiva = cooperativa.split("-");
				try {
					codigoCooperativa = Integer.valueOf(dadosCooperaiva[0]);
				} catch (Exception e2) {
					ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
					throw e2;
				}
			}else{
				ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
				throw e;
			}
		}
		
		Cooperativa cooperativa = application.consultarCooperativaPorKey(codigoCooperativa);
		return cooperativa;
	}
	
	public void salvarCooperativas(List<Cooperativa> cooperativas) throws Exception{
		application.salvarCooperativas(cooperativas);
	}
	
	public boolean dadosSaoValidos(){
		SingleSelectionModel<String> selectionModel;
		int index;
		
		selectionModel = comboBoxFormatoRemessa.getSelectionModel();
		index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Selecione um formato de remessa");
			return false;
		}
		
		selectionModel = comboBoxFormatoRetorno.getSelectionModel();
		index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Selecione um formato de retorno");
			return false;
		}
		
		if (edtEmail.getText().trim().equals("")) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um email valido");
			return false;
		}
		
		if (edtSenha.getText().trim().equals("")) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite uma senha valida");
			return false;
		}
		
		if (edtUsuario.getText().trim().equals("")) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um usuario valido");
			return false;
		}
		
		if (edtCooperativa.getText().trim().equals("")) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
			return false;
		}
		
		Long codigoCooperativa;
		
		try {
			codigoCooperativa = Long.valueOf(edtCooperativa.getText());
		} catch (Exception e) {
			String cooperativa  = edtCooperativa.getText();
			if(cooperativa.indexOf("-") != -1){
				String[] dadosCooperaiva = cooperativa.split("-");
				try {
					codigoCooperativa = Long.valueOf(dadosCooperaiva[0]);
				} catch (Exception e2) {
					ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
					return false;
				}
			}else{
				ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
				return false;
			}
		}
		
		if(!application.codigoCooperativaEValido(codigoCooperativa)){
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Digite um código de cooperativa valido");
			return false;
		}
		
		return true;
	}
	
	public ParametrosApplication getApplication() {
		return application;
	}

	public void setApplication(ParametrosApplication application) {
		this.application = application;
	}
}
