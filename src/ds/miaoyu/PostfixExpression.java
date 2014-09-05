package ds.miaoyu;

import java.util.Scanner;

public class PostfixExpression {

	private String pe;
	private Stack<Character> m ;
	
	public void init(){
		m = new Stack<Character>(); 
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextLine())
			pe = sc.nextLine();
		sc.close();
	}
	
	public int calculate(){
		char[] line = pe.toCharArray();
		for(char c : line){
			if(c == '+' || c == '-' || c == '*' || c == '/'){
				double b = Double.parseDouble(m.getTop()+"");
				m.pop();
				double a = Double.parseDouble(m.getTop()+"");
				m.pop();
				double r = 0;
				switch(c){
				case '+':
					r = a + b;
					break;
				case '-':
					r = a - b;
					break;
				case '*':
					r = a * b;
					break;
				case '/':
						r = a / b;
					break;	
				}
				m.push((char)((int)r+'0'));
			}
			else m.push(c);
			
			m.print();
		}
		return Integer.parseInt(m.getTop()+"");
	}
	
	public static void main(String[] args) {
		PostfixExpression pfe = new PostfixExpression();
		pfe.init();
		int result = pfe.calculate();
		System.out.println(result);
	}
	
	
	private class Stack<T extends Comparable<T>>{
		private MyLinkedList<T> stack = new MyLinkedList<T>();
		
		public boolean isEmpty(){
			return stack.isEmpty();
		}
		
		public boolean push(T x){
			stack.add(0, x);
			return true;
		}
		
		public boolean pop(){
			if(!isEmpty()){
				stack.remove(0);
				return true;
			}else return false;
		}
		
		public T getTop(){
			return stack.get(0);
		}
		
		public void print(){
			stack.print();
		}
	}
}
