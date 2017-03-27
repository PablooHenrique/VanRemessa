package br.com.netsuprema.view;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDrawer;

import br.com.netsuprema.application.RemessasApplication;
import br.com.netsuprema.application.dto.RemessaDto;
import br.com.netsuprema.utils.ConfigUtils;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	
	private ObservableList<RemessaDto> remessasData = FXCollections.observableArrayList();
	
	public EnviosDetalhadosController() {
		
	}
	
	@FXML
	public void initialize(){
		initializeComponents();
		inicializarTabela();
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
		remessasData.clear();
		List<RemessaDto> remessas = new RemessasApplication().listar();
		remessas.stream().forEach(x->remessasData.add(x));
	}
	
	public void handleExibirDetalhes(){
		int selectedIndex = remessasTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			RemessaDto remessa = new RemessasApplication().listar(selectedIndex);
			mainApp.getController().showCadastroDetalhesRemessaDialog(getMainApp(), mainApp.getRootLayout(), remessa);	
		}else {
	    	ViewUtils.exibirMensagemAlerta("Nenhuma seleção", "Nenhuma remessa selecionada", "Por favor, selecione uma remessa na tabela.");
	    }
	}
}
