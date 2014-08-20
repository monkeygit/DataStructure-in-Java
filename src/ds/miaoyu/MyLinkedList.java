package ds.miaoyu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T extends Comparable<T>> implements Iterable<T>{
	
	public static void main(String[] args) {
		MyLinkedList<Integer> mll = new MyLinkedList<Integer>();
		System.out.println("Current size: "+mll.size());
		mll.add(1);
		System.out.println("Add 1");
		mll.print();
		mll.add(0, 1);
		mll.print();
		mll.set(1, 100);
		mll.print();
		Iterator<Integer> itr = mll.iterator();
		System.out.println(itr.next());
		itr.remove();
		mll.print();
		System.out.println(mll.contains(100));
	}
	
	private static class Node<T>{
		public Node(T d, Node<T> p, Node<T> n){
			data = d;
			prev = p;
			next = n;
		}
		
		public T data;
		public Node<T> prev;
		public Node<T> next;
	}
	
	public MyLinkedList(){
		clear();
	}
	
	public void clear(){
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	public boolean add(T newVal){
		add(size(), newVal);
		return true;
	}
	
	public void add(int idx ,T newVal){
		addBefore(getNode(idx), newVal);
	}
	
	public T get(int idx){
		return getNode(idx).data;
	}
	
	public T set(int idx, T newVal){
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	public T remove(int idx){
		return remove(getNode(idx));
	}
	
	/*
	 * 3.3
	 */
	public Iterator<T> contains(T x){
		Node<T> p = null;
		Iterator<T> itr = this.iterator();
		while(itr.hasNext()){
			if(itr.next() == x)
				return itr;
		}
		return null;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	public void print(){
		Node<T> p;
		System.out.print("Show: ");
		
		for(int i=0; i<size(); i++){
			p = getNode(i);
			System.out.print(p.data+" ");
		}
		System.out.println();
	}
	
	/*
	 * 3.4
	 */
	public MyLinkedList<T> copy(MyLinkedList<T> src, MyLinkedList<T> dst){
		if(src == null || dst == null)
			throw new NullPointerException("src or dst is null!");
		for(int i=0; i<src.size(); i++){
			dst.add(src.get(i));
		}
		return dst;
	}
	
/*	public MyLinkedList<T> interSection(MyLinkedList<T> one, MyLinkedList<T> another){
		MyLinkedList<T> result = new MyLinkedList<T>();
		
	}*/
	
	/*
	 * union
	 */
	public MyLinkedList<T> unionSection(MyLinkedList<T> one, MyLinkedList<T> another){
		MyLinkedList<T> result = new MyLinkedList<T>();
		int p=0, q=0;
		while(p<one.size() && q<another.size()){
			if(one.get(p).compareTo(another.get(q)) <= 0){
				result.add(one.get(p));
				p++;
			}
			else{
				result.add(another.get(q));
				q++;
			}
		}
		if(p == one.size()){
			while(q<another.size()){
				result.add(another.get(q));
				q++;
			}
		}
		if(q == another.size()){
			while(p<one.size()){
				result.add(one.get(p));
				p++;
			}
		}
		return result;
	}
	
	/*
	 * 3.9 
	 * O(n)
	 */
	public void addAll(Iterable<? extends T> items){
		Iterator<? extends T> itr = items.iterator();
		while(itr.hasNext()){
			this.add(itr.next());
		}
	}
	
	/*
	 * 3.10
	 * O(M*N)
	 */
	public void removeAll(Iterable<? extends T> items){
		Iterator<? extends T> itrItems = items.iterator();
		T element,item;
		Node<T> p = null;
		while(itrItems.hasNext()){
			item = itrItems.next();
			Iterator<? extends T> itrList = iterator();
			while(itrList.hasNext()){
				element = itrList.next();
				if(element.equals(item))
					itrList.remove();
			}
		}
	}
	
	/*
	 * 3.11.a
	 */
	
	public int sizeEX(){
		Node<T> p = beginMarker.next;
		int s = 0;
		while(p != null){
			s++;
			p = p.next;
		}
		return s;
	}
	
	/*
	 * 3.11.b
	 */
	public void printEX(){
		Node<T> p = beginMarker.next;
		while(p != null){
			System.out.print(p.data);
		}
	}
	
	/*
	 * 3.11.c
	 */
	public boolean containsEX(T x){
		Node<T> p = beginMarker.next;
		while(p != null){
			if(p.data == x)
				return true;
		}
		return false;
	}
	
	/*
	 * 3.11.d
	 * return true if list not contains x, and insert x into the tail
	 * return false if list contais x
	 */
	public boolean addIfNotContains(T x){
		if(containsEX(x))
			return false;
		else{
			Node<T> newNode = new Node<T>(x, beginMarker, beginMarker.next);
			newNode.next.prev = newNode;
			beginMarker.next = newNode;
			return true;
		}
	}


	
	
	private Node<T> getNode(int idx){
		Node<T> p;
		
		if(idx<0 || idx>size())
			throw new IndexOutOfBoundsException();
		
		if(idx < size()/2){
			p = beginMarker.next;
			for(int i=0; i<idx; i++)
				p = p.next;
		}
		else{
			p = endMarker;
			for(int i=size(); i>idx; i--)
				p = p.prev;
		}
		return p;
	}
	
	
	private T remove(Node<T> p){
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		return p.data;
	}
	
	private void addBefore(Node<T> p, T x){
		Node<T> newNode = new Node<T>(x, p.prev, p);
		p.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	private class LinkedListIterator implements Iterator<T>{
		
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next() {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			
			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		@Override
		public void remove() {
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}
		
	}
	
	
	private int theSize = 0;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

}
