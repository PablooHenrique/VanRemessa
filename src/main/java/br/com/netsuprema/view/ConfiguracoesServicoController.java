package br.com.netsuprema.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ConfiguracoesServicoController extends AbstractController{
	
	@FXML
	private ComboBox<String> comboBoxCooperativas;
	
	ObservableList<String> cooperativas = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		cooperativas.add("Item Um");
		cooperativas.add("Item Dois");
		comboBoxCooperativas.setItems(this.cooperativas);
	}
}
