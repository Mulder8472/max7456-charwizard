package net.codehustler.max7456.charwizard.newui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import net.codehustler.max7456.charwizard.ui.util.ObservabList;
import net.codehustler.max7456.charwizard.ui.util.UserSettings;

public class Max7456CharwizardUI {

	public static final String TITLE = "MAX7456 MCM Wizard";
	
	private JFrame frame;
	
	private UserSettings userSettings = new UserSettings();
	
	//private Max7456Charset charset;
	
	private MenuBar menuBar;
	
	private Max7456CharwizardActions actions;
	private Desktop desktop;
	private StartMenu startMenu;
	
	
	
	private ObservabList<CharsetEditor> editors = new ObservabList<CharsetEditor>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Max7456CharwizardUI window = new Max7456CharwizardUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Max7456CharwizardUI() {
		this.actions = new Max7456CharwizardActions(this);
		initializeData();
		initializeUI();
	}

	private void initializeData() {
		this.userSettings.load();
//	    this.charset = new Max7456Charset();
//	    this.charset.createEmpty();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() {
		frame = new JFrame(TITLE);
		frame.setBounds(100, 100, 738, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		desktop = new Desktop();
		editors.addObserver(desktop);
		frame.getContentPane().add(desktop);
		
		
		startMenu = new StartMenu();
		editors.addObserver(startMenu);
		frame.getContentPane().add(startMenu, BorderLayout.NORTH);
		
		this.menuBar = new MenuBar(this.actions);
		frame.setJMenuBar(menuBar);
		
		initializeRecentFilesMenu();
	}
	
	private void initializeRecentFilesMenu() {
		this.menuBar.initializeRecentFilesMenu(actions, userSettings);
	}
	
	public void addEditor(CharsetEditor charsetEditor) {
		this.editors.add(charsetEditor);
		try {
			charsetEditor.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public void removeEditor(CharsetEditor charsetEditor) {
		this.editors.remove(charsetEditor);
	}

	public CharsetEditor getActiveEditor() {
		CharsetEditor ce = (CharsetEditor) this.desktop.getSelectedFrame();
		return ce;
	}
}
