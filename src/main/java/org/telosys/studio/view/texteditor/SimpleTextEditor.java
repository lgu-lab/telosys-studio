package org.telosys.studio.view.texteditor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.telosys.studio.commons.Editor;
import org.telosys.studio.commons.ParentView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 */
public class SimpleTextEditor implements Editor {
	
//	private final static String TEXT_EDITOR_CSS = 
//			  "-fx-font: \"Courier New\"; "
//			+ "-fx-font-family: \"Courier New\"; "
//			//+ "-fx-font-weight: bold; "
//			+ "-fx-font-size: 12pt;"
//			;
	private final static String TEXT_EDITOR_CSS = 
			  "-fx-font-family: \"Courier New\"; "
			//+ "-fx-font-weight: bold; "
			+ "-fx-font-size: 12pt;"
			;
    private final ParentView parentView ; 
    private final TextArea textArea ; 
    private final File     file ;

    private boolean  modified = false;

    public SimpleTextEditor(ParentView parentView, File file) {
		super();
		this.textArea = new TextArea();
		this.textArea.setStyle(TEXT_EDITOR_CSS);
		//this.textArea.setOnKeyReleased(getKeyEventHandler());
		this.modified = false;
		this.file = file;
		this.parentView = parentView;
		loadFileContent();
	}

    @Override
    public File getFile() {
        return file;
    }
    public TextArea getTextArea() {
        return textArea;
    }
	public boolean isModified() {
        return modified;
    }
//    public void setModified(boolean modified) {
//        this.modified = modified;
//    }

    private void loadFileContent() {
        //String openFileName = file.getAbsolutePath();
        StringBuffer sb = new StringBuffer();
        try ( FileInputStream fis = new FileInputStream(file);
              BufferedInputStream bis = new BufferedInputStream(fis) ) {
            while ( bis.available() > 0 ) {
                sb.append((char)bis.read());
            }
        }
        catch ( Exception e ) {
            throw new RuntimeException("Cannot load file " + file.getName(), e);
        }
        textArea.setText( sb.toString() );
        
        // Set the change listener to detect text modification 
		this.textArea.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		        // this will run whenever text is changed
		    	//System.out.println("ChangeListener");
		    	setModified();
		    }
		});
    }

    @Override
    public void saveFileContent() {
    	try ( FileOutputStream fos = new FileOutputStream(file);
    			BufferedOutputStream bos = new BufferedOutputStream(fos) ) {
    		
			String text = textArea.getText();
			bos.write(text.getBytes());
			bos.flush();
		}
		catch ( Exception e ) {
            throw new RuntimeException("Cannot save file " + file.getName(), e);
		}
    }
    
    private void setModified() {
    	if ( ! modified ) {
        	modified = true ;
        	parentView.setModified() ;
    	}
    }
    
    private EventHandler<KeyEvent> getKeyEventHandler() {
    	return new EventHandler<KeyEvent>() {
    		
            public void handle(KeyEvent ke) {
                String text = ke.getText();
                KeyCode code = ke.getCode();
                System.out.println("onKeyReleased: code="+code+", text="+text);
                if ( code == KeyCode.BACK_SPACE || 
                     code == KeyCode.ENTER ||
                     code == KeyCode.DELETE ) {
                	setModified();
                }
                else if ( text != null && text.length() > 0 ) {
                	setModified();
                }
            }
        };
    }
}
