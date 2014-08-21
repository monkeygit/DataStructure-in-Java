package ds.miaoyu;

public class SingleList<T extends Comparable> {
	
	private Node<T> head;
	private int theSize;
	
	public SingleList(){
		init();
	}
	
	void init(){
		theSize = 0;
		head = new Node<T>();
		head.next = null;
	}
	
	public int size(){
		return theSize;
	}
	
	public void print(){
		Node<T> p = head.next;
		while(p != null){
			System.out.print(p.data+" ");
			p = p.next;
		}
		System.out.println();
	}
	
	public boolean contains(T x){
		Node<T> p = head.next;
		while(p != null){
			if(p.data.equals(x))
				return true;
			else
				p = p.next;
		}
		return false;
	}
	
	public boolean add(T x){
		if(contains(x))
			return false;
		else{
			Node<T> p = new Node<T>(x);
			p.next = head.next;
			head.next = p;
			theSize++;
		}
		return true;
	}
	
	public boolean addSorted(T x){
		if(contains(x))
			return false;
		else{
			Node<T> p = head;
			while(p.next != null && p.next.data.compareTo(x) < 0){
				p = p.next;
			}
			Node<T> newNode = new Node<T>(x, null);
			if(p.next == null){
				p.next = newNode;
			}
			else{
				newNode.next = p.next;
				p.next = newNode;
			}
			theSize++;
		}
		return true;
	}
	
	public boolean remove(T x){
		if(!contains(x))
			return false;
		else{
			Node<T> p = head.next;
			Node<T> tailer = head;
			while(!p.data.equals(x)){
				tailer = p;
				p = p.next;
			}
			tailer.next = p.next;
			theSize--;
		}
		return true;
	}
	
	private class Node<T>{
		
		public Node(){
			this(null, null);
		}
		
		public Node(T d){
			this(d, null);
		}
		
		public Node(T d, Node<T> n){
			data = d;
			next = n;
		}
		
		private T data;
		private Node<T> next;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
