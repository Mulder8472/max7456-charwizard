package net.codehustler.max7456.charwizard.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import net.codehustler.max7456.charwizard.Pixel;

public class PixelListTransferable implements Transferable {
	// private List<Pixel> pixels = null;

	public static DataFlavor PIXEL_DATAFLAVOUR =  new DataFlavor(Pixel[].class, "PIXELS");

	private Pixel[] pixels;

	public PixelListTransferable(List<Pixel> pixels) {
		importPixels(pixels);
	}

	public void importPixels(List<Pixel> pixels) {
		//this.pixels = new ArrayList();
		this.pixels = new Pixel[pixels.size()];
		int index = 0;
		for (Pixel p : pixels) {
			//this.pixels.add(new Pixel(p.getIndex(), p.getValue()));
			this.pixels[index] = new Pixel(p.getIndex(), p.getValue());
			index ++;
		}
	}

	public Pixel[] getPixels() {
		return this.pixels;
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return this.pixels;
	}
	

	public DataFlavor[] getTransferDataFlavors() {
		try {
			DataFlavor[] df = { PIXEL_DATAFLAVOUR };
			return df;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.getHumanPresentableName().equals("PIXELS");
		//return flavor.getMimeType().equals("PIXELS");
	}
}
