package Algorithms_5;

public class MSD {
	private String[] aux;
	private int R;
	
	public MSD(String[] s, int R) {
		this.R = R;
		aux = new String[s.length];
		sort(s, 0, s.length-1, 0);
		
		for(String str : s)
			System.out.println(str);
	}
	
	public void sort(String[] s, int lo, int hi, int d) {
		if(hi <= lo+5) {
			insertSort(s, lo, hi, d);
			return;
		}
			
		int[] count = new int[R+2];
		
		for(int i = lo; i <= hi; i++) {
			count[charTo(s[i], d) + 2]++;
		}
		
		for(int i = 0; i < R+1; i++) {
			count[i+1] += count[i];
		}
		
		for(int i = lo; i <= hi; i++) {
			aux[lo + count[charTo(s[i], d)+1]++] = s[i];
		}
		
		for(int i = lo; i <= hi; i++) {
			s[i] = aux[i];
		}
		
		for(int i = 0;i<R;i++) {
			sort(s, lo + count[i], lo + count[i+1] - 1, d+1);
		}
	}
	
	private int charTo(String s, int d) {
		if(s.length() <= d) {
			return -1;
		} else
			return s.charAt(d);
	}
	
	private void insertSort(String[] s, int lo, int hi, int d) {
		for(int i = lo; i <= hi; i++) {
			for(int j = i; j > lo; j--) {
				if(s[j].substring(d).compareTo(s[j-1].substring(d)) < 0) {
					exch(s, j,j-1);
				}
			}
		}
	}
	
	private void exch(String[] s, int a, int b) {
		String tmp = s[a];
		s[a] = s[b];
		s[b] = tmp;
	}
	
	public static void main(String[] args) {
		new MSD(new String[]{"yx", "wdx", "wxx"}, 256);
		
	}
}
