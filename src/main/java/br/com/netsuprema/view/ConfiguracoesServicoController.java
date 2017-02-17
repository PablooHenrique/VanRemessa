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
import br.com.netsuprema.controller.MainAppController;
import br.com.netsuprema.dominio.Cooperativa;
import br.com.netsuprema.dominio.Parametros;
import br.com.netsuprema.dominio.enuns.FormatoRemessa;
import br.com.netsuprema.utils.ConfigUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	private ImageView imgConfiguracoes;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Pane panelMensagem;
	@FXML
	private Label msgAvisoUsuario;
	
	ObservableList<String> cooperativas = FXCollections.observableArrayList();
	ObservableList<String> formatoRemessa = FXCollections.observableArrayList();
	
	public ConfiguracoesServicoController() {
		setApplication(new ParametrosApplication());
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
	}
	
	public void initializeComponents(){
		
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
		imgConfiguracoes.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO +"icone_configuracao.png"));
	}
	
	public void carregarComboCooperativa(){
		List<Cooperativa> cooperativas = obterCooperativas();
		cooperativas.stream().forEach(x-> this.cooperativas.add(x.getKey() +"-"+x.getNome().toUpperCase()));
		comboBoxCooperativas.setItems(this.cooperativas);
	}
	
	public void carregarComboFormatoRemessa(){
		List<FormatoRemessa> list = Arrays.asList(FormatoRemessa.values());
		list.stream().forEach(x-> this.formatoRemessa.add(x.getCodigo() + "-" +x.getDescricao().toUpperCase()));
		comboBoxFormatoRemessa.setItems(this.formatoRemessa);
	}
	
	public List<Cooperativa> obterCooperativas(){
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>();
		try {
			cooperativas = getApplication().consultarCooperativasWebService();
		} catch (URISyntaxException | JSONException e) {
			logger.error("ObterCooperativa: " + e.getMessage());
			//TODO : Colocar uma forma de devolver o erro pro usuario    
		}
		return cooperativas;
	}
	
	@FXML
	public void handleVoltar(){
		MainAppController controller = getMainApp().getController();
		controller.showMenuPrincipal(getMainApp(), getMainApp().getRootLayout());
	}
	
	public void handleSalvar(){
		Parametros parametros = getParametros();
		getApplication().salvar(parametros);
	}
	
	public Parametros getParametros(){
		Parametros parametros = new Parametros();
		
		parametros.setEmail(edtEmail.getText());
		parametros.setLogin(edtUsuario.getText());
		parametros.setSenha(edtSenha.getText());
		
		parametros.setCooperativa(getCriarCooperativa());
		parametros.setFormatoRemessa(getFormatoRemessa());
		
		return parametros;
	}

	public FormatoRemessa getFormatoRemessa() {
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

	public Cooperativa getCriarCooperativa() {
		SingleSelectionModel<String> selectionModel = comboBoxCooperativas.getSelectionModel();
		String item = selectionModel.getSelectedItem();
		
		if (!item.trim().isEmpty()) {
			String[] itens = item.split("-");
			System.out.println(itens[0]);
			Cooperativa cooperativa = getApplication().consultarCooperativaPorKey(Integer.valueOf(itens[0]));
			return cooperativa;
		}
		
		return null;
	}
	
	public void escreverMensagem(String mensagem, Boolean erro){
		msgAvisoUsuario.setText(mensagem);
	}
	
	public void handleTesteExecucao(){
		panelMensagem.getStyleClass().add("mensagem-usuario-sucess");
		escreverMensagem("TesteExecucao", false);
	}

	public ParametrosApplication getApplication() {
		return application;
	}

	public void setApplication(ParametrosApplication application) {
		this.application = application;
	}
}
