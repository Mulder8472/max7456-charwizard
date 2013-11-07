package net.codehustler.max7456.charwizard.ui;

import static net.codehustler.max7456.charwizard.Logger.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import net.codehustler.max7456.charwizard.MCMChar;
import net.codehustler.max7456.charwizard.Pixel;

public class MCMCharacterEditor extends JPanel implements MouseListener,
		CharSelectionListener, MouseMotionListener, ClipboardOwner {
	private static final long serialVersionUID = 1L;
	final int charWidth = 12;
	final int charHeight = 18;

//	final int offsetX = 0;
//	final int offsetY = 50;
	
	final Point offset = new Point(0, 0);

	final int pixelSize = 15;

	private int borderWidth = 1;

	private int width = 12 * (pixelSize + this.borderWidth) + this.borderWidth + offset.x;
	private int height = 18 * (pixelSize + this.borderWidth) + this.borderWidth + offset.y;

	private int selectedColor = Pixel.WHITE.intValue();

	private List<Pixel> selection = new ArrayList();

	private static Cursor MOVE_CURSOR = new Cursor(13);
	private static Cursor DEFAULT_CURSOR = new Cursor(0);

	private boolean overSelection = false;

	private Clipboard clipboard = new Clipboard("MAX7456");

	private boolean selectTransparentPixels = false;

	private MCMChar mcmChar = null;
	private Rectangle whiteSelectionSquare;
	private Rectangle blackSelectionSquare;
	private Rectangle transparentSelectionSquare;
	private Rectangle pixelArea;

	public MCMCharacterEditor() {
		setMinimumSize(new Dimension(this.width, this.height));
		setPreferredSize(new Dimension(this.width, this.height));
		setMaximumSize(new Dimension(this.width, this.height));

		setSize(width, height);
		
		addMouseListener(this);
		addMouseMotionListener(this);

		//Log("getting system clipboard");
		this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		StringSelection ss = new StringSelection("test");
		this.clipboard.setContents(ss, this);
	}

	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		clear(g);
		//paintCharAddress(g);
		//paintPalette(g);

		paintChar(getMcmChar(), g);

		//paintSelectionArea(g);

		g.dispose();
	}

	private void paintCharAddress(Graphics2D g) {
		if (this.mcmChar == null)
			return;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 1, 15));
		g.drawString(
				"0x" + this.mcmChar.getHexCol() + this.mcmChar.getHexRow(), 5,
				25);
		g.drawString("" + this.mcmChar.getIndex(), 5, 40);
	}

	private void paintPalette(Graphics2D g) {
		int paletteOffset = 52;

		g.setColor(Color.white);
		g.fillRect(5 + paletteOffset, 4, 40, 40);
		if (this.selectedColor == Pixel.WHITE.intValue())
			g.setColor(Color.RED);
		else
			g.setColor(Color.black);
		g.drawRect(5 + paletteOffset, 4, 40, 40);
		this.whiteSelectionSquare = new Rectangle(5 + paletteOffset, 4, 40, 40);

		g.setColor(Color.black);
		g.fillRect(50 + paletteOffset, 4, 40, 40);
		if (this.selectedColor == Pixel.BLACK.intValue())
			g.setColor(Color.RED);
		else
			g.setColor(Color.black);
		g.drawRect(50 + paletteOffset, 4, 40, 40);
		this.blackSelectionSquare = new Rectangle(50 + paletteOffset, 4, 40, 40);

		g.setColor(Color.gray);
		g.fillRect(95 + paletteOffset, 4, 40, 40);
		if (this.selectedColor == Pixel.TRANSPARENT.intValue())
			g.setColor(Color.RED);
		else
			g.setColor(Color.black);
		g.drawRect(95 + paletteOffset, 4, 40, 40);
		this.transparentSelectionSquare = new Rectangle(95 + paletteOffset, 4,
				40, 40);
	}

	private void paintSelectionArea(Graphics2D g) {
		if (this.dragStart == null)
			return;

		g.setColor(Color.green);

		int x = Math.min(this.dragStart.x, this.dragEnd.x);
		int y = Math.min(this.dragStart.y, this.dragEnd.y);

		int width = Math.abs(this.dragEnd.x - this.dragStart.x);
		int height = Math.abs(this.dragEnd.y - this.dragStart.y);

		g.drawRect(x, y, width, height);
	}

	private Point getPosition(Pixel p, Point offset) {
		if (offset == null) {
			offset = new Point(0,0);
		}
		int x = ((pixelSize + this.borderWidth) * (p.getIndex() % 12)) + this.borderWidth + offset.x;
		int y = ((pixelSize + this.borderWidth) * (p.getIndex() / 12)) + this.borderWidth + offset.y;
		return new Point(x, y);
	

//		return new Point((pixelSize + this.borderWidth) * (p.getIndex() % 12 + offset.x) + this.borderWidth + 0,
//				         (pixelSize + this.borderWidth) * (p.getIndex() / 12 + offset.y)	+ this.borderWidth + 50);
	}

	private Rectangle getRectangle(Pixel p, Point offset) {
		return new Rectangle(getPosition(p, offset), new Dimension(pixelSize, pixelSize));
	}

	private boolean containsPoint(Pixel pixel, Point point) {
		return getRectangle(pixel, offset).contains(point);
	}

	private void paintChar(MCMChar c, Graphics2D g) {
		if (c == null)
			return;

		for (Pixel p : c.getPixels()) {
			paintPixel(p, this.offset, g);
		}

		
		g.setColor(Color.red);
		for (Pixel p : this.selection) {
			
			int x = ((15 + this.borderWidth) * (p.getIndex() % 12)) + offset.x;
			int y = ((15 + this.borderWidth) * (p.getIndex() / 12)) + offset.y;
			g.setColor(Color.red);
			g.drawRect(x, y, 15 + this.borderWidth, 15 + this.borderWidth);

			if (!(moveInProgress())) {
				continue;
			}

			paintPixel(new Pixel(p.getIndex(), Pixel.TRANSPARENT.intValue()), offset, g);

			x = ((15 + this.borderWidth) * ((p.getIndex() % 12) + this.moveDiff.x)) + offset.x;
			y = ((15 + this.borderWidth) * ((p.getIndex() / 12) + this.moveDiff.y)) + offset.y;
			
			setColorByPixel(p, g);
			g.fillRect(x, y, 15 + this.borderWidth, 15 + this.borderWidth);
			
			g.setColor(Color.green);
			g.drawRect(x, y, 15 + this.borderWidth, 15 + this.borderWidth);
		}
		
		this.pixelArea = new Rectangle(offset, new Dimension(12 * (pixelSize + this.borderWidth),	18 * (pixelSize + this.borderWidth)));
	}

	private void paintPixel(Pixel p, Point offset, Graphics2D g) {
		setColorByPixel(p, g);
		g.fill(getRectangle(p, offset));
	}

	private void setColorByPixel(Pixel p, Graphics2D g) {
		if (p.getValue() == 0)
			g.setColor(Color.black);
		if (p.getValue() == 1)
			g.setColor(Color.gray);
		if (p.getValue() == 2) {
			g.setColor(Color.white);
		}
	}

	private void clear(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//palette area
//		g.setColor(new Color(235, 233, 237));
//		g.fillRect(0, 0, getWidth(), this.height);

		//palette border
//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, getWidth() - 1, 48);

		g.fillRect(offset.x, offset.y, width-offset.x, height-offset.y);
	}

	public int getBorderWidth() {
		return this.borderWidth;
	}

	public void setBorderWidth(int border) {
		this.borderWidth = border;
		repaint();
	}

	private void processPixel(Point p) {
		Pixel pixel = getPixel(p);

		if (pixel != null) {
			pixel.setValue(this.selectedColor);
		}
		repaint();
	}

	private Pixel getPixel(Point p) {
		Double x = Double.valueOf((p.getX() - (this.borderWidth + offset.x))
				/ (pixelSize + this.borderWidth));
		Double y = Double.valueOf((p.getY() - (this.borderWidth + offset.y))
				/ (pixelSize + this.borderWidth));

		x = Double.valueOf(Math.max(Math.min(x.doubleValue(), 12 - 1), 0.0D));
		y = Double.valueOf(Math.max(Math.min(y.doubleValue(), 18 - 1), 0.0D));

		if ((this.mcmChar == null) || (x.doubleValue() < 0.0D)
				|| (y.doubleValue() < 0.0D) || (x.doubleValue() >= 12.0D)
				|| (y.doubleValue() >= 18.0D))
			return null;

		return getPixel(x.intValue(), y.intValue());
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			if (pointInWhite(e.getPoint())) {
				this.selectedColor = Pixel.WHITE.intValue();
			} else if (pointInBlack(e.getPoint())) {
				this.selectedColor = Pixel.BLACK.intValue();
			} else if (pointInTransparent(e.getPoint())) {
				this.selectedColor = Pixel.TRANSPARENT.intValue();
			} else if (this.pixelArea.contains(e.getPoint())) {
				processPixel(e.getPoint());
				this.selection.clear();
			}
			repaint();
		} else {
			if ((e.getButton() != 3)
					|| (!(this.pixelArea.contains(e.getPoint())))) {
				return;
			}

			if (!(MouseEvent.getMouseModifiersText(e.getModifiers())
					.contains("Ctrl"))) {
				this.selection.clear();
			}
			Pixel pixel = getPixel(e.getPoint());

			if ((pixel != null)
					&& ((

					(this.selectTransparentPixels) || (pixel.getValue() != Pixel.TRANSPARENT
							.intValue())))) {
				this.selection.add(pixel);
			}

			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (this.dragStart != null)

		{
			int x = Math.min(this.dragStart.x, this.dragEnd.x);
			int y = Math.min(this.dragStart.y, this.dragEnd.y);

			int width = Math.abs(this.dragEnd.x - this.dragStart.x);
			int height = Math.abs(this.dragEnd.y - this.dragStart.y);

			Pixel p0 = getPixel(new Point(x, y));
			Pixel p1 = getPixel(new Point(x + width, y + height));

			if (!(MouseEvent.getMouseModifiersText(e.getModifiers())
					.contains("Ctrl"))) {
				this.selection.clear();

			}

			for (int x0 = p0.getX(); x0 <= p1.getX(); ++x0) {
				for (int y0 = p0.getY(); y0 <= p1.getY(); ++y0) {
					Pixel p = getPixel(x0, y0);
					if ((!(this.selectTransparentPixels))
							&& (p.getValue() == Pixel.TRANSPARENT.intValue()))
						continue;
					this.selection.add(p);
				}

			}

			this.dragStart = null;
			this.dragEnd = null;
			repaint();
		} else {
			if (!(moveInProgress()))
				return;
			this.moveStart = null;
			transferValues(this.moveDiff);
			repaint();
		}
	}

	private boolean pointInWhite(Point p) {
		return this.whiteSelectionSquare.contains(p);
	}

	private boolean pointInBlack(Point p) {
		return this.blackSelectionSquare.contains(p);
	}

	private boolean pointInTransparent(Point p) {
		return this.transparentSelectionSquare.contains(p);
	}

	private void transferValues(Point diff) {
		List newSelection = new ArrayList();

		for (Pixel p : this.selection)

		{
			Pixel target = this.mcmChar.getPixelAt(p.getX() + diff.x, p.getY()
					+ diff.y);
			target.setValue(p.getValue());
			p.setValue(Pixel.TRANSPARENT.intValue());

			newSelection.add(target);
		}

		this.selection.clear();
		this.selection.addAll(newSelection);
	}

	private Pixel getPixel(int x, int y) {
		return getMcmChar().getPixels()[(12 * y + x)];
	}

	public void characterSelectionChanged(MCMChar character) {
		setMcmChar(new MCMChar(character));
		this.selection.clear();
		repaint();
	}
	
	public void characterSelectionChanged(MCMChar[][] characterGrid) {
		setMcmChar(new MCMChar(characterGrid[0][0]));
		this.selection.clear();
		repaint();
	}

	public void setMcmChar(MCMChar mcmChar) {
		this.mcmChar = mcmChar;
	}

	public MCMChar getMcmChar() {
		return this.mcmChar;
	}

	Point dragStart = null;
	Point dragEnd = null;

	public void mouseDragged(MouseEvent e) {
		if ((MouseEvent.getModifiersExText(e.getModifiersEx())
				.contains("Button1"))
				&& (this.pixelArea.contains(e.getPoint()))) {
			if (this.overSelection) {
				moveSelection(e.getPoint());
			} else {
				this.selection.clear();
				processPixel(e.getPoint());
			}
		} else {
			if ((!(MouseEvent.getModifiersExText(e.getModifiersEx())
					.contains("Button3")))
					|| (!(this.pixelArea.contains(e.getPoint()))))
				return;
			if (this.dragStart == null) {
				this.dragStart = e.getPoint();

				if (this.dragStart.x > this.width)
					this.dragStart.x = (this.width - 1);
				if (this.dragStart.x < 0)
					this.dragStart.x = 0;

				if (this.dragStart.y >= this.height)
					this.dragStart.y = (this.height - 1);
				if (this.dragStart.y < 50)
					this.dragStart.y = 50;
			}

			this.dragEnd = e.getPoint();

			if (this.dragEnd.x > this.width)
				this.dragEnd.x = (this.width - 1);
			if (this.dragEnd.x < 0)
				this.dragEnd.x = 0;

			if (this.dragEnd.y >= this.height)
				this.dragEnd.y = (this.height - 1);
			if (this.dragEnd.y < 50)
				this.dragEnd.y = 50;

			repaint();
		}
	}

	private Point moveStart = null;
	private Point moveDiff = null;

	private boolean moveInProgress() {
		return (this.moveStart != null);
	}

	private void moveSelection(Point p) {
		if (this.moveStart == null) {
			this.moveStart = p;

		}

		Pixel s = getPixel(this.moveStart);
		Pixel e = getPixel(p);

		int diffX = e.getX() - s.getX();
		int diffY = e.getY() - s.getY();

		this.moveDiff = new Point(diffX, diffY);

		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		this.overSelection = false;
		if (this.selection.size() <= 0)
			return;
		for (Pixel p : this.selection) {
			if (!(containsPoint(p, e.getPoint())))
				continue;
			setMoveCursor();
			this.overSelection = true;
		}

		if (this.overSelection)
			return;
		setDefaultDursor();
	}

	private void setMoveCursor() {
		if (getCursor() != MOVE_CURSOR)
			setCursor(MOVE_CURSOR);
	}

	private void setDefaultDursor() {
		if (getCursor() != DEFAULT_CURSOR)
			setCursor(DEFAULT_CURSOR);
	}

	public void setAllTransparent() {
		setAll(Pixel.TRANSPARENT.intValue());
	}

	public void setAllWhite() {
		setAll(Pixel.WHITE.intValue());
	}

	public void setAllBlack() {
		setAll(Pixel.BLACK.intValue());
	}

	private void setAll(int color) {
		if (this.selection.size() <= 0)
			return;
		for (Pixel p : this.selection) {
			p.setValue(color);
		}
		repaint();
	}

	public void setSelectedColor(int color) {
		this.selectedColor = color;
	}

	public void selectAll() {
		this.selection.clear();
		for (Pixel p : this.mcmChar.getPixels()) {
			if ((!(this.selectTransparentPixels))
					&& (p.getValue() == Pixel.TRANSPARENT.intValue()))
				continue;
			this.selection.add(p);
		}
		repaint();
	}

	public void inverseSelection() {
		List oldSelection = new ArrayList();
		oldSelection.addAll(this.selection);

		this.selection.clear();
		for (Pixel p : this.mcmChar.getPixels()) {
			this.selection.add(p);
		}

		this.selection.removeAll(oldSelection);

		repaint();
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		//Log("lost ownership of clipboard... don't care!");
	}

	public void copySelectedPixels() {
		Transferable t = new PixelListTransferable(this.selection);
		this.clipboard.setContents(t, this);
	}

	public void pasteSelectedPixels() {
		Transferable t = this.clipboard.getContents(null);

		boolean hasTransferablePixels = t != null
				&& t.isDataFlavorSupported(PixelListTransferable.PIXEL_DATAFLAVOUR);

		if ((t == null) || (this.mcmChar == null) || !hasTransferablePixels)
			return;

		List<Pixel> p;
		try {
			p = Arrays.asList((Pixel[]) t
					.getTransferData(PixelListTransferable.PIXEL_DATAFLAVOUR));
			this.selection.clear();
			this.selection.addAll(p);
			if (p == null)
				return;
			this.mcmChar.importPixels(p);
			repaint();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cutSelectedPixels() {
		if (this.selection.size() <= 0)
			return;
		copySelectedPixels();
		setAllTransparent();
	}

	public void setBrushColor(Integer color) {
		this.selectedColor = color.intValue();
		repaint();
	}

	public void invertPixels() {
		if (this.mcmChar == null)
			return;
		for (Pixel p : this.selection) {
			p.invert();
		}
		repaint();
	}

	public void setSelectTransparentPixels(boolean selectTransparentPixels) {
		this.selectTransparentPixels = selectTransparentPixels;
	}

	public boolean isSelectTransparentPixels() {
		return this.selectTransparentPixels;
	}

	public void flipVertical() {
		Log("flipVertical");
		if (this.mcmChar != null) {
			this.mcmChar.flipVertical();
		}
		repaint();
	}

	public void flipHorizontal() {
		Log("flipHorizontal");
		if (this.mcmChar != null) {
			this.mcmChar.flipHorizontal();
		}
		repaint();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}