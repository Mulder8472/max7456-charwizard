package net.codehustler.max7456.charwizard.newui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import net.codehustler.max7456.charwizard.ui.util.UserSettings;

public class MenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private JMenu mnRecentFiles;
	private final Max7456CharwizardActions actions;

	public MenuBar(Max7456CharwizardActions actions) {
		this.actions = actions;
		this.initialize();
	}

	private void initialize() {
		JMenu mnFile = new JMenu("File");
		this.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.newCharset();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);
		
		mnFile.add(new JSeparator());
		
		mnRecentFiles = new JMenu("Recent Files");
		mnFile.add(mnRecentFiles);
		
		mnFile.add(new JSeparator());
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		this.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		mnEdit.add(new JSeparator());
		
		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				actions.selectPixels();
			}
		});
		mnEdit.add(mntmSelectAll);
		
		JMenuItem mntmInverseSelection = new JMenuItem("Invert Selection");
		mntmInverseSelection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmInverseSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				actions.invertSelection();
			}
		});
		mnEdit.add(mntmInverseSelection);
		
		JMenu mnTools = new JMenu("Tools");
		this.add(mnTools);
		
		JMenuItem mntmFillWhite = new JMenuItem("Fill White");
		mntmFillWhite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mnTools.add(mntmFillWhite);
		
		JMenuItem mntmFillBlack = new JMenuItem("Fill Black");
		mntmFillBlack.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnTools.add(mntmFillBlack);
		
		JMenuItem mntmFillTransparent = new JMenuItem("Fill Transparent");
		mntmFillTransparent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mnTools.add(mntmFillTransparent);
		
		mnTools.add(new JSeparator());
		
		JMenuItem mntmInvertColors = new JMenuItem("Invert Colors");
		mntmInvertColors.setAccelerator(KeyStroke.getKeyStroke("I"));
		mnTools.add(mntmInvertColors);
		
		mnTools.add(new JSeparator());

		JMenuItem mntmSetColorWhite = new JMenuItem("Set Color White");
		mntmSetColorWhite.setAccelerator(KeyStroke.getKeyStroke("W"));
		mntmSetColorWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				actions.setBrushColorWhite();
			}
		});
		mnTools.add(mntmSetColorWhite);
		
		JMenuItem mntmSetColorBlack = new JMenuItem("Set Color Black");
		mntmSetColorBlack.setAccelerator(KeyStroke.getKeyStroke("B"));
		mntmSetColorBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				actions.setBrushColorBlack();
			}
		});
		mnTools.add(mntmSetColorBlack);
		
		JMenuItem mntmSetColorTransparent = new JMenuItem("Set Color Transparent");
		mntmSetColorTransparent.setAccelerator(KeyStroke.getKeyStroke("T"));
		mntmSetColorTransparent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				actions.setBrushColorTransparent();
			}
		});
		mnTools.add(mntmSetColorTransparent);
		
		mnTools.add(new JSeparator());
		
		JMenuItem mntmFlipVertically = new JMenuItem("Flip Vertically");
		mntmFlipVertically.setAccelerator(KeyStroke.getKeyStroke("V"));
		mnTools.add(mntmFlipVertically);
		
		JMenuItem mntmFlipHorizontally = new JMenuItem("Flip Horizontally");
		mntmFlipHorizontally.setAccelerator(KeyStroke.getKeyStroke("H"));
		mnTools.add(mntmFlipHorizontally);
		
		mnTools.add(new JSeparator());
		
		JMenuItem mntmSaveChar = new JMenuItem("Save Character");
		mntmSaveChar.setAccelerator(KeyStroke.getKeyStroke("S"));
		mnTools.add(mntmSaveChar);
		
		JMenu mnHelp = new JMenu("Help");
		this.add(mnHelp);
		
		JMenuItem mntmSupport = new JMenuItem("Support");
		mnHelp.add(mntmSupport);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnHelp.add(mntmAbout);
	}

	public void initializeRecentFilesMenu(final Max7456CharwizardActions actions, UserSettings userSettings) {
		int i = 48;
		for (String f : userSettings.getRecentFiles()) {
			JMenuItem mi = new JMenuItem(f);
			mi.setAccelerator(KeyStroke.getKeyStroke(++i, InputEvent.CTRL_MASK));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					actions.openRecentFileHandler(evt);
				}
			});
			this.mnRecentFiles.add(mi);
		}
	}
	
}
