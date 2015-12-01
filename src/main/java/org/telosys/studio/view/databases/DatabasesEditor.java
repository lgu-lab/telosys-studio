package org.telosys.studio.view.databases;

import java.io.File;

import org.telosys.studio.commons.Editor;

public class DatabasesEditor implements Editor {

    private final File     file ;
    
	public DatabasesEditor(File file) {
		super();
		this.file = file;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void saveFileContent() {
		// TODO Auto-generated method stub
		
	}

}
