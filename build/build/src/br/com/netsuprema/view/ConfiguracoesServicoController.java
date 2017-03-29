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

	public void initializeComponents() throws Exception{
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
	
	public void inicializarCooperativas() throws Exception {
		List<Cooperativa> cooperativas;
		
		cooperativas = obterCooperativasBanco();
		
		if (cooperativas.isEmpty()) {
			cooperativas = obterCooperativasViaWebService();
			application.salvarCooperativas(cooperativas);
		}
	}

	public List<Cooperativa> obterCooperativasBanco() throws Exception {
		List<Cooperativa> cooperativas = application.consultarCooperativas();
		return cooperativas;
	}

	public void carregarComboCooperativa() throws Exception{
//		List<Cooperativa> cooperativas = obterCooperativasViaWebService();
		List<Cooperativa> cooperativas = obterCooperativasBanco();
		cooperativas.stream().forEach(x-> this.cooperativas.add(x.getKeyCop() +"-"+x.getNome().toUpperCase()));
		comboBoxCooperativas.setItems(this.cooperativas);
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

	public Cooperativa criarCooperativa() throws NumberFormatException, Exception {
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		String item = selectionModel.getSelectedItem();
		
		if (!item.trim().isEmpty()) {
			String[] itens = item.split("-");
			Cooperativa cooperativa = getApplication().consultarCooperativaPorKey(Integer.valueOf(itens[0]));
			return cooperativa;
		}
		
		return null;
	}
	
	public void salvarCooperativas(List<Cooperativa> cooperativas) throws Exception{
		application.salvarCooperativas(cooperativas);
	}
	
	public boolean dadosSaoValidos(){
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		int index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "selecione uma cooperativa");
			return false;
		}
		
		selectionModel = comboBoxFormatoRemessa.getSelectionModel();
		index = selectionModel.getSelectedIndex();
		
		if (index == -1) {
			ViewUtils.exibirMensagemAlerta("Dados Invalidos", "", "Selecione um formato");
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
		
		return true;
	}
	
	public ParametrosApplication getApplication() {
		return application;
	}

	public void setApplication(ParametrosApplication application) {
		this.application = application;
	}
}
