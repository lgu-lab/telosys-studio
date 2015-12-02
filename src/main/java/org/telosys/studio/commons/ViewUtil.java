package org.telosys.studio.commons;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ViewUtil {
	
	private static URL findFile( String fxmlFileName ) {
		URL location = ViewUtil.class.getResource("/views/"+fxmlFileName);
		if ( location == null ) {
			MsgBox.error("Cannot find '" + fxmlFileName + "'");
			return null ;
		}
		return location ;
	}
	
	public static Node loadView( String fxmlFileName, FxmlController controller) {
		System.out.println("getLocation('" + fxmlFileName + "')" );

		Node view = null ;
		URL location = findFile(fxmlFileName);
		if ( location != null ) {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(location);
	        loader.setController(controller);
	        try {
				view =  loader.load();
			} catch (IOException e) {
				MsgBox.error("Cannot load '" + fxmlFileName + "' (IOException)");
				//return null ;
			}
		}
		return view ;
	}

}
