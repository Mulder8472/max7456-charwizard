package net.codehustler.max7456.charwizard.newui;

import java.util.List;

import javax.swing.JDesktopPane;

import net.codehustler.max7456.charwizard.ui.util.CollectionChangedListener;

public class Desktop extends JDesktopPane implements CollectionChangedListener<CharsetEditor> {

	
	
	public void itemAdded(List<CharsetEditor> list, CharsetEditor charsetEditor) {
		// TODO Auto-generated method stub
		this.add(charsetEditor);
	}

	public void itemRemoved(List<CharsetEditor> list, CharsetEditor charsetEditor) {
		// TODO Auto-generated method stub
		
	}

}
