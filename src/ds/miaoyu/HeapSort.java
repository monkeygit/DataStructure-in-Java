package ds.miaoyu;

public class HeapSort {
	
	public static void headSort(int[] a){
		for(int i = a.length/2; i >= 0; i--)
			percDown(a, i, a.length);
		for(int i = a.length - 1; i>0; i--){
			swapReferences(a, 0, i);
			percDown(a, 0, i);
		}
		
	}
	
	private static int leftChild(int i){
		return 2*i + 1;
	}
	
	private static void percDown(int[] a, int i, int n){
		int child;
		int tmp;
		
		for(tmp = a[i]; leftChild(i) < n; i = child){
			child = leftChild(i);
			if(child != n - 1 && a[child] < a[child + 1])
				child++;
			if(tmp < a[child])
				a[i] = a[child];
			else break;
		}
		a[i] = tmp;
	}
	
	private static void swapReferences(int[] a, int x, int y){
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {6,5,7,4,8,3,9,2,1,0};
		headSort(num);
		for(int i=0; i<num.length; i++)
			System.out.println(num[i]);
	}

}
