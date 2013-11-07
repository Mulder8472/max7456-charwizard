package net.codehustler.max7456.charwizard.newui;

import static net.codehustler.max7456.charwizard.Logger.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;

import net.codehustler.max7456.charwizard.Max7456Charset;
import net.codehustler.max7456.charwizard.Pixel;


public class Max7456CharwizardActions {

	private final Max7456CharwizardUI max7456CharwizardUI;

	public Max7456CharwizardActions(Max7456CharwizardUI max7456CharwizardUI) {
		this.max7456CharwizardUI = max7456CharwizardUI;
	}


	public void openRecentFileHandler(ActionEvent evt) {
		Log("open: " + evt.getActionCommand());
		Max7456Charset cs = new Max7456Charset(evt.getActionCommand());
		try {
			cs.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CharsetEditor ce= new CharsetEditor(cs);
		max7456CharwizardUI.addEditor(ce);	
		
		try {
			ce.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	
	public void newCharset() {
		Log("newCharset");
		CharsetEditor ce= new CharsetEditor();
		max7456CharwizardUI.addEditor(ce);
	}
	
	public void saveCharset() {
		Log("saveCharset");
	}
	
	public void openCharset() {
		Log("openCharset");
	}
	
	public void setBrushColorWhite(){
		Log("setBrushColorWhite");
//		CharsetEditor ce = max7456CharwizardUI.getActiveEditor();
//		if ( ce != null ) {
//			ce.setBrushColor(Pixel.WHITE);
//		}
	}
	
	public void setBrushColorBlack(){
		Log("setBrushColorBlack");
//		CharsetEditor ce = max7456CharwizardUI.getActiveEditor();
//		if ( ce != null ) {
//			ce.setBrushColor(Pixel.BLACK);
//		}
	}

	public void setBrushColorTransparent(){
		Log("setBrushColorTransparent");
//		CharsetEditor ce = max7456CharwizardUI.getActiveEditor();
//		if ( ce != null ) {
//			ce.setBrushColor(Pixel.TRANSPARENT);
//		}
	}
	
	public void selectPixels() {
//		CharsetEditor ce = max7456CharwizardUI.getActiveEditor();
//		if ( ce != null ) {
//			ce.getCharacterEditor().selectAll();
//		}
	}
	
	public void invertSelection() {
//		CharsetEditor ce = max7456CharwizardUI.getActiveEditor();
//		if ( ce != null ) {
//			ce.getCharacterEditor().inverseSelection();
//		}
	}
}
