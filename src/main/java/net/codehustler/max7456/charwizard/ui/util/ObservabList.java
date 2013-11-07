package net.codehustler.max7456.charwizard.ui.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ObservabList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;
	
	private Set<CollectionChangedListener<T>> observers = new HashSet<CollectionChangedListener<T>>();

	@Override
	public boolean add(T e) {
		boolean add = super.add(e);
		this.fireItemAdded(e);
		return add;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		boolean remove = super.remove(o);
		this.fireItemRemoved((T) o);
		return remove;
	}
	
	public void fireItemAdded(T item) {
		for ( CollectionChangedListener<T> o: observers ) {
			o.itemAdded(this, item);
		}
	}

	public void fireItemRemoved(T item) {
		for ( CollectionChangedListener<T> o: observers ) {
			o.itemRemoved(this, item);
		}
	}
	
	public void addObserver(CollectionChangedListener<T> observer) {
		this.observers.add(observer);
	}

	public Set<CollectionChangedListener<T>> getObservers() {
		return observers;
	}

	public void setObservers(Set<CollectionChangedListener<T>> observers) {
		this.observers = observers;
	}
}
