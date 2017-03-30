package br.com.netsuprema.view;

import br.com.netsuprema.application.dto.RemessaDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DetalhesRemessaController extends AbstractController{
	
	@FXML
	private TableView<RemessaDto> cedenteTable;
	@FXML
	private TableColumn<RemessaDto, String> codigoCedenteColumn;
	@FXML
	private TableColumn<RemessaDto, String> nomeCedenteColumn;
	@FXML
	private TableColumn<RemessaDto, String> contaColumn;
	
	@FXML
	private TableView<RemessaDto> remessaTable;
	@FXML
	private TableColumn<RemessaDto, String> dataRemessaColumn;
	@FXML
	private TableColumn<RemessaDto, String> codigoLoteAgendamentoRemessaColumn;
	@FXML
	private TableColumn<RemessaDto, String> situacaoRemessaColumn;
	@FXML
	private TableColumn<RemessaDto, String> erroRemessaColumn;

	@FXML
	private TableView<RemessaDto> processamentoTable;
	@FXML
	private TableColumn<RemessaDto, String> dataEnvioProcessamentoColumn;
	@FXML
	private TableColumn<RemessaDto, String> statusProcessamentoColumn;
	@FXML
	private TableColumn<RemessaDto, String> detalheProcessamentoColumn;
	
	private RemessaDto remessa;
	
	private ObservableList<RemessaDto> cedenteData = FXCollections.observableArrayList();
	private ObservableList<RemessaDto> remessaData = FXCollections.observableArrayList();
	private ObservableList<RemessaDto> processamentoData = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		
	}
	
	public void preencherTableCedente(){
		codigoCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCedente().getCodigoProperty());
		nomeCedenteColumn.setCellValueFactory(cellData -> cellData.getValue().getCedente().getNomeProperty());
		contaColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroConta());
		
		cedenteTable.setItems(cedenteData);
	}
	
	public void preencherTableRemessa(){
		dataRemessaColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getDataHoraEnvio());
		codigoLoteAgendamentoRemessaColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getCodigoLoteAgendamento());
		situacaoRemessaColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getSituacao());
		erroRemessaColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getDetalheErro());
		
		remessaTable.setItems(remessaData);
	}
	
	public void preencherTableProcessamento(){
		dataEnvioProcessamentoColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getDhProcessamento());
		statusProcessamentoColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getStatusProcessamento());
		detalheProcessamentoColumn.setCellValueFactory(cellData -> cellData.getValue().getLog().getLogProcessamento());
		
		processamentoTable.setItems(processamentoData);
	}
	
	public void inicializarTables(){
		cedenteData.add(remessa);
		remessaData.add(remessa);
		processamentoData.add(remessa);
		
		preencherTableCedente();
		preencherTableProcessamento();
		preencherTableRemessa();
	}

	public RemessaDto getRemessa() {
		return remessa;
	}

	public void setRemessa(RemessaDto remessa) {
		this.remessa = remessa;
	}
}
