package ds.miaoyu;

public class QuickSort {
	
	public static void quickSort(int[] a){
		if(a.length > 0)
			quickSort(a, 0, a.length-1);
	}
	
	private static void quickSort(int[] a, int left, int right){
		if(left < right){
			int middle = partition(a, left, right);
			quickSort(a, left, middle-1);
			quickSort(a, middle+1, right);
		}
	}
	
	private static int partition(int[] a, int l, int r){
		int tmp = a[l];
		while(l < r){
			while(l < r && a[r] > tmp)
				r--;
			a[l] = a[r];
			while(l < r && a[l] < tmp)
				l++;
			a[r] = a[l];
		}
		
		a[l] = tmp;
		return l;
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {6,5,7,4,8,3,9,2,1,0};
		quickSort(num);
		for(int i=0; i<num.length; i++)
			System.out.println(num[i]);
	}

}
