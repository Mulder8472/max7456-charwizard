package net.codehustler.max7456.charwizard.ui.util;

import java.util.List;

public interface CollectionChangedListener<T> {
	
	public void itemAdded(List<T> list, T t);
	
	public void itemRemoved(List<T> list, T t);

}
