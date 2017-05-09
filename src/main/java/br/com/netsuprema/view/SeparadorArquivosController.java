package br.com.netsuprema.view;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import br.com.netsuprema.application.ParametrosApplication;
import br.com.netsuprema.application.SeparadorArquivosApplication;
import br.com.netsuprema.dominio.parametros.Parametros;
import br.com.netsuprema.dominio.parametros.SeparadorArquivos;
import br.com.netsuprema.view.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SeparadorArquivosController extends AbstractController {
	
	@FXML
	private JFXButton btnSelecionarDiretorio;
	@FXML
	private JFXButton btnSalvar;
	@FXML
	private TextField edtDiretorio;
	@FXML
	private CheckBox checkAtivarSeparadorArquivos;
	
	private Stage dialogStage;
	
	
	@FXML
	public void initialize() throws Exception{
		inicializarSeparadorArquivos();
	}
	
	private void inicializarSeparadorArquivos() {
		try {
			SeparadorArquivos separadorArquivos = carregarSeparadorArquivo();
			edtDiretorio.setText(separadorArquivos.getDiretorio());
			Parametros parametros = carregarParametros();
			checkAtivarSeparadorArquivos.setSelected(parametros.isUtilizaSeparadorArquivoPorPastasCedentes());
		} catch (Exception e) {
			ViewUtils.exibirMensagemErro("Erro", "Falha na consulta", "Motivo: " + e.getMessage());
		}
	}

	private Parametros carregarParametros() throws Exception {
		ParametrosApplication application = new ParametrosApplication();
		Parametros parametros = application.consultarParametros();
		return parametros;
	}

	private SeparadorArquivos carregarSeparadorArquivo() throws Exception {
		SeparadorArquivos separador = new SeparadorArquivosApplication().listarSeparadorArquivo();
		return separador;
	}

	@FXML
	public void handleSelecionarDiretorio(){
		DirectoryChooser dirChoser = new DirectoryChooser();
		dirChoser.setTitle("Selecione o diretorio");
		File file = dirChoser.showDialog(null);
		
		if (file != null) {
			this.edtDiretorio.setText(file.getAbsolutePath());
		}
	}
	
	@FXML
	public void handleSalvar(){
		salvarDadosSeparadorArquivos();
	}

	private void salvarDadosSeparadorArquivos() {
		if(diretorioEscolhidoEValido()){
			boolean ativarSeparador = checkAtivarSeparadorArquivos.isSelected();
			String diretorio = edtDiretorio.getText();
			
			SeparadorArquivosApplication application = new SeparadorArquivosApplication();
			
			try {
				application.salvarSeparadorArquivo(diretorio, ativarSeparador);
				ViewUtils.exibirMensagemSucesso("Sucesso", "", "Diretório salvo com sucesso");
				dialogStage.close();
			} catch (Exception e) {
				ViewUtils.exibirMensagemErro("Erro", "Falha ao salvar o diretorio escolhido", "Motivo: " + e.getMessage());
			}
		}
	}

	private boolean diretorioEscolhidoEValido() {
		if (edtDiretorio.getText().trim().isEmpty()) {
			return false;
		}
		
		return true;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public CheckBox getCheckAtivarSeparadorArquivos() {
		return checkAtivarSeparadorArquivos;
	}

	public void setCheckAtivarSeparadorArquivos(CheckBox checkAtivarSeparadorArquivos) {
		this.checkAtivarSeparadorArquivos = checkAtivarSeparadorArquivos;
	}
}
