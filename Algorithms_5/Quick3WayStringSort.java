package Algorithms_5;

public class Quick3WayStringSort {
	private boolean less(String a, String b) {
		return a.compareTo(b) < 0;
	}
	private int charAt(String s, int d) {
		if(s.length() <= d) {
			return -1;
		} else
			return s.charAt(d);
	}
	public void sort(String[] s, int lo, int hi, int d) {
		if(lo >= hi) return;
		int lt = lo;
		int gt = hi;
		int v = charAt(s[lo], d);
		int i = lt + 1;
		
		while(i <= gt) {
			int now = charAt(s[i],d);
			if(now < v) {
				exch(s, lt++, i++);
			} else if(now > v) {
				exch(s, gt--, i);
			} else {
				i++;
			}
		}
		
		sort(s, lo, lt-1, d);
		if(v >= 0) sort(s, lt, gt, d+1);
		sort(s, gt+1, hi, d);
		
		
	}
	
	public void exch(String[] s, int a, int b) {
		String tmp = s[a];
		s[a] = s[b];
		s[b] = tmp;
	}
	
	public static void main(String[] args) {
		String[] s = new String[]{"yx", "zzz", "wdx", "wxx"};
		new Quick3WayStringSort().sort(s, 0, 3, 0);
		for(String n : s)
			System.out.println(n);
	}
}
