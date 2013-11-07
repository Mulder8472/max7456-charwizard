package net.codehustler.max7456.charwizard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Max7456Charset {
	private String filename = "UNNAMED";
	private MCMChar[][] chars;

	
	public Max7456Charset() {
		init();
	}
	
	public Max7456Charset(String file) {
		this.filename = file;
		init();
	}

	private void init() {
		this.chars = new MCMChar[16][16];

		for (int row = 0; row < 16; ++row) {
			for (int col = 0; col < 16; ++col) {
				this.chars[row][col] = new MCMChar(col, row);
			}
		}
	}

	public String toString() {
		String s = "MAX7456";
		for (int row = 0; row < 16; ++row) {
			for (int col = 0; col < 16; ++col) {
				s = s + "\r\n" + this.chars[col][row].toString();
			}
		}
		return s;
	}

	public void load() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				this.filename)));

		Integer rowIndex = Integer.valueOf(0);
		int row = 0;
		int col = 0;

		int pixelIndex = 0;

		if (br.readLine() != null) {
			String rowText;
			while ((rowText = br.readLine()) != null) {
//				String rowText;
				Integer rowByte = Integer.valueOf(0);

				if (row >= 16)

				{
					rowIndex = Integer.valueOf(rowIndex.intValue() + 1);

				} else {
					this.chars[col][row].getPixels()[(pixelIndex + 0)] = new Pixel(
							pixelIndex + 0, Integer.parseInt("" + rowText.charAt(0) + rowText.charAt(1), 2));
					this.chars[col][row].getPixels()[(pixelIndex + 1)] = new Pixel(
							pixelIndex + 1, Integer.parseInt("" + rowText.charAt(2)
									+ rowText.charAt(3), 2));
					this.chars[col][row].getPixels()[(pixelIndex + 2)] = new Pixel(
							pixelIndex + 2, Integer.parseInt("" + rowText.charAt(4)
									+ rowText.charAt(5), 2));
					this.chars[col][row].getPixels()[(pixelIndex + 3)] = new Pixel(
							pixelIndex + 3, Integer.parseInt("" + rowText.charAt(6)
									+ rowText.charAt(7), 2));

					pixelIndex += 4;

					if (pixelIndex == 216)

					{
						for (int l = 0; l < 10; ++l) {
							br.readLine();
						}
						pixelIndex = 0;
						++col;
						if (col == 16) {
							col = 0;
							++row;

						}

					}

					rowIndex = Integer.valueOf(rowIndex.intValue() + 1);
				}
			}
		}
	}

	public void save() throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(this.filename));

		fos.write(toString().getBytes());

		fos.flush();
		fos.close();
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MCMChar[][] getChars() {
		return this.chars;
	}

	public void createEmpty() {
		this.filename = "unknown";
		init();
	}
}