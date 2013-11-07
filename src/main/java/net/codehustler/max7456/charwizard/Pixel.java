package net.codehustler.max7456.charwizard;

import java.io.Serializable;

public class Pixel implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final Integer BLACK = Integer.valueOf(0);
	public static final Integer TRANSPARENT = Integer.valueOf(1);
	public static final Integer WHITE = Integer.valueOf(2);

	private int value = TRANSPARENT.intValue();

	private int index;

	private boolean changed = false;

	public Pixel(int index, int value) {
		setValue(value);
		setIndex(index);
	}

	public void setValue(int value) {
		this.changed = true;
		this.value = value;
	}

	public void shiftValue() {
		this.value += 1;
		if (this.value != 3)
			return;
		this.value = 0;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		if (this.value == BLACK.intValue())
			return "00";
		if (this.value == TRANSPARENT.intValue())
			return "01";
		return "10";
	}

	public void invert() {
		if (getValue() == BLACK.intValue())
			setValue(WHITE.intValue());
		else if (getValue() == WHITE.intValue())
			setValue(BLACK.intValue());
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public int getY() {
		return (this.index / 12);
	}

	public int getX() {
		return (this.index % 12);
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + this.index;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		Pixel other = (Pixel) obj;

		return (this.index == other.index);
	}

	public void changesSaved() {
		this.changed = false;
	}

	public boolean hasChanged() {
		return this.changed;
	}
}