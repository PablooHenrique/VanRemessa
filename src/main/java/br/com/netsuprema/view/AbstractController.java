package br.com.netsuprema.view;

import br.com.netsuprema.MainApp;
import br.com.netsuprema.controller.MainAppController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AbstractController {
	protected MainApp mainApp;
	
	@FXML
	public void handleVoltar(){
		MainAppController controller = getMainApp().getController();
		controller.showMenuPrincipal(getMainApp(), getMainApp().getRootLayout());
	}
	
	protected void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
	
	protected void addUpperCaseText(final TextField tf) {
		tf.textProperty().addListener((ov, oldValue, newValue) -> {
			tf.setText(newValue.toUpperCase());
		});
	}
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
