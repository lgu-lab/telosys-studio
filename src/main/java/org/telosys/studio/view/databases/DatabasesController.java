package org.telosys.studio.view.databases;

import java.net.URL;
import java.util.ResourceBundle;

import org.telosys.studio.commons.FxmlController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DatabasesController extends FxmlController implements Initializable {

	@FXML 
	private Button myButton ;
	
	@FXML 
	private void doSomething(ActionEvent actionEvent) {
		System.out.println("Click");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize...");
		
	}

}
