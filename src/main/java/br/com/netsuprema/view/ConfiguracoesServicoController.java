package br.com.netsuprema.view;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.ParametrosApplication;
import br.com.netsuprema.dominio.enuns.FormatoRemessa;
import br.com.netsuprema.dominio.parametros.Cooperativa;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfiguracoesServicoController extends AbstractController{
	
	private static final Logger logger = Logger.getLogger(ConfiguracoesServicoController.class);
	
	private ParametrosApplication application; 
	
	@FXML
	private ComboBox<String> comboBoxCooperativas;
	@FXML
	private ComboBox<String> comboBoxFormatoRemessa;
	@FXML
	private TextField edtUsuario;
	@FXML
	private TextField edtEmail;
	@FXML
	private TextField edtSenha;
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnsalvar;
	@FXML
	private ImageView imgLogo;
	
	ObservableList<String> cooperativas = FXCollections.observableArrayList();
	ObservableList<String> formatoRemessa = FXCollections.observableArrayList();
	
	public ConfiguracoesServicoController() {
		setApplication(new ParametrosApplication());
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
		carregarInformacoesIniciais();
	}
	
	public void carregarInformacoesIniciais() {
		Parametros parametros = application.consultarParametros();
		if (parametros != null) {
			preencherInformacoesParametros(parametros);
		}
	}

	public void preencherInformacoesParametros(Parametros parametros) {
		edtEmail.setText(parametros.getEmail());
		edtUsuario.setText(parametros.getLogin());
		edtSenha.setText(parametros.getSenha());
		
		preencherInformacoesComboCooperativa(parametros);
		preencherInformacoesComboFormatoRemessa(parametros);
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

	public void preencherInformacoesComboCooperativa(Parametros parametros) {
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		
		for (int i = 0; i < cooperativas.size(); i++) {
			if (!cooperativas.get(i).trim().isEmpty()) {
				String[] itens = cooperativas.get(i).split("-");
				
				if (Integer.valueOf(itens[0]) == parametros.getCooperativa().getKeyCop()) {
					selectionModel.select(cooperativas.get(i));
					break;
				}
			}
		}
	}

	public void initializeComponents(){
		inicializarCooperativas();
		carregarComboCooperativa();
		carregarComboFormatoRemessa();
		
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
	
	public void inicializarCooperativas() {
		List<Cooperativa> cooperativas;
		
		cooperativas = obterCooperativasBanco();
		
		if (cooperativas.isEmpty()) {
			cooperativas = obterCooperativasViaWebService();
			application.salvarCooperativas(cooperativas);
		}
	}

	public List<Cooperativa> obterCooperativasBanco() {
		List<Cooperativa> cooperativas = application.consultarCooperativas();
		return cooperativas;
	}

	public void carregarComboCooperativa(){
		List<Cooperativa> cooperativas = obterCooperativasViaWebService();
		cooperativas.stream().forEach(x-> this.cooperativas.add(x.getKeyCop() +"-"+x.getNome().toUpperCase()));
		comboBoxCooperativas.setItems(this.cooperativas);
	}
	
	public void carregarComboFormatoRemessa(){
		List<FormatoRemessa> list = Arrays.asList(FormatoRemessa.values());
		list.stream().forEach(x-> this.formatoRemessa.add(x.getCodigo() + "-" +x.getDescricao().toUpperCase()));
		comboBoxFormatoRemessa.setItems(this.formatoRemessa);
	}
	
	public List<Cooperativa> obterCooperativasViaWebService(){
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>();
		try {
			cooperativas = getApplication().consultarCooperativasWebService();
		} catch (URISyntaxException | JSONException e) {
			logger.error("ObterCooperativa: " + e.getMessage());
			exibirMensagem("Falha ao realizar a consultar de cooperativas no servidor.", true);    
		}
		return cooperativas;
	}
	
	@FXML
	public void handleSalvar(){
		if (dadosSaoValidos()) {
			salvarParametros();
			exibirMensagem("Parametros Salvos Com sucesso", false);
		}
	}

	public void salvarParametros() {
		Parametros parametros = getParametros();
		getApplication().salvar(parametros);
	}
	
	public Parametros getParametros(){
		Parametros parametros = new Parametros();
		
		parametros.setEmail(edtEmail.getText());
		parametros.setLogin(edtUsuario.getText());
		parametros.setSenha(edtSenha.getText());
		
		parametros.setCooperativa(null);
		parametros.setCooperativa(criarCooperativa());
		parametros.setFormatoRemessa(criarFormatoRemessa());
		
		return parametros;
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

	public Cooperativa criarCooperativa() {
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		String item = selectionModel.getSelectedItem();
		
		if (!item.trim().isEmpty()) {
			String[] itens = item.split("-");
			Cooperativa cooperativa = getApplication().consultarCooperativaPorKey(Integer.valueOf(itens[0]));
			return cooperativa;
		}
		
		return null;
	}
	
	public void salvarCooperativas(List<Cooperativa> cooperativas){
		application.salvarCooperativas(cooperativas);
	}
	
	public boolean dadosSaoValidos(){
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		int index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			exibirMensagem("selecione uma cooperativa", true);
			return false;
		}
		
		selectionModel = comboBoxFormatoRemessa.getSelectionModel();
		index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			exibirMensagem("Selecione um formato", true);
			return false;
		}
		
		if (edtEmail.getText().trim().equals("")) {
			exibirMensagem("Digite um email valido", true);
			return false;
		}
		
		if (edtSenha.getText().trim().equals("")) {
			exibirMensagem("Digite uma senha valida", true);
			return false;
		}
		
		if (edtUsuario.getText().trim().equals("")) {
			exibirMensagem("Digite um usuario valido", true);
			return false;
		}
		
		return true;
	}
	
	public void exibirMensagem(String mensagem, boolean erro){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informacao");
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
	

	public ParametrosApplication getApplication() {
		return application;
	}

	public void setApplication(ParametrosApplication application) {
		this.application = application;
	}
}
