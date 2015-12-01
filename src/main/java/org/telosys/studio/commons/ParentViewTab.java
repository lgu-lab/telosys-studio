package org.telosys.studio.commons;

import javafx.scene.control.Tab;

public class ParentViewTab implements ParentView {

	private final Tab tab ;
	
	
	public ParentViewTab(Tab tab) {
		super();
		this.tab = tab;
	}


	@Override
	public void setModified() {
		String s = tab.getText() ;
		tab.setText("* " + s );
	}

}
