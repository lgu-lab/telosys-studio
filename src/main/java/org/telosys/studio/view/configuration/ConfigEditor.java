package org.telosys.studio.view.configuration;

import java.io.File;

import org.telosys.studio.commons.Editor;

public class ConfigEditor implements Editor {

    private final File     file ;
    
	public ConfigEditor(File file) {
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
