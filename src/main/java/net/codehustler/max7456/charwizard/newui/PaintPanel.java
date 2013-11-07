package net.codehustler.max7456.charwizard.newui;

import javax.swing.JPanel;

import net.codehustler.max7456.charwizard.MCMChar;
import net.codehustler.max7456.charwizard.ui.CharSelectionListener;

public class PaintPanel extends JPanel implements CharSelectionListener {
	private static final long serialVersionUID = 1L;

	public PaintPanel() {
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
	}

	public void characterSelectionChanged(MCMChar[][] grid) {
		removeAll();
		
		int editorWidth = grid[0][0].getEditor().getWidth();
		int editorHeight = grid[0][0].getEditor().getHeight();
		
		for ( int y = 0; y < grid[0].length; y++ ) {
			for ( int x = 0; x < grid.length; x++ ) {
				grid[x][y].getEditor().setBounds(editorWidth*x, editorHeight*y, editorWidth, editorHeight);
				this.add(grid[x][y].getEditor());
			}	
		}
		
		updateUI();
	}
}
