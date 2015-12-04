package algorithms_1to2;

public class Quick3Way {
	private static boolean less(Comparable c1, Comparable c2) {
		return c1.compareTo(c2) < 0;
	}
	private static void exchange(Comparable c[], int a, int b) {
		Comparable e = c[a];
		c[a] = c[b];
		c[b] = e;
	}
	
	private static void sort(Comparable c[], int lo,int hi) {
		if(lo >= hi)
			return;
		int l = lo;
		Comparable t = c[lo];
		int i = lo;
		int h = hi;
		while(i<=h) {
			if(t.compareTo(c[i]) > 0)
				exchange(c,l++,i++);
			else if(t.compareTo(c[i]) < 0) 
				exchange(c,h--,i);
			else
				i++;
		}
		sort(c,lo,l-1);
		sort(c,h+1,hi);	
	}
	
	public static void main(String[] args) {
		Integer []a = new Integer[10000000];
		for(int i = 0;i<10000000;i++)
			a[i] = (int)(Math.random()*100);
		
		long start1 = System.currentTimeMillis();
		
		sort(a,0,a.length-1);
		System.out.println((double)(System.currentTimeMillis() - start1)/1000);
		
	}
}
