package net.codehustler.max7456.charwizard;

import java.util.List;

import net.codehustler.max7456.charwizard.ui.MCMCharacterEditor;

public class MCMChar {
	private int[] bytes = new int[64];

	private Pixel[] pixels = new Pixel[216];

	private MCMChar original = null;

	private int col;
	private int row;
	
	private MCMCharacterEditor editor;

	public MCMChar(int col, int row) {
		setCol(col);
		setRow(row);
		init();
	}
	
	public MCMChar(MCMChar original) {
		this.original = original;
		setCol(original.getCol());
		setRow(original.getRow());

		init();

		importPixels(original.getPixels());
	}

	public void importPixels(Pixel[] pixels) {
		for (Pixel p : pixels) {
			this.pixels[p.getIndex()] = new Pixel(p.getIndex(), p.getValue());
		}
	}

	public void importPixels(List<Pixel> pixels) {
		for (Pixel p : pixels) {
			this.pixels[p.getIndex()] = new Pixel(p.getIndex(), p.getValue());
		}
	}


	private void init() {
		this.pixels = new Pixel[216];

		for (int n = 0; n < this.bytes.length; ++n) {
			this.bytes[n] = 0;
		}

		for (int n = 0; n < this.pixels.length; ++n) {
			this.pixels[n] = new Pixel(n, Pixel.TRANSPARENT.intValue());
		}
		
		MCMCharacterEditor editor = new MCMCharacterEditor();
		editor.setMcmChar(this);
		this.setEditor(editor);
	}

	public String toString() {
		String s = "";

		int row = 0;
		for (int n = 0; n < this.pixels.length; ++n) {
			Pixel p1 = this.pixels[n];
			Pixel p2 = this.pixels[(++n)];
			Pixel p3 = this.pixels[(++n)];
			Pixel p4 = this.pixels[(++n)];

			s = s + p1 + p2 + p3 + p4 + "\r\n";

			++row;

		}

		for (int n = 0; n < 9; ++n) {
			s = s + "01010101\r\n";
		}

		s = s + "01010101";

		return s;
	}

	public void setPixels(Pixel[] pixels) {
		this.pixels = pixels;
	}

	public Pixel[] getPixels() {
		return this.pixels;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCol() {
		return this.col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return this.row;
	}

	public String getHexRow() {
		if (getRow() <= 9)
			return "" + getRow();
		return "" + (char) (getRow() + 55);
	}

	public String getHexCol() {
		if (getCol() <= 9)
			return "" + getCol();
		return "" + (char) (getCol() + 55);
	}

	public int getIndex() {
		return (this.row * 16 + this.col);
	}

	public void saveChanges() {
		this.original.importPixels(this.pixels);
		for (Pixel p : this.pixels)
			p.changesSaved();
	}

	public boolean hasChanged() {
		for (Pixel p : this.pixels)
			if (p.hasChanged())
				return true;
		return false;
	}

	public void invertPixels() {
		for (Pixel p : this.pixels)
			p.invert();
	}

	public Pixel getPixelAt(int x, int y) {
		return this.pixels[(y * 12 + x)];
	}

	public void flipVertical() {
		MCMChar mirror = new MCMChar(this);

		for (int x = 0; x < 12; ++x) {
			for (int y = 0; y < 18; ++y) {
				Pixel p = mirror.getPixels()[((17 - y) * 12 + x)];
				getPixels()[(y * 12 + x)].setValue(p.getValue());
			}
		}
	}

	public void flipHorizontal() {
		MCMChar mirror = new MCMChar(this);

		for (int y = 0; y < 18; ++y) {
			for (int x = 0; x < 12; ++x) {
				Pixel p = mirror.getPixels()[(y * 12 + 11 - x)];
				getPixels()[(y * 12 + x)].setValue(p.getValue());
			}
		}
	}

	public MCMCharacterEditor getEditor() {
		return editor;
	}

	public void setEditor(MCMCharacterEditor editor) {
		this.editor = editor;
	}
}
