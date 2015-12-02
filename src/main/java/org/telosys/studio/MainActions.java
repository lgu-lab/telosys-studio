package org.telosys.studio;

import java.io.File;

import org.telosys.studio.commons.Const;
import org.telosys.studio.commons.Editor;
import org.telosys.studio.commons.MsgBox;
import org.telosys.studio.commons.ParentView;
import org.telosys.studio.commons.ParentViewTab;
import org.telosys.studio.commons.ViewUtil;
import org.telosys.studio.view.configuration.ConfigController;
import org.telosys.studio.view.configuration.ConfigEditor;
import org.telosys.studio.view.databases.DatabasesController;
import org.telosys.studio.view.databases.DatabasesEditor;
import org.telosys.studio.view.texteditor.SimpleTextEditor;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainActions {

	private String  _workspacePath ;
	private Stage   _stage;
	private TabPane _tabPane ;
	
	protected void init(String workspacePath, Stage stage, TabPane tabPane) {
		this._stage = stage ;
		this._tabPane = tabPane;
		this._workspacePath = workspacePath;
	}
	
	public void exit() {
		System.out.println("exit()");
		_stage.close();
	}
	
	public void showConfigurationView() {
		String fileAbsolutePath = _workspacePath + "/" + Const.TELOSYS_TOOLS_CFG ;
		showConfigurationView(new File(fileAbsolutePath));
	}
	
	public void showConfigurationView(File file) {
		System.out.println("showConfigurationView() " + file.getAbsolutePath());
		if ( checkAlreadyOpen(file) == false) {
			ConfigEditor configEditor = new ConfigEditor(file);
			Node view = ViewUtil.loadView("Config.fxml", new ConfigController() );
			
			Tab tab = new Tab();
	        tab.setText("Configuration");
	        tab.setContent(view);
	        tab.setUserData(configEditor);
			_tabPane.getTabs().add(tab);
		    _tabPane.getSelectionModel().select(tab); // set this tab as the "active" tab
		}
	}

	public void showDatabasesView() {
		String fileAbsolutePath = _workspacePath + "/" + Const.TELOSYS_TOOLS_DATABASES_DBCFG ;
		showDatabasesView(new File(fileAbsolutePath));
	}
	public void showDatabasesView(File file) {
		System.out.println("showDatabasesView() " + file.getAbsolutePath() );
		if ( checkAlreadyOpen(file) == false) {			
			DatabasesEditor editor = new DatabasesEditor(file);
			Node view = ViewUtil.loadView("Databases.fxml", new DatabasesController() );
			
			Tab tab = new Tab();
	        tab.setText("Databases");
	        tab.setContent(view);
	        tab.setUserData(editor);
			_tabPane.getTabs().add(tab);
		    _tabPane.getSelectionModel().select(tab); // set this tab as the "active" tab
		}
	}

	public void showEditorView(File file) {
		System.out.println("showTextEditorView() "+ file.getAbsolutePath());
		if ( checkAlreadyOpen(file) == false) {
			
			if ( file.getName().equals(Const.TELOSYS_TOOLS_CFG) ) {
				showConfigurationView(file);
			}
			else if ( file.getName().endsWith(Const.EXT_DBCFG) ) {
				showDatabasesView(file);
			}
			else {
			    // Create a tab to house the new editor
			    Tab tab = new Tab();

			    ParentView parentView = new ParentViewTab(tab);
			    // Create the editor with this content and store it
			    SimpleTextEditor editor = new SimpleTextEditor(parentView, file);

			    tab.setUserData(editor);
			    tab.setText(file.getName());
			    tab.setContent(editor.getTextArea());
			    _tabPane.getTabs().add(tab);
			    _tabPane.getSelectionModel().select(tab); // set this tab as the "active" tab
			}
		}
	}

	private boolean checkAlreadyOpen(File file) {
		ObservableList<Tab> tabs = _tabPane.getTabs() ;
        if ( tabs.size() > 0 ) {
	        for ( Tab tab : tabs ) {
	        	Object userData = tab.getUserData();
	        	if ( userData instanceof Editor ) {
	        		Editor editor = (Editor)userData;
	        		File fileOpen = editor.getFile();
	        		if ( file.getAbsolutePath().equals(fileOpen.getAbsolutePath())) {
	        			// Found already open => just select the tab 
	        			_tabPane.getSelectionModel().select(tab);
	        			return true ;
	        		}
	        	}
	        }
        }
       	return false ;
	}
	
	public void closeCurrentFile() {
		System.out.println("closeCurrentFile()");
		
        if ( _tabPane.getTabs().size() > 0 ) {
        	Tab selectedTab = _tabPane.getSelectionModel().getSelectedItem();        	
	        if ( _tabPane.getTabs().contains(selectedTab) ) {
	        	// TODO 
	        	// check is file modified and need "save"
	            _tabPane.getTabs().remove(selectedTab);
	        }
	        else {
	        	throw new RuntimeException("Current tab not found");
	        }
        }
	}

	public void closeAll() {
		System.out.println("closeAll()");
		
		ObservableList<Tab> tabs = _tabPane.getTabs() ;
        if ( tabs.size() > 0 ) {
	        for ( Tab tab : tabs ) {
	        	// TODO 
	        	// check is file modified and need "save"
	            
	        }
            tabs.clear();
        }
	}

	public void saveCurrentFile() {
		System.out.println("saveCurrentFile()");
		
        if ( _tabPane.getTabs().size() > 0 ) {
	        Tab selectedTab = _tabPane.getSelectionModel().getSelectedItem();
	        Object tabData = selectedTab.getUserData();
	        if ( tabData instanceof Editor ) {
	        	Editor editor = (Editor) tabData ;
	        	editor.saveFileContent();
	        }
	        else {
	        	throw new RuntimeException("Unknown editor");
	        }
        }
	}

	public void status() {
		System.out.println("status()");
		
		
		ObservableList<Tab> tabs = _tabPane.getTabs();
		StringBuilder sb = new StringBuilder() ;
		sb.append("Tabs count : " + tabs.size() + "\n" );
        if ( tabs.size() > 0 ) {
	        for ( Tab tab : tabs ) {
	        	Object tabData = tab.getUserData();
		        if ( tabData instanceof SimpleTextEditor ) {
		        	SimpleTextEditor editor = (SimpleTextEditor) tabData ;
		        	sb.append(" Text editor : file = " + editor.getFile().getName() + "\n" );
		        }
		        else {
		        	sb.append(" Other : file = " + "???" + "\n" );
		        }
	        }
        }

//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Status ");
//		alert.setHeaderText(null);
//		alert.setContentText(sb.toString());
//		alert.showAndWait();

		MsgBox.info("Status", sb.toString());
	}

	public void about() {
		System.out.println("about()");
		
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("About Telosys Studio");
//		alert.setHeaderText(null);
//		alert.setContentText("My message");
//
//		alert.showAndWait();
		MsgBox.info("About Telosys Studio", "My message");
	}

}
