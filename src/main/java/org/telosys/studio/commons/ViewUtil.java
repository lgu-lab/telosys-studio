package org.telosys.studio.commons;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ViewUtil {
	
	public static Node loadView( String fxmlFileName, FxmlController controller) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("getLocation('" + fxmlFileName + "')" );
		URL location = controller.getClass().getResource(fxmlFileName);
		System.out.println("getLocation('" + fxmlFileName + "') : " + location );
		
        loader.setLocation( location );
        loader.setController(controller);
        
        try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot load " + fxmlFileName );
		}
	}

}
