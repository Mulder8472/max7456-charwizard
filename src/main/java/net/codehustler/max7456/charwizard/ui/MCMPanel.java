package net.codehustler.max7456.charwizard.ui;

import static net.codehustler.max7456.charwizard.Logger.Log;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.codehustler.max7456.charwizard.MCMChar;
import net.codehustler.max7456.charwizard.Max7456Charset;
import net.codehustler.max7456.charwizard.Pixel;

public class MCMPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	final int charWidth = 12;
	final int charHeight = 18;

	final int columns = 16;
	final int rows = 16;

	private int borderWidth = 3;
	private int width = columns * (charWidth + this.borderWidth) + this.borderWidth;
	private int height = rows * (charHeight + this.borderWidth) + this.borderWidth;

	//private int selectedRow = 0;
	//private int selectedCol = 0;

	private Rectangle selectedArea = new Rectangle(0,0,0,0);
	
	private BufferedImage buffer = new BufferedImage(this.width, this.height, 2);

	private List<CharSelectionListener> selectionListener = new ArrayList();

	public MCMPanel() {
		setMinimumSize(new Dimension(this.width, this.height));
		setPreferredSize(new Dimension(this.width, this.height));
		setMaximumSize(new Dimension(this.width, this.height));

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private boolean gridBuffered = false;
	private Max7456Charset charset;
	private Point dragStart;
	private Point dragEnd;

	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		clear(g);
		paintGrid(g);
		paintSelection(g);
		g.dispose();
	}

	private void clear(Graphics2D g) {
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);
	}
	
	private void paintSelection(Graphics2D g) {
		g.setColor(Color.red);
		
		int x1 = selectedArea.x * (charWidth + borderWidth);
		int y1 = selectedArea.y * (charHeight + borderWidth);
		
		int w = ((selectedArea.width-selectedArea.x) * (charWidth+borderWidth)) + charWidth + borderWidth;
		int h = ((selectedArea.height-selectedArea.y) * (charHeight+borderWidth)) + charHeight + borderWidth;
		
		g.setStroke(new BasicStroke(borderWidth));
		g.drawRect(x1, y1, w, h);
	}

	private void paintGrid(Graphics2D g1) {
		if (!(this.gridBuffered)) {
			Graphics2D g = this.buffer.createGraphics();

			g.setColor(Color.BLACK);

			if (getCharset() != null) {
				MCMChar[][] chars = getCharset().getChars();

				for (int row = 0; row < 16; ++row) {
					for (int col = 0; col < 16; ++col) {
						MCMChar c = chars[col][row];
						Pixel[] pixels = c.getPixels();
						for (Pixel p : pixels) {
							if (p.getValue() == 0)
								g.setColor(Color.black);
							if (p.getValue() == 1)
								g.setColor(Color.gray);
							if (p.getValue() == 2) {
								g.setColor(Color.white);
							}

							g.fillRect(col * (12 + this.borderWidth) + p.getIndex() % 12
									+ this.borderWidth, row
									* (18 + this.borderWidth) + p.getIndex() / 12
									+ this.borderWidth, 1, 1);
						}
					}
				}
			}

		}

		g1.drawImage(this.buffer, null, 0, 0);
	}

	public void setCharset(Max7456Charset charset) {
		this.charset = charset;
		repaint();
	}

	public Max7456Charset getCharset() {
		return this.charset;
	}

	public int getBorderWidth() {
		return this.borderWidth;
	}

	public void setBorderWidth(int border) {
		this.borderWidth = border;
		repaint();
	}
	
	private MCMChar[][] getSelectedCharacterGrid() {
		int cols = selectedArea.width - selectedArea.x + 1;
		int rows = selectedArea.height - selectedArea.y + 1;
		MCMChar[][] grid = new MCMChar[cols][rows];
		
		for ( int y = selectedArea.y; y <= selectedArea.height; y ++ ) {
			for ( int x = selectedArea.x; x <= selectedArea.width; x ++ ) {
				grid[x-selectedArea.x][y-selectedArea.y] = this.charset.getChars()[x][y];
			}
		}
		return grid;
	}

	public void mouseReleased(MouseEvent e) {
		setSelectedArea(e);
		//notifyCharacterSelection(this.charset.getChars()[this.selectedArea.x][this.selectedArea.y]);
		notifyCharacterSelection(getSelectedCharacterGrid());
		repaint();
		
		dragStart = null;
	}

//	@Deprecated
//	private void notifyCharacterSelection(MCMChar c) {
//		for (CharSelectionListener l : this.selectionListener)
//			l.characterSelectionChanged(c);
//	}
	
	private void notifyCharacterSelection(MCMChar[][] c) {
		for (CharSelectionListener l : this.selectionListener)
			l.characterSelectionChanged(c);
	}

	public void addCharacterSelectionListener(CharSelectionListener listener) {
		this.selectionListener.add(listener);
		//listener.characterSelectionChanged(this.charset.getChars()[this.selectedArea.x][this.selectedArea.y]);
	}

	public void resetSelection() {
		selectedArea = new Rectangle(0,0,0,0);
		//notifyCharacterSelection(this.charset.getChars()[this.selectedArea.x][this.selectedArea.y]);
		notifyCharacterSelection(getSelectedCharacterGrid());
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		setSelectedArea(e);
		repaint();
	}

	
	public void mouseDragged(MouseEvent e) {
		setSelectedArea(e);
		this.repaint();
	}

	private void setSelectedArea(MouseEvent e) {
		if ( dragStart == null ) {
			dragStart = e.getPoint();
		}
		
		Double colStart = Double.valueOf((dragStart.getX() - borderWidth)	/ (charWidth  + this.borderWidth));
		Double rowStart = Double.valueOf((dragStart.getY() - borderWidth)	/ (charHeight + this.borderWidth));
		
		Double colEnd = Double.valueOf((e.getPoint().getX() - borderWidth)	/ (charWidth  + this.borderWidth));
		Double rowEnd = Double.valueOf((e.getPoint().getY() - borderWidth)	/ (charHeight + this.borderWidth));

		selectedArea = new Rectangle(Math.max(Math.min(colStart.intValue(), colEnd.intValue()), 0), 
									 Math.max(Math.min(rowStart.intValue(), rowEnd.intValue()), 0), 
									 Math.min(Math.max(colStart.intValue(), colEnd.intValue()), columns-1), 
									 Math.min(Math.max(rowStart.intValue(), rowEnd.intValue()), rows-1));
	}

	public void mouseMoved(MouseEvent e) {
		
	}
}
