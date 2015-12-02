package org.telosys.studio.commons;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MsgBox {
	
	private static void alert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static void info(String message) {
		alert(AlertType.INFORMATION, "Information", message);
	}
	public static void info(String title, String message) {
		alert(AlertType.INFORMATION, title, message);
	}

	public static void error(String message) {
		alert(AlertType.ERROR, "Error", message);
	}
	public static void error(String title, String message) {
		alert(AlertType.ERROR, title, message);
	}
}
