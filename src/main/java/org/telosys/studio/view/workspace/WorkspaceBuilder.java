package org.telosys.studio.view.workspace;

import java.io.File;

import org.telosys.studio.MainActions;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class WorkspaceBuilder {
	
	private final MainActions _mainActions ;

	public WorkspaceBuilder(MainActions _mainActions) {
		super();
		this._mainActions = _mainActions;
	}

	public TreeView<ProjectFile> buildTreeView(String workspacePath) {
		
		File workspaceRoot = new File(workspacePath);
		// TODO check existence
		ProjectFile workspaceRootProjectFile = new ProjectFile(workspaceRoot);
		
		TreeItem<ProjectFile> workspaceRootItem = buildTreeItem(workspaceRootProjectFile, 1);
		
		TreeView<ProjectFile> workspaceTreeView = new TreeView<ProjectFile>(workspaceRootItem);
		workspaceTreeView.setShowRoot(false);
		
		workspaceTreeView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {            
		        if(mouseEvent.getClickCount() == 2)
		        {
		            TreeItem<ProjectFile> item = workspaceTreeView.getSelectionModel().getSelectedItem();
		            File file = item.getValue().getFile() ;
		            System.out.println("Double click on file : " + file.getAbsolutePath() );
		            if ( file.isFile() ) {
			            _mainActions.showEditorView(file);
		            }
		        }
		    }
		});		
		return workspaceTreeView ;
	}
	
	private TreeItem<ProjectFile> buildTreeItem(ProjectFile projectFile, int level) {
		
		TreeItem<ProjectFile> treeItem = new TreeItem<ProjectFile> (projectFile);
		
		treeItem.setExpanded( (level < 2 ));
        
		// Has children ?
		File treeItemFile = projectFile.getFile();
		if ( treeItemFile.isDirectory() ) {
			File[] files = treeItemFile.listFiles();
			if (files != null) {
				for (File childFile : files) {
					ProjectFile childProjectFile = new ProjectFile(childFile);
		            TreeItem<ProjectFile> item = buildTreeItem(childProjectFile, level+1);
					treeItem.getChildren().add(item);
				}
			}
		}
		return treeItem;
	}
	
	
}
