package br.com.netsuprema.view.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ViewUtils {

	public static Integer converterEntradaStringParaInteger(String text) throws NumberFormatException {
		return Integer.parseInt(text.trim());
	}

	public static void validarTamanhoString(String text, int i) throws Exception {
		if(text.trim().length() != 1){
			throw new Exception("Tamanho da string inválido");
		}
	}
	
	public static void exibirMensagemErro(String title, String header, String mensagem) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(title);
        error.setHeaderText(header);
		error.setContentText(mensagem);
		error.showAndWait();
	}

	public static void exibirMensagemAlerta(String title, String header, String mensagem) {
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(mensagem);

        alert.showAndWait();
	}

	public static void exibirMensagemSucesso(String title, String header, String mensagem) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(mensagem);

        alert.showAndWait();
	}

}
