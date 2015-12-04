package algorithms_1to2;
import java.util.Arrays;

public class Quick {

	private static boolean less(Comparable c1, Comparable c2) {
		return c1.compareTo(c2) < 0;
	}
	private static void exchange(Comparable c[], int a, int b) {
		Comparable e = c[a];
		c[a] = c[b];
		c[b] = e;
	}
	private static void insertSort(Comparable[] cpb,int lo,int hi) {
		for(int i = lo+1;i<=hi;i++) {
			Comparable t = cpb[i];
			int j;
			for(j = i -1;j>=lo;j--) {
				if(less(t,cpb[j])) {
					cpb[j+1] = cpb[j];
				}
				else
					break;
			}
			cpb[j+1] = t; 
		}
	}
	private static int partition(Comparable c[],int lo,int hi) {
		int mid = (lo+hi)/2;
		int i = lo;
		int j = hi+1;
		if(!less(c[lo],c[hi])) {
			exchange(c,lo,hi);
		}
		if(less(c[lo],c[mid]))
			exchange(c,lo,mid);
		if(!less(c[lo],c[hi]))
			exchange(c,lo,hi);
		while(i<j) {
			while(less(c[++i],c[lo])) {
				if(i==hi) break;
			}
			
			while(less(c[lo],c[--j]))
				;
			if(i<j) {
				Comparable e = c[i];
				c[i] = c[j];
				c[j] = e;				
			}
			
		}
		Comparable e = c[j];
		c[j] = c[lo];
		c[lo] = e;
		
		return j;
	}
	
	private static void sort(Comparable c[], int lo ,int hi) {
		if(lo >= hi)
			return;
		if(lo+10 > hi) {
			insertSort(c,lo,hi);
			return;
		}
		int p = partition(c,lo,hi);
		sort(c,lo,p-1);
		sort(c,p+1,hi);
	}
	
	public static void main(String[] args) {
		
		Integer []a = new Integer[10000000];
		for(int i = 0;i<10000000;i++)
			a[i] = -i;
		long start1 = System.currentTimeMillis();
		
		sort(a,0,a.length-1);
		System.out.println((double)(System.currentTimeMillis() - start1)/1000);
		
	}
}
