package br.com.netsuprema.view;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.com.netsuprema.application.ParametrosApplication;
import br.com.netsuprema.application.RetornosApplication;
import br.com.netsuprema.application.dto.RetornoLiquidacaoDto;
import br.com.netsuprema.utils.ConfigUtils;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RetornosController extends AbstractController{

	@FXML
	private JFXButton btnVoltar;
	@FXML
	private JFXButton btnSalvar;
	@FXML
	private TextField edtDiretorio;
	@FXML
	private ImageView imgLogo;
	@FXML
	private DatePicker edtDataInicial;
	@FXML
	private DatePicker edtDataFinal;
	
	@FXML
	private TableView<RetornoLiquidacaoDto> retornoTable;
	
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> codCedenteColumn;
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> contaColumn;
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> dataRetornoColumn;
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> situacaoColumn;
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> detalheColumn;
	@FXML
	private TableColumn<RetornoLiquidacaoDto, String> logColumn;
	
	private ObservableList<RetornoLiquidacaoDto> retornosData = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		initializeComponents();
		inicializarDiretorio();
		inicializarTabela();
		inicializarDatas();
	}

	private void inicializarDatas() {
		LocalDate dataAtual = LocalDate.now();
		try {
			edtDataInicial.setValue(LocalDate.of(dataAtual.getYear(), dataAtual.getMonth(), dataAtual.getDayOfMonth() - 1));
			edtDataFinal.setValue(LocalDate.of(dataAtual.getYear(), dataAtual.getMonth(), dataAtual.getDayOfMonth() - 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void inicializarTabela() {
		codCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCedente().getCodigoProperty());
		contaColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroContaProperty());
		dataRetornoColumn.setCellValueFactory(cellData -> cellData.getValue().getLogEnvioRetornoLiquidacao().getDataRetornoProperty());
		situacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getLogEnvioRetornoLiquidacao().getSituacaoProperty());
		detalheColumn.setCellValueFactory(cellData -> cellData.getValue().getLogEnvioRetornoLiquidacao().getDetalheSituacaoProperty());
		logColumn.setCellValueFactory(cellData -> cellData.getValue().getLogEnvioRetornoLiquidacao().getLogProcessamentoProperty());
		
		retornoTable.setItems(this.retornosData);
	}

	private void initializeComponents(){
		initializeComponenteBtnVoltar();
		imgLogo.setImage(new Image(ConfigUtils.PATH_RESOURCE_PADRAO+"imagens/logo.png"));
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
	
	public void inicializarDiretorio(){
		String diretorio = listarDiretorio();
		edtDiretorio.setText(diretorio);
	}
	
	private String listarDiretorio() {
		try {
			ParametrosApplication application = new ParametrosApplication();
			String diretorio = application.consultarDiretorioRetornos();
			return diretorio;
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Erro ao realizar tentar consultar o diretório dos arquivos de retorno")
			 		 .append("Cause: " + e.getCause().getMessage())
			 		 .append("Mensagem: " + e.getMessage());
			ViewUtils.exibirMensagemErro("Erro", "", exception.toString());
		}
		return "";
	}

	public void handleSalvar(){
		if (validarDiretorioRetorno(edtDiretorio.getText().trim())) {
			salvarDiretorioRetornos();
		}
	}

	private void salvarDiretorioRetornos() {
		try {
			ParametrosApplication application = new ParametrosApplication();
			String diretorioRetorno = edtDiretorio.getText();
			application.salvarDiretorioRetorno(diretorioRetorno);
			ViewUtils.exibirMensagemSucesso("Sucesso", "", "Diretório retorno salvo com sucesso.");
		} catch (Exception e) {
			StringBuilder exception = new StringBuilder();
			exception.append("Cause: " + e.getCause().getMessage())
					 .append("Mensagem: " + e.getMessage());
			
			ViewUtils.exibirMensagemErro("Erro", "Erro ao realizar tentar salvar o diretório dos arquivos de retorno", exception.toString());
		}
	}
	
	public boolean validarDiretorioRetorno(String diretorio){
		if (!new File(diretorio).isDirectory()) {
			ViewUtils.exibirMensagemErro("Inválido", "Diretório inválido.", "O diretório do arquivo e inválido. Selecione um diretório valido para prosseguir");
			return false;
		}
		
		return true;
	}
	
	public List<RetornoLiquidacaoDto> pesquisar(LocalDate dataInicial, LocalDate dataFinal){
		RetornosApplication application = new RetornosApplication();
		List<RetornoLiquidacaoDto> retornos = application.consultarRetornosPorPeriodo(dataInicial, dataFinal);
		return retornos;
	}
	
	public void preencherTableRetornos(){
		if ((edtDataFinal.getValue() != null) && (edtDataInicial.getValue() != null)) {
			retornosData.clear();
			List<RetornoLiquidacaoDto> retornos = pesquisar(edtDataInicial.getValue(), edtDataFinal.getValue());
			retornos.stream().forEach(x->retornosData.add(x));
		}else{
			ViewUtils.exibirMensagemAlerta("Atenção", "", "Selecione um periodo para realizar a pesquisa.");
		}
	}
	
	public void handlePesquisar(){
		preencherTableRetornos();
	}
}
