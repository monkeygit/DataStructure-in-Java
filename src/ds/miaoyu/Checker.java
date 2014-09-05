package ds.miaoyu;

import java.util.Scanner;

public class Checker {
	private MyStack<Character> m = new MyStack<Character>();
	
	public boolean match(){
		System.out.println("Input a string:");
		Scanner sc = new Scanner(System.in);
		try {
			while (sc.hasNextLine()) {
				char[] line = sc.nextLine().toCharArray();
				for (int i = 0; i < line.length; i++) {
					if (line[i] == '/') {
						if (line[i + 1] == '*') {
							m.push('/');
							m.push('*');
							i++;
							m.print();
							continue;
						}
					}
					if (line[i] == '*') {
						if (line[i+1] == '/')
							if (m.getTop() == '*') {
								m.pop();
								m.pop();
								i++;
							} else return false;
					}
					
					if (line[i] == '(' || line[i] == '{' || line[i] == '[')
						m.push(line[i]);

					if (line[i] == '}') {
						if (m.getTop() == '{')
							m.pop();
						else return false;
					}

					if (line[i] == ')') {
						if (m.getTop() == '(')
							m.pop();
						else return false;
					}

					if (line[i] == ']') {
						if (m.getTop() == '[')
							m.pop();
						else return false;
					}
					m.print();
				}
			}
		}finally{
			sc.close();
		}
		
		if(m.isEmpty())
			return true;
		else return false;
		
	}
	
	public static void main(String[] args) {
		Checker c = new Checker();
		boolean result = c.match();
		System.out.println(result);
	}
	
	private class MyStack<T extends Comparable<T>>{
		private MyLinkedList<T> stack;
		
		public MyStack(){
			stack = new MyLinkedList<T>();
		}
		
		public boolean isEmpty(){
			return stack.isEmpty();
		}
		
		public boolean push(T x){
			stack.add(0, x);
			return true;
		}
		
		public boolean pop(){
			if(!stack.isEmpty()){
				stack.remove(0);
				return true;
			}
			else
				return false;
		}
		
		public T getTop(){
			return stack.get(0);
		}
		
		
		public void print(){
			stack.print();
		}
	}

}
