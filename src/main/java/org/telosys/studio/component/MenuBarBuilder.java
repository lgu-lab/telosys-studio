package org.telosys.studio.component;

import org.telosys.studio.MainActions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuBarBuilder {
	
	private final MainActions _mainActions ;

	public MenuBarBuilder(MainActions _mainActions) {
		super();
		this._mainActions = _mainActions;
	}

	public MenuBar buildMenuBar() {
		/* Create a new MenuBar. */
		MenuBar menuBar = new MenuBar();
		
		/* Create new sub menus. */
		menuBar.getMenus().add( buildFileMenu() );
		menuBar.getMenus().add( buildConfigurationMenu() );
		menuBar.getMenus().add( buildHelpMenu() );
		
		return menuBar ;
	}
	
	private Menu buildConfigurationMenu() {
		Menu menu = new Menu("Project");
		
		MenuItem menuItem ;
		
		//--- 
		menuItem = new MenuItem("Configuration");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_mainActions.showConfigurationView();
			}
		});
		menu.getItems().add(menuItem);
		
		//--- 
		menuItem = new MenuItem("Databases");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_mainActions.showDatabasesView();
			}
		});
		menu.getItems().add(menuItem);
		
		return menu ;
	}

	private Menu buildFileMenu() {
		Menu menu = new Menu("File");
		
		MenuItem menuItem ;
		
		//--- 
		menuItem = new MenuItem("Close");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				_mainActions.closeCurrentFile();
            }
		});
		menu.getItems().add(menuItem);

		//--- 
		menuItem = new MenuItem("Close All");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				_mainActions.closeAll();
            }
		});
		menu.getItems().add(menuItem);

		//--- 
		menu.getItems().add( new SeparatorMenuItem() );
		
		//--- 
		menuItem = new MenuItem("Save");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				_mainActions.saveCurrentFile();
            }
		});
		menu.getItems().add(menuItem);

		//--- 
		menuItem = new MenuItem("Save All");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				//_mainActions.saveAll();
            }
		});
		menu.getItems().add(menuItem);

		//--- 
		menu.getItems().add( new SeparatorMenuItem() );
		
		//--- 
		menuItem = new MenuItem("Refresh");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				// TODO
            }
		});
		menu.getItems().add(menuItem);
		
		//--- 
		menu.getItems().add( new SeparatorMenuItem() );
		
		//--- 
		menuItem = new MenuItem("Switch Workspace");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				// TODO
            }
		});
		menu.getItems().add(menuItem);
		
		//--- 
		menu.getItems().add( new SeparatorMenuItem() );
		
		//--- 
		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent t) {
				_mainActions.exit();
            }
		});
		menu.getItems().add(menuItem);
		
		return menu;
	}

	private Menu buildHelpMenu() {
		Menu menuHelp = new Menu("Help");
		
		MenuItem menuItem ;
		
		//--- 
		menuItem = new MenuItem("Status");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_mainActions.status();
			}
		});
		menuHelp.getItems().add(menuItem);
		
		//--- 
		menuItem = new MenuItem("About");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_mainActions.about();
			}
		});
		menuHelp.getItems().add(menuItem);
		
		
		return menuHelp;
	}
}
