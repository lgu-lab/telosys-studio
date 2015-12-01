package org.telosys.studio.view.workspace;

import java.io.File;

public class ProjectFile {

	private final File   file ;

	public ProjectFile(File file) {
		super();
		this.file = file;
	}

	public File getFile() {
		return file ;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}
	
}
