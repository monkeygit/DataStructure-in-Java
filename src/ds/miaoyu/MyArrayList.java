package ds.miaoyu;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private int theSize = 0;
	private T[] theItems; 
	
	public MyArrayList(){
		clear();
	}
	
	public void clear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	public void trimToSize(){
		ensureCapacity(size());
	}
	
	public T get(int idx){
		if(idx < 0 || idx > theSize)
			throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}
	
	public T set(int idx, T newValue){
		if(idx < 0 || idx > theSize)
			throw new ArrayIndexOutOfBoundsException();
		
		T old = theItems[idx];
		theItems[idx] = newValue;
		return old;
	}
	
	public void ensureCapacity(int newCapacity){
		if(newCapacity < theSize)
			return;
		T[] old = theItems;
		theItems = (T[])new Object[newCapacity];
		for(int i=0; i<theSize; i++)
			theItems[i] = old[i];
	}
	
	public boolean add(T x){
		add(0, x);
		return true;
	}
	
	public void add(int idx, T x){
		if(idx < 0 || idx > theSize)
			throw new ArrayIndexOutOfBoundsException();
		if(theItems.length == theSize)
			ensureCapacity(2*theSize + 1);
		for(int i=theSize; i>idx; i--)
			theItems[i] = theItems[i-1];
		theItems[idx] = x;
		
		theSize++;
	}
	
	public T remove(int idx){
		if(idx < 0 || idx > theSize)
			throw new ArrayIndexOutOfBoundsException();
		T removedItem = theItems[idx];
		for(int i=idx; i<theSize; i++)
			theItems[i] = theItems[i+1];
		return removedItem;
	}
	
	public Iterator<T> iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{

		private int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return theItems[current++];
		}

		@Override
		public void remove() {
			MyArrayList.this.remove(--current);
		}
		
	}
	
	
	/*
	 * 3.13
	 */
/*	public ListIterator<T> listIterator(){
		return new ArrayListIterator();
	}*/
	
/*	private class ArrayListIterator implements ListIterator<T>{

		private int current = 0;
		boolean backwards = false;
		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			MyArrayList.this.add(current++, e);
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < size();
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return current > 0;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException();
			return theItems[current++];
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			if(!hasPrevious())
				throw new NoSuchElementException();
			backwards = true;
			return theItems[--current];
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(backwards)
				MyArrayList.this.remove(current--);
			else 
				MyArrayList.this.remove(--current);
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			MyArrayList.this.set(current, e);
		}
		
	}*/
	
	/*
	 * 3.16
	 */
	public Iterator<T> reverseIterator(){
		return new ArrayListReverseIterator();
	}
	private class ArrayListReverseIterator implements Iterator<T>{

		private int current = size() - 1;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current >= 0;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException();
			return theItems[current--];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			MyArrayList.this.remove(--current);
		}
		
	}
	
	public static void main(String[] args) {

	}

}
