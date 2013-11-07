package net.codehustler.max7456.charwizard.newui;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.codehustler.max7456.charwizard.Logger;
import net.codehustler.max7456.charwizard.Max7456Charset;
import net.codehustler.max7456.charwizard.ui.MCMPanel;
import net.codehustler.max7456.charwizard.ui.MCMCharacterEditor;
import javax.swing.JPanel;
import javax.swing.JButton;

public class CharsetEditor extends JInternalFrame implements  InternalFrameListener {

	static int index = 0;
	private static final long serialVersionUID = 1L;
	
	private Max7456Charset charset;
	//private MCMCharacterEditor characterEditor;
	private MCMPanel charsetPanel;
	private PaintPanel paintPanel;
	
	
	
	public CharsetEditor() {
		super();
		this.charset = new Max7456Charset("unknown"+index);
		index++;
		setTitle(this.charset.getFilename());
		this.initialize();
	}
	
	public CharsetEditor(Max7456Charset charset){
		super();
		this.charset = charset;
		setTitle(this.charset.getFilename());
		this.initialize();
	}
	
	private void initialize() {
		
		this.setClosable(true);
		this.setIconifiable(true);
		this.setResizable(true);
		this.setMaximizable(true);
		
		this.setVisible(true);
		
		setBounds(0, 0, 600, 370);
		charsetPanel = new MCMPanel();
		charsetPanel.setCharset(this.charset);
		getContentPane().add(charsetPanel, BorderLayout.WEST);
		
		
		
		paintPanel = new PaintPanel();
		getContentPane().add(paintPanel, BorderLayout.CENTER);
		charsetPanel.addCharacterSelectionListener(paintPanel);
		
//		characterEditor = new MCMCharacterEditor();
//		characterEditor.setBounds(0, 0, 200, 300);
//		paintPanel.add(characterEditor);
		
		
		//charsetPanel.addCharacterSelectionListener(characterEditor);
		this.addInternalFrameListener(this);		
	}
	
	public void internalFrameActivated(InternalFrameEvent arg0) {
		Logger.Log("internalFrameActivated");
	}

	public void internalFrameClosed(InternalFrameEvent arg0) {
		Logger.Log("internalFrameClosed");
	}

	public void internalFrameClosing(InternalFrameEvent arg0) {
		Logger.Log("internalFrameClosing");
	}

	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		Logger.Log("internalFrameDeactivated");
	}

	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		Logger.Log("internalFrameDeiconified");
	}

	public void internalFrameIconified(InternalFrameEvent arg0) {
		Logger.Log("internalFrameIconified");
		setVisible(false);
	}

	public void internalFrameOpened(InternalFrameEvent arg0) {
		Logger.Log("internalFrameOpened");
	}

//	public void setBrushColor(Integer color) {
//		this.characterEditor.setBrushColor(color);
//	}
	
	
	public Max7456Charset getCharset() {
		return charset;
	}
	
//	public MCMCharacterEditor getCharacterEditor() {
//		return characterEditor;
//	}
	
	public MCMPanel getCharsetPanel() {
		return charsetPanel;
	}
}
