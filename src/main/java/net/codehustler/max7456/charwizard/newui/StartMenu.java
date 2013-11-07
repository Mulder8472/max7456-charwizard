package net.codehustler.max7456.charwizard.newui;

import static net.codehustler.max7456.charwizard.Logger.Log;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.codehustler.max7456.charwizard.ui.util.CollectionChangedListener;

public class StartMenu extends JPanel implements CollectionChangedListener<CharsetEditor>, InternalFrameListener, ActionListener {
	
	
	private Map<CharsetEditor, JButton> buttons = new HashMap<CharsetEditor, JButton>();

	public StartMenu() {
		initialize();
	}


	private void initialize() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		setMinimumSize(new Dimension(1, 30));
		setPreferredSize(new Dimension(1, 30));
	}
	

	private void updateStartBar(List<CharsetEditor> list) {
		
	}

	public void itemAdded(List<CharsetEditor> list, CharsetEditor ce) {
		Log("Editor added: " + ce.getTitle());
		ce.addInternalFrameListener(this);
		JButton button = new JButton(ce.getTitle());
		button.setName(""+ce.hashCode());
		button.addActionListener(this);
		this.buttons.put(ce, button);
		add(button);
		updateUI();
	}

	public void itemRemoved(List<CharsetEditor> list, CharsetEditor o) {
//		JButton button = buttons.remove(o);
//		remove(button);
//		Log("----!!--");
	}


	public void internalFrameActivated(InternalFrameEvent e) {
		CharsetEditor ce = (CharsetEditor) e.getSource();
		JButton b = buttons.get(ce);
		b.setBackground(Color.red);
	}


	public void internalFrameClosed(InternalFrameEvent e) {
		CharsetEditor ce = (CharsetEditor) e.getSource();
		JButton b = buttons.get(ce);
		remove(b);
		updateUI();
	}


	public void internalFrameClosing(InternalFrameEvent e) {
		
	}


	public void internalFrameDeactivated(InternalFrameEvent e) {
		CharsetEditor ce = (CharsetEditor) e.getSource();
		JButton b = buttons.get(ce);
		b.setBackground(Color.white);
	}


	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Log("" + button.getName());
		
		CharsetEditor ce = findEditor(button);
		
		
		try {
			ce.setVisible(true);
			ce.setIcon(false);
			ce.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
	
	private CharsetEditor findEditor(int hashCode) {
		for ( CharsetEditor ce: buttons.keySet() ) {
			if ( ce.hashCode() == hashCode )
				return ce;
		}
		return null;
	}

	private CharsetEditor findEditor(JButton button) {
		for ( CharsetEditor ce: buttons.keySet() ) {
			if (buttons.get(ce) == button )
				return ce;
		}
		return null;
	}
}



