package algorithms_1to2;

public class Merge {
	private static Comparable n[] = new Comparable[10];

	private static boolean less(Comparable c1, Comparable c2) {
		return c1.compareTo(c2) < 0;
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
	public static void merge(Comparable[] cpb,int lo,int hi) {
	
		int mid = (lo+hi)/2;
		
		for(int i = lo;i<=hi;i++) {
			n[i] = cpb[i];
		}
		int p = lo;
		int i = lo;
		int j = mid+1;
		while(i<=mid || j<=hi ) {
			if(i<=mid && j > hi)
				cpb[p++] = n[i++];
			else if(i>mid && j<=hi) {
				cpb[p++] = n[j++];
			}
			else if(less(n[i],n[j]))
				cpb[p++] = n[i++];
			else
				cpb[p++] = n[j++];		
		}
	}
	
	public static void _sort(Comparable [] cpb,int lo,int hi) {
		if(lo >= hi)	return;
		if(lo+10 > hi) {
			insertSort(cpb,lo,hi);
			return;
		}
		int mid = (lo+hi)/2;
		_sort(cpb,lo,mid);
		_sort(cpb,mid+1,hi);
		if(cpb[mid].compareTo(cpb[mid+1]) > 0)
			merge(cpb,lo,hi);
	}
	
	public static void sort(Comparable[] cpb) {
		if(n != null && n.length >= cpb.length)
			_sort(cpb,0,cpb.length-1);
		else {
			n = new Comparable[cpb.length];
			_sort(cpb,0,cpb.length-1);
			
		}
	}
	
	
	public static void main(String[] args) {
		Integer []a = new Integer[10000000];
		for(int i = 0;i<10000000;i++)
			a[i] = (int)(Math.random()*10000000);

		long start = System.currentTimeMillis();
		sort(a);
		System.out.println((double)(System.currentTimeMillis() - start)/1000);
		//for(int i = 0;i<10000000;i++)
			//System.out.println(a[i]);
	}
}
