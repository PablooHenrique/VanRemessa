package br.com.netsuprema.view;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.DiretorioEnvioApplication;
import br.com.netsuprema.application.dto.CedenteDto;
import br.com.netsuprema.utils.ConfigUtils;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiretoriosEnvioController extends AbstractController{
	
	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnBuscar;
	@FXML
	private JFXButton btnRemover;
	@FXML
	private JFXButton btnAbrirDiretorio;
	@FXML
	private ImageView imgLogo;
	@FXML
	private JFXButton btnAdicionarDiretorio;
	@FXML
	private TextField edtNome;
	@FXML
	private TextField edtCodigo;
	@FXML
	private TableView<CedenteDto> cedentesTable;
	@FXML
	private TableColumn<CedenteDto, String> codigoCedenteColumn;
	@FXML
	private TableColumn<CedenteDto, String> nomeColumn;
	@FXML
	private TableColumn<CedenteDto, String> diretorioColumn;
	
	private ObservableList<CedenteDto> cedentesData = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		initializeComponents();
		inicializarTabela();
	}
	
	private void inicializarTabela() {
		codigoCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCodigoProperty());
		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		diretorioColumn.setCellValueFactory(cellData -> cellData.getValue().getDiretorioPadraoProperty());
		
		cedentesTable.setItems(this.cedentesData);
	}

	public void initializeComponents(){
		inicializarBtnVoltar();
		initializeComponenteBtnAdicionarDiretorio();
		inicializarEdts();
	}

	private void inicializarEdts() {
		addUpperCaseText(edtNome);
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
	
	private void initializeComponenteBtnAdicionarDiretorio() {
		btnAdicionarDiretorio.setText("");
		Label label = new Label("+");
		btnAdicionarDiretorio.getStyleClass().add("floatingFiltros");
		btnAdicionarDiretorio.setGraphic(label);
		
		btnAdicionarDiretorio.setPrefHeight(60);
		btnAdicionarDiretorio.setPrefWidth(60);
		label.getStyleClass().add("floatingfontsize");
		btnAdicionarDiretorio.setButtonType(ButtonType.RAISED);
		btnAdicionarDiretorio.getStyleClass().add("animated-option-button");
	} 
	
	public void handleAdicionarDiretorioEnvio(){
		adicionarDiretorioEnvio();
	}
	
	public void handleBuscarCedentes(){
		buscarCedentes();
	}
	
	public void handleExcluirCedente(){
		removerCedente();
	}
	
	public void handleAbrirDiretorio(){
		int selectedIndex = cedentesTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			CedenteDto cedente = cedentesTable.getItems().get(selectedIndex);
	    	String codigo = cedente.getCodigo();
	    	Integer cod = ViewUtils.converterEntradaStringParaInteger(codigo);
	    	abrirDiretorio(cod);
		}else {
	    	ViewUtils.exibirMensagemAlerta("Nenhuma seleção", "Nenhum cedente selecionado", "Por favor, selecione um cedente na tabela.");
	    }
	}

	private void abrirDiretorio(Integer codigo) {
		DiretorioEnvioApplication application = new DiretorioEnvioApplication();
		application.abrirDiretorio(codigo);
		
	}

	private void removerCedente() {
		try {
			int selectedIndex = cedentesTable.getSelectionModel().getSelectedIndex();
		    if (selectedIndex >= 0) {
		    	CedenteDto cedente = cedentesTable.getItems().get(selectedIndex);
		    	String codigo = cedente.getCodigo();
		    	Integer cod = ViewUtils.converterEntradaStringParaInteger(codigo);
		    	excluirCedente(cod);
		    	atualizarTabela();
		    	ViewUtils.exibirMensagemSucesso("Sucesso", "Removido com sucesso", "Cedente: " + cedente.getNome());
		    } else {
		    	ViewUtils.exibirMensagemAlerta("Nenhuma seleção", "Nenhum cedente selecionado", "Por favor, selecione um cedente na tabela.");
		    }
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Falha ao realizar a exclusão do cedente", "Motivo: " + e.getMessage());
		}
	}

	private void atualizarTabela() {
		List<CedenteDto> cedentes = listarCedentes();
		preencherTabelaCedentes(cedentes);
	}

	private void excluirCedente(Integer codigo) throws Exception {
		new DiretorioEnvioApplication().excluirCedente(codigo);
	}

	private void buscarCedentes() {
		List<CedenteDto> cedentes = new ArrayList<CedenteDto>();
		
		if (!edtNome.getText().trim().isEmpty()) {
			cedentes = listarCedentes(edtNome.getText().trim());
		}else if (!edtCodigo.getText().trim().isEmpty()) {
			try {
				Integer codigo = ViewUtils.converterEntradaStringParaInteger(edtCodigo.getText());
				CedenteDto cedente = listarCedentes(codigo);
				cedentes.add(cedente);
			} catch (NumberFormatException e) {
				ViewUtils.exibirMensagemAlerta("Atenção", "Código cedente inválido", "o código do cedente deve conter apenas numeros");
				cedentes = listarCedentes();
			}
		}else{
			cedentes = listarCedentes();
		}
		
		preencherTabelaCedentes(cedentes);
	}

	private void adicionarDiretorioEnvio() {
		CedenteDto cedente = new CedenteDto();
		boolean salvou = mainApp.getController().showCadastroDiretoriosEnvioDialog(getMainApp(), mainApp.getRootLayout(), cedente);
		if (salvou) {
			List<CedenteDto> cedentesDto = listarCedentes();
			preencherTabelaCedentes(cedentesDto);
		}
	}

	private void preencherTabelaCedentes(List<CedenteDto> cedentesDto) {
		cedentesData.clear();
		cedentesDto.stream().forEach(x->cedentesData.add(x));
	}

	private List<CedenteDto> listarCedentes() {
		return new DiretorioEnvioApplication().listarCedentes();
	}
	
	private List<CedenteDto> listarCedentes(String nome) {
		return new DiretorioEnvioApplication().listarCedentes(nome);
	}
	
	private CedenteDto listarCedentes(int codigo) {
		return new DiretorioEnvioApplication().listarCedentes(codigo);
	}
}
